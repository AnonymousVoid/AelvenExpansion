package dev.anonymousvoid.aelven_expansion.world.feature.tree.placer.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.function.BiConsumer;

public class DroopyFoliagePlacer extends FoliagePlacer {
    public static final Codec<DroopyFoliagePlacer> CODEC = RecordCodecBuilder.create((placer) -> {
        return foliagePlacerParts(placer).apply(placer, DroopyFoliagePlacer::new);
    });

    public DroopyFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    protected FoliagePlacerType<?> type() {
        return FoliagePlacerType.DARK_OAK_FOLIAGE_PLACER;
    }

    protected void createFoliage(
            LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, TreeConfiguration config,
            int treeHeight, FoliagePlacer.FoliageAttachment attachment, int height, int radius, int offset) {

        BlockPos blockpos = attachment.pos().above(offset);
        boolean flag = attachment.doubleTrunk();

        for (int i = 0; i < height; i ++) {
            this.placeLeavesRow(level, blockSetter, random, config, blockpos,
                    radius, i - (int)Math.floor(height / 2), flag);
        }


        BlockPos.MutableBlockPos blockpos$mutableblockpos = blockpos.mutable();
        boolean j = true;
        boolean k = true;

        for(int i = 0; i < radius * 3; ++i) {
            j = !j;
            blockpos$mutableblockpos.setWithOffset(blockpos, random.nextInt(radius-1) - random.nextInt(radius-1),
                    -1 - (int)Math.floor(height / 2), random.nextInt(radius-1) - random.nextInt(radius-1));
            tryPlaceLeaf(level, blockSetter, random, config, blockpos$mutableblockpos);
            if (j) {
                k = !k;
                tryPlaceLeaf(level, blockSetter, random, config, blockpos$mutableblockpos.below());
                if  (k) tryPlaceLeaf(level, blockSetter, random, config, blockpos$mutableblockpos.below(2));
            }
        }

    }

    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return 5;
    }

    protected boolean shouldSkipLocationSigned(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return dist(dist(localX, localZ), localY+0.5) >= range || random.nextInt(50) == 0;
    }

    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return dist(dist(localX, localZ), localY+0.5) >= range || random.nextInt(50) == 0;
    }

    private double dist(double x, double y) {
        return Math.sqrt( ( x * x ) + ( y * y ) );
    }
}
