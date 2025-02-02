package dev.anonymousvoid.aelven_expansion.block.custom;

import dev.anonymousvoid.aelven_expansion.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class SpreadingJadewartBlock extends Block {
    protected final Block deadBlock;

    public SpreadingJadewartBlock(Properties properties, Block block) {
        super(properties);
        this.deadBlock = block;
    }

    private static boolean canBeJadewart(LevelReader pLevelReader, BlockPos pPos) {
        BlockPos blockpos = pPos.above();
        BlockState blockstate = pLevelReader.getBlockState(blockpos);
        if (blockstate.isCollisionShapeFullBlock(pLevelReader, pPos) && !blockstate.is(Blocks.WATER) && !blockstate.is(Blocks.AIR)) {
            return false;
        } else {
            return true;
        }
    }

    private void trySpreadBlock(BlockState spreadTarget, ServerLevel pLevel, BlockPos pPos) {
        BlockState newState = null;
        if (spreadTarget.is(this.deadBlock)) {
            newState = this.defaultBlockState();
        } else if (spreadTarget.is(Blocks.MOSS_BLOCK)) {
            newState = ModBlocks.JADEWART.get().defaultBlockState();
        } else if (spreadTarget.is(Blocks.MOSS_CARPET)) {
            newState = ModBlocks.JADEWART_CARPET.get().defaultBlockState();
        }
        if (newState != null) {
            pLevel.setBlockAndUpdate(pPos, newState);
        }
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!canBeJadewart(pLevel, pPos)) {
            if (!pLevel.isAreaLoaded(pPos, 1))
                return;
            pLevel.setBlockAndUpdate(pPos, this.deadBlock.defaultBlockState());
        } else {
            if (!pLevel.isAreaLoaded(pPos, 3))
                return;

            for(int i = 0; i < 4; ++i) {
                BlockPos blockpos = pPos.offset(pRandom.nextInt(3) - 1, pRandom.nextInt(5) - 3, pRandom.nextInt(3) - 1);
                if (canBeJadewart(pLevel, blockpos)) {
                    trySpreadBlock(pLevel.getBlockState(blockpos), pLevel, blockpos);
                }
            }
        }
    }
}
