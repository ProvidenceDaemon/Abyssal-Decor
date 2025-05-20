package net.starrysock.abyssaldecor.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.item.DyeColor;

import java.util.EnumMap;
import java.util.function.Consumer;
import java.util.function.Function;

public record ColorFamily<T>(EnumMap<DyeColor, RegistrySupplier<T>> map) {

    public static final DyeColor[] DYE_COLORS = DyeColor.values();

    public static <T> ColorFamily<T> createAndRegister(DeferredRegister<? super T> deferredRegister,Function<DyeColor,T> creator, String suffix) {
        EnumMap<DyeColor,RegistrySupplier<T>> m = new EnumMap<>(DyeColor.class);
        for (DyeColor color : DYE_COLORS) {
            m.put(color, deferredRegister.register(color.getName()+"_"+ suffix,() -> creator.apply(color)));
        }
        return new ColorFamily<>(m);
    }

    public void forEach(Consumer<T> consumer) {
        map.forEach((color, supplier) -> consumer.accept(supplier.get()));
    }

    public RegistrySupplier<T> getEntry(DyeColor color) {
        return map.get(color);
    }
}
