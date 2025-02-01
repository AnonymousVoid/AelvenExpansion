package dev.anonymousvoid.aelven_expansion.world.feature.custom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class UnderwaterRandomPatchFeature extends Feature<UnderwaterRandomPatchConfiguration> {
    public UnderwaterRandomPatchFeature(Codec<UnderwaterRandomPatchConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<UnderwaterRandomPatchConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource rand = context.random();
        BlockPos blockpos = context.origin();
        UnderwaterRandomPatchConfiguration config = context.config();

        int i = 0;
        int range_xz = config.range_xz.sample(rand) + 1;
        int range_y = config.range_y.sample(rand) + 1;

        for(int j = 0; j < config.tries.sample(rand); ++j) {
            BlockPos pos = blockpos.offset(rand.nextInt(range_xz) - rand.nextInt(range_xz),
                    rand.nextInt(range_y) - rand.nextInt(range_y), rand.nextInt(range_xz) - rand.nextInt(range_xz));
            if (config.tall_state.canSurvive(level, pos) && rand.nextInt(3) == 0 &&
                    level.getBlockState(pos).is(Blocks.WATER) && level.getBlockState(pos.above()).is(Blocks.WATER)) {
                if (config.tall_state.hasProperty(TallSeagrassBlock.HALF)) {
                    if (level.setBlock(pos, config.tall_state, 2) && level.setBlock(pos.above(),
                            config.tall_state.setValue(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER), 2)) {
                        ++i;
                    }
                } else if (level.setBlock(pos, config.tall_state, 2)) {
                    ++i;
                }
            } else if (config.state.canSurvive(level, pos) && level.getBlockState(pos).is(Blocks.WATER)) {
                if (level.setBlock(pos, config.state, 2)) {
                    ++i;
                }
            }
        }

        return i > 0;
    }

}
