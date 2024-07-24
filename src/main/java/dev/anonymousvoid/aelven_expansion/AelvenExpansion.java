package dev.anonymousvoid.aelven_expansion;

import com.mojang.logging.LogUtils;
import dev.anonymousvoid.aelven_expansion.block.ModBlocks;
import dev.anonymousvoid.aelven_expansion.block.entity.ModBlockEntities;
import dev.anonymousvoid.aelven_expansion.block.entity.ModWoodTypes;
import dev.anonymousvoid.aelven_expansion.entity.ModEntityTypes;
import dev.anonymousvoid.aelven_expansion.entity.client.model.GnomeModel;
import dev.anonymousvoid.aelven_expansion.entity.client.model.ModBoatModel;
import dev.anonymousvoid.aelven_expansion.entity.client.renderer.GnomeRenderer;
import dev.anonymousvoid.aelven_expansion.entity.client.renderer.ModBoatRenderer;
import dev.anonymousvoid.aelven_expansion.entity.vehicle.ModBoat;
import dev.anonymousvoid.aelven_expansion.entity.vehicle.ModChestBoat;
import dev.anonymousvoid.aelven_expansion.item.ModItems;
import dev.anonymousvoid.aelven_expansion.item.ModSounds;
import dev.anonymousvoid.aelven_expansion.particle.ModParticles;
import dev.anonymousvoid.aelven_expansion.recipe.ModRecipes;
import dev.anonymousvoid.aelven_expansion.screen.KilnScreen;
import dev.anonymousvoid.aelven_expansion.screen.ModMenuTypes;
import dev.anonymousvoid.aelven_expansion.world.biome.ModBiomes;
import dev.anonymousvoid.aelven_expansion.world.biome_mods.ModBiomeModifiers;
import dev.anonymousvoid.aelven_expansion.world.dimension.ModDimensions;
import dev.anonymousvoid.aelven_expansion.world.feature.*;
import dev.anonymousvoid.aelven_expansion.world.feature.ModPlacedFeatures;
import dev.anonymousvoid.aelven_expansion.world.feature.tree.placer.foliage.ModFoliagePlacerType;
import dev.anonymousvoid.aelven_expansion.world.feature.tree.placer.roots.ModRootPlacerType;
import dev.anonymousvoid.aelven_expansion.world.feature.tree.placer.trunk.ModTrunkPlacerType;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@SuppressWarnings("removal")
@Mod(AelvenExpansion.MODID)
public class AelvenExpansion {
    public static final String MODID = "aelven_expansion";
    private static final Logger LOGGER = LogUtils.getLogger();

    public AelvenExpansion() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModSounds.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModEntityTypes.register(modEventBus);
        ModParticles.register(modEventBus);

        ModDimensions.register();
        ModBiomes.register(modEventBus);
        ModFeatures.register(modEventBus);

        ModBiomeModifiers.register(modEventBus);
        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        ModTrunkPlacerType.register(modEventBus);
        ModFoliagePlacerType.register(modEventBus);
        ModRootPlacerType.register(modEventBus);


        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MOON_FIR_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MOON_FIR_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SILVERBLOOD_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SILVERBLOOD_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PEACHGROVE_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PEACHGROVE_TRAPDOOR.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HYDROSATIN_CAP.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GLIMMERSATIN.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GLIMMERSATIN_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GLIMMERSATIN_WALL_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SILVERSATIN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SILVERSATIN_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SILVERSATIN_WALL_TORCH.get(), RenderType.cutout());


        WoodType.register(ModWoodTypes.MOON_FIR);
        WoodType.register(ModWoodTypes.SILVERBLOOD);
        WoodType.register(ModWoodTypes.PEACHGROVE);
        WoodType.register(ModWoodTypes.HYDROSATIN);
        BlockEntityRenderers.register(ModBlockEntities.SIGN_BLOCK_ENTITIES.get(), SignRenderer::new);

        EntityRenderers.register(ModEntityTypes.MOD_BOAT.get(), (context) -> {
            return new ModBoatRenderer(context, false);
        });
        EntityRenderers.register(ModEntityTypes.MOD_CHEST_BOAT.get(), (context) -> {
            return new ModBoatRenderer(context, true);
        });

    }

    private void commonSetup(final FMLCommonSetupEvent event) {

        Sheets.addWoodType(ModWoodTypes.MOON_FIR);
        Sheets.addWoodType(ModWoodTypes.SILVERBLOOD);
        Sheets.addWoodType(ModWoodTypes.PEACHGROVE);
        Sheets.addWoodType(ModWoodTypes.HYDROSATIN);

        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.MOON_FIR_SAPLING.getId(), ModBlocks.POTTED_MOON_FIR_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.MOON_BLOOM.getId(), ModBlocks.POTTED_MOON_BLOOM);

            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.SILVERBLOOD_SAPLING.getId(), ModBlocks.POTTED_SILVERBLOOD_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.SILVER_MARIGOLD.getId(), ModBlocks.POTTED_SILVER_MARIGOLD);

            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.PEACHGROVE_SAPLING.getId(), ModBlocks.POTTED_PEACHGROVE_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.PEACH_LAVENDER.getId(), ModBlocks.POTTED_PEACH_LAVENDER);
        });

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.KILN_MENU.get(), KilnScreen::new);
          /*  MenuScreens.register(ModMenuTypes.IDOL_TABLE_MENU.get(), IdolTableScreen::new); */
        }

        @SubscribeEvent
        public static void entityRenderer(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(ModEntityTypes.GNOME.get(), GnomeRenderer::new);
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(final EntityRenderersEvent.RegisterLayerDefinitions event) {

            // Gnome
            event.registerLayerDefinition(GnomeModel.LAYER_LOCATION, GnomeModel::createBodyLayer);

            // Moon Fir Boats
            event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(AelvenExpansion.MODID,
                    "mod_boat/" + ModBoat.Type.MOON_FIR.getName()), "main"), () -> {
                return ModBoatModel.createBodyModel(false);
            });
            event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(AelvenExpansion.MODID,
                    "mod_chest_boat/" + ModChestBoat.Type.MOON_FIR.getName()), "main"), () -> {
                return ModBoatModel.createBodyModel(true);
            });

            // Silverblood Boats
            event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(AelvenExpansion.MODID,
                    "mod_boat/" + ModBoat.Type.SILVERBLOOD.getName()), "main"), () -> {
                return ModBoatModel.createBodyModel(false);
            });
            event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(AelvenExpansion.MODID,
                    "mod_chest_boat/" + ModChestBoat.Type.SILVERBLOOD.getName()), "main"), () -> {
                return ModBoatModel.createBodyModel(true);
            });

            // Peachgrove Boats
            event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(AelvenExpansion.MODID,
                    "mod_boat/" + ModBoat.Type.PEACHGROVE.getName()), "main"), () -> {
                return ModBoatModel.createBodyModel(false);
            });
            event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(AelvenExpansion.MODID,
                    "mod_chest_boat/" + ModChestBoat.Type.PEACHGROVE.getName()), "main"), () -> {
                return ModBoatModel.createBodyModel(true);
            });
            
            // Hydrosatin Boats
            event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(AelvenExpansion.MODID,
                    "mod_boat/" + ModBoat.Type.HYDROSATIN.getName()), "main"), () -> {
                return ModBoatModel.createBodyModel(false);
            });
            event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(AelvenExpansion.MODID,
                    "mod_chest_boat/" + ModChestBoat.Type.HYDROSATIN.getName()), "main"), () -> {
                return ModBoatModel.createBodyModel(true);
            });

        }

    }
}
