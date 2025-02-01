package dev.anonymousvoid.aelven_expansion.block.custom;

import dev.anonymousvoid.aelven_expansion.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SeagrassBlock;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class JadegrassBlock extends SeagrassBlock {

    public JadegrassBlock(Properties properties) {
        super(properties);
    }

    public void performBonemeal(ServerLevel level, RandomSource rand, BlockPos lowerPos, BlockState state) {
        BlockState lowerState = ModBlocks.TALL_JADEGRASS.get().defaultBlockState();
        BlockState upperState = lowerState.setValue(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER);
        BlockPos upperPos = lowerPos.above();
        if (level.getBlockState(upperPos).is(Blocks.WATER)) {
            level.setBlock(lowerPos, lowerState, 2);
            level.setBlock(upperPos, upperState, 2);
        }
    }
}
