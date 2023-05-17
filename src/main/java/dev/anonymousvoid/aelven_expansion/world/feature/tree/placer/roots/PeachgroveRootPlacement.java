package dev.anonymousvoid.aelven_expansion.world.feature.tree.placer.roots;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record PeachgroveRootPlacement(HolderSet<Block> canGrowThrough, HolderSet<Block> muddyRootsIn, BlockStateProvider muddyRootsProvider, int maxRootWidth, int maxRootLength, float randomSkewChance) {
    public static final Codec<PeachgroveRootPlacement> CODEC = RecordCodecBuilder.create((p_225789_) -> {
        return p_225789_.group(RegistryCodecs.homogeneousList(Registry.BLOCK_REGISTRY).fieldOf("can_grow_through").forGetter((placement) -> {
            return placement.canGrowThrough;
        }), RegistryCodecs.homogeneousList(Registry.BLOCK_REGISTRY).fieldOf("muddy_roots_in").forGetter((placement) -> {
            return placement.muddyRootsIn;
        }), BlockStateProvider.CODEC.fieldOf("muddy_roots_provider").forGetter((placement) -> {
            return placement.muddyRootsProvider;
        }), Codec.intRange(1, 12).fieldOf("max_root_width").forGetter((placement) -> {
            return placement.maxRootWidth;
        }), Codec.intRange(1, 64).fieldOf("max_root_length").forGetter((placement) -> {
            return placement.maxRootLength;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("random_skew_chance").forGetter((placement) -> {
            return placement.randomSkewChance;
        })).apply(p_225789_, PeachgroveRootPlacement::new);
    });
}
