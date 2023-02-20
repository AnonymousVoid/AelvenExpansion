package dev.anonymousvoid.aelven_expansion.world.feature.tree.placer.trunk;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
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

    protected TrunkPlacerType<?> type() { return TrunkPlacerType.DARK_OAK_TRUNK_PLACER; }

    public List<FoliagePlacer.FoliageAttachment> placeTrunk(
            LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random,
            int height, BlockPos blockPos, TreeConfiguration config) {

        // GENERAL
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        list.add(new FoliagePlacer.FoliageAttachment(blockPos.above(height), 0, true));

        BlockPos blockpos = blockPos.below();
        setDirtAt(level, blockSetter, random, blockpos, config);
        setDirtAt(level, blockSetter, random, blockpos.east(), config);
        setDirtAt(level, blockSetter, random, blockpos.south(), config);
        setDirtAt(level, blockSetter, random, blockpos.south().east(), config);

        int i = blockPos.getX();
        int j = blockPos.getY();
        int k = blockPos.getZ();

        for(int i2 = 0; i2 < height; ++i2) {

            int j2 = j + i2;
            BlockPos blockpos2 = new BlockPos(i, j2, k);
            if (TreeFeature.isAirOrLeaves(level, blockpos2)) {
                this.placeLog(level, blockSetter, random, blockpos2, config);
                this.placeLog(level, blockSetter, random, blockpos2.east(), config);
                this.placeLog(level, blockSetter, random, blockpos2.south(), config);
                this.placeLog(level, blockSetter, random, blockpos2.east().south(), config);
            }
        }


        // BRANCHES
        if (random.nextInt(5) > 0) {
            BlockPos p = blockPos.north().east(random.nextInt(2)).above(5 + random.nextInt(height - 7));
            int l = random.nextInt(3) + 2;
            placeBranch(p, l, Direction.NORTH, level, blockSetter, random, config);
            list.add(new FoliagePlacer.FoliageAttachment(p.north(l), 0, false));
        }
        if (random.nextInt(5) > 0) {
            BlockPos p = blockPos.east(2).south(random.nextInt(2)).above(5 + random.nextInt(height - 7));
            int l = random.nextInt(3) + 2;
            placeBranch(p, l, Direction.EAST, level, blockSetter, random, config);
            list.add(new FoliagePlacer.FoliageAttachment(p.east(l), 0, false));
        }
        if (random.nextInt(5) > 0) {
            BlockPos p = blockPos.south(2).east(random.nextInt(2)).above(5 + random.nextInt(height - 7));
            int l = random.nextInt(3) + 2;
            placeBranch(p, l, Direction.SOUTH, level, blockSetter, random, config);
            list.add(new FoliagePlacer.FoliageAttachment(p.south(l), 0, false));
        }
        if (random.nextInt(5) > 0) {
            BlockPos p = blockPos.west().south(random.nextInt(2)).above(5 + random.nextInt(height - 7));
            int l = random.nextInt(3) + 2;
            placeBranch(p, l, Direction.WEST, level, blockSetter, random, config);
            list.add(new FoliagePlacer.FoliageAttachment(p.west(l), 0, false));
        }


        // ROOTS
        List<BlockPos> r1 = List.of(
                blockPos.north(), blockPos.east(2), blockPos.east().south(2), blockPos.south().west());
        List<BlockPos> r2 = List.of(
                blockPos.west(), blockPos.south(2), blockPos.south().east(2), blockPos.east().north());
        List<BlockPos> roots1 = r1;
        List<BlockPos> roots2 = r2;
        if (random.nextBoolean()) roots1 = r2; roots2 = r1;

        if (random.nextInt(20) == 0) {
            placeRoot(roots2.get(random.nextInt(roots2.size())), random.nextBoolean(), random.nextBoolean(),
                    level, blockSetter, random, config);
        }
        for(BlockPos rootPos : roots1) {
            if (random.nextInt(100) > 0) {
                placeRoot(rootPos, random.nextBoolean(), random.nextBoolean(), level, blockSetter, random, config);
            }
        }


        return list;
    }

    private void placeBranch(BlockPos pos, int length, Direction direction, LevelSimulatedReader level,
                             BiConsumer<BlockPos, BlockState> consumer, RandomSource random, TreeConfiguration config) {
        for (int i = 0; i < length; i ++) {
            if (direction == Direction.NORTH) {

                this.placeLog(level, consumer, random, pos.north(i), config, (state) -> {
                    return state.setValue(RotatedPillarBlock.AXIS, direction.getAxis()); });

            } else if (direction == Direction.EAST) {

                this.placeLog(level, consumer, random, pos.east(i), config, (state) -> {
                    return state.setValue(RotatedPillarBlock.AXIS, direction.getAxis()); });

            } else if (direction == Direction.SOUTH) {

                this.placeLog(level, consumer, random, pos.south(i), config, (state) -> {
                    return state.setValue(RotatedPillarBlock.AXIS, direction.getAxis()); });

            } else if (direction == Direction.WEST) {

                this.placeLog(level, consumer, random, pos.west(i), config, (state) -> {
                    return state.setValue(RotatedPillarBlock.AXIS, direction.getAxis()); });

            }
        }
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
