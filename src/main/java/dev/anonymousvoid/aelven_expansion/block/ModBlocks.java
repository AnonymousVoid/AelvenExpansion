package dev.anonymousvoid.aelven_expansion.block;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.block.custom.*;
import dev.anonymousvoid.aelven_expansion.block.custom.ModStandingSignBlock;
import dev.anonymousvoid.aelven_expansion.block.custom.ModWallSignBlock;
import dev.anonymousvoid.aelven_expansion.block.entity.ModWoodTypes;
import dev.anonymousvoid.aelven_expansion.item.ModCreativeModeTab;
import dev.anonymousvoid.aelven_expansion.item.ModItems;
import dev.anonymousvoid.aelven_expansion.world.feature.tree.MoonFirTreeGrower;
import dev.anonymousvoid.aelven_expansion.world.feature.tree.PeachgroveTreeGrower;
import dev.anonymousvoid.aelven_expansion.world.feature.tree.SilverbloodTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, AelvenExpansion.MODID);

    // BLOCK PROPERTIES
    private static final BlockBehaviour.Properties woodProperties = BlockBehaviour.Properties.of(Material.WOOD).requiresCorrectToolForDrops().strength(2.0F, 3.0F).sound(SoundType.WOOD);
    private static final BlockBehaviour.Properties woodPropertiesNoCollide = BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noCollission();
    private static final BlockBehaviour.Properties woodPropertiesNoOcclude = BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion();
    private static final BlockBehaviour.Properties azaleaProperties = BlockBehaviour.Properties.copy(Blocks.AZALEA_LEAVES);
    private static final BlockBehaviour.Properties flowerProperties = BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ);
    private static final BlockBehaviour.Properties coralProperties = BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL);
    private static final BlockBehaviour.Properties pottedFlowerProperties = BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion();
    private static final BlockBehaviour.Properties replaceablePlantProperties = BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ);
    private static final BlockBehaviour.Properties stonePlantProperties = BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL).sound(SoundType.TUFF).offsetType(BlockBehaviour.OffsetType.XYZ);
    private static final BlockBehaviour.Properties stoneLeafProperties = BlockBehaviour.Properties.copy(Blocks.AZALEA_LEAVES).sound(SoundType.MANGROVE_ROOTS);
    private static final BlockBehaviour.Properties mudProperties = BlockBehaviour.Properties.copy(Blocks.DIRT).color(MaterialColor.TERRACOTTA_CYAN).sound(SoundType.MUD).randomTicks();
    private static final BlockBehaviour.Properties mulchProperties = BlockBehaviour.Properties.copy(Blocks.DIRT).color(MaterialColor.TERRACOTTA_CYAN).sound(SoundType.MOSS).strength(0.1F).randomTicks();
    private static final BlockBehaviour.Properties muddyLeafProperties = BlockBehaviour.Properties.copy(Blocks.DIRT).color(MaterialColor.TERRACOTTA_CYAN).sound(SoundType.MUD);

    private static final BlockBehaviour.Properties chittaProperties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE).strength(2.0F, 6.0F);
    private static final BlockBehaviour.Properties chittaBrickProperties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE_BRICKS).strength(2.5F, 6.5F);
    private static final BlockBehaviour.Properties chittaTileProperties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE_TILES).strength(2.5F, 6.5F);
    private static final BlockBehaviour.Properties chittaPolishedProperties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.POLISHED_DEEPSLATE).strength(2.5F, 6.5F);
    private static final BlockBehaviour.Properties chittaKilnProperties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.POLISHED_DEEPSLATE).strength(3.5F, 8.0F)
            .lightLevel(state -> state.getValue(KilnBlock.LIT) ? 15 : 0);

    private static final BlockBehaviour.Properties chalkProperties = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_WHITE).requiresCorrectToolForDrops().sound(SoundType.CALCITE).strength(0.6F, 0.5F);
    private static final BlockBehaviour.Properties chipstoneProperties = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().sound(SoundType.TUFF).strength(1.8F, 6.0F);
    private static final BlockBehaviour.Properties drystoneProperties = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE).strength(1.0F, 6.0F);
    private static final BlockBehaviour.Properties drystoneBrickProperties = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE_BRICKS).strength(1.5F, 6.5F);

    private static final BlockBehaviour.Properties mudstoneProperties = BlockBehaviour.Properties.copy(Blocks.PACKED_MUD);
    private static final BlockBehaviour.Properties mudstoneBrickProperties = BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS);

    private static final BlockBehaviour.Properties gravelProperties = BlockBehaviour.Properties.of(Material.SAND, MaterialColor.TERRACOTTA_WHITE).strength(0.5F).sound(SoundType.SAND);
    private static final BlockBehaviour.Properties chalkDustProperties = BlockBehaviour.Properties.of(Material.SAND, MaterialColor.TERRACOTTA_WHITE).strength(0.5F).sound(SoundType.SAND).randomTicks();
    private static final BlockBehaviour.Properties chalkDustLayerProperties = BlockBehaviour.Properties.of(Material.SAND, MaterialColor.TERRACOTTA_WHITE).strength(0.3F).sound(SoundType.SAND).requiresCorrectToolForDrops();
    private static final BlockBehaviour.Properties shingleProperties = BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK).strength(1.75F, 1.5F);

    private static final BlockBehaviour.Properties eleriumBlockProperties = BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(6.0F, 240.0F);
    private static final BlockBehaviour.Properties elerutiteBlockProperties = BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK).requiresCorrectToolForDrops().strength(6.0F, 240.0F);
    private static final BlockBehaviour.Properties rawSilverBlockProperties = BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(5.0F, 120.0F);
    private static final BlockBehaviour.Properties silverBlockProperties = BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.NETHERITE_BLOCK).requiresCorrectToolForDrops().strength(5.0F, 120.0F);
    private static final BlockBehaviour.Properties stoneOreProperties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.STONE).strength(3.0F, 3.0F);
    private static final BlockBehaviour.Properties deepslateOreProperties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE).strength(4.5F, 3.0F);
    private static final BlockBehaviour.Properties shroomProperties = BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK).noOcclusion();

    private static final BlockBehaviour.Properties hydrojadeProperties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE).strength(2.0F, 6.0F);
    private static final BlockBehaviour.Properties hydrojadeBrickProperties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE_BRICKS).strength(2.5F, 6.5F);
    private static final BlockBehaviour.Properties hydrojadeTileProperties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE_TILES).strength(2.5F, 6.5F);
    private static final BlockBehaviour.Properties hydrojadePolishedProperties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.POLISHED_DEEPSLATE).strength(2.5F, 6.5F);
    private static final BlockBehaviour.Properties jadegrassNyliumProperties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.NYLIUM).strength(1.8F,5.0F);
    // BLOCKS
    public static final RegistryObject<Block> MOON_FIR_LOG = registerBlock("moon_fir_log",
            () -> logBlock(MaterialColor.COLOR_GRAY, MaterialColor.COLOR_BLUE), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MOON_FIR_WOOD = registerBlock("moon_fir_wood",
            () -> logBlock(MaterialColor.COLOR_BLUE, MaterialColor.COLOR_BLUE), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MOON_FIR_BEAMS = registerBlock("moon_fir_beams",
            () -> logBlock(MaterialColor.COLOR_GRAY, MaterialColor.COLOR_BLUE), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_MOON_FIR_LOG = registerBlock("stripped_moon_fir_log",
            () -> logBlock(MaterialColor.COLOR_GRAY, MaterialColor.COLOR_BLUE), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_MOON_FIR_WOOD = registerBlock("stripped_moon_fir_wood",
            () -> logBlock(MaterialColor.COLOR_GRAY, MaterialColor.COLOR_GRAY), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_MOON_FIR_BEAMS = registerBlock("stripped_moon_fir_beams",
            () -> logBlock(MaterialColor.COLOR_GRAY, MaterialColor.COLOR_BLUE), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MOON_FIR_PLANKS = registerBlock("moon_fir_planks",
            () -> flammableBlock(woodProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MOON_FIR_STAIRS = registerBlock("moon_fir_stairs",
            () -> flammableStairBlock(() -> ModBlocks.MOON_FIR_PLANKS.get().defaultBlockState(), woodProperties),
            ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MOON_FIR_SLAB = registerBlock("moon_fir_slab",
            () -> flammableSlabBlock(woodProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> MOON_FIR_FENCE = registerBlock("moon_fir_fence",
            () -> flammableFenceBlock(woodProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MOON_FIR_FENCE_GATE = registerBlock("moon_fir_fence_gate",
            () -> flammableFenceGateBlock(woodProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MOON_FIR_BUTTON = registerBlock("moon_fir_button",
            () -> new WoodButtonBlock(woodPropertiesNoCollide), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MOON_FIR_PRESSURE_PLATE = registerBlock("moon_fir_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, woodProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MOON_FIR_DOOR = registerBlock("moon_fir_door",
            () -> flammableDoorBlock(woodPropertiesNoOcclude), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MOON_FIR_TRAPDOOR = registerBlock("moon_fir_trapdoor",
            () -> flammableTrapDoorBlock(woodPropertiesNoOcclude), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MOON_FIR_WALL_SIGN = registerBlockWithoutBlockItem("moon_fir_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), ModWoodTypes.MOON_FIR));
    public static final RegistryObject<Block> MOON_FIR_SIGN = registerBlockWithoutBlockItem("moon_fir_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), ModWoodTypes.MOON_FIR));
    public static final RegistryObject<Block> MOON_FIR_SAPLING = registerBlock("moon_fir_sapling",
            () -> new SaplingBlock(new MoonFirTreeGrower(), flowerProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> POTTED_MOON_FIR_SAPLING = registerBlockWithoutBlockItem("potted_moon_fir_sapling",
            () -> new FlowerPotBlock(MOON_FIR_SAPLING.get(), pottedFlowerProperties));
    public static final RegistryObject<Block> MOON_BLOOM = registerBlock("moon_bloom",
            () -> new FlowerBlock(MobEffects.NIGHT_VISION, 10, flowerProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> POTTED_MOON_BLOOM = registerBlockWithoutBlockItem("potted_moon_bloom",
            () -> new FlowerPotBlock(MOON_BLOOM.get(), pottedFlowerProperties));
    public static final RegistryObject<Block> MOON_FIR_LEAVES = registerBlock("moon_fir_leaves",
            () -> aelvenDecayLeavesBlock(azaleaProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> SILVERBLOOD_LOG = registerBlock("silverblood_log",
            () -> logBlock(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> SILVERBLOOD_WOOD = registerBlock("silverblood_wood",
            () -> logBlock(MaterialColor.COLOR_PINK, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> SILVERBLOOD_BEAMS = registerBlock("silverblood_beams",
            () -> logBlock(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_SILVERBLOOD_LOG = registerBlock("stripped_silverblood_log",
            () -> logBlock(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_SILVERBLOOD_WOOD = registerBlock("stripped_silverblood_wood",
            () -> logBlock(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_LIGHT_GRAY), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_SILVERBLOOD_BEAMS = registerBlock("stripped_silverblood_beams",
            () -> logBlock(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> SILVERBLOOD_PLANKS = registerBlock("silverblood_planks",
            () -> flammableBlock(woodProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> SILVERBLOOD_STAIRS = registerBlock("silverblood_stairs",
            () -> flammableStairBlock(() -> ModBlocks.SILVERBLOOD_PLANKS.get().defaultBlockState(), woodProperties),
            ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> SILVERBLOOD_SLAB = registerBlock("silverblood_slab",
            () -> flammableSlabBlock(woodProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> SILVERBLOOD_FENCE = registerBlock("silverblood_fence",
            () -> flammableFenceBlock(woodProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> SILVERBLOOD_FENCE_GATE = registerBlock("silverblood_fence_gate",
            () -> flammableFenceGateBlock(woodProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> SILVERBLOOD_BUTTON = registerBlock("silverblood_button",
            () -> new WoodButtonBlock(woodPropertiesNoCollide), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> SILVERBLOOD_PRESSURE_PLATE = registerBlock("silverblood_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, woodProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> SILVERBLOOD_DOOR = registerBlock("silverblood_door",
            () -> flammableDoorBlock(woodPropertiesNoOcclude), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> SILVERBLOOD_TRAPDOOR = registerBlock("silverblood_trapdoor",
            () -> flammableTrapDoorBlock(woodPropertiesNoOcclude), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> SILVERBLOOD_WALL_SIGN = registerBlockWithoutBlockItem("silverblood_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), ModWoodTypes.SILVERBLOOD));
    public static final RegistryObject<Block> SILVERBLOOD_SIGN = registerBlockWithoutBlockItem("silverblood_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), ModWoodTypes.SILVERBLOOD));
    public static final RegistryObject<Block> SILVERBLOOD_SAPLING = registerBlock("silverblood_sapling",
            () -> new SaplingBlock(new SilverbloodTreeGrower(), flowerProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> POTTED_SILVERBLOOD_SAPLING = registerBlockWithoutBlockItem("potted_silverblood_sapling",
            () -> new FlowerPotBlock(SILVERBLOOD_SAPLING.get(), pottedFlowerProperties));
    public static final RegistryObject<Block> SILVER_MARIGOLD = registerBlock("silver_marigold",
            () -> new FlowerBlock(MobEffects.MOVEMENT_SPEED, 10, flowerProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> POTTED_SILVER_MARIGOLD = registerBlockWithoutBlockItem("potted_silver_marigold",
            () -> new FlowerPotBlock(SILVER_MARIGOLD.get(), pottedFlowerProperties));
    public static final RegistryObject<Block> SILVERBLOOD_LEAVES = registerBlock("silverblood_leaves",
            () -> aelvenDecayLeavesBlock(azaleaProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);


    public static final RegistryObject<Block> PEACHGROVE_LOG = registerBlock("peachgrove_log",
            () -> logBlock(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> PEACHGROVE_WOOD = registerBlock("peachgrove_wood",
            () -> logBlock(MaterialColor.COLOR_PINK, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> PEACHGROVE_BEAMS = registerBlock("peachgrove_beams",
            () -> logBlock(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_PEACHGROVE_LOG = registerBlock("stripped_peachgrove_log",
            () -> logBlock(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_PEACHGROVE_WOOD = registerBlock("stripped_peachgrove_wood",
            () -> logBlock(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_LIGHT_GRAY), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_PEACHGROVE_BEAMS = registerBlock("stripped_peachgrove_beams",
            () -> logBlock(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> PEACHGROVE_PLANKS = registerBlock("peachgrove_planks",
            () -> flammableBlock(woodProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> PEACHGROVE_STAIRS = registerBlock("peachgrove_stairs",
            () -> flammableStairBlock(() -> ModBlocks.PEACHGROVE_PLANKS.get().defaultBlockState(), woodProperties),
            ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> PEACHGROVE_SLAB = registerBlock("peachgrove_slab",
            () -> flammableSlabBlock(woodProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> PEACHGROVE_FENCE = registerBlock("peachgrove_fence",
            () -> flammableFenceBlock(woodProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> PEACHGROVE_FENCE_GATE = registerBlock("peachgrove_fence_gate",
            () -> flammableFenceGateBlock(woodProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> PEACHGROVE_BUTTON = registerBlock("peachgrove_button",
            () -> new WoodButtonBlock(woodPropertiesNoCollide), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> PEACHGROVE_PRESSURE_PLATE = registerBlock("peachgrove_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, woodProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> PEACHGROVE_DOOR = registerBlock("peachgrove_door",
            () -> flammableDoorBlock(woodPropertiesNoOcclude), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> PEACHGROVE_TRAPDOOR = registerBlock("peachgrove_trapdoor",
            () -> flammableTrapDoorBlock(woodPropertiesNoOcclude), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> PEACHGROVE_WALL_SIGN = registerBlockWithoutBlockItem("peachgrove_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), ModWoodTypes.PEACHGROVE));
    public static final RegistryObject<Block> PEACHGROVE_SIGN = registerBlockWithoutBlockItem("peachgrove_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), ModWoodTypes.PEACHGROVE));
    public static final RegistryObject<Block> PEACHGROVE_SAPLING = registerBlock("peachgrove_sapling",
            () -> new SaplingBlock(new PeachgroveTreeGrower(), flowerProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> POTTED_PEACHGROVE_SAPLING = registerBlockWithoutBlockItem("potted_peachgrove_sapling",
            () -> new FlowerPotBlock(PEACHGROVE_SAPLING.get(), pottedFlowerProperties));
    public static final RegistryObject<Block> PEACH_LAVENDER = registerBlock("peach_lavender",
            () -> new FlowerBlock(MobEffects.ABSORPTION, 10, flowerProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> POTTED_PEACH_LAVENDER = registerBlockWithoutBlockItem("potted_peach_lavender",
            () -> new FlowerPotBlock(PEACH_LAVENDER.get(), pottedFlowerProperties));
    public static final RegistryObject<Block> PEACHGROVE_LEAVES = registerBlock("peachgrove_leaves",
            () -> aelvenDecayLeavesBlock(azaleaProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);


    public static final RegistryObject<Block> OAK_BEAMS = registerBlock("oak_beams",
            () -> logBlock(MaterialColor.WOOD, MaterialColor.PODZOL), null);
    public static final RegistryObject<Block> STRIPPED_OAK_BEAMS = registerBlock("stripped_oak_beams",
            () -> logBlock(MaterialColor.WOOD, MaterialColor.WOOD), null);

    public static final RegistryObject<Block> SPRUCE_BEAMS = registerBlock("spruce_beams",
            () -> logBlock(MaterialColor.PODZOL, MaterialColor.COLOR_BROWN), null);
    public static final RegistryObject<Block> STRIPPED_SPRUCE_BEAMS = registerBlock("stripped_spruce_beams",
            () -> logBlock(MaterialColor.PODZOL, MaterialColor.PODZOL), null);

    public static final RegistryObject<Block> BIRCH_BEAMS = registerBlock("birch_beams",
            () -> logBlock(MaterialColor.SAND, MaterialColor.QUARTZ), null);
    public static final RegistryObject<Block> STRIPPED_BIRCH_BEAMS = registerBlock("stripped_birch_beams",
            () -> logBlock(MaterialColor.SAND, MaterialColor.SAND), null);

    public static final RegistryObject<Block> JUNGLE_BEAMS = registerBlock("jungle_beams",
            () -> logBlock(MaterialColor.DIRT, MaterialColor.PODZOL), null);
    public static final RegistryObject<Block> STRIPPED_JUNGLE_BEAMS = registerBlock("stripped_jungle_beams",
            () -> logBlock(MaterialColor.DIRT, MaterialColor.DIRT), null);

    public static final RegistryObject<Block> ACACIA_BEAMS = registerBlock("acacia_beams",
            () -> logBlock(MaterialColor.COLOR_ORANGE, MaterialColor.STONE), null);
    public static final RegistryObject<Block> STRIPPED_ACACIA_BEAMS = registerBlock("stripped_acacia_beams",
            () -> logBlock(MaterialColor.COLOR_ORANGE, MaterialColor.COLOR_ORANGE), null);

    public static final RegistryObject<Block> DARK_OAK_BEAMS = registerBlock("dark_oak_beams",
            () -> logBlock(MaterialColor.COLOR_BROWN, MaterialColor.COLOR_BROWN), null);
    public static final RegistryObject<Block> STRIPPED_DARK_OAK_BEAMS = registerBlock("stripped_dark_oak_beams",
            () -> logBlock(MaterialColor.COLOR_BROWN, MaterialColor.COLOR_BROWN), null);

    public static final RegistryObject<Block> MANGROVE_BEAMS = registerBlock("mangrove_beams",
            () -> logBlock(MaterialColor.COLOR_RED, MaterialColor.PODZOL), null);
    public static final RegistryObject<Block> STRIPPED_MANGROVE_BEAMS = registerBlock("stripped_mangrove_beams",
            () -> logBlock(MaterialColor.COLOR_RED, MaterialColor.COLOR_RED), null);

    public static final RegistryObject<Block> CRIMSON_BEAMS = registerBlock("crimson_beams",
            () -> stemBlock(MaterialColor.CRIMSON_STEM, MaterialColor.CRIMSON_STEM), null);
    public static final RegistryObject<Block> STRIPPED_CRIMSON_BEAMS = registerBlock("stripped_crimson_beams",
            () -> stemBlock(MaterialColor.CRIMSON_STEM, MaterialColor.CRIMSON_STEM), null);

    public static final RegistryObject<Block> WARPED_BEAMS = registerBlock("warped_beams",
            () -> stemBlock(MaterialColor.WARPED_STEM, MaterialColor.WARPED_STEM), null);
    public static final RegistryObject<Block> STRIPPED_WARPED_BEAMS = registerBlock("stripped_warped_beams",
            () -> stemBlock(MaterialColor.WARPED_STEM, MaterialColor.WARPED_STEM), null);


    public static final RegistryObject<Block> MUDDY_MULCH = registerBlock("muddy_mulch",
            () -> new MulchBlock(mudProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> MULCH = registerBlock("mulch",
            () -> new MulchBlock(mulchProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> MULCH_CARPET = registerBlock("mulch_carpet",
            () -> new CarpetBlock(mulchProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MULCHY_GRASS = registerBlock("mulchy_grass",
            () -> new MulchyGrassBlock(replaceablePlantProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> TALL_MULCHY_GRASS = registerBlock("tall_mulchy_grass",
            () -> new DoublePlantBlock(replaceablePlantProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<MangroveRootsBlock> PEACHGROVE_ROOTS = registerBlock("peachgrove_roots",
            () -> new MangroveRootsBlock(BlockBehaviour.Properties.copy(Blocks.MANGROVE_ROOTS)), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<RotatedPillarBlock> MUDDY_PEACHGROVE_ROOTS = registerBlock("muddy_peachgrove_roots",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.MUDDY_MANGROVE_ROOTS)), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MUDDY_PEACHGROVE_LEAVES = registerBlock("muddy_peachgrove_leaves",
            () -> new Block(muddyLeafProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> MUDSTONE = registerBlock("mudstone",
            () -> new Block(mudstoneProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MUDSTONE_STAIRS = registerBlock("mudstone_stairs",
            () -> new StairBlock(() -> ModBlocks.MUDSTONE.get().defaultBlockState(),
                    mudstoneProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MUDSTONE_SLAB = registerBlock("mudstone_slab",
            () -> new SlabBlock(mudstoneProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MUDSTONE_WALL = registerBlock("mudstone_wall",
            () -> new WallBlock(mudstoneProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> MUDSTONE_BRICKS = registerBlock("mudstone_bricks",
            () -> new Block(mudstoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MUDSTONE_BRICK_STAIRS = registerBlock("mudstone_brick_stairs",
            () -> new StairBlock(() -> ModBlocks.MUDSTONE_BRICKS.get().defaultBlockState(),
                    mudstoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MUDSTONE_BRICK_SLAB = registerBlock("mudstone_brick_slab",
            () -> new SlabBlock(mudstoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MUDSTONE_BRICK_WALL = registerBlock("mudstone_brick_wall",
            () -> new WallBlock(mudstoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> CRACKED_MUDSTONE_BRICKS = registerBlock("cracked_mudstone_bricks",
            () -> new Block(mudstoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CRACKED_MUDSTONE_BRICK_STAIRS = registerBlock("cracked_mudstone_brick_stairs",
            () -> new StairBlock(() -> ModBlocks.CRACKED_MUDSTONE_BRICKS.get().defaultBlockState(),
                    mudstoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CRACKED_MUDSTONE_BRICK_SLAB = registerBlock("cracked_mudstone_brick_slab",
            () -> new SlabBlock(mudstoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CRACKED_MUDSTONE_BRICK_WALL = registerBlock("cracked_mudstone_brick_wall",
            () -> new WallBlock(mudstoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> MULCHY_MUDSTONE_BRICKS = registerBlock("mulchy_mudstone_bricks",
            () -> new Block(mudstoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MULCHY_MUDSTONE_BRICK_STAIRS = registerBlock("mulchy_mudstone_brick_stairs",
            () -> new StairBlock(() -> ModBlocks.MULCHY_MUDSTONE_BRICKS.get().defaultBlockState(),
                    mudstoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MULCHY_MUDSTONE_BRICK_SLAB = registerBlock("mulchy_mudstone_brick_slab",
            () -> new SlabBlock(mudstoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MULCHY_MUDSTONE_BRICK_WALL = registerBlock("mulchy_mudstone_brick_wall",
            () -> new WallBlock(mudstoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);


    public static final RegistryObject<Block> MOONSHADE = registerBlock("moonshade",
            () -> new TallFlowerBlock(flowerProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> SILVER_SPRING = registerBlock("silver_spring",
            () -> new TallFlowerBlock(flowerProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> PEACH_LILAC = registerBlock("peach_lilac",
            () -> new TallFlowerBlock(flowerProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);


    public static final RegistryObject<Block> COBBLED_CHITTA = registerBlock("cobbled_chitta",
            () -> new Block(chittaProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> DUSTY_COBBLED_CHITTA = registerBlock("dusty_cobbled_chitta",
            () -> new Block(chittaProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CRACKED_CHITTA = registerBlock("cracked_chitta",
            () -> new Block(chittaProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHITTA = registerBlock("chitta",
            () -> new Block(chittaProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHITTA_BRICKS = registerBlock("chitta_bricks",
            () -> new Block(chittaBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CARVED_CHITTA_BRICKS = registerBlock("carved_chitta_bricks",
            () -> new Block(chittaBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CARVED_CHITTA_RUNED_BRICKS = registerBlock("carved_chitta_runed_bricks",
            () -> new Block(chittaBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHITTA_TILES = registerBlock("chitta_tiles",
            () -> new Block(chittaTileProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CARVED_CHITTA_AELVEN_TILES = registerBlock("carved_chitta_aelven_tiles",
            () -> new Block(chittaTileProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CARVED_CHITTA_ENCHANT_TILES = registerBlock("carved_chitta_enchant_tiles",
            () -> new Block(chittaTileProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CARVED_CHITTA_GLYPHIC_TILES = registerBlock("carved_chitta_glyphic_tiles",
            () -> new Block(chittaTileProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CARVED_CHITTA_NORSE_TILES = registerBlock("carved_chitta_norse_tiles",
            () -> new Block(chittaTileProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CARVED_CHITTA_AELVEN_RUNED_TILES = registerBlock("carved_chitta_aelven_runed_tiles",
            () -> new Block(chittaTileProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CARVED_CHITTA_ENCHANT_RUNED_TILES = registerBlock("carved_chitta_enchant_runed_tiles",
            () -> new Block(chittaTileProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CARVED_CHITTA_GLYPHIC_RUNED_TILES = registerBlock("carved_chitta_glyphic_runed_tiles",
            () -> new Block(chittaTileProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CARVED_CHITTA_NORSE_RUNED_TILES = registerBlock("carved_chitta_norse_runed_tiles",
            () -> new Block(chittaTileProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHITTA_LARGE_TILE = registerBlock("chitta_large_tile",
            () -> new Block(chittaPolishedProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CARVED_CHITTA_LARGE_TILE = registerBlock("carved_chitta_large_tile",
            () -> new Block(chittaPolishedProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CARVED_CHITTA_RUNED_LARGE_TILE = registerBlock("carved_chitta_runed_large_tile",
            () -> new Block(chittaPolishedProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> KILN = registerBlock("kiln",
            () -> new KilnBlock(chittaKilnProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> IDOL_TABLE = registerBlock("idol_table",
            () -> new IdolTableBlock(woodProperties), null);


    public static final RegistryObject<Block> COBBLED_CHITTA_STAIRS = registerBlock("cobbled_chitta_stairs",
            () -> new StairBlock(() -> ModBlocks.COBBLED_CHITTA.get().defaultBlockState(),
                    chittaProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> COBBLED_CHITTA_SLAB = registerBlock("cobbled_chitta_slab",
            () -> new SlabBlock(chittaProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> COBBLED_CHITTA_WALL = registerBlock("cobbled_chitta_wall",
            () -> new WallBlock(chittaProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> CRACKED_CHITTA_STAIRS = registerBlock("cracked_chitta_stairs",
            () -> new StairBlock(() -> ModBlocks.CRACKED_CHITTA.get().defaultBlockState(),
                    chittaProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CRACKED_CHITTA_SLAB = registerBlock("cracked_chitta_slab",
            () -> new SlabBlock(chittaProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CRACKED_CHITTA_WALL = registerBlock("cracked_chitta_wall",
            () -> new WallBlock(chittaProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> CHITTA_STAIRS = registerBlock("chitta_stairs",
            () -> new StairBlock(() -> ModBlocks.CHITTA.get().defaultBlockState(),
                    chittaProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHITTA_SLAB = registerBlock("chitta_slab",
            () -> new SlabBlock(chittaProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHITTA_WALL = registerBlock("chitta_wall",
            () -> new WallBlock(chittaProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> CHITTA_BRICK_STAIRS = registerBlock("chitta_brick_stairs",
            () -> new StairBlock(() -> ModBlocks.CHITTA_BRICKS.get().defaultBlockState(),
                    chittaBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHITTA_BRICK_SLAB = registerBlock("chitta_brick_slab",
            () -> new SlabBlock(chittaBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHITTA_BRICK_WALL = registerBlock("chitta_brick_wall",
            () -> new WallBlock(chittaBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> CHITTA_TILE_STAIRS = registerBlock("chitta_tile_stairs",
            () -> new StairBlock(() -> ModBlocks.CHITTA_TILES.get().defaultBlockState(),
                    chittaTileProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHITTA_TILE_SLAB = registerBlock("chitta_tile_slab",
            () -> new SlabBlock(chittaTileProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHITTA_TILE_WALL = registerBlock("chitta_tile_wall",
            () -> new WallBlock(chittaTileProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> CHITTA_LARGE_TILE_STAIRS = registerBlock("chitta_large_tile_stairs",
            () -> new StairBlock(() -> ModBlocks.CHITTA_LARGE_TILE.get().defaultBlockState(),
                    chittaPolishedProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHITTA_LARGE_TILE_SLAB = registerBlock("chitta_large_tile_slab",
            () -> new SlabBlock(chittaPolishedProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHITTA_LARGE_TILE_WALL = registerBlock("chitta_large_tile_wall",
            () -> new WallBlock(chittaPolishedProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> CHITTA_PLAYING_TILES = registerBlockWithItemProperties("chitta_playing_tiles",
            () -> new PlayingTilesBlock(chittaPolishedProperties), new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_BLOCKS)
                    .stacksTo(16));


    public static final RegistryObject<Block> ELERIUM_ORE = registerBlock("elerium_ore",
            () -> new DropExperienceBlock(stoneOreProperties, UniformInt.of(3, 7)), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> DEEPSLATE_ELERIUM_ORE = registerBlock("deepslate_elerium_ore",
            () -> new DropExperienceBlock(deepslateOreProperties, UniformInt.of(3, 7)), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> ELERIUM_BLOCK = registerBlock("elerium_block",
            () -> new Block(eleriumBlockProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> ELERUTITE_BLOCK = registerBlock("elerutite_block",
            () -> new Block(elerutiteBlockProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);


    public static final RegistryObject<Block> CHALK = registerBlock("chalk",
            () -> new Block(chalkProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHALK_STAIRS = registerBlock("chalk_stairs",
            () -> new StairBlock(() -> ModBlocks.CHALK.get().defaultBlockState(),
                    chalkProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHALK_SLAB = registerBlock("chalk_slab",
            () -> new SlabBlock(chalkProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHALK_WALL = registerBlock("chalk_wall",
            () -> new WallBlock(chalkProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHALK_BRICKS = registerBlock("chalk_bricks",
            () -> new Block(chalkProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHALK_BRICK_STAIRS = registerBlock("chalk_brick_stairs",
            () -> new StairBlock(() -> ModBlocks.CHALK_BRICKS.get().defaultBlockState(),
                    chalkProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHALK_BRICK_SLAB = registerBlock("chalk_brick_slab",
            () -> new SlabBlock(chalkProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHALK_BRICK_WALL = registerBlock("chalk_brick_wall",
            () -> new WallBlock(chalkProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> POLISHED_CHALK = registerBlock("polished_chalk",
            () -> new Block(chalkProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> POLISHED_CHALK_STAIRS = registerBlock("polished_chalk_stairs",
            () -> new StairBlock(() -> ModBlocks.POLISHED_CHALK.get().defaultBlockState(),
                    chalkProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> POLISHED_CHALK_SLAB = registerBlock("polished_chalk_slab",
            () -> new SlabBlock(chalkProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> POLISHED_CHALK_WALL = registerBlock("polished_chalk_wall",
            () -> new WallBlock(chalkProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> CHALK_DUST_BLOCK = registerBlock("chalk_dust_block",
            () -> new ChalkDustBlock(chalkDustProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHALK_DUST = registerBlock("chalk_dust",
            () -> new SnowLayerBlock(chalkDustLayerProperties),  ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> CHIPSTONE_GRAVEL = registerBlock("chipstone_gravel",
            () -> new FallingBlock(gravelProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<RotatedPillarBlock> CHIPSTONE = registerBlock("chipstone",
            () -> new RotatedPillarBlock(chipstoneProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHIPSTONE_STAIRS = registerBlock("chipstone_stairs",
            () -> new StairBlock(() -> ModBlocks.CHIPSTONE.get().defaultBlockState(),
                    chipstoneProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHIPSTONE_SLAB = registerBlock("chipstone_slab",
            () -> new SlabBlock(chipstoneProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHIPSTONE_WALL = registerBlock("chipstone_wall",
            () -> new WallBlock(chipstoneProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> POLISHED_CHIPSTONE = registerBlock("polished_chipstone",
            () -> new Block(chipstoneProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> POLISHED_CHIPSTONE_STAIRS = registerBlock("polished_chipstone_stairs",
            () -> new StairBlock(() -> ModBlocks.POLISHED_CHIPSTONE.get().defaultBlockState(),
                    chipstoneProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> POLISHED_CHIPSTONE_SLAB = registerBlock("polished_chipstone_slab",
            () -> new SlabBlock(chipstoneProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> POLISHED_CHIPSTONE_WALL = registerBlock("polished_chipstone_wall",
            () -> new WallBlock(chipstoneProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> DRYSTONE = registerBlock("drystone",
            () -> new Block(drystoneProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> DRYSTONE_STAIRS = registerBlock("drystone_stairs",
            () -> new StairBlock(() -> ModBlocks.DRYSTONE.get().defaultBlockState(),
                    drystoneProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> DRYSTONE_SLAB = registerBlock("drystone_slab",
            () -> new SlabBlock(drystoneProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> DRYSTONE_WALL = registerBlock("drystone_wall",
            () -> new WallBlock(drystoneProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> MOSSY_DRYSTONE = registerBlock("mossy_drystone",
            () -> new Block(drystoneProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MOSSY_DRYSTONE_STAIRS = registerBlock("mossy_drystone_stairs",
            () -> new StairBlock(() -> ModBlocks.DRYSTONE.get().defaultBlockState(),
                    drystoneProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MOSSY_DRYSTONE_SLAB = registerBlock("mossy_drystone_slab",
            () -> new SlabBlock(drystoneProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MOSSY_DRYSTONE_WALL = registerBlock("mossy_drystone_wall",
            () -> new WallBlock(drystoneProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<RotatedPillarBlock> DRYSTONE_BRICKS = registerBlock("drystone_bricks",
            () -> new RotatedPillarBlock(drystoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> DRYSTONE_BRICK_STAIRS = registerBlock("drystone_brick_stairs",
            () -> new StairBlock(() -> ModBlocks.DRYSTONE_BRICKS.get().defaultBlockState(),
                    drystoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> DRYSTONE_BRICK_SLAB = registerBlock("drystone_brick_slab",
            () -> new SlabBlock(drystoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> DRYSTONE_BRICK_WALL = registerBlock("drystone_brick_wall",
            () -> new WallBlock(drystoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<RotatedPillarBlock> CRACKED_DRYSTONE_BRICKS = registerBlock("cracked_drystone_bricks",
            () -> new RotatedPillarBlock(drystoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CRACKED_DRYSTONE_BRICK_STAIRS = registerBlock("cracked_drystone_brick_stairs",
            () -> new StairBlock(() -> ModBlocks.DRYSTONE_BRICKS.get().defaultBlockState(),
                    drystoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CRACKED_DRYSTONE_BRICK_SLAB = registerBlock("cracked_drystone_brick_slab",
            () -> new SlabBlock(drystoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CRACKED_DRYSTONE_BRICK_WALL = registerBlock("cracked_drystone_brick_wall",
            () -> new WallBlock(drystoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> MOSSY_DRYSTONE_BRICKS = registerBlock("mossy_drystone_bricks",
            () -> new Block(drystoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MOSSY_DRYSTONE_BRICK_STAIRS = registerBlock("mossy_drystone_brick_stairs",
            () -> new StairBlock(() -> ModBlocks.DRYSTONE_BRICKS.get().defaultBlockState(),
                    drystoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MOSSY_DRYSTONE_BRICK_SLAB = registerBlock("mossy_drystone_brick_slab",
            () -> new SlabBlock(drystoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MOSSY_DRYSTONE_BRICK_WALL = registerBlock("mossy_drystone_brick_wall",
            () -> new WallBlock(drystoneBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> SILVER_ORE = registerBlock("silver_ore",
            () -> new Block(stoneOreProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore",
            () -> new Block(deepslateOreProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> RAW_SILVER_BLOCK = registerBlock("raw_silver_block",
            () -> new Block(rawSilverBlockProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> SILVER_BLOCK = registerBlock("silver_block",
            () -> new Block(silverBlockProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> SHINGLES = registerBlock("shingles",
            () -> new Block(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> SHINGLE_STAIRS = registerBlock("shingle_stairs",
            () -> new StairBlock(() -> ModBlocks.SHINGLES.get().defaultBlockState(),
                    shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> SHINGLE_SLAB = registerBlock("shingle_slab",
            () -> new SlabBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> SHINGLE_WALL = registerBlock("shingle_wall",
            () -> new WallBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> WHITE_SHINGLES = registerBlock("white_shingles",
            () -> new Block(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> WHITE_SHINGLE_STAIRS = registerBlock("white_shingle_stairs",
            () -> new StairBlock(() -> ModBlocks.WHITE_SHINGLES.get().defaultBlockState(),
                    shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> WHITE_SHINGLE_SLAB = registerBlock("white_shingle_slab",
            () -> new SlabBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> WHITE_SHINGLE_WALL = registerBlock("white_shingle_wall",
            () -> new WallBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> ORANGE_SHINGLES = registerBlock("orange_shingles",
            () -> new Block(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> ORANGE_SHINGLE_STAIRS = registerBlock("orange_shingle_stairs",
            () -> new StairBlock(() -> ModBlocks.ORANGE_SHINGLES.get().defaultBlockState(),
                    shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> ORANGE_SHINGLE_SLAB = registerBlock("orange_shingle_slab",
            () -> new SlabBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> ORANGE_SHINGLE_WALL = registerBlock("orange_shingle_wall",
            () -> new WallBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> MAGENTA_SHINGLES = registerBlock("magenta_shingles",
            () -> new Block(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MAGENTA_SHINGLE_STAIRS = registerBlock("magenta_shingle_stairs",
            () -> new StairBlock(() -> ModBlocks.MAGENTA_SHINGLES.get().defaultBlockState(),
                    shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MAGENTA_SHINGLE_SLAB = registerBlock("magenta_shingle_slab",
            () -> new SlabBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MAGENTA_SHINGLE_WALL = registerBlock("magenta_shingle_wall",
            () -> new WallBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> LIGHT_BLUE_SHINGLES = registerBlock("light_blue_shingles",
            () -> new Block(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> LIGHT_BLUE_SHINGLE_STAIRS = registerBlock("light_blue_shingle_stairs",
            () -> new StairBlock(() -> ModBlocks.LIGHT_BLUE_SHINGLES.get().defaultBlockState(),
                    shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> LIGHT_BLUE_SHINGLE_SLAB = registerBlock("light_blue_shingle_slab",
            () -> new SlabBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> LIGHT_BLUE_SHINGLE_WALL = registerBlock("light_blue_shingle_wall",
            () -> new WallBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> YELLOW_SHINGLES = registerBlock("yellow_shingles",
            () -> new Block(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> YELLOW_SHINGLE_STAIRS = registerBlock("yellow_shingle_stairs",
            () -> new StairBlock(() -> ModBlocks.YELLOW_SHINGLES.get().defaultBlockState(),
                    shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> YELLOW_SHINGLE_SLAB = registerBlock("yellow_shingle_slab",
            () -> new SlabBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> YELLOW_SHINGLE_WALL = registerBlock("yellow_shingle_wall",
            () -> new WallBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> LIME_SHINGLES = registerBlock("lime_shingles",
            () -> new Block(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> LIME_SHINGLE_STAIRS = registerBlock("lime_shingle_stairs",
            () -> new StairBlock(() -> ModBlocks.LIME_SHINGLES.get().defaultBlockState(),
                    shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> LIME_SHINGLE_SLAB = registerBlock("lime_shingle_slab",
            () -> new SlabBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> LIME_SHINGLE_WALL = registerBlock("lime_shingle_wall",
            () -> new WallBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> PINK_SHINGLES = registerBlock("pink_shingles",
            () -> new Block(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> PINK_SHINGLE_STAIRS = registerBlock("pink_shingle_stairs",
            () -> new StairBlock(() -> ModBlocks.PINK_SHINGLES.get().defaultBlockState(),
                    shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> PINK_SHINGLE_SLAB = registerBlock("pink_shingle_slab",
            () -> new SlabBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> PINK_SHINGLE_WALL = registerBlock("pink_shingle_wall",
            () -> new WallBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> GRAY_SHINGLES = registerBlock("gray_shingles",
            () -> new Block(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> GRAY_SHINGLE_STAIRS = registerBlock("gray_shingle_stairs",
            () -> new StairBlock(() -> ModBlocks.GRAY_SHINGLES.get().defaultBlockState(),
                    shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> GRAY_SHINGLE_SLAB = registerBlock("gray_shingle_slab",
            () -> new SlabBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> GRAY_SHINGLE_WALL = registerBlock("gray_shingle_wall",
            () -> new WallBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> LIGHT_GRAY_SHINGLES = registerBlock("light_gray_shingles",
            () -> new Block(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> LIGHT_GRAY_SHINGLE_STAIRS = registerBlock("light_gray_shingle_stairs",
            () -> new StairBlock(() -> ModBlocks.LIGHT_GRAY_SHINGLES.get().defaultBlockState(),
                    shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> LIGHT_GRAY_SHINGLE_SLAB = registerBlock("light_gray_shingle_slab",
            () -> new SlabBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> LIGHT_GRAY_SHINGLE_WALL = registerBlock("light_gray_shingle_wall",
            () -> new WallBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> CYAN_SHINGLES = registerBlock("cyan_shingles",
            () -> new Block(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CYAN_SHINGLE_STAIRS = registerBlock("cyan_shingle_stairs",
            () -> new StairBlock(() -> ModBlocks.CYAN_SHINGLES.get().defaultBlockState(),
                    shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CYAN_SHINGLE_SLAB = registerBlock("cyan_shingle_slab",
            () -> new SlabBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CYAN_SHINGLE_WALL = registerBlock("cyan_shingle_wall",
            () -> new WallBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> PURPLE_SHINGLES = registerBlock("purple_shingles",
            () -> new Block(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> PURPLE_SHINGLE_STAIRS = registerBlock("purple_shingle_stairs",
            () -> new StairBlock(() -> ModBlocks.PURPLE_SHINGLES.get().defaultBlockState(),
                    shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> PURPLE_SHINGLE_SLAB = registerBlock("purple_shingle_slab",
            () -> new SlabBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> PURPLE_SHINGLE_WALL = registerBlock("purple_shingle_wall",
            () -> new WallBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> BLUE_SHINGLES = registerBlock("blue_shingles",
            () -> new Block(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> BLUE_SHINGLE_STAIRS = registerBlock("blue_shingle_stairs",
            () -> new StairBlock(() -> ModBlocks.BLUE_SHINGLES.get().defaultBlockState(),
                    shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> BLUE_SHINGLE_SLAB = registerBlock("blue_shingle_slab",
            () -> new SlabBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> BLUE_SHINGLE_WALL = registerBlock("blue_shingle_wall",
            () -> new WallBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> BROWN_SHINGLES = registerBlock("brown_shingles",
            () -> new Block(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> BROWN_SHINGLE_STAIRS = registerBlock("brown_shingle_stairs",
            () -> new StairBlock(() -> ModBlocks.BROWN_SHINGLES.get().defaultBlockState(),
                    shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> BROWN_SHINGLE_SLAB = registerBlock("brown_shingle_slab",
            () -> new SlabBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> BROWN_SHINGLE_WALL = registerBlock("brown_shingle_wall",
            () -> new WallBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> GREEN_SHINGLES = registerBlock("green_shingles",
            () -> new Block(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> GREEN_SHINGLE_STAIRS = registerBlock("green_shingle_stairs",
            () -> new StairBlock(() -> ModBlocks.GREEN_SHINGLES.get().defaultBlockState(),
                    shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> GREEN_SHINGLE_SLAB = registerBlock("green_shingle_slab",
            () -> new SlabBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> GREEN_SHINGLE_WALL = registerBlock("green_shingle_wall",
            () -> new WallBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> RED_SHINGLES = registerBlock("red_shingles",
            () -> new Block(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> RED_SHINGLE_STAIRS = registerBlock("red_shingle_stairs",
            () -> new StairBlock(() -> ModBlocks.RED_SHINGLES.get().defaultBlockState(),
                    shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> RED_SHINGLE_SLAB = registerBlock("red_shingle_slab",
            () -> new SlabBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> RED_SHINGLE_WALL = registerBlock("red_shingle_wall",
            () -> new WallBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> BLACK_SHINGLES = registerBlock("black_shingles",
            () -> new Block(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> BLACK_SHINGLE_STAIRS = registerBlock("black_shingle_stairs",
            () -> new StairBlock(() -> ModBlocks.BLACK_SHINGLES.get().defaultBlockState(),
                    shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> BLACK_SHINGLE_SLAB = registerBlock("black_shingle_slab",
            () -> new SlabBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> BLACK_SHINGLE_WALL = registerBlock("black_shingle_wall",
            () -> new WallBlock(shingleProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> CHIPSTONE_LOG = registerBlock("chipstone_log",
            () -> stemBlock(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHIPSTONE_LEAVES = registerBlock("chipstone_leaves",
            () -> new Block(stoneLeafProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HANGING_CHIPSTONE_LEAVES = registerBlock("hanging_chipstone_leaves",
            () -> new HangingRootsBlock(BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL).sound(SoundType.MANGROVE_ROOTS)), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHIPSTONE_GRASS = registerBlock("chipstone_grass",
            () -> new StoneGrassBlock(stonePlantProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHIPSTONE_BUSH = registerBlock("chipstone_bush",
            () -> new StoneDoublePlantBlock(stonePlantProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHIPSTONE_BLADES = registerBlock("chipstone_blades",
            () -> new StoneGrassBlock(stonePlantProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> TALL_CHIPSTONE_BLADES = registerBlock("tall_chipstone_blades",
            () -> new StoneDoublePlantBlock(stonePlantProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);



    public static final RegistryObject<Block> GIANT_BRAIN_CORAL = registerBlock("giant_brain_coral",
            () -> new TallSeagrassBlock(coralProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> DEAD_GIANT_BRAIN_CORAL = registerBlock("dead_giant_brain_coral",
            () -> new TallSeagrassBlock(coralProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> GIANT_TUBE_CORAL = registerBlock("giant_tube_coral",
            () -> new TallSeagrassBlock(coralProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> DEAD_GIANT_TUBE_CORAL = registerBlock("dead_giant_tube_coral",
            () -> new TallSeagrassBlock(coralProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);


    public static final RegistryObject<Block> GIANT_FIRE_CORAL = registerBlock("giant_fire_coral",
            () -> new TallSeagrassBlock(coralProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> DEAD_GIANT_FIRE_CORAL = registerBlock("dead_giant_fire_coral",
            () -> new TallSeagrassBlock(coralProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);


    public static final RegistryObject<Block> GIANT_HORN_CORAL = registerBlock("giant_horn_coral",
            () -> new TallSeagrassBlock(coralProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> DEAD_GIANT_HORN_CORAL = registerBlock("dead_giant_horn_coral",
            () -> new TallSeagrassBlock(coralProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);


    public static final RegistryObject<Block> GIANT_BUBBLE_CORAL = registerBlock("giant_bubble_coral",
            () -> new TallSeagrassBlock(coralProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> DEAD_GIANT_BUBBLE_CORAL = registerBlock("dead_giant_bubble_coral",
            () -> new TallSeagrassBlock(coralProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> HYDROSATIN_STEM = registerBlock("hydrosatin_stem",
            () -> new ModLogBlock(BlockBehaviour.Properties.of(Material.WOOD, (state) ->
                    state.getValue(RotatedPillarBlock.AXIS) ==
                            Direction.Axis.Y ? MaterialColor.COLOR_GRAY : MaterialColor.COLOR_BLUE)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD)), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HYDROSATIN_HYPHAE = registerBlock("hydrosatin_hyphae",
            () -> new ModLogBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD)), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HYDROSATIN_BEAMS = registerBlock("hydrosatin_beams",
            () -> new ModLogBlock(BlockBehaviour.Properties.of(Material.WOOD, (state) ->
                            state.getValue(RotatedPillarBlock.AXIS) ==
                                    Direction.Axis.Y ? MaterialColor.COLOR_GRAY : MaterialColor.COLOR_BLUE)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD)), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_HYDROSATIN_STEM = registerBlock("stripped_hydrosatin_stem",
            () -> new ModLogBlock(BlockBehaviour.Properties.of(Material.WOOD, (state) ->
                            state.getValue(RotatedPillarBlock.AXIS) ==
                                    Direction.Axis.Y ? MaterialColor.COLOR_GRAY : MaterialColor.COLOR_BLUE)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD)), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_HYDROSATIN_HYPHAE = registerBlock("stripped_hydrosatin_hyphae",
            () -> new ModLogBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_GRAY)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD)), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_HYDROSATIN_BEAMS = registerBlock("stripped_hydrosatin_beams",
            () -> new ModLogBlock(BlockBehaviour.Properties.of(Material.WOOD, (state) ->
                            state.getValue(RotatedPillarBlock.AXIS) ==
                                    Direction.Axis.Y ? MaterialColor.COLOR_GRAY : MaterialColor.COLOR_BLUE)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD)), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HYDROSATIN_PLANKS = registerBlock("hydrosatin_planks",
            () -> flammableBlock(woodProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HYDROSATIN_STAIRS = registerBlock("hydrosatin_stairs",
            () -> flammableStairBlock(() -> ModBlocks.HYDROSATIN_PLANKS.get().defaultBlockState(), woodProperties),
            ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HYDROSATIN_SLAB = registerBlock("hydrosatin_slab",
            () -> flammableSlabBlock(woodProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> HYDROSATIN_FENCE = registerBlock("hydrosatin_fence",
            () -> flammableFenceBlock(woodProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HYDROSATIN_FENCE_GATE = registerBlock("hydrosatin_fence_gate",
            () -> flammableFenceGateBlock(woodProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HYDROSATIN_BUTTON = registerBlock("hydrosatin_button",
            () -> new WoodButtonBlock(woodPropertiesNoCollide), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HYDROSATIN_PRESSURE_PLATE = registerBlock("hydrosatin_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, woodProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HYDROSATIN_DOOR = registerBlock("hydrosatin_door",
            () -> flammableDoorBlock(woodPropertiesNoOcclude), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HYDROSATIN_TRAPDOOR = registerBlock("hydrosatin_trapdoor",
            () -> flammableTrapDoorBlock(woodPropertiesNoOcclude), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HYDROSATIN_WALL_SIGN = registerBlockWithoutBlockItem("hydrosatin_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), ModWoodTypes.HYDROSATIN));
    public static final RegistryObject<Block> HYDROSATIN_SIGN = registerBlockWithoutBlockItem("hydrosatin_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), ModWoodTypes.HYDROSATIN));
    public static final RegistryObject<Block> HYDROSATIN_FUNGUS = registerBlock("hydrosatin_fungus",
            () -> new HydrosatinFungusFeatureBlock(flowerProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HYDROSATIN_CAP = registerBlock("hydrosatin_cap",
            () -> new TransparentWaterloggableBlock(shroomProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> GLIMMERSATIN = registerBlock("glimmersatin",
            () -> new TransparentWaterloggableBlock(BlockBehaviour.Properties.copy(HYDROSATIN_CAP.get()).sound(SoundType.SHROOMLIGHT).lightLevel((state) -> {
                return 9;
            })), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> SILVERSATIN = registerBlock("silversatin",
            () -> new Block(BlockBehaviour.Properties.of(SILVER_BLOCK.get().defaultBlockState().getMaterial()).lightLevel((state) -> {
                return 15;
            })), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> GLIMMERSATIN_TORCH = registerBlockWithoutBlockItem("glimmersatin_torch",
            () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> {
                return 8;
            }).sound(SoundType.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
    public static final RegistryObject<Block> GLIMMERSATIN_WALL_TORCH = registerBlockWithoutBlockItem("glimmersatin_wall_torch",
            () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> {
                return 8;
            }).sound(SoundType.WOOD).dropsLike(GLIMMERSATIN_TORCH.get()), ParticleTypes.SOUL_FIRE_FLAME));
    public static final RegistryObject<Block> SILVERSATIN_TORCH = registerBlockWithoutBlockItem("silversatin_torch",
            () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> {
                return 14;
            }).sound(SoundType.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
    public static final RegistryObject<Block> SILVERSATIN_WALL_TORCH = registerBlockWithoutBlockItem("silversatin_wall_torch",
            () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> {
                return 14;
            }).sound(SoundType.WOOD).dropsLike(SILVERSATIN_TORCH.get()), ParticleTypes.SOUL_FIRE_FLAME));
    public static final RegistryObject<Block> ELERIUM_TORCH = registerBlockWithoutBlockItem("elerium_torch",
            () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> {
                return 12;
            }).sound(SoundType.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
    public static final RegistryObject<Block> ELERIUM_WALL_TORCH = registerBlockWithoutBlockItem("elerium_wall_torch",
            () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> {
                return 12;
            }).sound(SoundType.WOOD).dropsLike(ELERIUM_TORCH.get()), ParticleTypes.SOUL_FIRE_FLAME));

    public static final RegistryObject<Block> GLIMMERSATIN_LANTERN = registerBlock("glimmersatin_lantern",
            () -> new LanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).lightLevel((state) -> {
                return 9;
            })), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> SILVERSATIN_LANTERN = registerBlock("silversatin_lantern",
            () -> new LanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).lightLevel((state) -> {
                return 15;
            })), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> ELERIUM_LANTERN = registerBlock("elerium_lantern",
            () -> new LanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).lightLevel((state) -> {
                return 13;
            })), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> GLIMMERSATIN_CAMPFIRE = registerBlock("glimmersatin_campfire",
            () -> new CampfireBlock(false, 1,
                    BlockBehaviour.Properties.copy(Blocks.CAMPFIRE).lightLevel((state) -> {
                        return state.getValue(BlockStateProperties.LIT) ? 9 : 5;
                    })), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> SILVERSATIN_CAMPFIRE = registerBlock("silversatin_campfire",
            () -> new CampfireBlock(false, 2,
                    BlockBehaviour.Properties.copy(Blocks.CAMPFIRE).lightLevel((state) -> {
                        return state.getValue(BlockStateProperties.LIT) ? 15 : 9;
                    })), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> ELERIUM_CAMPFIRE = registerBlock("elerium_campfire",
            () -> new CampfireBlock(false, 1,
                    BlockBehaviour.Properties.copy(Blocks.CAMPFIRE).lightLevel((state) -> {
                        return state.getValue(BlockStateProperties.LIT) ? 13 : 0;
                    })), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HYDROJADE = registerBlock("hydrojade",
            () -> new Block(hydrojadeProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HYDROJADE_STAIRS = registerBlock("hydrojade_stairs",
            () -> new StairBlock(() -> ModBlocks.HYDROJADE.get().defaultBlockState(),
                    hydrojadeProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HYDROJADE_SLAB = registerBlock("hydrojade_slab",
            () -> new SlabBlock(hydrojadeProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HYDROJADE_WALL = registerBlock("hydrojade_wall",
            () -> new WallBlock(hydrojadeProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> COBBLED_HYDROJADE = registerBlock("cobbled_hydrojade",
            () -> new Block(hydrojadeProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> COBBLED_HYDROJADE_STAIRS = registerBlock("cobbled_hydrojade_stairs",
            () -> new StairBlock(() -> ModBlocks.COBBLED_HYDROJADE.get().defaultBlockState(),
                    hydrojadeProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> COBBLED_HYDROJADE_SLAB = registerBlock("cobbled_hydrojade_slab",
            () -> new SlabBlock(hydrojadeProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> COBBLED_HYDROJADE_WALL = registerBlock("cobbled_hydrojade_wall",
            () -> new WallBlock(hydrojadeProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> FUNGAL_COBBLED_HYDROJADE = registerBlock("fungal_cobbled_hydrojade",
            () -> new Block(hydrojadeProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> FUNGAL_COBBLED_HYDROJADE_STAIRS = registerBlock("fungal_cobbled_hydrojade_stairs",
            () -> new StairBlock(() -> ModBlocks.FUNGAL_COBBLED_HYDROJADE.get().defaultBlockState(),
                    hydrojadeProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> FUNGAL_COBBLED_HYDROJADE_SLAB = registerBlock("fungal_cobbled_hydrojade_slab",
            () -> new SlabBlock(hydrojadeProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> FUNGAL_COBBLED_HYDROJADE_WALL = registerBlock("fungal_cobbled_hydrojade_wall",
            () -> new WallBlock(hydrojadeProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> HYDROJADE_TILES = registerBlock("hydrojade_tiles",
            () -> new Block(hydrojadeTileProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HYDROJADE_TILE_STAIRS = registerBlock("hydrojade_tile_stairs",
            () -> new StairBlock(() -> ModBlocks.HYDROJADE_TILES.get().defaultBlockState(),
                    hydrojadeTileProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HYDROJADE_TILE_SLAB = registerBlock("hydrojade_tile_slab",
            () -> new SlabBlock(hydrojadeTileProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HYDROJADE_TILE_WALL = registerBlock("hydrojade_tile_wall",
            () -> new WallBlock(hydrojadeTileProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> HYDROJADE_BRICKS = registerBlock("hydrojade_bricks",
            () -> new Block(hydrojadeBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HYDROJADE_BRICK_STAIRS = registerBlock("hydrojade_brick_stairs",
            () -> new StairBlock(() -> ModBlocks.HYDROJADE_BRICKS.get().defaultBlockState(),
                    hydrojadeBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HYDROJADE_BRICK_SLAB = registerBlock("hydrojade_brick_slab",
            () -> new SlabBlock(hydrojadeBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> HYDROJADE_BRICK_WALL = registerBlock("hydrojade_brick_wall",
            () -> new WallBlock(hydrojadeBrickProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> POLISHED_HYDROJADE = registerBlock("polished_hydrojade",
            () -> new Block(hydrojadeProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> POLISHED_HYDROJADE_STAIRS = registerBlock("polished_hydrojade_stairs",
            () -> new StairBlock(() -> ModBlocks.POLISHED_HYDROJADE.get().defaultBlockState(),
                    hydrojadeProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> POLISHED_HYDROJADE_SLAB = registerBlock("polished_hydrojade_slab",
            () -> new SlabBlock(hydrojadeProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> POLISHED_HYDROJADE_WALL = registerBlock("polished_hydrojade_wall",
            () -> new WallBlock(hydrojadeProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> HYDROJADE_PILLAR = registerBlock("hydrojade_pillar",
            () -> new RotatedPillarBlock(hydrojadeProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> JADEGRASS = registerBlock("jadegrass",
            () -> new SeagrassBlock(coralProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> TALL_JADEGRASS = registerBlockWithoutBlockItem("tall_jadegrass",
            () -> new ModSeagrassBlock(coralProperties, JADEGRASS.get()));
    public static final RegistryObject<Block> HYDROSATIN_SPROUTS = registerBlock("hydrosatin_sprouts",
            () -> new SeagrassBlock(coralProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> TALL_HYDROSATIN_SPROUTS = registerBlock("tall_hydrosatin_sprouts",
            () -> new ModSeagrassBlock(coralProperties, HYDROSATIN_SPROUTS.get()), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static  final RegistryObject<Block> JADEGRASS_NYLIUM = registerBlock("jadegrass_nylium",
            () -> new Block(jadegrassNyliumProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static  final RegistryObject<Block> HYDROCELIUM = registerBlock("hydrocelium",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.MYCELIUM)), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static  final RegistryObject<Block> JADEWART = registerBlock("jadewart",
            () -> new Block(mulchProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static  final RegistryObject<Block> JADEWART_CARPET = registerBlock("jadewart_carpet",
            () -> new CarpetBlock(mulchProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> LUMINESCENT_KELP = registerBlock("luminescent_kelp",
            () -> new KelpBlock(coralProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> LUMINESCENT_KELP_PLANT = registerBlockWithoutBlockItem("luminescent_kelp_plant",
            () -> new ModKelpPlantBlock(coralProperties, LUMINESCENT_KELP.get()));



    private static ModLogBlock logBlock(MaterialColor c1, MaterialColor c2) {
        return new ModLogBlock(BlockBehaviour.Properties.of(Material.WOOD, (state) ->
                state.getValue(RotatedPillarBlock.AXIS) ==
                        Direction.Axis.Y ? c1 : c2).strength(2.0F, 3.0F).sound(SoundType.WOOD));
    }
    private static RotatedPillarBlock stemBlock(MaterialColor c1, MaterialColor c2) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.NETHER_WOOD, (state) ->
                state.getValue(RotatedPillarBlock.AXIS) ==
                        Direction.Axis.Y ? c1 : c2).strength(2.0F, 3.0F).sound(SoundType.WOOD));
    }

    private static Block flammableBlock(BlockBehaviour.Properties properties, int flammability, int spreadSpeed) {
        return new Block(properties) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return flammability; }
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return spreadSpeed; }
        };
    }
    private static Block flammableBlock(BlockBehaviour.Properties properties) {
        return flammableBlock(properties, 5, 5);
    }

    private static StairBlock flammableStairBlock(Supplier<BlockState> defaultState, BlockBehaviour.Properties properties, int flammability, int spreadSpeed) {
        return new StairBlock(defaultState, properties) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return flammability; }
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return spreadSpeed; }
        };
    }
    private static StairBlock flammableStairBlock(Supplier<BlockState> defaultState, BlockBehaviour.Properties properties) {
        return flammableStairBlock(defaultState, properties, 5, 5);
    }

    private static SlabBlock flammableSlabBlock(BlockBehaviour.Properties properties, int flammability, int spreadSpeed) {
        return new SlabBlock(properties) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return flammability; }
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return spreadSpeed; }
        };
    }
    private static SlabBlock flammableSlabBlock(BlockBehaviour.Properties properties) {
        return flammableSlabBlock(properties, 5, 5);
    }

    private static FenceBlock flammableFenceBlock(BlockBehaviour.Properties properties, int flammability, int spreadSpeed) {
        return new FenceBlock(properties) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return flammability; }
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return spreadSpeed; }
        };
    }
    private static FenceBlock flammableFenceBlock(BlockBehaviour.Properties properties) {
        return flammableFenceBlock(properties, 5, 5);
    }

    private static FenceGateBlock flammableFenceGateBlock(BlockBehaviour.Properties properties, int flammability, int spreadSpeed) {
        return new FenceGateBlock(properties) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return flammability; }
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return spreadSpeed; }
        };
    }
    private static FenceGateBlock flammableFenceGateBlock(BlockBehaviour.Properties properties) {
        return flammableFenceGateBlock(properties, 5, 5);
    }

    private static DoorBlock flammableDoorBlock(BlockBehaviour.Properties properties, int flammability, int spreadSpeed) {
        return new DoorBlock(properties) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return flammability; }
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return spreadSpeed; }
        };
    }
    private static DoorBlock flammableDoorBlock(BlockBehaviour.Properties properties) {
        return flammableDoorBlock(properties, 5, 5);
    }

    private static TrapDoorBlock flammableTrapDoorBlock(BlockBehaviour.Properties properties, int flammability, int spreadSpeed) {
        return new TrapDoorBlock(properties) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return flammability; }
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return spreadSpeed; }
        };
    }
    private static TrapDoorBlock flammableTrapDoorBlock(BlockBehaviour.Properties properties) {
        return flammableTrapDoorBlock(properties, 5, 5);
    }

    private static LeavesBlock flammableLeavesBlock(BlockBehaviour.Properties properties, int flammability, int spreadSpeed) {
        return new LeavesBlock(properties) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return flammability; }
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return spreadSpeed; }
        };
    }
    private static LeavesBlock flammableLeavesBlock(BlockBehaviour.Properties properties) {
        return flammableLeavesBlock(properties, 5, 5);
    }
    private static LeavesBlock aelvenDecayLeavesBlock(BlockBehaviour.Properties properties) {
        return new LeavesBlock(properties) {
            @Override
            protected boolean decaying(BlockState p_221386_) {
                return false;
            }
        };
    }


    
    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithItemProperties(String name, Supplier<T> block,
                                                                                       Item.Properties properties) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItemWithProperties(name, toReturn, properties);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItemWithProperties(String name, RegistryObject<T> block,
                                                                                          Item.Properties properties) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
