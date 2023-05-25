package dev.anonymousvoid.aelven_expansion.world.feature.custom;

import com.mojang.serialization.Codec;
import dev.anonymousvoid.aelven_expansion.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

public class SpireFeature extends Feature<BlockStateConfiguration> {

    public SpireFeature(Codec<BlockStateConfiguration> pCodec) {
        super(pCodec);
    }

    public boolean place(FeaturePlaceContext<BlockStateConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource rand = context.random();
        BlockPos pos = context.origin().below();
        BlockState state = (context.config()).state;
        int length = rand.nextInt(10) + 15;

        double xOffset = (double)rand.nextIntBetweenInclusive(-10, 10) / 20;
        double yOffset = (double)rand.nextIntBetweenInclusive(-10, 10) / 20;

        if (state.isCollisionShapeFullBlock(level, pos)) {
            for (int i = -length; i < length; i++) {
                double radius = (length - Math.abs(i)) / 4 + 0.5;
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
