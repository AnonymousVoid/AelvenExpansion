package dev.anonymousvoid.aelven_expansion.item;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.block.ModBlocks;
import dev.anonymousvoid.aelven_expansion.entity.vehicle.ModBoat;
import dev.anonymousvoid.aelven_expansion.item.custom.*;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.item.RecordItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AelvenExpansion.MODID);

    public static final RegistryObject<Item> CHITTA_CHISEL = ITEMS.register("chitta_chisel",
            () -> new ChiselItem(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS).durability(512)));

    public static final RegistryObject<Item> ELERIUM = ITEMS.register("elerium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> SLIME_PASTE = ITEMS.register("slime_paste",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> ELERIUM_PASTE = ITEMS.register("elerium_paste",
            () -> new EleriumPasteItem(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS).durability(32)));

    public static final RegistryObject<Item> ELERUTITE_INGOT = ITEMS.register("elerutite_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));


    public static final RegistryObject<Item> CHALK_DUST = ITEMS.register("chalk_dust_item",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));

    public static final RegistryObject<Item> RAW_SILVER = ITEMS.register("raw_silver",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> SILVER_NUGGET = ITEMS.register("silver_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> SILVER_DUST = ITEMS.register("silver_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> SILVER_PUMICE = ITEMS.register("silver_pumice",
            () -> new SilverPumiceItem(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS).durability(64)));

    public static final RegistryObject<SwordItem> SILVER_SWORD = ITEMS.register("silver_sword",
            () -> new SwordItem(Tiers.SILVER, 3, -2.4F, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<SwordItem> SILVER_DAGGER = ITEMS.register("silver_dagger",
            () -> new SwordItem(Tiers.SILVER, -1, 4.0F, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<ShovelItem> SILVER_SHOVEL = ITEMS.register("silver_shovel",
            () -> new ShovelItem(Tiers.SILVER, 1.5F, -3.0F, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<PickaxeItem> SILVER_PICKAXE = ITEMS.register("silver_pickaxe",
            () -> new PickaxeItem(Tiers.SILVER, 1, -2.8F, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<AxeItem> SILVER_AXE = ITEMS.register("silver_axe",
            () -> new AxeItem(Tiers.SILVER, 6.0F, -3.1F, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<HoeItem> SILVER_HOE = ITEMS.register("silver_hoe",
            () -> new HoeItem(Tiers.SILVER, -2, -1.0F, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));

    public static final RegistryObject<ArmorItem> SILVER_HELMET = ITEMS.register("silver_helmet",
            () -> new ArmorItem(ArmorTiers.SILVER, EquipmentSlot.HEAD, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<ArmorItem> SILVER_CHESTPLATE = ITEMS.register("silver_chestplate",
            () -> new ArmorItem(ArmorTiers.SILVER, EquipmentSlot.CHEST, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<ArmorItem> SILVER_LEGGINGS = ITEMS.register("silver_leggings",
            () -> new ArmorItem(ArmorTiers.SILVER, EquipmentSlot.LEGS, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<ArmorItem> SILVER_BOOTS = ITEMS.register("silver_boots",
            () -> new ArmorItem(ArmorTiers.SILVER, EquipmentSlot.FEET, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));

    public static final RegistryObject<Item> MOON_FIR_SIGN = ITEMS.register("moon_fir_sign",
            () -> new SignItem(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS).stacksTo(16),
                    ModBlocks.MOON_FIR_SIGN.get(), ModBlocks.MOON_FIR_WALL_SIGN.get()));
    public static final RegistryObject<Item> SILVERBLOOD_SIGN = ITEMS.register("silverblood_sign",
            () -> new SignItem(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS).stacksTo(16),
                    ModBlocks.SILVERBLOOD_SIGN.get(), ModBlocks.SILVERBLOOD_WALL_SIGN.get()));
    public static final RegistryObject<Item> PEACHGROVE_SIGN = ITEMS.register("peachgrove_sign",
            () -> new SignItem(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS).stacksTo(16),
                    ModBlocks.PEACHGROVE_SIGN.get(), ModBlocks.PEACHGROVE_WALL_SIGN.get()));

    public static final RegistryObject<ModBoatItem> MOON_FIR_BOAT = ITEMS.register("moon_fir_boat",
            () -> new ModBoatItem(false, ModBoat.Type.MOON_FIR,
                    new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS).stacksTo(1)));
    public static final RegistryObject<ModBoatItem> MOON_FIR_CHEST_BOAT = ITEMS.register("moon_fir_chest_boat",
            () -> new ModBoatItem(true, ModBoat.Type.MOON_FIR,
                    new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS).stacksTo(1)));
    public static final RegistryObject<ModBoatItem> SILVERBLOOD_BOAT = ITEMS.register("silverblood_boat",
            () -> new ModBoatItem(false, ModBoat.Type.SILVERBLOOD,
                    new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS).stacksTo(1)));
    public static final RegistryObject<ModBoatItem> SILVERBLOOD_CHEST_BOAT = ITEMS.register("silverblood_chest_boat",
            () -> new ModBoatItem(true, ModBoat.Type.SILVERBLOOD,
                    new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS).stacksTo(1)));
    public static final RegistryObject<ModBoatItem> PEACHGROVE_BOAT = ITEMS.register("peachgrove_boat",
            () -> new ModBoatItem(false, ModBoat.Type.PEACHGROVE,
                    new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS).stacksTo(1)));
    public static final RegistryObject<ModBoatItem> PEACHGROVE_CHEST_BOAT = ITEMS.register("peachgrove_chest_boat",
            () -> new ModBoatItem(true, ModBoat.Type.PEACHGROVE,
                    new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS).stacksTo(1)));



    public static final RegistryObject<Item> QUESTION_MARK = ITEMS.register("question_mark",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> QUEST_MARK = ITEMS.register("quest_mark",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MUSIC_DISC = ITEMS.register("music_disc",
            () -> new RecordItem(7, ModSounds.MUSIC_DISC, new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.MOD_TAB_ITEMS).rarity(Rarity.RARE), 203));

    public static class Tiers {
        public static final Tier SILVER = new ForgeTier(2,200,6.5F,2.75F,25,null, () -> Ingredient.of(ModItems.SILVER_INGOT.get()));
    }

    public static class ArmorTiers {
        public static final ArmorMaterial SILVER = new ModArmorMaterial(
                "silver",
                1,
                new int[] { 3, 7, 8, 3 },
                25,
                SoundEvents.ARMOR_EQUIP_IRON,
                0.0f,
                0.0f,
                () -> Ingredient.of(ModItems.SILVER_INGOT.get())
        );
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
