package net.starrysock.abyssaldecor.client;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;

import java.util.function.BiConsumer;

public class AbyssalDecorClient {

    public static void setup(BiConsumer<Block, RenderType> renderTypes) {

        renderTypes.accept(AbyssalDecorBlocks.AMARANTH.get(),RenderType.cutout());

        renderTypes.accept(AbyssalDecorBlocks.SOLAR_ROD.get(),RenderType.cutout());
        renderTypes.accept(AbyssalDecorBlocks.STELLAR_ROD.get(),RenderType.cutout());
        renderTypes.accept(AbyssalDecorBlocks.TERRESTRIAL_ROD.get(),RenderType.cutout());
        renderTypes.accept(AbyssalDecorBlocks.LUNAR_ROD.get(),RenderType.cutout());
        renderTypes.accept(AbyssalDecorBlocks.ETHEREAL_ROD.get(),RenderType.cutout());

        renderTypes.accept(AbyssalDecorBlocks.DAFFODIL.get(),RenderType.cutout());
        renderTypes.accept(AbyssalDecorBlocks.ASTER.get(),RenderType.cutout());
        renderTypes.accept(AbyssalDecorBlocks.SNAPLEAF.get(),RenderType.cutout());

        renderTypes.accept(AbyssalDecorBlocks.IRON_LAMP.get(),RenderType.cutoutMipped());
        renderTypes.accept(AbyssalDecorBlocks.WALL_IRON_LAMP.get(),RenderType.cutoutMipped());

        renderTypes.accept(AbyssalDecorBlocks.FLOWER_LAMP.get(),RenderType.cutoutMipped());
        renderTypes.accept(AbyssalDecorBlocks.QUARTZ_LAMP.get(),RenderType.cutoutMipped());
        renderTypes.accept(AbyssalDecorBlocks.WALL_QUARTZ_LAMP.get(),RenderType.cutoutMipped());
        renderTypes.accept(AbyssalDecorBlocks.CEILING_QUARTZ_LAMP.get(),RenderType.cutoutMipped());
        renderTypes.accept(AbyssalDecorBlocks.JADE_LAMP.get(),RenderType.cutoutMipped());
        renderTypes.accept(AbyssalDecorBlocks.WALL_JADE_LAMP.get(),RenderType.cutoutMipped());
    }
}
