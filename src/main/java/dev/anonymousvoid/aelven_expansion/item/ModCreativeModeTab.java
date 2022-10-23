package dev.anonymousvoid.aelven_expansion.item;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ModCreativeModeTab {
    public static final CreativeModeTab MOD_TAB_BLOCKS = new CreativeModeTab(AelvenExpansion.MODID + "_blocks") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModBlocks.KILN.get());
        }
    };
    public static final CreativeModeTab MOD_TAB_ITEMS = new CreativeModeTab(AelvenExpansion.MODID + "_items") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItems.CHITTA_CHISEL.get());
        }
    };

}
