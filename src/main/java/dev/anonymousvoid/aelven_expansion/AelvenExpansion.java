package dev.anonymousvoid.aelven_expansion;

import com.mojang.logging.LogUtils;
import dev.anonymousvoid.aelven_expansion.block.ModBlocks;
import dev.anonymousvoid.aelven_expansion.block.entity.ModBlockEntities;
import dev.anonymousvoid.aelven_expansion.block.entity.ModWoodTypes;
import dev.anonymousvoid.aelven_expansion.item.ModItems;
import dev.anonymousvoid.aelven_expansion.item.ModSounds;
import dev.anonymousvoid.aelven_expansion.recipe.ModRecipes;
import dev.anonymousvoid.aelven_expansion.screen.IdolTableScreen;
import dev.anonymousvoid.aelven_expansion.screen.KilnScreen;
import dev.anonymousvoid.aelven_expansion.screen.ModMenuTypes;
import dev.anonymousvoid.aelven_expansion.world.biome_mods.ModBiomeModifiers;
import dev.anonymousvoid.aelven_expansion.world.feature.ModConfiguredFeatures;
import dev.anonymousvoid.aelven_expansion.world.feature.ModPlacedFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@SuppressWarnings("removal")
@Mod(AelvenExpansion.MODID)
public class AelvenExpansion {
    public static final String MODID = "aelven_expansion";
    private static final Logger LOGGER = LogUtils.getLogger();


    public AelvenExpansion() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModBiomeModifiers.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);
        ModConfiguredFeatures.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);

        ModSounds.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        // TODO see if ``` "render_type": "cutout" ``` works
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MOON_FIR_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MOON_FIR_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MOON_FIR_TRAPDOOR.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SILVERBLOOD_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SILVERBLOOD_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SILVERBLOOD_TRAPDOOR.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PEACHGROVE_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PEACHGROVE_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PEACHGROVE_TRAPDOOR.get(), RenderType.cutout());

        WoodType.register(ModWoodTypes.MOON_FIR);
        WoodType.register(ModWoodTypes.SILVERBLOOD);
        WoodType.register(ModWoodTypes.PEACHGROVE);
        BlockEntityRenderers.register(ModBlockEntities.SIGN_BLOCK_ENTITIES.get(), SignRenderer::new);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {

        Sheets.addWoodType(ModWoodTypes.MOON_FIR);
        Sheets.addWoodType(ModWoodTypes.SILVERBLOOD);
        Sheets.addWoodType(ModWoodTypes.PEACHGROVE);

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.KILN_MENU.get(), KilnScreen::new);
          /*  MenuScreens.register(ModMenuTypes.IDOL_TABLE_MENU.get(), IdolTableScreen::new); */
        }
    }
}
