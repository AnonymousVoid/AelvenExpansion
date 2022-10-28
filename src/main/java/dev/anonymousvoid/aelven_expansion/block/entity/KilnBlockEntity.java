package dev.anonymousvoid.aelven_expansion.block.entity;

import dev.anonymousvoid.aelven_expansion.item.ModItems;
import dev.anonymousvoid.aelven_expansion.recipe.KilnRecipe;
import dev.anonymousvoid.aelven_expansion.screen.KilnMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class KilnBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(7) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int maxProgress = 240;
    private int progress1 = 0;
    private int progress2 = 0;
//    private int progress3 = 0;

    public KilnBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.KILN.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> KilnBlockEntity.this.maxProgress;
                    case 1 -> KilnBlockEntity.this.progress1;
                    case 2 -> KilnBlockEntity.this.progress2;
//                    case 3 -> KilnBlockEntity.this.progress3;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> KilnBlockEntity.this.maxProgress = value;
                    case 1 -> KilnBlockEntity.this.progress1 = value;
                    case 2 -> KilnBlockEntity.this.progress2 = value;
//                    case 3 -> KilnBlockEntity.this.progress3 = value;
                }
            }

            @Override
            public int getCount() { return 6; }
        };
    }

    @Override
    public Component getDisplayName() { return Component.literal("Kiln"); }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new KilnMenu(id, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        nbt.putInt("kiln.progress1", this.progress1);
        nbt.putInt("kiln.progress2", this.progress2);
//        nbt.putInt("kiln.progress3", this.progress3);

        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress1 = nbt.getInt("kiln.progress1");
        progress2 = nbt.getInt("kiln.progress2");
//        progress3 = nbt.getInt("kiln.progress3");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, KilnBlockEntity entity) {
        if (level.isClientSide()) {
            return;
        }


        if (hasRecipe1(entity)) {
            entity.progress1++;
            setChanged(level, pos, state);

            if (entity.progress1 >= entity.maxProgress) {
                craftItem1(entity);
            }
        } else {
            entity.resetProgress1();
            setChanged(level, pos, state);
        }

        if (hasRecipe2(entity)) {
            entity.progress2++;
            setChanged(level, pos, state);

            if (entity.progress2 >= entity.maxProgress) {
                craftItem2(entity);
            }
        } else {
            entity.resetProgress2();
            setChanged(level, pos, state);
        }

//        if (hasRecipe3(entity)) {
//            entity.progress3++;
//            setChanged(level, pos, state);
//
//            if (entity.progress3 >= entity.maxProgress) {
//                craftItem3(entity);
//            }
//        } else {
//            entity.resetProgress3();
//            setChanged(level, pos, state);
//        }
    }


    private void resetProgress1() { this.progress1 = 0; }
    private void resetProgress2() { this.progress2 = 0; }
//    private void resetProgress3() { this.progress3 = 0; }


    private static void craftItem1(KilnBlockEntity entity) {
        Level lvl = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<KilnRecipe> recipe = lvl.getRecipeManager()
                .getRecipeFor(KilnRecipe.Type.INSTANCE, inventory, lvl);

        if (hasRecipe1(entity)) {
            entity.itemHandler.extractItem(0, 1, false);
            entity.itemHandler.extractItem(1, 1, false);
            entity.itemHandler.setStackInSlot(4, new ItemStack(recipe.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(4).getCount() + 1));

            entity.resetProgress1();
        }

    }

    private static void craftItem2(KilnBlockEntity entity) {
        Level lvl = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<KilnRecipe> recipe = lvl.getRecipeManager()
                .getRecipeFor(KilnRecipe.Type.INSTANCE, inventory, lvl);//TODO

        if (hasRecipe2(entity)) {
            entity.itemHandler.extractItem(0, 1, false);
            entity.itemHandler.extractItem(2, 1, false);
            entity.itemHandler.setStackInSlot(5, new ItemStack(recipe.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(5).getCount() + 1));

            entity.resetProgress2();
        }

    }

//    private static void craftItem3(KilnBlockEntity entity) {
//        Level lvl = entity.level;
//        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
//        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
//            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
//        }
//
//        Optional<KilnRecipe> recipe = lvl.getRecipeManager()
//                .getRecipeFor(KilnRecipe.Type.INSTANCE, inventory, lvl);
//
//        if (hasRecipe3(entity)) {
//            entity.itemHandler.extractItem(0, 1, false);
//            entity.itemHandler.extractItem(3, 1, false);
//            entity.itemHandler.setStackInSlot(6, new ItemStack(recipe.get().getResultItem().getItem(),
//                    entity.itemHandler.getStackInSlot(6).getCount() + 1));
//
//            entity.resetProgress3();
//        }
//
//    }


    private static boolean hasRecipe1(KilnBlockEntity entity) {
        Level lvl = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<KilnRecipe> recipe = lvl.getRecipeManager()
                .getRecipeFor(KilnRecipe.Type.INSTANCE, inventory, lvl);

        boolean hasFuel = entity.itemHandler.getStackInSlot(0).getItem() == ModItems.ELERIUM.get();

        return recipe.isPresent() && hasFuel && canOutput1(inventory, recipe.get().getResultItem());
    }

    private static boolean hasRecipe2(KilnBlockEntity entity) {
        Level lvl = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

//        Optional<KilnRecipe> recipe = lvl.getRecipeManager()
//                .getRecipeFor(KilnRecipe.Type.INSTANCE, inventory, lvl);//TODO
        List<KilnRecipe> list = lvl.getRecipeManager().getRecipesFor(KilnRecipe.Type.INSTANCE, inventory, lvl);
        Optional<KilnRecipe> recipe1 = Optional.ofNullable(list.get(0));;
        Optional<KilnRecipe> recipe2 = Optional.ofNullable(list.get(1));;


        boolean hasFuel = entity.itemHandler.getStackInSlot(0).getItem() == ModItems.ELERIUM.get();

        return recipe2.isPresent() && hasFuel && canOutput2(inventory, recipe2.get().getResultItem());
    }

//    private static boolean hasRecipe3(KilnBlockEntity entity) {
//        Level lvl = entity.level;
//        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
//        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
//            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
//        }
//
//        Optional<KilnRecipe> recipe = lvl.getRecipeManager()
//                .getRecipeFor(KilnRecipe.Type.INSTANCE, inventory, lvl);
//
//        boolean hasFuel = entity.itemHandler.getStackInSlot(0).getItem() == ModItems.ELERIUM.get();
//
//        return recipe.isPresent() && hasFuel && canOutput3(inventory, recipe.get().getResultItem());
//    }


    private static boolean canOutput1(SimpleContainer inventory, ItemStack stack) {
        boolean outputMatches = inventory.getItem(4).getItem() ==
                stack.getItem() || inventory.getItem(4).isEmpty();
        boolean keepsLimit = inventory.getItem(4).getMaxStackSize() >=
                inventory.getItem(4).getCount() + stack.getCount();
        return outputMatches && keepsLimit;
    }

    private static boolean canOutput2(SimpleContainer inventory, ItemStack stack) {
        boolean outputMatches = inventory.getItem(5).getItem() ==
                stack.getItem() || inventory.getItem(5).isEmpty();
        boolean keepsLimit = inventory.getItem(5).getMaxStackSize() >=
                inventory.getItem(5).getCount() + stack.getCount();
        return outputMatches && keepsLimit;
    }

//    private static boolean canOutput3(SimpleContainer inventory, ItemStack stack) {
//        boolean outputMatches = inventory.getItem(6).getItem() ==
//                stack.getItem() || inventory.getItem(6).isEmpty();
//        boolean keepsLimit = inventory.getItem(6).getMaxStackSize() >=
//                inventory.getItem(6).getCount() + stack.getCount();
//        return outputMatches && keepsLimit;
//    }
}
