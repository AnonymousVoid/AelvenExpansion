package dev.anonymousvoid.aelven_expansion.item.custom;

import dev.anonymousvoid.aelven_expansion.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class ChiselItem extends Item {

    public ChiselItem(Properties properties) { super(properties); }

    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();
        if (level.getBlockState(pos) == ModBlocks.CHITTA_LARGE_TILE.get().defaultBlockState()) {
            level.setBlockAndUpdate(pos, ModBlocks.CARVED_CHITTA_LARGE_TILE.get().defaultBlockState());
            useChisel(level, player, pos, stack);
            return InteractionResult.SUCCESS;
        } else if (level.getBlockState(pos) == ModBlocks.CHITTA_BRICKS.get().defaultBlockState()) {
            level.setBlockAndUpdate(pos, ModBlocks.CARVED_CHITTA_BRICKS.get().defaultBlockState());
            useChisel(level, player, pos, stack);
            return InteractionResult.SUCCESS;
        } else if (level.getBlockState(pos) == ModBlocks.CHITTA_TILES.get().defaultBlockState()) {
            level.setBlockAndUpdate(pos, ModBlocks.CARVED_CHITTA_AELVEN_TILES.get().defaultBlockState());
            useChisel(level, player, pos, stack);
            return InteractionResult.SUCCESS;
        } else if (level.getBlockState(pos) == ModBlocks.CARVED_CHITTA_AELVEN_TILES.get().defaultBlockState()) {
            level.setBlockAndUpdate(pos, ModBlocks.CARVED_CHITTA_ENCHANT_TILES.get().defaultBlockState());
            useChisel(level, player, pos, stack);
            return InteractionResult.SUCCESS;
        } else if (level.getBlockState(pos) == ModBlocks.CARVED_CHITTA_ENCHANT_TILES.get().defaultBlockState()) {
            level.setBlockAndUpdate(pos, ModBlocks.CARVED_CHITTA_GLYPHIC_TILES.get().defaultBlockState());
            useChisel(level, player, pos, stack);
            return InteractionResult.SUCCESS;
        } else if (level.getBlockState(pos) == ModBlocks.CARVED_CHITTA_GLYPHIC_TILES.get().defaultBlockState()) {
            level.setBlockAndUpdate(pos, ModBlocks.CARVED_CHITTA_NORSE_TILES.get().defaultBlockState());
            useChisel(level, player, pos, stack);
            return InteractionResult.SUCCESS;
        } else if (level.getBlockState(pos) == ModBlocks.CARVED_CHITTA_NORSE_TILES.get().defaultBlockState()) {
            level.setBlockAndUpdate(pos, ModBlocks.CARVED_CHITTA_AELVEN_TILES.get().defaultBlockState());
            useChisel(level, player, pos, stack);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    private void useChisel(Level level, Player player, BlockPos pos, ItemStack stack) {
        level.playSound(player, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, new Random().nextFloat()*0.2F + 0.8F);
        stack.hurtAndBreak(1, player, (entity) -> {
            entity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
    }

}
