package dev.anonymousvoid.aelven_expansion.world.feature.tree;

import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MoonFirTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource randomSource, boolean b) {
        return dev.anonymousvoid.aelven_expansion.world.feature.ModConfiguredFeatures.VegetalGeneration.MOON_FIR_TREE;
    }
}
