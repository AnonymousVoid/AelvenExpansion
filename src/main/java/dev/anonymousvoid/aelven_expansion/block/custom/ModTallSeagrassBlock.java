package dev.anonymousvoid.aelven_expansion.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ModTallSeagrassBlock extends TallSeagrassBlock {
    protected final Block copyBlock;

    public ModTallSeagrassBlock(BlockBehaviour.Properties properties, Block pCopyBlock) {
        super(properties);
        this.copyBlock = pCopyBlock;
    }

    public ItemStack getCloneItemStack(BlockGetter pBlockGetter, BlockPos pPos, BlockState pState) {
        return new ItemStack(copyBlock);
    }

}
