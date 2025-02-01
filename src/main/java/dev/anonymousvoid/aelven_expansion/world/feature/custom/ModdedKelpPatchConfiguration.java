package dev.anonymousvoid.aelven_expansion.world.feature.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import javax.annotation.Nullable;

public class ModdedKelpPatchConfiguration implements FeatureConfiguration {
    public static final Codec<ModdedKelpPatchConfiguration> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(BlockState.CODEC.fieldOf("body_state").forGetter((configuration) -> {
            return configuration.body_state;
        }), BlockState.CODEC.fieldOf("head_state").forGetter((configuration) -> {
            return configuration.head_state;
        }), IntProvider.CODEC.fieldOf("range_xz").forGetter((configuration) -> {
            return configuration.range_xz;
        }), IntProvider.CODEC.fieldOf("range_y").forGetter((configuration) -> {
            return configuration.range_y;
        }), IntProvider.CODEC.fieldOf("height").forGetter((configuration) -> {
            return configuration.height;
        }), IntProvider.CODEC.fieldOf("tries").forGetter((configuration) -> {
            return configuration.tries;
        })).apply(instance, ModdedKelpPatchConfiguration::new);
    });
    public final BlockState body_state;
    public final BlockState head_state;
    public final IntProvider range_xz;
    public final IntProvider range_y;
    public final IntProvider height;
    public final IntProvider tries;

    /**
     * @param bodyState blockstate to place the kelp body
     * @param headState blockstate to place the kelp head
     * @param range_xz positive integer for the range of horizontal spread
     * @param range_y positive integer for the range of vertical spread
     * @param height range of how tall kelp can generate
     * @param tries how many times it will try placing the blockstate
     */
    public ModdedKelpPatchConfiguration(BlockState bodyState, BlockState headState, IntProvider range_xz, IntProvider range_y, IntProvider height, IntProvider tries) {
        this.body_state = bodyState;
        this.head_state = headState;
        this.range_xz = range_xz;
        this.range_y = range_y;
        this.height = height;
        this.tries = tries;
    }
}
