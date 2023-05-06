package dev.anonymousvoid.aelven_expansion.screen.slot;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.function.Predicate;

public class ModOutputSlot extends SlotItemHandler {

    public ModOutputSlot(IItemHandler itemHandler, int slot, int xPos, int yPos) {
        super(itemHandler, slot, xPos, yPos);
    }

    public boolean mayPlace(ItemStack stack) { return false; }

}
