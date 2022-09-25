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

public class EleriumPasteItem extends Item {

    public EleriumPasteItem(Properties properties) { super(properties); }

    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();
        if (level.getBlockState(pos) == ModBlocks.CARVED_CHITTA_LARGE_TILE.get().defaultBlockState()) {
            level.setBlockAndUpdate(pos, ModBlocks.CARVED_CHITTA_RUNED_LARGE_TILE.get().defaultBlockState());
            usePaste(level, player, pos, stack);
            return InteractionResult.SUCCESS;
        } else if (level.getBlockState(pos) == ModBlocks.CARVED_CHITTA_AELVEN_TILES.get().defaultBlockState()) {
            level.setBlockAndUpdate(pos, ModBlocks.CARVED_CHITTA_AELVEN_RUNED_TILES.get().defaultBlockState());
            usePaste(level, player, pos, stack);
            return InteractionResult.SUCCESS;
        } else if (level.getBlockState(pos) == ModBlocks.CARVED_CHITTA_ENCHANT_TILES.get().defaultBlockState()) {
            level.setBlockAndUpdate(pos, ModBlocks.CARVED_CHITTA_ENCHANT_RUNED_TILES.get().defaultBlockState());
            usePaste(level, player, pos, stack);
            return InteractionResult.SUCCESS;
        } else if (level.getBlockState(pos) == ModBlocks.CARVED_CHITTA_GLYPHIC_TILES.get().defaultBlockState()) {
            level.setBlockAndUpdate(pos, ModBlocks.CARVED_CHITTA_GLYPHIC_RUNED_TILES.get().defaultBlockState());
            usePaste(level, player, pos, stack);
            return InteractionResult.SUCCESS;
        } else if (level.getBlockState(pos) == ModBlocks.CARVED_CHITTA_NORSE_TILES.get().defaultBlockState()) {
            level.setBlockAndUpdate(pos, ModBlocks.CARVED_CHITTA_NORSE_RUNED_TILES.get().defaultBlockState());
            usePaste(level, player, pos, stack);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    private void usePaste(Level level, Player player, BlockPos pos, ItemStack stack) {
        level.playSound(player, pos, SoundEvents.HONEY_BLOCK_SLIDE, SoundSource.BLOCKS, 1.0F, new Random().nextFloat()*0.2F + 0.8F);
        stack.hurtAndBreak(1, player, (entity) -> {
            entity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
    }

}
