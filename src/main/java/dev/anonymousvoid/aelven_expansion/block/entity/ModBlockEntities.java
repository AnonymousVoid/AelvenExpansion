package dev.anonymousvoid.aelven_expansion.block.entity;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, AelvenExpansion.MODID);

    public static final RegistryObject<BlockEntityType<KilnBlockEntity>> KILN =
            BLOCK_ENTITIES.register("kiln", () ->
                    BlockEntityType.Builder.of(KilnBlockEntity::new,
                            ModBlocks.KILN.get()).build(null));

    public static final RegistryObject<BlockEntityType<IdolTableBlockEntity>> IDOL_TABLE =
            BLOCK_ENTITIES.register("idol_table", () ->
                    BlockEntityType.Builder.of(IdolTableBlockEntity::new,
                            ModBlocks.IDOL_TABLE.get()).build(null));

    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> SIGN_BLOCK_ENTITIES =
            BLOCK_ENTITIES.register("sign_block_entity", () ->
                    BlockEntityType.Builder.of(ModSignBlockEntity::new,
                            ModBlocks.MOON_FIR_WALL_SIGN.get(),
                            ModBlocks.MOON_FIR_SIGN.get(),
                            ModBlocks.SILVERBLOOD_WALL_SIGN.get(),
                            ModBlocks.SILVERBLOOD_SIGN.get(),
                            ModBlocks.PEACHGROVE_WALL_SIGN.get(),
                            ModBlocks.PEACHGROVE_SIGN.get(),
                            ModBlocks.HYDROSATIN_WALL_SIGN.get(),
                            ModBlocks.HYDROSATIN_SIGN.get()).build(null));
                    







    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
