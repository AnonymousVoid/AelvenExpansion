package dev.anonymousvoid.aelven_expansion.item.custom;

import com.google.common.collect.ImmutableMap;
import dev.anonymousvoid.aelven_expansion.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Random;

public class ChiselItem extends CustomItem {
    protected static final Map<Block, Block> CHISELABLES = (new ImmutableMap.Builder<Block, Block>())
            .put(ModBlocks.CHITTA_LARGE_TILE.get(), ModBlocks.CARVED_CHITTA_LARGE_TILE.get())
            .put(ModBlocks.CHITTA_BRICKS.get(), ModBlocks.CARVED_CHITTA_BRICKS.get())
            .put(ModBlocks.CHITTA_TILES.get(), ModBlocks.CARVED_CHITTA_AELVEN_TILES.get())
            .put(ModBlocks.CARVED_CHITTA_AELVEN_TILES.get(), ModBlocks.CARVED_CHITTA_ENCHANT_TILES.get())
            .put(ModBlocks.CARVED_CHITTA_ENCHANT_TILES.get(), ModBlocks.CARVED_CHITTA_GLYPHIC_TILES.get())
            .put(ModBlocks.CARVED_CHITTA_GLYPHIC_TILES.get(), ModBlocks.CARVED_CHITTA_NORSE_TILES.get())
            .put(ModBlocks.CARVED_CHITTA_NORSE_TILES.get(), ModBlocks.CARVED_CHITTA_AELVEN_TILES.get())
            .build();

    public ChiselItem(Properties properties) { super(properties); }

    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();
        if (CHISELABLES.containsKey(level.getBlockState(pos).getBlock())) {
            level.setBlockAndUpdate(pos, CHISELABLES.get(level.getBlockState(pos).getBlock())
                    .withPropertiesOf(level.getBlockState(pos)));
            level.playSound(player, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, new Random().nextFloat()*0.2F + 0.8F);
            stack.hurtAndBreak(1, player, (entity) -> {
                entity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
            for (int i = 0; i < 10; i ++) spawnParticleCube(level, ParticleTypes.WAX_OFF, context);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

}
