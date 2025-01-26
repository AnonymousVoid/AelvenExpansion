package dev.anonymousvoid.aelven_expansion.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;

public class TransparentWaterloggableBouncyBlock extends TransparentWaterloggableBlock {

    public TransparentWaterloggableBouncyBlock(Properties properties) {
        super(properties);
    }

    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {
        if (!pEntity.isSuppressingBounce() && pState.getValue(BlockStateProperties.WATERLOGGED)) {
            pEntity.causeFallDamage(pFallDistance, 0.0F, DamageSource.FALL);
        } else {
            super.fallOn(pLevel, pState, pPos, pEntity, pFallDistance);
        }
    }

    public void updateEntityAfterFallOn(BlockGetter pLevel, Entity pEntity) {
        if (!pEntity.isSuppressingBounce() &&
                pLevel.getBlockState(pEntity.getOnPos()).getValue(BlockStateProperties.WATERLOGGED)) {
            this.bounceUp(pEntity);
        } else {
            super.updateEntityAfterFallOn(pLevel, pEntity);
        }

    }

    private void bounceUp(Entity pEntity) {
        Vec3 vec3 = pEntity.getDeltaMovement();
        if (vec3.y < 0.0D) {
            double d0 = pEntity instanceof LivingEntity ? 0.8D : 0.6D;
            pEntity.setDeltaMovement(vec3.x, -vec3.y * d0, vec3.z);
        }

    }

    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        double d0 = Math.abs(pEntity.getDeltaMovement().y);
        if (d0 < 0.1D && !pEntity.isSteppingCarefully() && pState.getValue(BlockStateProperties.WATERLOGGED)) {
            double d1 = 0.4D + d0 * 0.2D;
            pEntity.setDeltaMovement(pEntity.getDeltaMovement().multiply(d1, 1.0D, d1));
        }

        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}
