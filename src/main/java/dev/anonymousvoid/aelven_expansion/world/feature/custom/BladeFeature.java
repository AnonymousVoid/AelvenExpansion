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
import net.minecraft.world.phys.Vec2;

import java.util.ArrayList;

public class BladeFeature extends Feature<BlockStateConfiguration> {

    public BladeFeature(Codec<BlockStateConfiguration> pCodec) {
        super(pCodec);
    }

    public boolean place(FeaturePlaceContext<BlockStateConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource rand = context.random();
        BlockPos pos = context.origin().below();
        BlockState state = (context.config()).state;
        int radius = rand.nextInt(3) + 8;
        int origin = -radius / 2;

        Vec2 v0 = new Vec2(rand.nextInt(radius * 2) - radius, rand.nextInt(radius * 2) - radius);

        ArrayList<Vec2> line = Util.getLine(
                (int)Math.floor(v0.x), (int)Math.floor(v0.y),
                (int)Math.floor(v0.x * -1), (int)Math.floor(v0.y * -1));

        if (state.isCollisionShapeFullBlock(level, pos)) {
            for (Vec2 v : line) {
                for (int y = -radius; y < radius; y++) {
                    if (Util.dist3D(v.x, y, v.y) < radius) {
                        this.setBlock(level, pos.north((int) Math.floor(v.x)).east((int) Math.floor(v.y)).above(origin + y), state);
                    }
                }
            }
        }

        return true;
    }
}
