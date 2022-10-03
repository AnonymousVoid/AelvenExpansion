package dev.anonymousvoid.aelven_expansion.block;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.block.custom.KilnBlock;
import dev.anonymousvoid.aelven_expansion.block.custom.ModFlammableRotatedPillarBlock;
import dev.anonymousvoid.aelven_expansion.item.ModCreativeModeTab;
import dev.anonymousvoid.aelven_expansion.item.ModItems;
import dev.anonymousvoid.aelven_expansion.world.feature.tree.MoonFirTreeGrower;
import dev.anonymousvoid.aelven_expansion.world.feature.tree.PeachgroveTreeGrower;
import dev.anonymousvoid.aelven_expansion.world.feature.tree.SilverbloodTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
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

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, AelvenExpansion.MODID);

    // BLOCK PROPERTIES
    private static final BlockBehaviour.Properties woodProperties = BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD);
    private static final BlockBehaviour.Properties woodPropertiesNoCollide = BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noCollission();
    private static final BlockBehaviour.Properties woodPropertiesNoOcclude = BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion();
    private static final BlockBehaviour.Properties leavesProperties = BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES);
    private static final BlockBehaviour.Properties sandProperties = BlockBehaviour.Properties.copy(Blocks.SAND);
    private static final BlockBehaviour.Properties plantProperties = BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS);
    private static final BlockBehaviour.Properties stoneProperties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.STONE).strength(1.5F, 6.0F);
    private static final BlockBehaviour.Properties deepslateProperties = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE).strength(4.5F, 3.0F);


    // BLOCKS

    public static final RegistryObject<Block> MOON_FIR_LOG = registerBlock("moon_fir_log",
            () -> log(MaterialColor.COLOR_GRAY, MaterialColor.COLOR_BLUE), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> STRIPPED_MOON_FIR_LOG = registerBlock("stripped_moon_fir_log",
            () -> log(MaterialColor.COLOR_GRAY, MaterialColor.COLOR_BLUE), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> MOON_FIR_WOOD = registerBlock("moon_fir_wood",
            () -> log(MaterialColor.COLOR_BLUE, MaterialColor.COLOR_BLUE), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> STRIPPED_MOON_FIR_WOOD = registerBlock("stripped_moon_fir_wood",
            () -> log(MaterialColor.COLOR_GRAY, MaterialColor.COLOR_GRAY), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> MOON_FIR_BEAMS = registerBlock("moon_fir_beams",
            () -> log(MaterialColor.COLOR_GRAY, MaterialColor.COLOR_BLUE), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> STRIPPED_MOON_FIR_BEAMS = registerBlock("stripped_moon_fir_beams",
            () -> log(MaterialColor.COLOR_GRAY, MaterialColor.COLOR_BLUE), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> MOON_FIR_PLANKS = registerBlock("moon_fir_planks",
            () -> new Block(woodProperties) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
            }, ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> MOON_FIR_SAPLING = registerBlock("moon_fir_sapling",
            () -> new SaplingBlock(new MoonFirTreeGrower(), plantProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> MOON_FIR_LEAVES = registerBlock("moon_fir_leaves",
            () -> new LeavesBlock(leavesProperties) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 60; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 30; }
            }, ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> MOON_FIR_STAIRS = registerBlock("moon_fir_stairs",
            () -> new StairBlock(() -> ModBlocks.MOON_FIR_PLANKS.get().defaultBlockState(),
                    woodProperties) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
            }, ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> MOON_FIR_SLAB = registerBlock("moon_fir_slab",
            () -> new SlabBlock(woodProperties) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
            }, ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> MOON_FIR_FENCE = registerBlock("moon_fir_fence",
            () -> new FenceBlock(woodProperties) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
            }, ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> MOON_FIR_FENCE_GATE = registerBlock("moon_fir_fence_gate",
            () -> new FenceGateBlock(woodProperties) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
            }, ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> MOON_FIR_BUTTON = registerBlock("moon_fir_button",
            () -> new WoodButtonBlock(woodPropertiesNoCollide), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> MOON_FIR_PRESSURE_PLATE = registerBlock("moon_fir_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, woodProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> MOON_FIR_DOOR = registerBlock("moon_fir_door",
            () -> new DoorBlock(woodPropertiesNoOcclude) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
            }, ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> MOON_FIR_TRAPDOOR = registerBlock("moon_fir_trapdoor",
            () -> new TrapDoorBlock(woodPropertiesNoOcclude) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
            }, ModCreativeModeTab.MOD_TAB);
    // Moon Fir Sign

    public static final RegistryObject<Block> SILVERBLOOD_LOG = registerBlock("silverblood_log",
            () -> log(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> STRIPPED_SILVERBLOOD_LOG = registerBlock("stripped_silverblood_log",
            () -> log(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> SILVERBLOOD_WOOD = registerBlock("silverblood_wood",
            () -> log(MaterialColor.COLOR_PINK, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> STRIPPED_SILVERBLOOD_WOOD = registerBlock("stripped_silverblood_wood",
            () -> log(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_LIGHT_GRAY), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> SILVERBLOOD_BEAMS = registerBlock("silverblood_beams",
            () -> log(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> STRIPPED_SILVERBLOOD_BEAMS = registerBlock("stripped_silverblood_beams",
            () -> log(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> SILVERBLOOD_PLANKS = registerBlock("silverblood_planks",
            () -> new Block(woodProperties) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
            }, ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> SILVERBLOOD_SAPLING = registerBlock("silverblood_sapling",
            () -> new SaplingBlock(new SilverbloodTreeGrower(), plantProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> SILVERBLOOD_LEAVES = registerBlock("silverblood_leaves",
            () -> new LeavesBlock(leavesProperties) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 60; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 30; }
            }, ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> SILVERBLOOD_STAIRS = registerBlock("silverblood_stairs",
            () -> new StairBlock(() -> ModBlocks.SILVERBLOOD_PLANKS.get().defaultBlockState(),
                    woodProperties) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
            }, ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> SILVERBLOOD_SLAB = registerBlock("silverblood_slab",
            () -> new SlabBlock(woodProperties) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
            }, ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> SILVERBLOOD_FENCE = registerBlock("silverblood_fence",
            () -> new FenceBlock(woodProperties) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
            }, ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> SILVERBLOOD_FENCE_GATE = registerBlock("silverblood_fence_gate",
            () -> new FenceGateBlock(woodProperties) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
            }, ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> SILVERBLOOD_BUTTON = registerBlock("silverblood_button",
            () -> new WoodButtonBlock(woodPropertiesNoCollide), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> SILVERBLOOD_PRESSURE_PLATE = registerBlock("silverblood_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, woodProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> SILVERBLOOD_DOOR = registerBlock("silverblood_door",
            () -> new DoorBlock(woodPropertiesNoOcclude) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
            }, ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> SILVERBLOOD_TRAPDOOR = registerBlock("silverblood_trapdoor",
            () -> new TrapDoorBlock(woodPropertiesNoOcclude) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
            }, ModCreativeModeTab.MOD_TAB);
    // Silverblood Sign

    public static final RegistryObject<Block> PEACHGROVE_LOG = registerBlock("peachgrove_log",
            () -> log(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> STRIPPED_PEACHGROVE_LOG = registerBlock("stripped_peachgrove_log",
            () -> log(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> PEACHGROVE_WOOD = registerBlock("peachgrove_wood",
            () -> log(MaterialColor.COLOR_PINK, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> STRIPPED_PEACHGROVE_WOOD = registerBlock("stripped_peachgrove_wood",
            () -> log(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_LIGHT_GRAY), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> PEACHGROVE_BEAMS = registerBlock("peachgrove_beams",
            () -> log(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> STRIPPED_PEACHGROVE_BEAMS = registerBlock("stripped_peachgrove_beams",
            () -> log(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.COLOR_PINK), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> PEACHGROVE_PLANKS = registerBlock("peachgrove_planks",
            () -> new Block(woodProperties) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
            }, ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> PEACHGROVE_SAPLING = registerBlock("peachgrove_sapling",
            () -> new SaplingBlock(new PeachgroveTreeGrower(), plantProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> PEACHGROVE_LEAVES = registerBlock("peachgrove_leaves",
            () -> new LeavesBlock(leavesProperties) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 60; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 30; }
            }, ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> PEACHGROVE_STAIRS = registerBlock("peachgrove_stairs",
            () -> new StairBlock(() -> ModBlocks.PEACHGROVE_PLANKS.get().defaultBlockState(),
                    woodProperties) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
            }, ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> PEACHGROVE_SLAB = registerBlock("peachgrove_slab",
            () -> new SlabBlock(woodProperties) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
            }, ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> PEACHGROVE_FENCE = registerBlock("peachgrove_fence",
            () -> new FenceBlock(woodProperties) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
            }, ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> PEACHGROVE_FENCE_GATE = registerBlock("peachgrove_fence_gate",
            () -> new FenceGateBlock(woodProperties) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
            }, ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> PEACHGROVE_BUTTON = registerBlock("peachgrove_button",
            () -> new WoodButtonBlock(woodPropertiesNoCollide), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> PEACHGROVE_PRESSURE_PLATE = registerBlock("peachgrove_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, woodProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> PEACHGROVE_DOOR = registerBlock("peachgrove_door",
            () -> new DoorBlock(woodPropertiesNoOcclude) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
            }, ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> PEACHGROVE_TRAPDOOR = registerBlock("peachgrove_trapdoor",
            () -> new TrapDoorBlock(woodPropertiesNoOcclude) {
                @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return true; }
                @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) { return 5; }
            }, ModCreativeModeTab.MOD_TAB);
    // Peachgrove Sign


    public static final RegistryObject<Block> MOON_BLOOM = registerBlock("moon_bloom",
            () -> new TallGrassBlock(plantProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> MOONSHADE = registerBlock("moonshade",
            () -> new DoublePlantBlock(plantProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> SILVER_MARIGOLD = registerBlock("silver_marigold",
            () -> new TallGrassBlock(plantProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> SILVER_SPRING = registerBlock("silver_spring",
            () -> new DoublePlantBlock(plantProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> PEACH_LAVENDER = registerBlock("peach_lavender",
            () -> new TallGrassBlock(plantProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> PEACH_LILAC = registerBlock("peach_lilac",
            () -> new DoublePlantBlock(plantProperties), ModCreativeModeTab.MOD_TAB);


    public static final RegistryObject<Block> COBBLED_CHITTA = registerBlock("cobbled_chitta",
            () -> new Block(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CRACKED_CHITTA = registerBlock("cracked_chitta",
            () -> new Block(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CHITTA = registerBlock("chitta",
            () -> new Block(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CHITTA_BRICKS = registerBlock("chitta_bricks",
            () -> new Block(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CARVED_CHITTA_BRICKS = registerBlock("carved_chitta_bricks",
            () -> new Block(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CARVED_CHITTA_RUNED_BRICKS = registerBlock("carved_chitta_runed_bricks",
            () -> new Block(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CHITTA_TILES = registerBlock("chitta_tiles",
            () -> new Block(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CARVED_CHITTA_AELVEN_TILES = registerBlock("carved_chitta_aelven_tiles",
            () -> new Block(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CARVED_CHITTA_ENCHANT_TILES = registerBlock("carved_chitta_enchant_tiles",
            () -> new Block(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CARVED_CHITTA_GLYPHIC_TILES = registerBlock("carved_chitta_glyphic_tiles",
            () -> new Block(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CARVED_CHITTA_NORSE_TILES = registerBlock("carved_chitta_norse_tiles",
            () -> new Block(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CARVED_CHITTA_AELVEN_RUNED_TILES = registerBlock("carved_chitta_aelven_runed_tiles",
            () -> new Block(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CARVED_CHITTA_ENCHANT_RUNED_TILES = registerBlock("carved_chitta_enchant_runed_tiles",
            () -> new Block(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CARVED_CHITTA_GLYPHIC_RUNED_TILES = registerBlock("carved_chitta_glyphic_runed_tiles",
            () -> new Block(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CARVED_CHITTA_NORSE_RUNED_TILES = registerBlock("carved_chitta_norse_runed_tiles",
            () -> new Block(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CHITTA_LARGE_TILE = registerBlock("chitta_large_tile",
            () -> new Block(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CARVED_CHITTA_LARGE_TILE = registerBlock("carved_chitta_large_tile",
            () -> new Block(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CARVED_CHITTA_RUNED_LARGE_TILE = registerBlock("carved_chitta_runed_large_tile",
            () -> new Block(stoneProperties), ModCreativeModeTab.MOD_TAB);

    public static final RegistryObject<Block> KILN = registerBlock("kiln",
            () -> new KilnBlock(stoneProperties), ModCreativeModeTab.MOD_TAB);


    public static final RegistryObject<Block> COBBLED_CHITTA_STAIRS = registerBlock("cobbled_chitta_stairs",
            () -> new StairBlock(() -> ModBlocks.COBBLED_CHITTA.get().defaultBlockState(),
                    stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> COBBLED_CHITTA_SLAB = registerBlock("cobbled_chitta_slab",
            () -> new SlabBlock(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> COBBLED_CHITTA_WALL = registerBlock("cobbled_chitta_wall",
            () -> new WallBlock(stoneProperties), ModCreativeModeTab.MOD_TAB);

    public static final RegistryObject<Block> CRACKED_CHITTA_STAIRS = registerBlock("cracked_chitta_stairs",
            () -> new StairBlock(() -> ModBlocks.CRACKED_CHITTA.get().defaultBlockState(),
                    stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CRACKED_CHITTA_SLAB = registerBlock("cracked_chitta_slab",
            () -> new SlabBlock(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CRACKED_CHITTA_WALL = registerBlock("cracked_chitta_wall",
            () -> new WallBlock(stoneProperties), ModCreativeModeTab.MOD_TAB);

    public static final RegistryObject<Block> CHITTA_STAIRS = registerBlock("chitta_stairs",
            () -> new StairBlock(() -> ModBlocks.CHITTA.get().defaultBlockState(),
                    stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CHITTA_SLAB = registerBlock("chitta_slab",
            () -> new SlabBlock(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CHITTA_WALL = registerBlock("chitta_wall",
            () -> new WallBlock(stoneProperties), ModCreativeModeTab.MOD_TAB);

    public static final RegistryObject<Block> CHITTA_BRICK_STAIRS = registerBlock("chitta_brick_stairs",
            () -> new StairBlock(() -> ModBlocks.CHITTA_BRICKS.get().defaultBlockState(),
                    stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CHITTA_BRICK_SLAB = registerBlock("chitta_brick_slab",
            () -> new SlabBlock(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CHITTA_BRICK_WALL = registerBlock("chitta_brick_wall",
            () -> new WallBlock(stoneProperties), ModCreativeModeTab.MOD_TAB);

    public static final RegistryObject<Block> CHITTA_TILE_STAIRS = registerBlock("chitta_tile_stairs",
            () -> new StairBlock(() -> ModBlocks.CHITTA_TILES.get().defaultBlockState(),
                    stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CHITTA_TILE_SLAB = registerBlock("chitta_tile_slab",
            () -> new SlabBlock(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CHITTA_TILE_WALL = registerBlock("chitta_tile_wall",
            () -> new WallBlock(stoneProperties), ModCreativeModeTab.MOD_TAB);

    public static final RegistryObject<Block> CHITTA_LARGE_TILE_STAIRS = registerBlock("chitta_large_tile_stairs",
            () -> new StairBlock(() -> ModBlocks.CHITTA_LARGE_TILE.get().defaultBlockState(),
                    stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CHITTA_LARGE_TILE_SLAB = registerBlock("chitta_large_tile_slab",
            () -> new SlabBlock(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CHITTA_LARGE_TILE_WALL = registerBlock("chitta_large_tile_wall",
            () -> new WallBlock(stoneProperties), ModCreativeModeTab.MOD_TAB);


    public static final RegistryObject<Block> ELERIUM_ORE = registerBlock("elerium_ore",
            () -> new DropExperienceBlock(stoneProperties, UniformInt.of(3, 7)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> DEEPSLATE_ELERIUM_ORE = registerBlock("deepslate_elerium_ore",
            () -> new DropExperienceBlock(deepslateProperties, UniformInt.of(3, 7)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> ELERIUM_BLOCK = registerBlock("elerium_block",
            () -> new Block(stoneProperties), ModCreativeModeTab.MOD_TAB);


    public static final RegistryObject<Block> CHALK = registerBlock("chalk",
            () -> new Block(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CHALK_BRICKS = registerBlock("chalk_bricks",
            () -> new Block(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> POLISHED_CHALK = registerBlock("polished_chalk",
            () -> new Block(stoneProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CHALK_DUST_BLOCK = registerBlock("chalk_dust_block",
            () -> new FallingBlock(sandProperties), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CHALK_DUST = registerBlock("chalk_dust",
            () -> new SnowLayerBlock(sandProperties),  ModCreativeModeTab.MOD_TAB);



    private static ModFlammableRotatedPillarBlock log(MaterialColor c1, MaterialColor c2) {
        return new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, (state) ->
                state.getValue(RotatedPillarBlock.AXIS) ==
                        Direction.Axis.Y ? c1 : c2).strength(2.0F, 3.0F).sound(SoundType.WOOD));
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
