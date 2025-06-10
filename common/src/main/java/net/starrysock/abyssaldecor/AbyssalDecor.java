package net.starrysock.abyssaldecor;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrarManager;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.starrysock.abyssaldecor.platform.Services;
import net.starrysock.abyssaldecor.registry.*;

import java.util.function.Supplier;

public class AbyssalDecor {
    public static final String MOD_ID = "abyssaldecor";

    // We can use this if we don't want to use DeferredRegister
    public static final Supplier<RegistrarManager> REGISTRIES = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

    // Registering a new creative tab
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registries.BLOCK);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);

    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(MOD_ID, Registries.PAINTING_VARIANT);

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(MOD_ID, Registries.SOUND_EVENT);

    public static final DeferredRegister<DamageType> DAMAGE_TYPES = DeferredRegister.create(MOD_ID, Registries.DAMAGE_TYPE);
    
    public static void init() {

        AbyssalDecorBlocks.register();
        AbyssalDecorItems.register();
        AbyssalPaintings.register();
        AbyssalCreativeTabs.register();
        AbyssalDecorSounds.register();

        System.out.println(Services.PLATFORM.getConfigDirectory().toAbsolutePath().normalize());
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID,path);
    }
}
