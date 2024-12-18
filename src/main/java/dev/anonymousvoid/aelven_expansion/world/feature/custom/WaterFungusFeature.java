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

import javax.annotation.Nullable;

public class WaterFungusFeature extends Feature<WaterFungusConfiguration> {

    public WaterFungusFeature(Codec<WaterFungusConfiguration> pCodec) {
        super(pCodec);
    }

    public boolean place(FeaturePlaceContext<WaterFungusConfiguration> context) {
        System.out.println("Placing 1 Tree!");
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


        BlockPos blockpos = new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.OCEAN_FLOOR, pos.getX(), pos.getZ()), pos.getZ());

        int stemH = stemHProv.getMinValue();
        int stemW = stemWProv.getMinValue();
        int capH = capHProv.getMinValue();
        int capW = capWProv.getMinValue();
        double offX = ((double)rand.nextInt(5) - 2) / 2;
        double offY = ((double)rand.nextInt(5) - 2) / 2;

        if (true) { // TODO check if theres enough space to grow
            replaceBlock(level, blockpos, stemState);
            spreadGround(level, blockpos, rand, groundState, stemW, capW);
            BlockPos cap = placeStem(level, blockpos, rand, offX, offY, stemState, sporeState, stemW, stemH);
            placeCap(level, blockpos, rand, cap, offX, offY, capState, fruitState, capW, capH);
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
            replaceBlock(level, pos.below(), groundState);
        }
    }

    protected BlockPos placeStem(LevelAccessor level, BlockPos pos, RandomSource rand, double offsetX, double offsetY,
                                 BlockState stemState, @Nullable BlockState sporeState, int width, int height) {
        placeBlock(level, pos, stemState);
        if (sporeState != null) {
            Direction d = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
            BlockPos p = pos.offset(d.getNormal());
            placeBlock(level, p, sporeState);
        }
        return pos.above();
    }

    protected void placeCap(LevelAccessor level, BlockPos pos, RandomSource rand, BlockPos capPos, double offsetX, double offsetY,
                            BlockState capState, @Nullable BlockState fruitState, int width, int height) {
        placeBlock(level, capPos, capState);
        if (fruitState != null) {
            Direction d = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
            BlockPos p = capPos.offset(d.getNormal());
            placeBlock(level, p, fruitState);
        }
    }

    protected boolean placeBlock(LevelAccessor level, BlockPos pos, BlockState state) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = level.getBlockState(pos);
        if (blockstate.is(Blocks.WATER) && !(level.getBlockState(blockpos).is(Blocks.AIR))) {
            level.setBlock(pos, state, 3);
            return true;
        } else {
            return false;
        }
    }

    protected boolean replaceBlock(LevelAccessor level, BlockPos pos, BlockState state) {
        BlockState blockstate = level.getBlockState(pos.above());
        if (!(blockstate.is(Blocks.AIR))) {
            level.setBlock(pos, state, 3);
            return true;
        } else {
            return false;
        }
    }
}
