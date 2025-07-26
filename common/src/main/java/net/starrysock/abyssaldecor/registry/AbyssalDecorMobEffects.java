package net.starrysock.abyssaldecor.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.starrysock.abyssaldecor.AbyssalDecor;
import net.starrysock.abyssaldecor.SporeProtectionMobEffect;

public class AbyssalDecorMobEffects {

    public static final RegistrySupplier<MobEffect> SPORE_PROTECTION = AbyssalDecor.MOB_EFFECTS.register("spore_protection",
            () -> new SporeProtectionMobEffect(MobEffectCategory.BENEFICIAL, 0x00ffcb));

    public static void register() {
        AbyssalDecor.MOB_EFFECTS.register();
    }

}
