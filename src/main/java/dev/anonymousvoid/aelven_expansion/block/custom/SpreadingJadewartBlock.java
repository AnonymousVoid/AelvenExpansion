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

public class SpreadingJadewartBlock extends Block implements BonemealableBlock {
    protected final Block deadBlock;

    public SpreadingJadewartBlock(Properties properties, Block block) {
        super(properties);
        this.deadBlock = block;
    }

    public boolean isValidBonemealTarget(BlockGetter getter, BlockPos pos, BlockState state, boolean b) {
        return getter.getBlockState(pos.above()).isAir();
    }

    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos pos, BlockState state) {
        return true;
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
            BlockState blockstate = this.defaultBlockState();

            for(int i = 0; i < 4; ++i) {
                BlockPos blockpos = pPos.offset(pRandom.nextInt(3) - 1, pRandom.nextInt(5) - 3, pRandom.nextInt(3) - 1);
                if (canBeJadewart(pLevel, blockpos)) {
                    trySpreadBlock(pLevel.getBlockState(blockpos), pLevel, blockpos);
                }
            }
        }
    }

    public void performBonemeal(ServerLevel level, RandomSource rand, BlockPos pos, BlockState state) {
        BlockPos abovePos = pos.above();
        BlockState grassState = ModBlocks.MULCHY_GRASS.get().defaultBlockState();

        label46:
        for(int i = 0; i < 128; ++i) {
            BlockPos randPos = abovePos;

            for(int j = 0; j < i / 16; ++j) {
                randPos = randPos.offset(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
                if (!(level.getBlockState(randPos.below()).is(ModBlocks.MUDDY_MULCH.get()) ||
                        level.getBlockState(randPos.below()).is(Blocks.MUD) ||
                        level.getBlockState(randPos.below()).is(Blocks.GRASS_BLOCK)) ||
                        level.getBlockState(randPos).isCollisionShapeFullBlock(level, randPos)) {
                    continue label46;
                }
            }

            BlockState randState = level.getBlockState(randPos);
            if (randState.is(grassState.getBlock()) && rand.nextInt(10) == 0) {
                ((BonemealableBlock)grassState.getBlock()).performBonemeal(level, rand, randPos, randState);
            }

            if (randState.isAir() || randState.getMaterial().isReplaceable()) {
                if (randState.isAir() && rand.nextInt(2) == 0) {
                    if (rand.nextInt(8) == 0) {
                        level.setBlockAndUpdate(randPos, ModBlocks.PEACH_LAVENDER.get().defaultBlockState());
                    } else {
                        level.setBlockAndUpdate(randPos, ModBlocks.MULCHY_GRASS.get().defaultBlockState());
                    }
                } else if (level.getBlockState(randPos.below()).is(Blocks.MUD)) {
                    level.setBlockAndUpdate(randPos.below(), ModBlocks.MUDDY_MULCH.get().defaultBlockState());
                }
            }
        }
    }

//    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
//        if (!canBeJadewart(pState, pLevel, pPos)) {
//            if (!pLevel.isAreaLoaded(pPos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
//            pLevel.setBlockAndUpdate(pPos, Blocks.DIRT.defaultBlockState());
//        }
//    }
}
