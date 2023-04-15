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
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
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
    private static final BlockBehaviour.Properties plantProperties = BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ);
    private static final BlockBehaviour.Properties pottedPlantProperties = BlockBehaviour.Properties.copy(Blocks.FLOWER_POT);

    private static final BlockBehaviour.Properties chittaProperties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE).strength(2.0F, 6.0F);
    private static final BlockBehaviour.Properties chittaBrickProperties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE_BRICKS).strength(2.5F, 6.5F);
    private static final BlockBehaviour.Properties chittaTileProperties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE_TILES).strength(2.5F, 6.5F);
    private static final BlockBehaviour.Properties chittaPolishedProperties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.POLISHED_DEEPSLATE).strength(2.5F, 6.5F);
    private static final BlockBehaviour.Properties chittaKilnProperties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.POLISHED_DEEPSLATE).strength(3.5F, 8.0F);

    private static final BlockBehaviour.Properties chalkProperties = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_WHITE).requiresCorrectToolForDrops().sound(SoundType.CALCITE).strength(0.6F, 0.5F);
    private static final BlockBehaviour.Properties chipstoneProperties = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().sound(SoundType.TUFF).strength(1.8F, 6.0F);
    private static final BlockBehaviour.Properties drystoneProperties = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE).strength(1.0F, 6.0F);
    private static final BlockBehaviour.Properties drystoneBrickProperties = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE_BRICKS).strength(1.5F, 6.5F);

    private static final BlockBehaviour.Properties sandProperties = BlockBehaviour.Properties.copy(Blocks.SAND);
    private static final BlockBehaviour.Properties dustProperties = BlockBehaviour.Properties.copy(Blocks.SAND).requiresCorrectToolForDrops();
    private static final BlockBehaviour.Properties shingleProperties = BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK).strength(1.75F, 1.5F);

    private static final BlockBehaviour.Properties eleriumBlockProperties = BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(6.0F, 240.0F);
    private static final BlockBehaviour.Properties elerutiteBlockProperties = BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK).requiresCorrectToolForDrops().strength(6.0F, 240.0F);
    private static final BlockBehaviour.Properties rawSilverBlockProperties = BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(5.0F, 120.0F);
    private static final BlockBehaviour.Properties silverBlockProperties = BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.NETHERITE_BLOCK).requiresCorrectToolForDrops().strength(5.0F, 120.0F);
    private static final BlockBehaviour.Properties stoneOreProperties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.STONE).strength(3.0F, 3.0F);
    private static final BlockBehaviour.Properties deepslateOreProperties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE).strength(4.5F, 3.0F);


    // BLOCKS
    public static final RegistryObject<Block> MOON_FIR_LOG = registerBlock("moon_fir_log",
            () -> log(MaterialColor.COLOR_GRAY, MaterialColor.COLOR_BLUE), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MOON_FIR_WOOD = registerBlock("moon_fir_wood",
            () -> log(MaterialColor.COLOR_BLUE, MaterialColor.COLOR_BLUE), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> MOON_FIR_BEAMS = registerBlock("moon_fir_beams",
            () -> log(MaterialColor.COLOR_GRAY, MaterialColor.COLOR_BLUE), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_MOON_FIR_LOG = registerBlock("stripped_moon_fir_log",
            () -> log(MaterialColor.COLOR_GRAY, MaterialColor.COLOR_BLUE), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_MOON_FIR_WOOD = registerBlock("stripped_moon_fir_wood",
            () -> log(MaterialColor.COLOR_GRAY, MaterialColor.COLOR_GRAY), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_MOON_FIR_BEAMS = registerBlock("stripped_moon_fir_beams",
            () -> log(MaterialColor.COLOR_GRAY, MaterialColor.COLOR_BLUE), ModCreativeModeTab.MOD_TAB_BLOCKS);
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
            () -> new SaplingBlock(new MoonFirTreeGrower(), plantProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> POTTED_MOON_FIR_SAPLING = registerBlockWithoutBlockItem("potted_moon_fir_sapling",
            () -> new FlowerPotBlock(null, MOON_FIR_SAPLING, pottedPlantProperties));
    public static final RegistryObject<Block> MOON_BLOOM = registerBlock("moon_bloom",
            () -> new FlowerBlock(MobEffects.NIGHT_VISION, 10, plantProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> POTTED_MOON_BLOOM = registerBlockWithoutBlockItem("potted_moon_bloom",
            () -> new FlowerPotBlock(null, MOON_BLOOM, pottedPlantProperties));
    public static final RegistryObject<Block> MOON_FIR_LEAVES = registerBlock("moon_fir_leaves",
            () -> flammableLeavesBlock(azaleaProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);


    public static final RegistryObject<Block> SILVERBLOOD_LOG = registerBlock("silverblood_log",
            () -> log(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> SILVERBLOOD_WOOD = registerBlock("silverblood_wood",
            () -> log(MaterialColor.COLOR_PINK, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> SILVERBLOOD_BEAMS = registerBlock("silverblood_beams",
            () -> log(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_SILVERBLOOD_LOG = registerBlock("stripped_silverblood_log",
            () -> log(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_SILVERBLOOD_WOOD = registerBlock("stripped_silverblood_wood",
            () -> log(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_LIGHT_GRAY), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_SILVERBLOOD_BEAMS = registerBlock("stripped_silverblood_beams",
            () -> log(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB_BLOCKS);
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
            () -> new SaplingBlock(new SilverbloodTreeGrower(), plantProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> POTTED_SILVERBLOOD_SAPLING = registerBlockWithoutBlockItem("potted_silverblood_sapling",
            () -> new FlowerPotBlock(null, SILVERBLOOD_SAPLING, pottedPlantProperties));
    public static final RegistryObject<Block> SILVER_MARIGOLD = registerBlock("silver_marigold",
            () -> new FlowerBlock(MobEffects.MOVEMENT_SPEED, 10, plantProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> POTTED_SILVER_MARIGOLD = registerBlockWithoutBlockItem("potted_silver_marigold",
            () -> new FlowerPotBlock(null, SILVER_MARIGOLD, pottedPlantProperties));
    public static final RegistryObject<Block> SILVERBLOOD_LEAVES = registerBlock("silverblood_leaves",
            () -> flammableLeavesBlock(azaleaProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);


    public static final RegistryObject<Block> PEACHGROVE_LOG = registerBlock("peachgrove_log",
            () -> log(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> PEACHGROVE_WOOD = registerBlock("peachgrove_wood",
            () -> log(MaterialColor.COLOR_PINK, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> PEACHGROVE_BEAMS = registerBlock("peachgrove_beams",
            () -> log(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_PEACHGROVE_LOG = registerBlock("stripped_peachgrove_log",
            () -> log(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_PEACHGROVE_WOOD = registerBlock("stripped_peachgrove_wood",
            () -> log(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_LIGHT_GRAY), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_PEACHGROVE_BEAMS = registerBlock("stripped_peachgrove_beams",
            () -> log(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB_BLOCKS);
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
            () -> new SaplingBlock(new PeachgroveTreeGrower(), plantProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> POTTED_PEACHGROVE_SAPLING = registerBlockWithoutBlockItem("potted_peachgrove_sapling",
            () -> new FlowerPotBlock(null, PEACHGROVE_SAPLING, pottedPlantProperties));
    public static final RegistryObject<Block> PEACH_LAVENDER = registerBlock("peach_lavender",
            () -> new FlowerBlock(MobEffects.ABSORPTION, 10, plantProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> POTTED_PEACH_LAVENDER = registerBlockWithoutBlockItem("potted_peach_lavender",
            () -> new FlowerPotBlock(null, PEACH_LAVENDER, pottedPlantProperties));
    public static final RegistryObject<Block> PEACHGROVE_LEAVES = registerBlock("peachgrove_leaves",
            () -> flammableLeavesBlock(azaleaProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);



    public static final RegistryObject<Block> MOONSHADE = registerBlock("moonshade",
            () -> new TallFlowerBlock(plantProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> SILVER_SPRING = registerBlock("silver_spring",
            () -> new TallFlowerBlock(plantProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> PEACH_LILAC = registerBlock("peach_lilac",
            () -> new TallFlowerBlock(plantProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);


    public static final RegistryObject<Block> COBBLED_CHITTA = registerBlock("cobbled_chitta",
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
            () -> new IdolTableBlock(woodProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);


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
            () -> new FallingBlock(sandProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);
    public static final RegistryObject<Block> CHALK_DUST = registerBlock("chalk_dust",
            () -> new SnowLayerBlock(dustProperties),  ModCreativeModeTab.MOD_TAB_BLOCKS);

    public static final RegistryObject<Block> CHIPSTONE_GRAVEL = registerBlock("chipstone_gravel",
            () -> new FallingBlock(sandProperties), ModCreativeModeTab.MOD_TAB_BLOCKS);

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



    private static ModLogBlock log(MaterialColor c1, MaterialColor c2) {
        return new ModLogBlock(BlockBehaviour.Properties.of(Material.WOOD, (state) ->
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
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
