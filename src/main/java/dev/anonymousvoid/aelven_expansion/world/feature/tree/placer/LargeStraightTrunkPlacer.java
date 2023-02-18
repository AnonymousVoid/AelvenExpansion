package dev.anonymousvoid.aelven_expansion.world.feature.tree.placer;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
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
        return TrunkPlacerType.DARK_OAK_TRUNK_PLACER;
    }

    public List<FoliagePlacer.FoliageAttachment> placeTrunk(
            LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> consumer, RandomSource random,
            int height, BlockPos blockPos, TreeConfiguration config) {

        // GENERAL
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        list.add(new FoliagePlacer.FoliageAttachment(blockPos.above(height), 0, true));

        BlockPos blockpos = blockPos.below();
        setDirtAt(level, consumer, random, blockpos, config);
        setDirtAt(level, consumer, random, blockpos.east(), config);
        setDirtAt(level, consumer, random, blockpos.south(), config);
        setDirtAt(level, consumer, random, blockpos.south().east(), config);

        int i = height - random.nextInt(4);
        int j = 2 - random.nextInt(3);
        int k = blockPos.getX();
        int l = blockPos.getY();
        int i1 = blockPos.getZ();
        int l1 = l + height - 1;

        for(int i2 = 0; i2 < height; ++i2) {

            int j2 = l + i2;
            BlockPos blockpos2 = new BlockPos(k, j2, i1);
            if (TreeFeature.isAirOrLeaves(level, blockpos2)) {
                this.placeLog(level, consumer, random, blockpos2, config);
                this.placeLog(level, consumer, random, blockpos2.east(), config);
                this.placeLog(level, consumer, random, blockpos2.south(), config);
                this.placeLog(level, consumer, random, blockpos2.east().south(), config);
            }
        }


        // ROOTS
        BlockPos blockpos1 = new BlockPos(k, l, i1);
        List<BlockPos> r1 = List.of(
                blockpos1.north(), blockpos1.east(2), blockpos1.east().south(2), blockpos1.south().west());
        List<BlockPos> r2 = List.of(
                blockpos1.west(), blockpos1.south(2), blockpos1.south().east(2), blockpos1.east().north());
        List<BlockPos> roots1 = r1;
        List<BlockPos> roots2 = r2;
        if (random.nextBoolean()) roots1 = r2; roots2 = r1;

        if (random.nextInt(20) == 0) {
            placeRoot(roots2.get(random.nextInt(roots2.size())), random.nextBoolean(), random.nextBoolean(),
                    level, consumer, random, config);
        }
        for(BlockPos rootPos : roots1) {
            if (random.nextInt(100) > 0) {
                placeRoot(rootPos, random.nextBoolean(), random.nextBoolean(), level, consumer, random, config);
            }
        }

        // BRANCHES TODO
        //

        return list;
    }

    private void placeRoot(BlockPos pos, boolean depth, boolean height, LevelSimulatedReader level,
                           BiConsumer<BlockPos, BlockState> consumer, RandomSource random, TreeConfiguration config) {
        this.placeLog(level, consumer, random, pos, config);
        this.placeLog(level, consumer, random, pos.below(), config);
        this.placeLog(level, consumer, random, pos.below(2), config);
        if (depth) this.placeLog(level, consumer, random, pos.below(3), config);
        if (height) this.placeLog(level, consumer, random, pos.above(1), config);
    }
}
