package dev.anonymousvoid.aelven_expansion.world.feature.tree.placer.trunk;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.material.Fluids;

import java.util.List;
import java.util.function.BiConsumer;

public class SlantedTrunkPlacer extends TrunkPlacer {
    public static final Codec<SlantedTrunkPlacer> CODEC = RecordCodecBuilder.create((placer) -> {
        return trunkPlacerParts(placer).apply(placer, SlantedTrunkPlacer::new);
    });

    public SlantedTrunkPlacer(int baseHeight, int rand1, int rand2) {
        super(baseHeight, rand1, rand2);
    }

    protected TrunkPlacerType<?> type() { return ModTrunkPlacerType.SLANTED_TRUNK_PLACER.get(); }

    public List<FoliagePlacer.FoliageAttachment> placeTrunk(
            LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random,
            int height, BlockPos blockPos, TreeConfiguration config) {
        setDirtAt(level, blockSetter, random, blockPos.below(), config);

        double offX = ((double)random.nextInt(7) - 3) / height;
        double offY = ((double)random.nextInt(7) - 3) / height;

        // Branch Direction
        int b = random.nextInt(4);

        for(int i = 0; i < height; i++) {
            b++;
            BlockPos blockpos = blockPos.offset(offX * i, i, offY * i);

            this.placeLog(level, blockSetter, random, blockpos.below(2), config);
            this.placeLog(level, blockSetter, random, blockpos.below(), config);
            this.placeLog(level, blockSetter, random, blockpos, config);

            if (i > height / 4 && random.nextInt(3) == 0) {
                int side = b % 4;
                BlockPos p = blockpos;
                Direction d = Direction.UP;

                if (side == 0) {
                    p = blockpos.north();
                    d = Direction.NORTH;
                } else if (side == 1) {
                    p = blockpos.east();
                    d = Direction.EAST;
                } else if (side == 2) {
                    p = blockpos.south();
                    d = Direction.SOUTH;
                } else if (side == 3) {
                    p = blockpos.west();
                    d = Direction.WEST;
                }

                placeBranch((height - i)/3, p, d, level, blockSetter, random, config);
            }
        }

        int step = 3;
        for (int i = 0; i < height * 0.8; i ++) {
            int y = 1 + i + height / 3;
            this.placeLeavesRow(level, blockSetter, random, config,
                    blockPos.offset(offX * y, y, offY * y),
                    ((height - i) / 2 * step) / 7 + (i < height / 2 ? 1 : 0), Direction.Axis.Y);
            step = step <= 1 ? 3 : step - 1;
        }
        for (int i = -2; i < 3; i ++) {
            tryPlaceLeaf(level, blockSetter, random, config,
                    blockPos.offset(offX * (height + i), height + i - 1, offY * (height + i)));
            tryPlaceLeaf(level, blockSetter, random, config,
                    blockPos.offset(offX * (height + i), height + i, offY * (height + i)));
            tryPlaceLeaf(level, blockSetter, random, config,
                    blockPos.offset(offX * (height + i), height + i + 1, offY * (height + i)));
        }

        return ImmutableList.of();
    }

    private void placeBranch(int length, BlockPos pos, Direction direction, LevelSimulatedReader level,
                             BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, TreeConfiguration config) {
        boolean slantBranch = random.nextBoolean();
        BlockPos p = pos;
        if (direction == Direction.NORTH) p = p.south(1);
        if (direction == Direction.EAST) p = p.west(1);
        if (direction == Direction.SOUTH) p = p.north(1);
        if (direction == Direction.WEST) p = p.east(1);

//        this.placeLeavesRow(level, blockSetter, random, config, p, (length - 2) / 3, direction.getAxis());

        for (int i = 0; i <= length; i ++) {
            BlockPos p1 = pos;
            BlockPos p2 = p1;
            if (i > length / 3 && slantBranch) p1 = pos.above();
            if (direction == Direction.NORTH) p2 = p1.north(i);
            if (direction == Direction.EAST) p2 = p1.east(i);
            if (direction == Direction.SOUTH) p2 = p1.south(i);
            if (direction == Direction.WEST) p2 = p1.west(i);

            if (i < length/1.5) {
                this.placeLog(level, blockSetter, random, p2, config, (state) -> {
                    return state.setValue(RotatedPillarBlock.AXIS, direction.getAxis());
                });
            }

//            this.placeLeavesRow(level, blockSetter, random, config, p2, (length - i) / 3, direction.getAxis());

        }
    }

    protected void placeLeavesRow(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource rand,
                                  TreeConfiguration config, BlockPos pos, int range, Direction.Axis axis) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        int r = range;
        for(int j = -r; j <= r; ++j) {
            for(int k = -r; k <= r; ++k) {
                if (dist(j, k) <= r && (dist(j, k) >= r - 1 ? rand.nextInt(5) != 0 : true)) {
                    if (axis == Direction.Axis.X) blockpos$mutableblockpos.setWithOffset(pos, 0, j, k);
                    if (axis == Direction.Axis.Y) blockpos$mutableblockpos.setWithOffset(pos, j, 0, k);
                    if (axis == Direction.Axis.Z) blockpos$mutableblockpos.setWithOffset(pos, j, k, 0);
                    if (blockpos$mutableblockpos.getY() >= pos.getY() - 1) tryPlaceLeaf(level, blockSetter, rand, config, blockpos$mutableblockpos);
                }
            }
        }
    }

    protected static void tryPlaceLeaf(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom, TreeConfiguration pConfig, BlockPos pPos) {
        if (TreeFeature.validTreePos(pLevel, pPos)) {
            BlockState blockstate = pConfig.foliageProvider.getState(pRandom, pPos);
            if (blockstate.hasProperty(BlockStateProperties.WATERLOGGED)) {
                blockstate = blockstate.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(pLevel.isFluidAtPosition(pPos, (fluidState
                ) -> {
                    return fluidState.isSourceOfType(Fluids.WATER);
                })));
            }
            pBlockSetter.accept(pPos, blockstate);
        }

    }

    private double dist(double x, double y) {
        return Math.sqrt( ( x * x ) + ( y * y ) );
    }
}