package dev.anonymousvoid.aelven_expansion.world.feature.custom;

import com.mojang.serialization.Codec;
import dev.anonymousvoid.aelven_expansion.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class SpireFeature extends Feature<SpireConfiguration> {

    public SpireFeature(Codec<SpireConfiguration> pCodec) {
        super(pCodec);
    }

    public boolean place(FeaturePlaceContext<SpireConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource rand = context.random();
        BlockPos pos = context.origin().below();

        SpireConfiguration config = context.config();
        BlockState state = config.state;
        int length = config.length.sample(rand);
        int range = config.range.getValue();
        float slant = config.slant.getValue();

        double xOffset = (double)rand.nextIntBetweenInclusive(-range, range) / 32;
        double yOffset = (double)rand.nextIntBetweenInclusive(-range, range) / 32;

        if (state.isCollisionShapeFullBlock(level, pos)) {
            for (int i = -length; i < length; i++) {
                double radius = (length - Math.abs(i)) / slant + 0.5;
                for (double x = -radius - 1; x < radius + 1; x++) {
                    for (double y = -radius - 1; y < radius + 1; y++) {
                        BlockPos newPos = pos.above(i)
                                .north((int) Math.floor(i * xOffset + x))
                                .east((int) Math.floor(i * yOffset + y));
                        BlockState newState = state;
                        if (Util.dist(x, y) < radius * 0.75) {
                            if (Util.dist(x, y) > radius * 0.7 && rand.nextBoolean()) {
                                newState = level.getBlockState(newPos);
                            }
                            this.setBlock(level, newPos, newState);
                        }
                    }
                }
            }
        }

        return true;
    }
}
