package dev.anonymousvoid.aelven_expansion.world.feature.tree.placer;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class LargeStraightTrunkPlacer extends TrunkPlacer {
    public static final Codec<LargeStraightTrunkPlacer> CODEC = RecordCodecBuilder.create((placer) -> {
        return trunkPlacerParts(placer).apply(placer, LargeStraightTrunkPlacer::new);
    });

    public LargeStraightTrunkPlacer(int baseHeight, int rand1, int rand2) {
        super(baseHeight, rand1, rand2);
    }

    protected TrunkPlacerType<?> type() {
        return TrunkPlacerType.STRAIGHT_TRUNK_PLACER;
    }

    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> consumer, RandomSource rand, int height, BlockPos pos, TreeConfiguration config) {
        setDirtAt(level, consumer, rand, pos.below(), config);

        List<BlockPos> logPos = List.of(
                pos.north(), pos.north().below(),
                pos.east(), pos.east().below(),
                pos.south(), pos.south().below(),
                pos.west(), pos.west().below());
        for (BlockPos blockPos : logPos) {
            this.placeLog(level, consumer, rand, blockPos, config);
        }

        for(int i = 0; i < height; ++i) {
            this.placeLog(level, consumer, rand, pos.above(i), config);
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pos.above(height), 0, false));
    }
}
