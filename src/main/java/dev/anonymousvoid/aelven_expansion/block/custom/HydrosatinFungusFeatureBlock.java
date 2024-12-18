package dev.anonymousvoid.aelven_expansion.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

//public class WaterloggableFeatureBlock extends BushBlock implements BonemealableBlock, SimpleWaterloggedBlock {
public class HydrosatinFungusFeatureBlock extends BushBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 9.0D, 12.0D);
//    private final Supplier<Holder<? extends ConfiguredFeature<WaterFungusConfiguration, ?>>> featureSupplier;

//    public WaterloggableFeatureBlock(Supplier<Holder<? extends ConfiguredFeature<WaterFungusConfiguration, ?>>> pFeatureSupplier, BlockBehaviour.Properties pProperties) {
    public HydrosatinFungusFeatureBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
//        this.featureSupplier = pFeatureSupplier;
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, true));
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.isSolidRender(pLevel, pPos);
    }

//    public boolean isValidBonemealTarget(BlockGetter pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
//        return mayPlaceOn(pLevel.getBlockState(pPos.below()), pLevel, pPos.below()) && pState.getValue(WATERLOGGED);
//    }

//    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
//        return (double)pRandom.nextFloat() < 0.4D;
//    }

//    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
//        this.featureSupplier.get().get().feature().place(new WaterFungusConfiguration(
//                ModBlocks.FUNGAL_COBBLED_HYDROJADE.get().defaultBlockState(),
//                ModBlocks.HYDROSATIN_STEM.get().defaultBlockState(),
//                ModBlocks.HYDROSATIN_FUNGUS.get().defaultBlockState(),
//                ModBlocks.HYDROSATIN_CAP.get().defaultBlockState(),
//                ModBlocks.GLIMMERSATIN.get().defaultBlockState(),
//                UniformInt.of(1, 2),
//                UniformInt.of(1, 10),
//                UniformInt.of(5, 8),
//                UniformInt.of(1, 3)), pLevel, pLevel.getChunkSource().getGenerator(), pRandom, pPos);
//    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(WATERLOGGED);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluidstate.is(FluidTags.WATER) && fluidstate.getAmount() == 8));
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
        return pState.getFluidState().isEmpty();
    }
}
