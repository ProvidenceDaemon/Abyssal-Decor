package net.starrysock.abyssaldecor.client;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;

import java.util.function.BiConsumer;

public class AbyssalDecorClient {

    public static void setup(BiConsumer<Block, RenderType> renderTypes) {
        renderTypes.accept(AbyssalDecorBlocks.SOLAR_ROD.get(),RenderType.cutout());
    }
}
