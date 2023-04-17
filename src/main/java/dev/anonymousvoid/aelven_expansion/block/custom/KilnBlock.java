package dev.anonymousvoid.aelven_expansion.block.custom;

import dev.anonymousvoid.aelven_expansion.block.entity.KilnBlockEntity;
import dev.anonymousvoid.aelven_expansion.block.entity.ModBlockEntities;
import dev.anonymousvoid.aelven_expansion.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class KilnBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public KilnBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(LIT, Boolean.valueOf(true));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    /* BLOCK ENTITY */

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof KilnBlockEntity) {
                ((KilnBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult hit) {
        BlockEntity entity = level.getBlockEntity(pos);
        if (entity instanceof KilnBlockEntity) {
            if (!level.isClientSide()) NetworkHooks.openScreen(((ServerPlayer)player), (KilnBlockEntity)entity, pos);
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
            return InteractionResult.CONSUME;
        } else {
            return super.use(state, level, pos, player, hand, hit);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new KilnBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.KILN.get(),
                KilnBlockEntity::tick);
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            Direction direction = state.getValue(FACING);
            Direction.Axis direction$axis = direction.getAxis();
            double x = (double)pos.getX() + 0.5D;
            double y = (double)pos.getY() + 0.0D;
            double z = (double)pos.getZ() + 0.5D;
            double step = 0.52D;
            double randStep = random.nextDouble() * 0.6D - 0.3D;
            double randHeight = random.nextDouble() * 9.0D / 16.0D;
            double xStep = direction$axis == Direction.Axis.X ? (double)direction.getStepX() * step : randStep;
            double zStep = direction$axis == Direction.Axis.Z ? (double)direction.getStepZ() * step : randStep;

            if (random.nextDouble() < 0.1D) {
                level.playLocalSound(x, y, z, SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.BLOCKS,
                        1.0F, 1.0F, false);
                level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, x, y + 1.02D, z,
                        0.0D, 0.05D, 0.0D);
            }
            if (random.nextDouble() < 0.2) {
                level.addParticle(ModParticles.ELERIUM_FLAME_PARTICLES.get(), x + xStep, y + randHeight, z + zStep,
                        0.0D, 0.0D, 0.0D);
            }
            if (random.nextBoolean()) {
                level.addParticle(ParticleTypes.SMOKE, x + xStep, y + randHeight, z + zStep,
                        0.0D, 0.0D, 0.0D);
            }
        }
    }
}
