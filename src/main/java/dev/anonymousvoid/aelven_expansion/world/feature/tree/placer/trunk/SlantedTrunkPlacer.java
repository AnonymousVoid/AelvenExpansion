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
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class SlantedTrunkPlacer  extends TrunkPlacer {
    public static final Codec<SlantedTrunkPlacer> CODEC = RecordCodecBuilder.create((placer) -> {
        return trunkPlacerParts(placer).apply(placer, SlantedTrunkPlacer::new);
    });

    public SlantedTrunkPlacer(int baseHeight, int rand1, int rand2) {
        super(baseHeight, rand1, rand2);
    }

    protected TrunkPlacerType<?> type() { return TrunkPlacerType.STRAIGHT_TRUNK_PLACER; }

    public List<FoliagePlacer.FoliageAttachment> placeTrunk(
            LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random,
            int height, BlockPos blockPos, TreeConfiguration config) {
        setDirtAt(level, blockSetter, random, blockPos.below(), config);

        double offX = ((double)random.nextInt(5) - 2) / height;
        double offY = ((double)random.nextInt(5) - 2) / height;

        for(int i = 0; i < height; i++) {
            BlockPos blockpos = blockPos.offset(offX * i, i, offY * i);

            this.placeLog(level, blockSetter, random, blockpos.below(), config);
            this.placeLog(level, blockSetter, random, blockpos, config);
            this.placeLog(level, blockSetter, random, blockpos.above(), config);

            if (i > 3 && random.nextBoolean()) {
                int side = random.nextInt(4);
                if (side == 0) {
                    placeBranch((height - i) / 3, blockpos.north(), Direction.NORTH, level, blockSetter, random, config);
                }
                if (side == 1) {
                    placeBranch((height - i) / 3, blockpos.east(), Direction.EAST, level, blockSetter, random, config);
                }
                if (side == 2) {
                    placeBranch((height - i) / 3, blockpos.south(), Direction.SOUTH, level, blockSetter, random, config);
                }
                if (side == 3) {
                    placeBranch((height - i) / 3, blockpos.west(), Direction.WEST, level, blockSetter, random, config);
                }
            }
        }

        return ImmutableList.of();//new FoliagePlacer.FoliageAttachment(
//                blockPos.offset(offX * height, height + 1, offY * height), 0, false));
    }

    private void placeBranch(int length, BlockPos pos, Direction direction, LevelSimulatedReader level,
                             BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, TreeConfiguration config) {
        boolean slantBranch = random.nextBoolean();
        for (int i = 0; i < length; i ++) {
            BlockPos p = pos;
            if (i > length / 2 && slantBranch) p = pos.above();

            if(direction == Direction.NORTH) this.placeLog(level, blockSetter, random, p.north(i), config,
                    (state) -> { return state.setValue(RotatedPillarBlock.AXIS, direction.getAxis()); });
            if(direction == Direction.EAST) this.placeLog(level, blockSetter, random, p.east(i), config,
                    (state) -> { return state.setValue(RotatedPillarBlock.AXIS, direction.getAxis()); });
            if(direction == Direction.SOUTH) this.placeLog(level, blockSetter, random, p.south(i), config,
                    (state) -> { return state.setValue(RotatedPillarBlock.AXIS, direction.getAxis()); });
            if(direction == Direction.WEST) this.placeLog(level, blockSetter, random, p.west(i), config,
                    (state) -> { return state.setValue(RotatedPillarBlock.AXIS, direction.getAxis()); });
        }
    }
}