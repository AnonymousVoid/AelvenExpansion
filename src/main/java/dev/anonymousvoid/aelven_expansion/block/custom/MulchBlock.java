package dev.anonymousvoid.aelven_expansion.block.custom;

import dev.anonymousvoid.aelven_expansion.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LayerLightEngine;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MulchBlock extends Block implements BonemealableBlock {
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);

    public MulchBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public boolean isValidBonemealTarget(BlockGetter getter, BlockPos pos, BlockState state, boolean b) {
        return getter.getBlockState(pos.above()).isAir();
    }

    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos pos, BlockState state) {
        return true;
    }

    private static boolean canBeMulch(BlockState pState, LevelReader pLevelReader, BlockPos pPos) {
        BlockPos blockpos = pPos.above();
        BlockState blockstate = pLevelReader.getBlockState(blockpos);
        if (blockstate.is(Blocks.SNOW) && blockstate.getValue(SnowLayerBlock.LAYERS) == 1) {
            return true;
        } else if (blockstate.getFluidState().getAmount() == 8) {
            return false;
        } else {
            int i = LayerLightEngine.getLightBlockInto(pLevelReader, pState, pPos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(pLevelReader, blockpos));
            return i < pLevelReader.getMaxLightLevel();
        }
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!canBeMulch(pState, pLevel, pPos)) {
            if (!pLevel.isAreaLoaded(pPos, 1))
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            pLevel.setBlockAndUpdate(pPos, Blocks.MUD.defaultBlockState());
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

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    public VoxelShape getBlockSupportShape(BlockState p_221566_, BlockGetter p_221567_, BlockPos p_221568_) {
        return Shapes.block();
    }

    public VoxelShape getVisualShape(BlockState p_221556_, BlockGetter p_221557_, BlockPos p_221558_, CollisionContext p_221559_) {
        return Shapes.block();
    }

    public boolean isPathfindable(BlockState p_221547_, BlockGetter p_221548_, BlockPos p_221549_, PathComputationType p_221550_) {
        return false;
    }
}
