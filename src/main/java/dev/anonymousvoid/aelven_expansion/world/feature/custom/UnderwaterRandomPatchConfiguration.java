package dev.anonymousvoid.aelven_expansion.world.feature.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import javax.annotation.Nullable;

public class UnderwaterRandomPatchConfiguration implements FeatureConfiguration {
    public static final Codec<UnderwaterRandomPatchConfiguration> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(BlockState.CODEC.fieldOf("state").forGetter((configuration) -> {
            return configuration.state;
        }), BlockState.CODEC.fieldOf("tall_state").forGetter((configuration) -> {
            return configuration.tall_state;
        }), IntProvider.CODEC.fieldOf("range_xz").forGetter((configuration) -> {
            return configuration.range_xz;
        }), IntProvider.CODEC.fieldOf("range_y").forGetter((configuration) -> {
            return configuration.range_y;
        }), IntProvider.CODEC.fieldOf("tries").forGetter((configuration) -> {
            return configuration.tries;
        })).apply(instance, UnderwaterRandomPatchConfiguration::new);
    });
    public final BlockState state;
    public final BlockState tall_state;
    public final IntProvider range_xz;
    public final IntProvider range_y;
    public final IntProvider tries;

    /**
     * @param state blockstate to place
     * @param tallState blockstate to place 1/3 of the time instead of the first
     * @param range_xz positive integer for the range of horizontal spread
     * @param range_y positive integer for the range of vertical spread
     * @param tries how many times it will try placing the blockstate
     */
    public UnderwaterRandomPatchConfiguration(BlockState state, @Nullable BlockState tallState, IntProvider range_xz, IntProvider range_y, IntProvider tries) {
        this.state = state;
        this.tall_state = tallState;
        this.range_xz = range_xz;
        this.range_y = range_y;
        this.tries = tries;
    }

    /**
     * @param state blockstate to place
     * @param range_xz positive integer for the range of horizontal spread
     * @param range_y positive integer for the range of vertical spread
     * @param tries how many times it will try placing the blockstate
     */
    public UnderwaterRandomPatchConfiguration(BlockState state, IntProvider range_xz, IntProvider range_y, IntProvider tries) {
        this(state, null, range_xz, range_y, tries);
    }
}
