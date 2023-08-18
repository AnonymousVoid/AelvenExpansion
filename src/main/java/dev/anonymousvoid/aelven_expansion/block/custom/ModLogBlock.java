package dev.anonymousvoid.aelven_expansion.block.custom;

import dev.anonymousvoid.aelven_expansion.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.Nullable;

public class ModLogBlock extends RotatedPillarBlock {
    public ModLogBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if (context.getItemInHand().getItem() instanceof AxeItem) {
            if (state.is(ModBlocks.MOON_FIR_LOG.get())) {
                return ModBlocks.STRIPPED_MOON_FIR_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ModBlocks.MOON_FIR_WOOD.get())) {
                return ModBlocks.STRIPPED_MOON_FIR_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ModBlocks.MOON_FIR_BEAMS.get())) {
                return ModBlocks.STRIPPED_MOON_FIR_BEAMS.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if (state.is(ModBlocks.SILVERBLOOD_LOG.get())) {
                return ModBlocks.STRIPPED_SILVERBLOOD_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ModBlocks.SILVERBLOOD_WOOD.get())) {
                return ModBlocks.STRIPPED_SILVERBLOOD_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ModBlocks.SILVERBLOOD_BEAMS.get())) {
                return ModBlocks.STRIPPED_SILVERBLOOD_BEAMS.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if (state.is(ModBlocks.PEACHGROVE_LOG.get())) {
                return ModBlocks.STRIPPED_PEACHGROVE_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ModBlocks.PEACHGROVE_WOOD.get())) {
                return ModBlocks.STRIPPED_PEACHGROVE_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ModBlocks.PEACHGROVE_BEAMS.get())) {
                return ModBlocks.STRIPPED_PEACHGROVE_BEAMS.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            }

            if (state.is(ModBlocks.HYDROSATIN_STEM.get())) {
                return ModBlocks.STRIPPED_HYDROSATIN_STEM.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ModBlocks.HYDROSATIN_HYPHAE.get())) {
                return ModBlocks.STRIPPED_HYDROSATIN_HYPHAE.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ModBlocks.HYDROSATIN_BEAMS.get())) {
                return ModBlocks.STRIPPED_HYDROSATIN_BEAMS.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            
            if (state.is(ModBlocks.OAK_BEAMS.get())) {
                return ModBlocks.STRIPPED_OAK_BEAMS.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ModBlocks.SPRUCE_BEAMS.get())) {
                return ModBlocks.STRIPPED_SPRUCE_BEAMS.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ModBlocks.BIRCH_BEAMS.get())) {
                return ModBlocks.STRIPPED_BIRCH_BEAMS.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ModBlocks.JUNGLE_BEAMS.get())) {
                return ModBlocks.STRIPPED_JUNGLE_BEAMS.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ModBlocks.DARK_OAK_BEAMS.get())) {
                return ModBlocks.STRIPPED_DARK_OAK_BEAMS.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ModBlocks.ACACIA_BEAMS.get())) {
                return ModBlocks.STRIPPED_ACACIA_BEAMS.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ModBlocks.MANGROVE_BEAMS.get())) {
                return ModBlocks.STRIPPED_MANGROVE_BEAMS.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ModBlocks.CRIMSON_BEAMS.get())) {
                return ModBlocks.STRIPPED_CRIMSON_BEAMS.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ModBlocks.WARPED_BEAMS.get())) {
                return ModBlocks.STRIPPED_WARPED_BEAMS.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
