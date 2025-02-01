package dev.anonymousvoid.aelven_expansion.block.custom;

import dev.anonymousvoid.aelven_expansion.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SeagrassBlock;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class HydrosatinSproutsBlock extends SeagrassBlock {

    public HydrosatinSproutsBlock(Properties properties) {
        super(properties);
    }

    public void performBonemeal(ServerLevel level, RandomSource rand, BlockPos lowerPos, BlockState state) {
        BlockState lowerState = ModBlocks.TALL_HYDROSATIN_SPROUTS.get().defaultBlockState();
        BlockState upperState = lowerState.setValue(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER);
        BlockPos upperPos = lowerPos.above();
        if (level.getBlockState(upperPos).is(Blocks.WATER)) {
            level.setBlock(lowerPos, lowerState, 2);
            level.setBlock(upperPos, upperState, 2);
        }
    }
}
