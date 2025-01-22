package dev.anonymousvoid.aelven_expansion.world.feature.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class OasisConfiguration implements FeatureConfiguration {
    public static final Codec<OasisConfiguration> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(BlockState.CODEC.fieldOf("ground").forGetter((configuration) -> {
            return configuration.ground;
        }), BlockState.CODEC.fieldOf("underground").forGetter((configuration) -> {
            return configuration.underground;
        }), BlockState.CODEC.fieldOf("plants").forGetter((configuration) -> {
            return configuration.plants;
        }), BlockState.CODEC.fieldOf("rare_plants").forGetter((configuration) -> {
            return configuration.rare_plants;
        }), BlockState.CODEC.fieldOf("log").forGetter((configuration) -> {
            return configuration.log;
        }), BlockState.CODEC.fieldOf("leaves").forGetter((configuration) -> {
            return configuration.leaves;
        }), BlockState.CODEC.fieldOf("hanging_leaves").forGetter((configuration) -> {
            return configuration.hanging_leaves;
        }), BlockState.CODEC.fieldOf("boulder").forGetter((configuration) -> {
            return configuration.boulder;
        }), IntProvider.CODEC.fieldOf("ground_size").forGetter((configuration) -> {
            return configuration.ground_size;
        }), IntProvider.CODEC.fieldOf("tree_count").forGetter((configuration) -> {
            return configuration.tree_count;
        }), IntProvider.CODEC.fieldOf("tree_height").forGetter((configuration) -> {
            return configuration.tree_height;
        }), IntProvider.CODEC.fieldOf("boulder_count").forGetter((configuration) -> {
            return configuration.boulder_count;
        }), IntProvider.CODEC.fieldOf("boulder_size").forGetter((configuration) -> {
            return configuration.boulder_size;
        })).apply(instance, OasisConfiguration::new);
    });
    public final BlockState ground;
    public final BlockState underground;
    public final BlockState plants;
    public final BlockState rare_plants;
    public final BlockState log;
    public final BlockState leaves;
    public final BlockState hanging_leaves;
    public final BlockState boulder;

    public final IntProvider ground_size;
    public final IntProvider tree_count;
    public final IntProvider tree_height;
    public final IntProvider boulder_count;
    public final IntProvider boulder_size;

    /**
     * @param ground blockstate for the ground of the Oasis
     * @param underground blockstate for the blocks underneath the ground of the Oasis
     * @param plants blockstate plants to grow in the Oasis
     * @param rarePlants blockstate plants to grow rarely in the Oasis
     * @param log blockstate for the log of the Oasis tree
     * @param leaves blockstate for the leaves of the Oasis tree
     * @param hangingLeaves blockstate for the block to be placed beneath leaves of the Oasis tree
     * @param boulder blockstate for boulders around the Oasis
     * @param groundSize range for how large the ground is to spread
     * @param treeCount range for how many trees will be placed around the Oasis
     * @param treeHeight range for how tall each tree may be
     * @param boulderCount range for how many boulders will be placed around the Oasis
     * @param boulderSize range for how large each boulder may be
     */
    public OasisConfiguration(BlockState ground, BlockState underground, BlockState plants, BlockState rarePlants,
                              BlockState log, BlockState leaves, BlockState hangingLeaves, BlockState boulder,
                              IntProvider groundSize, IntProvider treeCount, IntProvider treeHeight,
                              IntProvider boulderCount, IntProvider boulderSize) {
        this.ground = ground;
        this.underground = underground;
        this.plants = plants;
        this.rare_plants = rarePlants;
        this.log = log;
        this.leaves = leaves;
        this.hanging_leaves = hangingLeaves;
        this.boulder = boulder;

        this.ground_size = groundSize;
        this.tree_count = treeCount;
        this.tree_height = treeHeight;
        this.boulder_count = boulderCount;
        this.boulder_size = boulderSize;
    }
}
