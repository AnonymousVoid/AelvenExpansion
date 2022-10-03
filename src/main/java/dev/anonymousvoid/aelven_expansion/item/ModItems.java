package dev.anonymousvoid.aelven_expansion.item;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.item.custom.ChiselItem;
import dev.anonymousvoid.aelven_expansion.item.custom.EleriumPasteItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AelvenExpansion.MODID);

    public static final RegistryObject<Item> CHITTA_CHISEL = ITEMS.register("chitta_chisel",
            () -> new ChiselItem(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB).durability(512)));

    public static final RegistryObject<Item> ELERIUM = ITEMS.register("elerium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));
    public static final RegistryObject<Item> ELERIUM_PASTE = ITEMS.register("elerium_paste",
            () -> new EleriumPasteItem(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB).durability(8)));

    public static final RegistryObject<Item> CHALK_DUST = ITEMS.register("chalk_dust_item",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
