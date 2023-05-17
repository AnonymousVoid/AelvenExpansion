package dev.anonymousvoid.aelven_expansion.world.feature.tree.placer.roots;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.rootplacers.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public class PeachgroveRootPlacer extends RootPlacer {
    public static final int ROOT_WIDTH_LIMIT = 4;
    public static final int ROOT_LENGTH_LIMIT = 8;
    public static final Codec<PeachgroveRootPlacer> CODEC = RecordCodecBuilder.create((placer) -> {
        return rootPlacerParts(placer).and(PeachgroveRootPlacement.CODEC.fieldOf("peachgrove_root_placement").forGetter((placer2) -> {
            return placer2.peachgroveRootPlacement;
        })).apply(placer, PeachgroveRootPlacer::new);
    });
    private final PeachgroveRootPlacement peachgroveRootPlacement;

    public PeachgroveRootPlacer(IntProvider p_225817_, BlockStateProvider p_225818_, Optional<AboveRootPlacement> p_225819_, PeachgroveRootPlacement placement) {
        super(p_225817_, p_225818_, p_225819_);
        this.peachgroveRootPlacement = placement;
    }

    public boolean placeRoots(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> consumer, RandomSource rand, BlockPos pos1, BlockPos pos2, TreeConfiguration config) {
        List<BlockPos> list = Lists.newArrayList();
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pos1.mutable();

        while(blockpos$mutableblockpos.getY() < pos2.getY()) {
            if (!this.canPlaceRoot(level, blockpos$mutableblockpos)) {
                return false;
            }

            blockpos$mutableblockpos.move(Direction.UP);
        }

        list.add(pos2.below());

        for(Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos blockpos = pos2.relative(direction);
            List<BlockPos> list1 = Lists.newArrayList();
            if (!this.simulateRoots(level, rand, blockpos, direction, pos2, list1, 0)) {
                return false;
            }

            list.addAll(list1);
            list.add(pos2.relative(direction));
        }

        for(BlockPos blockpos1 : list) {
            this.placeRoot(level, consumer, rand, blockpos1, config);
        }

        return true;
    }

    private boolean simulateRoots(LevelSimulatedReader level, RandomSource rand, BlockPos pos1, Direction direction, BlockPos pos2, List<BlockPos> posList, int length) {
        int i = this.peachgroveRootPlacement.maxRootLength();
        if (length != i && posList.size() <= i) {
            for(BlockPos blockpos : this.potentialRootPositions(pos1, direction, rand, pos2)) {
                if (this.canPlaceRoot(level, blockpos)) {
                    posList.add(blockpos);
                    if (!this.simulateRoots(level, rand, blockpos, direction, pos2, posList, length + 1)) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    protected List<BlockPos> potentialRootPositions(BlockPos pos1, Direction direction, RandomSource rand, BlockPos pos2) {
        BlockPos blockpos = pos1.below();
        BlockPos blockpos1 = pos1.relative(direction);
        int i = pos1.distManhattan(pos2);
        int j = this.peachgroveRootPlacement.maxRootWidth();
        float f = this.peachgroveRootPlacement.randomSkewChance();
        if (i > j - 3 && i <= j) {
            return rand.nextFloat() < f ? List.of(blockpos, blockpos1.below()) : List.of(blockpos);
        } else if (i > j) {
            return List.of(blockpos);
        } else if (rand.nextFloat() < f) {
            return List.of(blockpos);
        } else {
            return rand.nextBoolean() ? List.of(blockpos1) : List.of(blockpos);
        }
    }

    protected boolean canPlaceRoot(LevelSimulatedReader level, BlockPos pos) {
        return super.canPlaceRoot(level, pos) || level.isStateAtPosition(pos, (state) -> {
            return state.is(this.peachgroveRootPlacement.canGrowThrough());
        });
    }

    protected void placeRoot(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> consumer, RandomSource rand, BlockPos pos, TreeConfiguration config) {
        if (level.isStateAtPosition(pos, (state) -> {
            return state.is(this.peachgroveRootPlacement.muddyRootsIn());
        })) {
            BlockState blockstate = this.peachgroveRootPlacement.muddyRootsProvider().getState(rand, pos);
            consumer.accept(pos, this.getPotentiallyWaterloggedState(level, pos, blockstate));
        } else {
            super.placeRoot(level, consumer, rand, pos, config);
        }

    }

    protected RootPlacerType<?> type() {
        return ModRootPlacerType.PEACHGROVE_ROOT_PLACER.get();
    }
}
