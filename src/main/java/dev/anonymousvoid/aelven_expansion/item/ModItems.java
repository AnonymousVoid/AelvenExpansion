package dev.anonymousvoid.aelven_expansion.item;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.item.custom.ChiselItem;
import dev.anonymousvoid.aelven_expansion.item.custom.EleriumPasteItem;
import dev.anonymousvoid.aelven_expansion.item.custom.SilverPumiceItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AelvenExpansion.MODID);

    public static final RegistryObject<Item> CHITTA_CHISEL = ITEMS.register("chitta_chisel",
            () -> new ChiselItem(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS).durability(512)));

    public static final RegistryObject<Item> ELERIUM = ITEMS.register("elerium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> ELERIUM_PASTE = ITEMS.register("elerium_paste",
            () -> new EleriumPasteItem(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS).durability(32)));

    public static final RegistryObject<Item> ELERUTITE_INGOT = ITEMS.register("elerutite_ingot_item",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));



    public static final RegistryObject<Item> CHALK_DUST = ITEMS.register("chalk_dust_item",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));

    public static final RegistryObject<Item> RAW_SILVER = ITEMS.register("raw_silver_item",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot_item",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> SILVER_NUGGET = ITEMS.register("silver_nugget_item",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> SILVER_DUST = ITEMS.register("silver_dust_item",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> SILVER_PUMICE = ITEMS.register("silver_pumice_item",
            () -> new SilverPumiceItem(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS).durability(64)));

    public static final RegistryObject<Item> SILVER_SWORD = ITEMS.register("silver_sword",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> SILVER_DAGGER = ITEMS.register("silver_dagger",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> SILVER_SHOVEL = ITEMS.register("silver_shovel",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> SILVER_PICKAXE = ITEMS.register("silver_pickaxe",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> SILVER_AXE = ITEMS.register("silver_axe",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> SILVER_HOE = ITEMS.register("silver_hoe",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));

    public static final RegistryObject<Item> SILVER_HELMET = ITEMS.register("silver_helmet",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> SILVER_CHESTPLATE = ITEMS.register("silver_chestplate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> SILVER_LEGGINGS = ITEMS.register("silver_leggings",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));
    public static final RegistryObject<Item> SILVER_BOOTS = ITEMS.register("silver_boots",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB_ITEMS)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
