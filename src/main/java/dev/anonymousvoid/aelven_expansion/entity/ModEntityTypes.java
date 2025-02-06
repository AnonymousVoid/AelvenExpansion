package dev.anonymousvoid.aelven_expansion.entity;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.entity.mob.Gnome;
import dev.anonymousvoid.aelven_expansion.entity.projectile.Mudball;
import dev.anonymousvoid.aelven_expansion.entity.vehicle.ModBoat;
import dev.anonymousvoid.aelven_expansion.entity.vehicle.ModChestBoat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AelvenExpansion.MODID);


    public static final RegistryObject<EntityType<Gnome>> GNOME =
            ENTITY_TYPES.register("gnome",
                    () -> EntityType.Builder.<Gnome>of(Gnome::new, MobCategory.CREATURE)
                            .sized(0.8F, 0.8F)
                            .build(new ResourceLocation(AelvenExpansion.MODID, "gnome").toString()));


    public static final RegistryObject<EntityType<ModBoat>> MOD_BOAT =
            ENTITY_TYPES.register("mod_boat",
                    () -> EntityType.Builder.<ModBoat>of(ModBoat::new, MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(AelvenExpansion.MODID, "mod_boat").toString()));

    public static final RegistryObject<EntityType<ModChestBoat>> MOD_CHEST_BOAT =
            ENTITY_TYPES.register("mod_chest_boat",
                    () -> EntityType.Builder.<ModChestBoat>of(ModChestBoat::new, MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(AelvenExpansion.MODID, "mod_chest_boat").toString()));

    public static final RegistryObject<EntityType<Mudball>> MUDBALL =
            ENTITY_TYPES.register("mudball",
                    () -> EntityType.Builder.<Mudball>of(Mudball::new, MobCategory.MISC)
                            .sized(0.25F, 0.25F)
                            .clientTrackingRange(4)
                            .updateInterval(10)
                            .build(new ResourceLocation(AelvenExpansion.MODID, "mudball").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
