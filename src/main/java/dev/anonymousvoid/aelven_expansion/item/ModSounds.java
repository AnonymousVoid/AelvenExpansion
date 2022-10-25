package dev.anonymousvoid.aelven_expansion.item;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, AelvenExpansion.MODID);

    public static RegistryObject<SoundEvent> MUSIC_DISC = registerSoundEvent("music_disc");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(AelvenExpansion.MODID, name)));

    }
    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
