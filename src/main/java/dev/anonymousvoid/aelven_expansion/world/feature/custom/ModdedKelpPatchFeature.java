package dev.anonymousvoid.aelven_expansion.world.feature.custom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class ModdedKelpPatchFeature extends Feature<ModdedKelpPatchConfiguration> {
    public ModdedKelpPatchFeature(Codec<ModdedKelpPatchConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<ModdedKelpPatchConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource rand = context.random();
        BlockPos blockpos = context.origin();
        ModdedKelpPatchConfiguration config = context.config();

        int i = 0;
        int range_xz = config.range_xz.sample(rand) + 1;
        int range_y = config.range_y.sample(rand) + 1;

        for(int j = 0; j < config.tries.sample(rand); ++j) {
            BlockPos pos = blockpos.offset(rand.nextInt(range_xz) - rand.nextInt(range_xz),
                    rand.nextInt(range_y) - rand.nextInt(range_y), rand.nextInt(range_xz) - rand.nextInt(range_xz));
            if (config.body_state.canSurvive(level, pos) && level.getBlockState(pos).is(Blocks.WATER)) {
                for (int k = 0; k < config.height.sample(rand); k ++) {
                    BlockState placeState = config.head_state;
                    if (level.getBlockState(pos.above(k + 1)).is(Blocks.WATER)) {
                        placeState = config.body_state;
                    }
                    if (level.setBlock(pos.above(k), placeState, 2)) {
                        if (k == 0) {
                            ++i;
                        }
                    } else if (k > 0) {
                        level.setBlock(pos.above(k - 1), config.head_state, 2);
                    }
                }
            }
        }

        return i > 0;
    }

}
