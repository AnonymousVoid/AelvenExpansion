package dev.anonymousvoid.aelven_expansion.screen.slot;

import dev.anonymousvoid.aelven_expansion.item.ModItems;
import dev.anonymousvoid.aelven_expansion.screen.KilnMenu;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.function.Predicate;

public class ModInputSlot extends SlotItemHandler {
    private final Predicate<Holder<Item>> inputs;

    public ModInputSlot(Predicate<Holder<Item>> inputs, IItemHandler itemHandler, int slot, int xPos, int yPos) {
        super(itemHandler, slot, xPos, yPos);
        this.inputs = inputs;
    }

    public boolean mayPlace(ItemStack stack) {
        return stack.is(inputs);
    }

}
