package dev.anonymousvoid.aelven_expansion.world.feature.custom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;

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
        BlockState groundState = config.ground;
        BlockState stemState = config.stem;
        BlockState sporeState = config.spore;
        BlockState capState = config.cap;
        BlockState fruitState = config.fruit;
        IntProvider stemHProv = config.stem_height;
        IntProvider stemWProv = config.stem_width;
        IntProvider capHProv = config.cap_height;
        IntProvider capWProv = config.cap_width;


        int stemH = stemHProv.sample(rand);
        int stemW = stemWProv.sample(rand);
        int capH = capHProv.sample(rand);
        int capW = capWProv.sample(rand);
        double offX = ((double)rand.nextInt(5) - 2) / 2;
        double offY = ((double)rand.nextInt(5) - 2) / 2;

        if (true) { // TODO check if theres enough space to grow
//            replaceBlock(level, pos, stemState);
//            spreadGround(level, pos, rand, groundState, stemW, capW); TODO does not work?
            BlockPos cap = placeStem(level, pos, rand, offX, offY, stemState, sporeState, stemW, stemH);
            placeCap(level, pos, rand, cap, offX, offY, capState, fruitState, capW, capH);
        }

        return true;

//        for(int i = 0; i < height; i++) {
//            BlockPos blockpos = blockPos.offset(offX * i, i, offY * i);
//            this.placeBlock();
//        }
//
//        int step = 3;
//        for (int i = 0; i < height * 0.8; i ++) {
//            int y = 1 + i + height / 3;
//            this.placeLeavesRow(level, blockSetter, random, config,
//                    blockPos.offset(offX * y, y, offY * y),
//                    ((height - i) / 2 * step) / 7 + (i < height / 2 ? 1 : 0), Direction.Axis.Y);
//            step = step <= 1 ? 3 : step - 1;
//        }
//        for (int i = -2; i < 3; i ++) {
//            tryPlaceLeaf(level, blockSetter, random, config,
//                    blockPos.offset(offX * (height + i), height + i - 1, offY * (height + i)));
//            tryPlaceLeaf(level, blockSetter, random, config,
//                    blockPos.offset(offX * (height + i), height + i, offY * (height + i)));
//            tryPlaceLeaf(level, blockSetter, random, config,
//                    blockPos.offset(offX * (height + i), height + i + 1, offY * (height + i)));
//        }
    }

    protected void spreadGround(LevelAccessor level, BlockPos pos, RandomSource rand, @Nullable BlockState groundState, int min, int max) {
        if (groundState != null) {
            //(currentState.is(Blocks.WATER) || currentState.is(Blocks.AIR))
            for(int x = -max; x <= max; ++x) {
                for(int z = -max; z <= max; ++z) {
                    double distance = Math.sqrt(Math.abs(x) ^ 2 + Math.abs(z) ^ 2);
                    BlockPos pos1 = pos.offset(x, level.getHeight(Heightmap.Types.OCEAN_FLOOR, pos.getX() + x, pos.getZ() + z), z);
                    if (distance <= min) {
                        replaceBlock(level, pos1, groundState);
                        replaceBlock(level, pos1.offset(0, -1, 0), groundState);
                        replaceBlock(level, pos1.offset(0, -2, 0), groundState);
                    } else if (distance <= max && rand.nextInt(5) < 4) {
                        replaceBlock(level, pos1.offset(0, -rand.nextInt(2), 0), groundState);
                    }
                }
            }
        }
    }

    protected BlockPos placeStem(LevelAccessor level, BlockPos pos, RandomSource rand, double offsetX, double offsetY,
                                 BlockState stemState, @Nullable BlockState sporeState, int width, int height) {
        for (int y = -2; y < height; y ++) {
            replaceBlock(level, pos.offset(0, y, 0), stemState);

            replaceBlock(level, pos.offset(-1, y, 0), stemState);
            replaceBlock(level, pos.offset(1, y, 0), stemState);
            replaceBlock(level, pos.offset(0, y, -1), stemState);
            replaceBlock(level, pos.offset(0, y, 1), stemState);

            if (y < height / 2 - rand.nextInt(3)) {
                placeBlock(level, pos.offset(-1, y, -1), stemState);
            }
            if (y < height / 2 - rand.nextInt(3)) {
                placeBlock(level, pos.offset(1, y, -1), stemState);
            }
            if (y < height / 2 - rand.nextInt(3)) {
                placeBlock(level, pos.offset(1, y, 1), stemState);
            }
            if (y < height / 2 - rand.nextInt(3)) {
                placeBlock(level, pos.offset(-1, y, 1), stemState);
            }
        }
        return pos.offset(0, height, 0);
    }

    protected void placeCap(LevelAccessor level, BlockPos pos, RandomSource rand, BlockPos capPos, double offsetX, double offsetY,
                            BlockState capState, @Nullable BlockState fruitState, int width, int height) {

        for (int x = -height; x < height; x++) {
            for (int y = -height; y < height; y++) {
                for (int z = -height; z < height; z++) {
                    double dist = Math.sqrt(Math.abs(x)*Math.abs(x) + Math.abs(y)*Math.abs(y) + Math.abs(z)*Math.abs(z));
                    BlockState currentState = level.getBlockState(capPos.offset(x, y - height, z));
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
