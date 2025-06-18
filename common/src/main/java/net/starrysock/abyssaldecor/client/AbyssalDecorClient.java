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

        renderTypes.accept(AbyssalDecorBlocks.BULKHEAD_LAMP.get(),RenderType.cutoutMipped());

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
        renderTypes.accept(AbyssalDecorBlocks.VELVET_BARRIER.get(),RenderType.cutoutMipped());
        renderTypes.accept(AbyssalDecorBlocks.IRON_BARRIER.get(),RenderType.cutoutMipped());

        renderTypes.accept(AbyssalDecorBlocks.RAINBOW_LAMP.get(),RenderType.translucent());

        renderTypes.accept(AbyssalDecorBlocks.STARFISH.get(),RenderType.cutoutMipped());
        renderTypes.accept(AbyssalDecorBlocks.DRIED_STARFISH.get(),RenderType.cutoutMipped());
        renderTypes.accept(AbyssalDecorBlocks.STARLIGHT.get(),RenderType.cutoutMipped());
        renderTypes.accept(AbyssalDecorBlocks.LIFE_PRESERVER.get(),RenderType.cutoutMipped());
        renderTypes.accept(AbyssalDecorBlocks.SHIP_WHEEL.get(),RenderType.cutoutMipped());

        renderTypes.accept(AbyssalDecorBlocks.NITHING_POLE.get(),RenderType.cutoutMipped());
        renderTypes.accept(AbyssalDecorBlocks.TELESCOPE.get(),RenderType.cutoutMipped());

        renderTypes.accept(AbyssalDecorBlocks.HANGING_WEB.get(),RenderType.cutoutMipped());
        renderTypes.accept(AbyssalDecorBlocks.WALL_HANGING_WEB.get(),RenderType.cutoutMipped());

        renderTypes.accept(AbyssalDecorBlocks.DANGLING_WEB.get(),RenderType.cutoutMipped());
        renderTypes.accept(AbyssalDecorBlocks.WALL_DANGLING_WEB.get(),RenderType.cutoutMipped());

        renderTypes.accept(AbyssalDecorBlocks.PRISMARINE_CRYSTAL_BLOCK.get(),RenderType.translucent());
        renderTypes.accept(AbyssalDecorBlocks.PRISMARINE_CRYSTAL_PANE.get(),RenderType.translucent());

        renderTypes.accept(AbyssalDecorBlocks.FRESNEL_BLOCK.get(),RenderType.translucent());
        renderTypes.accept(AbyssalDecorBlocks.FRESNEL_PANE.get(),RenderType.translucent());

        renderTypes.accept(AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE.get(),RenderType.translucent());
        renderTypes.accept(AbyssalDecorBlocks.CRYSTALLIZED_GLOWSTONE_PANE.get(),RenderType.translucent());

        renderTypes.accept(AbyssalDecorBlocks.FRAMED_CRYSTALLIZED_GLOWSTONE.get(),RenderType.translucent());
        renderTypes.accept(AbyssalDecorBlocks.FRAMED_CRYSTALLIZED_GLOWSTONE_PANE.get(),RenderType.translucent());

        renderTypes.accept(AbyssalDecorBlocks.VERMILION_BLOCK.get(),RenderType.translucent());
        renderTypes.accept(AbyssalDecorBlocks.VERMILION_PANE.get(),RenderType.translucent());

        renderTypes.accept(AbyssalDecorBlocks.FRAMED_VERMILION_BLOCK.get(),RenderType.translucent());
        renderTypes.accept(AbyssalDecorBlocks.FRAMED_VERMILION_PANE.get(),RenderType.translucent());

        renderTypes.accept(AbyssalDecorBlocks.WHITE_PEARL_BARS.get(),RenderType.cutoutMipped());

        renderTypes.accept(AbyssalDecorBlocks.CLAM.get(),RenderType.cutoutMipped());
        renderTypes.accept(AbyssalDecorBlocks.CLAM_WITH_PEARL.get(),RenderType.cutoutMipped());

        renderTypes.accept(AbyssalDecorBlocks.DEEPBRONZE_BARS.get(),RenderType.cutoutMipped());

        renderTypes.accept(AbyssalDecorBlocks.STARGLASS.get(),RenderType.translucent());
        renderTypes.accept(AbyssalDecorBlocks.STARGLASS_PANE.get(),RenderType.translucent());

        renderTypes.accept(AbyssalDecorBlocks.PITCHGLASS.get(),RenderType.translucent());
        renderTypes.accept(AbyssalDecorBlocks.PITCHGLASS_PANE.get(),RenderType.translucent());

        renderTypes.accept(AbyssalDecorBlocks.FRAMED_PITCHGLASS.get(),RenderType.translucent());
        renderTypes.accept(AbyssalDecorBlocks.FRAMED_PITCHGLASS_PANE.get(),RenderType.translucent());

        renderTypes.accept(AbyssalDecorBlocks.BLACK_PEARL_BARS.get(),RenderType.cutoutMipped());
        renderTypes.accept(AbyssalDecorBlocks.SCRIMSHAW.get(),RenderType.cutoutMipped());

        renderTypes.accept(AbyssalDecorBlocks.SMALL_BLOOD_CORAL_BARS.get(),RenderType.cutoutMipped());
        renderTypes.accept(AbyssalDecorBlocks.SMALL_BLOOD_CORAL_CORNER_BARS.get(),RenderType.cutoutMipped());

        renderTypes.accept(AbyssalDecorBlocks.DEEPBRONZE_SCONCE.get(),RenderType.cutoutMipped());
        renderTypes.accept(AbyssalDecorBlocks.BLOOD_CORAL_SCONCE.get(),RenderType.cutoutMipped());
        renderTypes.accept(AbyssalDecorBlocks.DULL_IRON_SCONCE.get(),RenderType.cutoutMipped());
        renderTypes.accept(AbyssalDecorBlocks.IRON_SCONCE.get(),RenderType.cutoutMipped());
        renderTypes.accept(AbyssalDecorBlocks.GOLD_SCONCE.get(),RenderType.cutoutMipped());
        renderTypes.accept(AbyssalDecorBlocks.SEABRASS_SCONCE.get(),RenderType.cutoutMipped());

        renderTypes.accept(AbyssalDecorBlocks.WHITEWOOD_PICKET_FENCE.get(),RenderType.cutoutMipped());
    }
}
