package dev.anonymousvoid.aelven_expansion.world.feature.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class SpireConfiguration  implements FeatureConfiguration {
    public static final Codec<SpireConfiguration> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(BlockState.CODEC.fieldOf("state").forGetter((configuration) -> {
            return configuration.state;
        }), IntProvider.CODEC.fieldOf("length").forGetter((configuration) -> {
            return configuration.length;
        }), ConstantInt.CODEC.fieldOf("range").forGetter((configuration) -> {
            return configuration.range;
        }), ConstantFloat.CODEC.fieldOf("slant").forGetter((configuration) -> {
            return configuration.slant;
        })).apply(instance, SpireConfiguration::new);
    });
    public final BlockState state;
    public final IntProvider length;
    public final ConstantInt range;
    public final ConstantFloat slant;

    /**
     * @param state blockstate to construct the spire from
     * @param length min and max height to construct the spire
     * @param range inclusive random offset, between -range and range, divided by 32
     * @param slant how slim at the base (higher makes them slimmer)
     */
    public SpireConfiguration(BlockState state, IntProvider length, ConstantInt range, ConstantFloat slant) {
        this.state = state;
        this.length = length;
        this.range = range;
        this.slant = slant;
    }
}
