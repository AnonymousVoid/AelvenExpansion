package dev.anonymousvoid.aelven_expansion.block.custom;

import dev.anonymousvoid.aelven_expansion.block.ModBlocks;
import dev.anonymousvoid.aelven_expansion.world.biome.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class ChalkDustBlock extends FallingBlock {
    public ChalkDustBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        if (isFree(level.getBlockState(pos.below())) && pos.getY() >= level.getMinBuildHeight()) {
            FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(level, pos, state);
            this.falling(fallingblockentity);
        } else if (pos.getY() < level.getMaxBuildHeight()) {
            if (level.getBiome(pos).unwrapKey().get() == ModBiomes.CHALK_PEAKS.getHolder().get().unwrapKey().get()) {
                BlockState above = level.getBlockState(pos.above());
                if (above.is(Blocks.AIR) || above.is(ModBlocks.CHALK_DUST.get())) {
                    if (rand.nextInt(5000) + 70 <= pos.getY()) {
                        if (above.is(ModBlocks.CHALK_DUST.get())) {
                            int layers = above.getValue(BlockStateProperties.LAYERS);
                            if (layers < 8 && rand.nextInt(8) <= 8 - layers) {
                                level.setBlockAndUpdate(pos.above(), ModBlocks.CHALK_DUST.get()
                                        .defaultBlockState().setValue(BlockStateProperties.LAYERS, layers + 1));
                            }
                        } else {
                            level.setBlockAndUpdate(pos.above(), ModBlocks.CHALK_DUST.get().defaultBlockState());
                        }
                    }
                }
            }
        }
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        if (rand.nextInt(64) == 0) {
            BlockPos blockpos = pos.above();
            if (isFree(level.getBlockState(blockpos)) && pos.getY() > 70 &&
                    level.getBiome(pos).unwrapKey().get() == ModBiomes.CHALK_PEAKS.getHolder().get().unwrapKey().get()) {
                double d0 = (double)pos.getX() + rand.nextDouble();
                double d1 = (double)pos.getY() + 1.95D;
                double d2 = (double)pos.getZ() + rand.nextDouble();
                level.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, state), d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }
        if (rand.nextInt(32) == 0) {
            BlockPos blockpos = pos.below();
            if (isFree(level.getBlockState(blockpos))) {
                double d0 = (double)pos.getX() + rand.nextDouble();
                double d1 = (double)pos.getY() - 0.05D;
                double d2 = (double)pos.getZ() + rand.nextDouble();
                level.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, state), d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    public int getDustColor(BlockState pState, BlockGetter pLevel, BlockPos pPos) { return 0x888888; }
}
