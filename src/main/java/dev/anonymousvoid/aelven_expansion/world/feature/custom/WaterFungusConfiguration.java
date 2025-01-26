package dev.anonymousvoid.aelven_expansion.world.feature.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import javax.annotation.Nullable;

public class WaterFungusConfiguration implements FeatureConfiguration {
    public static final Codec<WaterFungusConfiguration> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(BlockState.CODEC.fieldOf("stem").forGetter((configuration) -> {
            return configuration.stem;
        }), BlockState.CODEC.fieldOf("spore").forGetter((configuration) -> {
            return configuration.spore;
        }), BlockState.CODEC.fieldOf("cap").forGetter((configuration) -> {
            return configuration.cap;
        }), BlockState.CODEC.fieldOf("fruit").forGetter((configuration) -> {
            return configuration.fruit;
        }), IntProvider.CODEC.fieldOf("stem_height").forGetter((configuration) -> {
            return configuration.stem_height;
        }), IntProvider.CODEC.fieldOf("cap_height").forGetter((configuration) -> {
            return configuration.cap_height;
        }), IntProvider.CODEC.fieldOf("cap_width").forGetter((configuration) -> {
            return configuration.cap_width;
        }), IntProvider.CODEC.fieldOf("spread_chance").forGetter((configuration) -> {
            return configuration.spread_chance;
        })).apply(instance, WaterFungusConfiguration::new);
    });
    public final BlockState stem;
    public final BlockState spore;
    public final BlockState cap;
    public final BlockState fruit;
    public final IntProvider stem_height;
    public final IntProvider cap_height;
    public final IntProvider cap_width;
    public final IntProvider spread_chance;

    /**
     * @param stem blockstate to construct the stem from
     * @param spore optional blockstate to place on and around the stem
     * @param cap blockstate to construct the cap from
     * @param fruit optional blockstate to place underneath the cap
     * @param stemHeight min and max height to construct the fungus stem
     * @param capHeight min and max height to construct the fungus cap
     * @param capWidth min and max width to construct the fungus cap
     * @param spreadChance the chance that the feature will place a bunch of spores instead of growing
     */
    public WaterFungusConfiguration(BlockState stem, @Nullable BlockState spore, BlockState cap,
                                    @Nullable BlockState fruit, IntProvider stemHeight, IntProvider capHeight,
                                    IntProvider capWidth, IntProvider spreadChance) {
        this.stem = stem;
        this.spore = spore;
        this.cap = cap;
        this.fruit = fruit;
        this.stem_height = stemHeight;
        this.cap_height = capHeight;
        this.cap_width = capWidth;
        this.spread_chance = spreadChance;
    }
}
