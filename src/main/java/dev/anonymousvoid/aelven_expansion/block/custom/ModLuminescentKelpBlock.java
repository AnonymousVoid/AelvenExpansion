package dev.anonymousvoid.aelven_expansion.block.custom;

import dev.anonymousvoid.aelven_expansion.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class ModLuminescentKelpBlock extends GrowingPlantHeadBlock implements LiquidBlockContainer {
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);

    public ModLuminescentKelpBlock(BlockBehaviour.Properties properties) {
        super(properties, Direction.UP, SHAPE, true, 0.14D);
    }

    protected boolean canGrowInto(BlockState pState) {
        return pState.is(Blocks.WATER);
    }

    protected Block getBodyBlock() {
        return ModBlocks.LUMINESCENT_KELP_PLANT.get();
    }

    public boolean canPlaceLiquid(BlockGetter pLevel, BlockPos pPos, BlockState pState, Fluid pFluid) {
        return false;
    }

    public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        return false;
    }

    protected int getBlocksToGrowWhenBonemealed(RandomSource pRandom) {
        return 1 + pRandom.nextInt(2);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        return fluidstate.is(FluidTags.WATER) && fluidstate.getAmount() == 8 ? super.getStateForPlacement(pContext) : null;
    }

    public FluidState getFluidState(BlockState pState) {
        return Fluids.WATER.getSource(false);
    }
}