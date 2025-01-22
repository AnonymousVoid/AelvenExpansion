package dev.anonymousvoid.aelven_expansion.world.feature.custom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import javax.annotation.Nullable;

public class WaterFungusFeature extends Feature<WaterFungusConfiguration> {

    public WaterFungusFeature(Codec<WaterFungusConfiguration> pCodec) {
        super(pCodec);
    }

    public boolean place(FeaturePlaceContext<WaterFungusConfiguration> context) {
        WorldGenLevel worldgenlevel = context.level();
        RandomSource randomsource = context.random();
        BlockPos blockpos = context.origin();
        return this.placeFeature(worldgenlevel, randomsource, blockpos, context.config());
    }

    protected boolean placeFeature(LevelAccessor level, RandomSource rand, BlockPos pos, WaterFungusConfiguration config) {
        BlockState stemState = config.stem;
        BlockState sporeState = config.spore;
        BlockState capState = config.cap;
        BlockState fruitState = config.fruit;
        IntProvider stemHProv = config.stem_height;
        IntProvider capHProv = config.cap_height;
        IntProvider capWProv = config.cap_width;


        int stemH = stemHProv.sample(rand);
        int capH = capHProv.sample(rand);
        int capW = capWProv.sample(rand);

        if (true) {
            BlockPos cap = placeStem(level, pos, rand, stemState, sporeState, stemH);
            placeCap(level, pos, rand, cap, capState, fruitState, capW, capH);
        }

        return true;

    }

    protected BlockPos placeStem(LevelAccessor level, BlockPos pos, RandomSource rand, BlockState stemState,
                                 @Nullable BlockState sporeState, int height) {
        for (int y = -2; y < height; y ++) {
            replaceBlock(level, pos.offset(0, y, 0), stemState);

            replaceBlock(level, pos.offset(-1, y, 0), stemState);
            replaceBlock(level, pos.offset(1, y, 0), stemState);
            replaceBlock(level, pos.offset(0, y, -1), stemState);
            replaceBlock(level, pos.offset(0, y, 1), stemState);

            if (y < height / 2 - rand.nextInt(3)) {
                placeBlock(level, pos.offset(-1, y, -1), stemState);
            } else if (level.getBlockState(pos.offset(-1, y, -1).below()).is(stemState.getBlock())) {
                placeBlock(level, pos.offset(-1, y, -1), sporeState);
            }

            if (y < height / 2 - rand.nextInt(3)) {
                placeBlock(level, pos.offset(1, y, -1), stemState);
            } else if (level.getBlockState(pos.offset(1, y, -1).below()).is(stemState.getBlock())) {
                placeBlock(level, pos.offset(1, y, -1), sporeState);
            }

            if (y < height / 2 - rand.nextInt(3)) {
                placeBlock(level, pos.offset(1, y, 1), stemState);
            } else if (level.getBlockState(pos.offset(1, y, 1).below()).is(stemState.getBlock())) {
                placeBlock(level, pos.offset(1, y, 1), sporeState);
            }

            if (y < height / 2 - rand.nextInt(3)) {
                placeBlock(level, pos.offset(-1, y, 1), stemState);
            } else if (level.getBlockState(pos.offset(-1, y, 1).below()).is(stemState.getBlock())) {
                placeBlock(level, pos.offset(-1, y, 1), sporeState);
            }
        }
        return pos.offset(0, height, 0);
    }

    protected void placeCap(LevelAccessor level, BlockPos pos, RandomSource rand, BlockPos capPos, BlockState capState,
                            @Nullable BlockState fruitState, int width, int height) {

        for (int x = -height; x < height; x++) {
            for (int y = -height; y < height; y++) {
                for (int z = -height; z < height; z++) {
                    double dist = Math.sqrt(Math.abs(x)*Math.abs(x) + Math.abs(y)*Math.abs(y) + Math.abs(z)*Math.abs(z));
                    BlockState currentState = level.getBlockState(capPos.offset(x, y - height + 1, z));
                    if (dist < height && dist >= height - width && y - rand.nextInt(2) >= 0 &&
                            (currentState.is(Blocks.WATER) || currentState.is(Blocks.AIR))) {
                        replaceBlock(level, capPos.offset(x, y - height + 1, z),
                                rand.nextInt(10) == 0 ? fruitState : capState);
                    }
                }
            }
        }

    }

    protected boolean placeBlock(LevelAccessor level, BlockPos pos, BlockState state) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = level.getBlockState(pos);
        if (!level.isOutsideBuildHeight(pos) && blockstate.is(Blocks.WATER) && !(level.getBlockState(blockpos).is(Blocks.AIR))) {
            level.setBlock(pos, state, 3);
            return true;
        } else {
            return false;
        }
    }

    protected boolean replaceBlock(LevelAccessor level, BlockPos pos, BlockState state) {
        BlockState blockstate = level.getBlockState(pos.above());
        if (!level.isOutsideBuildHeight(pos)) {
            level.setBlock(pos, state, 3);
            return true;
        } else {
            return false;
        }
    }
}
