package net.starrysock.abyssaldecor.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.Shapes;
import net.starrysock.abyssaldecor.AbyssalDecor;
import net.starrysock.abyssaldecor.BarrierRibbonBlock;
import net.starrysock.abyssaldecor.MixedBlock;
import net.starrysock.abyssaldecor.block.*;
import net.starrysock.abyssaldecor.block.properties.FrostedLampBlock;
import net.starrysock.abyssaldecor.platform.Services;

import java.util.function.ToIntFunction;

public class AbyssalDecorBlocks {
    static final BlockBehaviour.StatePredicate EMISSIVE_LAMP = (state, level, pos) -> state.getValue(RedstoneLampBlock.LIT);

    //section: Lamps
    public static RegistrySupplier<Block> FRESNEL_LAMP = AbyssalDecor.BLOCKS.register("fresnel_lamp", () -> new Block(BlockBehaviour.Properties
            .copy(Blocks.GLASS).lightLevel(s ->15)));

    // Lightbulb [directional], Wall Bulb Lamp [wall mounted], Tube Lamp [tube], Iron Lamp [directional], Flower Lamp [wall mounted],
    // Frosted Lamp [directional], Quartz Lamp [directional],
    // Jade Lamp [directional], Seaglass Lamp [directional], Blaze Lamp [wall mounted], Rainbow Lamp [directional]
    public static RegistrySupplier<Block> LIGHTBULB = AbyssalDecor.BLOCKS.register("lightbulb", () -> new ToggleableDirectionalLampBlock(lamp()));
    public static RegistrySupplier<Block> WALL_BULB_LAMP = AbyssalDecor.BLOCKS.register("wall_bulb_lamp", () -> new HorizontalLampBlock(lamp(),
            Shapes.or(Block.box(5.0, 8.0, 5.0, 11.0, 14.0, 11.0), Block.box(6, 3, 0, 10, 14, 1),
                    Block.box(7.0, 0.0, 7.0, 9.0, 8.0, 9.0))));
    public static RegistrySupplier<Block> TUBE_LAMP = AbyssalDecor.BLOCKS.register("tube_lamp", () -> new TubeLampBlock(lamp().noOcclusion()
            .hasPostProcess(EMISSIVE_LAMP).emissiveRendering(EMISSIVE_LAMP)
    ));


    public static RegistrySupplier<Block> VERTICAL_TUBE_LAMP = AbyssalDecor.BLOCKS.register("vertical_tube_lamp",
            () -> new VerticalTubeLamp(lamp().noOcclusion().dropsLike(TUBE_LAMP.get())
                    .hasPostProcess(EMISSIVE_LAMP).emissiveRendering(EMISSIVE_LAMP), Block.box(0,0,14,2,16,16)));


    public static RegistrySupplier<Block> IRON_LAMP = AbyssalDecor.BLOCKS.register("iron_lamp", () ->
            new LampBlock(lamp().noOcclusion().hasPostProcess(EMISSIVE_LAMP).emissiveRendering(EMISSIVE_LAMP), Shapes.or(Block.box(6.5, 2, 6.5, 9.5, 16, 9.5), Block.box(3, 6, 3, 13, 8, 13))));
    public static RegistrySupplier<Block> WALL_IRON_LAMP = AbyssalDecor.BLOCKS.register("wall_iron_lamp",
            () -> new HorizontalLampBlock(lamp().noOcclusion().dropsLike(IRON_LAMP.get())
                    .hasPostProcess(EMISSIVE_LAMP).emissiveRendering(EMISSIVE_LAMP),
                    Shapes.or(Block.box(3.0, 6.0, 3.0, 13.0, 8.0, 13.0), Block.box(6.0, 4.0, 0.0, 10.0, 12.0, 1.0), Block.box(6.5, 2.0, 6.5, 9.5, 9.0, 9.5))));


    static BlockBehaviour.Properties lamp() {
        return BlockBehaviour.Properties.of().lightLevel(litBlockEmission(15)).strength(0.3F).sound(SoundType.GLASS);
    }

    public static RegistrySupplier<Block> FLOWER_LAMP = AbyssalDecor.BLOCKS.register("flower_lamp", () ->
            new HorizontalLampBlock(lamp().hasPostProcess(EMISSIVE_LAMP).emissiveRendering(EMISSIVE_LAMP),
                    Shapes.or(Block.box(5.0, 6.0, 5.0, 11.0, 15.0, 11.0), Block.box(6.0, 4.0, 0.0, 10.0, 12.0, 1.0))));
    public static RegistrySupplier<Block> FROSTED_LAMP = AbyssalDecor.BLOCKS.register("frosted_lamp", () -> new FrostedLampBlock(lamp()));
    public static RegistrySupplier<Block> QUARTZ_LAMP = AbyssalDecor.BLOCKS.register("quartz_lamp", () -> new
            LampBlock(lamp().hasPostProcess(EMISSIVE_LAMP).emissiveRendering(EMISSIVE_LAMP), Block.box(5, 0, 5, 11, 16, 11)));
    public static RegistrySupplier<Block> WALL_QUARTZ_LAMP = AbyssalDecor.BLOCKS.register("wall_quartz_lamp", () ->
            new HorizontalLampBlock(lamp().dropsLike(QUARTZ_LAMP.get()).hasPostProcess(EMISSIVE_LAMP).emissiveRendering(EMISSIVE_LAMP),
                    Shapes.or(Block.box(5,3,5,11,16,11),
                    Block.box(6,3,0,10,13,1))));
    public static RegistrySupplier<Block> CEILING_QUARTZ_LAMP = AbyssalDecor.BLOCKS.register("ceiling_quartz_lamp", () ->
            new HorizontalLampBlock(lamp().dropsLike(QUARTZ_LAMP.get()).hasPostProcess(EMISSIVE_LAMP).emissiveRendering(EMISSIVE_LAMP),
                    Shapes.or(Block.box(6.5, 2, 6.5, 9.5, 16, 9.5), Block.box(5, 4, 5, 11, 9, 11),
                            Block.box(2, 10, 2, 14, 12, 14))));


    public static RegistrySupplier<Block> JADE_LAMP = AbyssalDecor.BLOCKS.register("jade_lamp", () -> new HorizontalLampBlock(lamp(), null));
    public static RegistrySupplier<Block> WALL_JADE_LAMP = AbyssalDecor.BLOCKS.register("wall_jade_lamp", () -> new HorizontalLampBlock(lamp().dropsLike(JADE_LAMP.get()), null));


    public static RegistrySupplier<Block> SEAGLASS_LAMP = AbyssalDecor.BLOCKS.register("seaglass_lamp", () -> new FrostedLampBlock(lamp()));
    public static RegistrySupplier<Block> BLAZE_LAMP = AbyssalDecor.BLOCKS.register("blaze_lamp", () ->
            new BlazeLampBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).strength(4.0F, 10.0F).lightLevel(s -> 12)
                    .noOcclusion().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)));
    public static RegistrySupplier<Block> RAINBOW_LAMP = AbyssalDecor.BLOCKS.register("rainbow_lamp", () -> new DirectionalLampBlock(BlockBehaviour.Properties.of().lightLevel(s -> 15).strength(0.3F).sound(SoundType.GLASS)));
    public static RegistrySupplier<BulkheadLampBlock> BULKHEAD_LAMP = AbyssalDecor.BLOCKS.register("bulkhead_lamp", () -> new BulkheadLampBlock(lamp()));

    public static RegistrySupplier<Block> JADE_LANTERN = AbyssalDecor.BLOCKS.register("jade_lantern", () -> new LampBlock(lamp()));
    public static RegistrySupplier<Block> ABYSSAL_LANTERN = AbyssalDecor.BLOCKS.register("abyssal_lantern", () -> new LampBlock(lamp()));
    public static RegistrySupplier<IronLanternBlock> IRON_LANTERN = AbyssalDecor.BLOCKS.register("iron_lantern", () -> new IronLanternBlock(lamp()));

    public static RegistrySupplier<Block> BLOOD_LANTERN = AbyssalDecor.BLOCKS.register("blood_lantern", () -> new SingleLampBlock(lamp()));
    public static RegistrySupplier<BloodLampMultiBlock> BLOOD_LANTERN_MULTIBLOCK = AbyssalDecor.BLOCKS.register("blood_lantern_multiblock",
            () -> new BloodLampMultiBlock(lamp().dropsLike(BLOOD_LANTERN.get()),BLOOD_LANTERN.get()));


    //end section

    //section : Barriers
    public static RegistrySupplier<Block> VELVET_BARRIER = AbyssalDecor.BLOCKS.register("velvet_barrier", () -> new BarrierPoleBlock(BlockBehaviour.Properties.of()
            .sound(SoundType.LANTERN).strength(2, 10).noOcclusion().pushReaction(PushReaction.BLOCK)));
    public static RegistrySupplier<Block> IRON_BARRIER = AbyssalDecor.BLOCKS.register("iron_barrier", () -> new BarrierPoleBlock(Block.Properties.copy(Blocks.IRON_BLOCK)));
    public static RegistrySupplier<Block> ROPE_BARRIER = AbyssalDecor.BLOCKS.register("rope_barrier", () -> new BarrierPoleBlock(Block.Properties.copy(Blocks.OAK_PLANKS)));
    public static RegistrySupplier<Block> BARBED_WIRE_BARRIER = AbyssalDecor.BLOCKS.register("barbed_wire_barrier", () -> new BarrierPoleBlock(Block.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistrySupplier<BarrierRibbonBlock> VELVET_BARRIER_RIBBON = AbyssalDecor.BLOCKS.register("velvet_barrier_ribbon",() ->
            new BarrierRibbonBlock(BlockBehaviour.Properties.of().ignitedByLava().sound(SoundType.WOOL).strength(1).noOcclusion().pushReaction(PushReaction.DESTROY),AbyssalDecorBlocks.VELVET_BARRIER));
    public static final RegistrySupplier<BarrierRibbonBlock> IRON_BARRIER_RIBBON = AbyssalDecor.BLOCKS.register("iron_barrier_ribbon",() ->
            new BarrierRibbonBlock(BlockBehaviour.Properties.of().ignitedByLava().sound(SoundType.WOOL).strength(1).noOcclusion().pushReaction(PushReaction.DESTROY),AbyssalDecorBlocks.IRON_BARRIER));
    public static final RegistrySupplier<BarrierRibbonBlock> ROPE_BARRIER_RIBBON = AbyssalDecor.BLOCKS.register("rope_barrier_ribbon",() ->
            new BarrierRibbonBlock(BlockBehaviour.Properties.of().ignitedByLava().sound(SoundType.WOOL).strength(1).noOcclusion().pushReaction(PushReaction.DESTROY),AbyssalDecorBlocks.ROPE_BARRIER));
    public static final RegistrySupplier<BarrierRibbonBlock> BARBED_WIRE_RIBBON = AbyssalDecor.BLOCKS.register("barbed_wire_ribbon",() ->
            new BarrierRibbonBlock(BlockBehaviour.Properties.of().ignitedByLava().sound(SoundType.CHAIN).strength(1).noOcclusion().pushReaction(PushReaction.DESTROY),AbyssalDecorBlocks.BARBED_WIRE_BARRIER));

    //blocks
    public static final RegistrySupplier<Block> SOLAR_ROD = AbyssalDecor.BLOCKS.register("solar_rod", AbyssalDecorBlocks::rodBlock);
    public static final RegistrySupplier<Block> STELLAR_ROD = AbyssalDecor.BLOCKS.register("stellar_rod", AbyssalDecorBlocks::rodBlock);
    public static final RegistrySupplier<Block> TERRESTRIAL_ROD = AbyssalDecor.BLOCKS.register("terrestrial_rod", AbyssalDecorBlocks::rodBlock);
    public static final RegistrySupplier<Block> LUNAR_ROD = AbyssalDecor.BLOCKS.register("lunar_rod", AbyssalDecorBlocks::rodBlock);
    public static final RegistrySupplier<Block> ETHEREAL_ROD = AbyssalDecor.BLOCKS.register("ethereal_rod", AbyssalDecorBlocks::rodBlock);

    public static Block rodBlock() {
        return new WaterloggedRodBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.BONE_BLOCK)
                .strength(1, 10).lightLevel(b -> 11).noOcclusion().hasPostProcess((bs, br, bp) -> true)
                .emissiveRendering((bs, br, bp) -> true));
    }

    public static final RegistrySupplier<Block> HANGING_MOSS = AbyssalDecor.BLOCKS.register("hanging_moss", () ->
            new HangingMossBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().randomTicks().strength(0.2f)
                    .sound(SoundType.VINE).ignitedByLava().pushReaction(PushReaction.DESTROY)));

    public static final RegistrySupplier<WallHangingMossBlock> WALL_HANGING_MOSS = AbyssalDecor.BLOCKS.register("wall_hanging_moss",
            () -> new WallHangingMossBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT)
            .replaceable().noCollission().randomTicks().strength(0.2f).sound(SoundType.VINE).ignitedByLava()
            .pushReaction(PushReaction.DESTROY).dropsLike(HANGING_MOSS.get())));

    public static final RegistrySupplier<Block> DAFFODIL = AbyssalDecor.BLOCKS.register("daffodil", () ->
            new BonemealableFlowerBlock(MobEffects.REGENERATION, 8, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY)));
    public static final RegistrySupplier<Block> ASTER = AbyssalDecor.BLOCKS.register("aster", () ->
            new BonemealableFlowerBlock(MobEffects.REGENERATION, 8, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY)));
    public static final RegistrySupplier<Block> SNAPLEAF = AbyssalDecor.BLOCKS.register("snapleaf", () ->
            new BonemealableFlowerBlock(MobEffects.REGENERATION, 8, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));

    public static final RegistrySupplier<Block> AMARANTH = AbyssalDecor.BLOCKS.register("amaranth", () ->
            new AmaranthBlock(BlockBehaviour.Properties.of().offsetType(BlockBehaviour.OffsetType.XZ).
            mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY), ModTags.Blocks.AMARANTH_GROWABLE));
    public static final RegistrySupplier<DoublePlantBlock> TALL_AMARANTH = AbyssalDecor.BLOCKS.register("tall_amaranth", () ->
            new TallAmaranthBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.DESTROY).randomTicks()));
    public static final RegistrySupplier<Block> AMARANTH_CRATE = AbyssalDecor.BLOCKS.register("amaranth_crate", () ->
            new AbstractDirectionalBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistrySupplier<CropBlock> MUCKROOT = AbyssalDecor.BLOCKS.register("muckroot", () ->
            new MuckrootBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY), ModTags.Blocks.MUCKROOT_GROWABLE));

    public static final RegistrySupplier<Block> BARK_ORCHID = AbyssalDecor.BLOCKS.register("bark_orchid", () -> new BarkOrchidBlock(BlockBehaviour.Properties.of().sound(SoundType.GRASS).instabreak().noOcclusion()));

    public static final RegistrySupplier<WisteriaBlock> WISTERIA = AbyssalDecor.BLOCKS.register("wisteria",() ->
            new WisteriaBlock(BlockBehaviour.Properties.of().ignitedByLava().sound(SoundType.VINE).strength(0.1F, 1)
                    .noCollission().noOcclusion().dynamicShape().offsetType(BlockBehaviour.OffsetType.XYZ)));
    public static final RegistrySupplier<WisteriaBlock> ELDER_WISTERIA = AbyssalDecor.BLOCKS.register("elder_wisteria",() ->
            new WisteriaBlock(BlockBehaviour.Properties.of().ignitedByLava().sound(SoundType.VINE).strength(0.1F, 1)
                    .noCollission().noOcclusion().dynamicShape().offsetType(BlockBehaviour.OffsetType.XYZ)));


    public static final RegistrySupplier<Block> WISTERIA_PETALS = AbyssalDecor.BLOCKS.register("wisteria_petals", () -> Blocks.leaves(SoundType.GRASS));
    public static final RegistrySupplier<Block> ELDER_WISTERIA_PETALS = AbyssalDecor.BLOCKS.register("elder_wisteria_petals", () -> Blocks.leaves(SoundType.GRASS));
    public static final RegistrySupplier<Block> ELDER_WISTERIA_LEAVES = AbyssalDecor.BLOCKS.register("elder_wisteria_leaves", () -> Blocks.leaves(SoundType.GRASS));

    public static final RegistrySupplier<RotatedPillarBlock> ANCIENT_BIRCH_LOG = AbyssalDecor.BLOCKS.register("ancient_birch_log", () -> Blocks.log(MapColor.SAND, MapColor.QUARTZ));
    public static final RegistrySupplier<RotatedPillarBlock> STRIPPED_ANCIENT_BIRCH_LOG = AbyssalDecor.BLOCKS.register("stripped_ancient_birch_log", () -> Blocks.log(MapColor.SAND, MapColor.QUARTZ));

    public static final RegistrySupplier<DirectionalBlock> FOXY_PILLAR = AbyssalDecor.BLOCKS.register("foxy_pillar", () ->
            directionalLog(MapColor.PODZOL, MapColor.COLOR_BROWN));

    public static DirectionalBlock directionalLog(MapColor topMapColor, MapColor sideMapColor) {
        return new AbstractDirectionalBlock(BlockBehaviour.Properties.of().mapColor((p_152624_) -> p_152624_.getValue(DirectionalBlock.FACING).getAxis() == Direction.Axis.Y ? topMapColor : sideMapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
    }

    public static final RegistrySupplier<Block> SCRIMSHAW = AbyssalDecor.BLOCKS.register("scrimshaw", () -> new ScrimshawBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.LODESTONE)
            .strength(2, 10).lightLevel((s) -> 4).noOcclusion()));

    public static final RegistrySupplier<Block> SCRIMSHAW_ALTAR = AbyssalDecor.BLOCKS.register("scrimshaw_altar", () -> new ScrimshawBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.LODESTONE)
            .strength(2, 10).lightLevel((s) -> 4).noOcclusion()));

    public static final RegistrySupplier<Block> DESK_BELL = AbyssalDecor.BLOCKS.register("desk_bell", () -> new DeskBellBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> STARFISH = AbyssalDecor.BLOCKS.register("starfish", () -> new StarfishBlock(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<Block> DRIED_STARFISH = AbyssalDecor.BLOCKS.register("dried_starfish", () -> new DriedStarfishBlock(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<Block> STARLIGHT = AbyssalDecor.BLOCKS.register("starlight", () ->
            new StarlightBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.AMETHYST_CLUSTER)
                    .strength(1).lightLevel(s -> 12).noCollission().noOcclusion().hasPostProcess((bs, br, bp) -> true)
                    .emissiveRendering((bs, br, bp) -> true)));

    public static final RegistrySupplier<Block> LIFE_PRESERVER = AbyssalDecor.BLOCKS.register("life_preserver", () ->
            new LifePreserverBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.WOOL)
                    .strength(0, 1).noOcclusion()));

    public static final RegistrySupplier<FaceAttachedHorizontalDirectionalBlock> WOOD_SUPPORT = AbyssalDecor.BLOCKS.register("wood_support",
            () -> new WoodSupportBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS).strength(2, 3).sound(SoundType.WOOD).ignitedByLava()));


    public static final RegistrySupplier<Block> SHIP_WHEEL = AbyssalDecor.BLOCKS.register("ship_wheel", () ->
            new ShipWheelBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).strength(2, 10).noOcclusion()));
    public static final RegistrySupplier<Block> WOODEN_DRAGON_HEAD = AbyssalDecor.BLOCKS.register("wooden_dragon_head", () ->
            new WoodenDragonHeadBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).strength(2, 10).noOcclusion()));
    public static final RegistrySupplier<Block> WOODEN_FROG = AbyssalDecor.BLOCKS.register("wooden_frog", () ->
            new WoodenFrogBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD)
                    .strength(2, 10).noOcclusion()));

    public static final RegistrySupplier<Block> LION_STATUE = AbyssalDecor.BLOCKS.register("lion_statue", () -> new LionStatueBlock(BlockBehaviour.Properties.of()
            .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)
            .strength(2, 10).requiresCorrectToolForDrops().noOcclusion().pushReaction(PushReaction.BLOCK)));
    public static final RegistrySupplier<Block> GARGOYLE = AbyssalDecor.BLOCKS.register("gargoyle", () ->
            new GargoyleBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)
            .strength(2, 10).requiresCorrectToolForDrops().noOcclusion().pushReaction(PushReaction.BLOCK)
                    .isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistrySupplier<Block> NITHING_POLE = AbyssalDecor.BLOCKS.register("nithing_pole", () ->
            new LionStatueBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).strength(3, 10).noOcclusion().pushReaction(PushReaction.BLOCK)));
    public static final RegistrySupplier<Block> TELESCOPE = AbyssalDecor.BLOCKS.register("telescope", () ->
            new LionStatueBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).strength(3, 10).noOcclusion().pushReaction(PushReaction.BLOCK)));

    public static final RegistrySupplier<Block> HANGING_WEB = AbyssalDecor.BLOCKS.register("hanging_web",
            () -> new WebBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).forceSolidOn().noCollission()
                    .requiresCorrectToolForDrops().strength(4).pushReaction(PushReaction.DESTROY)));

    public static final RegistrySupplier<Block> WALL_HANGING_WEB = AbyssalDecor.BLOCKS.register("wall_hanging_web",
            () -> new WallHangingWebBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).forceSolidOn().noCollission()
                    .requiresCorrectToolForDrops().strength(4).pushReaction(PushReaction.DESTROY).dropsLike(HANGING_WEB.get())));

    public static final RegistrySupplier<Block> DANGLING_WEB = AbyssalDecor.BLOCKS.register("dangling_web", () -> new HangingDoubleBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).forceSolidOn().noCollission()
            .requiresCorrectToolForDrops().strength(4).pushReaction(PushReaction.DESTROY)));

    public static final RegistrySupplier<Block> WALL_DANGLING_WEB = AbyssalDecor.BLOCKS.register("wall_dangling_web", () -> new WallHangingWebBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).forceSolidOn().noCollission()
            .requiresCorrectToolForDrops().strength(4).pushReaction(PushReaction.DESTROY).dropsLike(DANGLING_WEB.get())));

    public static final RegistrySupplier<Block> PRISMARINE_CRYSTAL_BLOCK = AbyssalDecor.BLOCKS.register("prismarine_crystal_block", () ->
            new GlassBlock(glassLike().lightLevel(s -> 15)));

    public static final RegistrySupplier<IronBarsBlock> PRISMARINE_CRYSTAL_PANE = AbyssalDecor.BLOCKS.register("prismarine_crystal_pane",
            () -> new IronBarsBlock(panes().lightLevel(s -> 15)));

    public static final RegistrySupplier<Block> FRESNEL_BLOCK = AbyssalDecor.BLOCKS.register("fresnel_block", () ->
            new GlassBlock(glassLike()));

    public static final RegistrySupplier<IronBarsBlock> FRESNEL_PANE = AbyssalDecor.BLOCKS.register("fresnel_pane",
            () -> new IronBarsBlock(panes()));

    public static final RegistrySupplier<Block> CRYSTALLIZED_GLOWSTONE = AbyssalDecor.BLOCKS.register("crystallized_glowstone", () ->
            new GlassBlock(glassLike().lightLevel(state -> 15)));

    public static final RegistrySupplier<IronBarsBlock> CRYSTALLIZED_GLOWSTONE_PANE = AbyssalDecor.BLOCKS.register("crystallized_glowstone_pane",
            () -> new IronBarsBlock(panes().lightLevel(state -> 15)));

    public static final RegistrySupplier<Block> FRAMED_CRYSTALLIZED_GLOWSTONE = AbyssalDecor.BLOCKS.register("framed_crystallized_glowstone", () ->
            new GlassBlock(glassLike().lightLevel(state -> 15)));

    public static final RegistrySupplier<IronBarsBlock> FRAMED_CRYSTALLIZED_GLOWSTONE_PANE = AbyssalDecor.BLOCKS.register("framed_crystallized_glowstone_pane",
            () -> new IronBarsBlock(panes().lightLevel(state -> 15)));

    public static final RegistrySupplier<Block> VERMILION_BLOCK = AbyssalDecor.BLOCKS.register("vermilion_block", () ->
            new GlassBlock(glassLike()));

    public static final RegistrySupplier<IronBarsBlock> VERMILION_PANE = AbyssalDecor.BLOCKS.register("vermilion_pane",
            () -> new IronBarsBlock(panes()));

    public static final RegistrySupplier<Block> FRAMED_VERMILION_BLOCK = AbyssalDecor.BLOCKS.register("framed_vermilion_block", () ->
            new GlassBlock(glassLike()));

    public static final RegistrySupplier<IronBarsBlock> FRAMED_VERMILION_PANE = AbyssalDecor.BLOCKS.register("framed_vermilion_pane",
            () -> new IronBarsBlock(panes()));

    /////////////////////////////////////

    public static final RegistrySupplier<RotatedPillarBlock> WHITEWOOD_LOG = AbyssalDecor.BLOCKS.register("whitewood_log", () -> Blocks.log(MapColor.SAND, MapColor.QUARTZ));
    public static final RegistrySupplier<RotatedPillarBlock> WHITEWOOD_WOOD = AbyssalDecor.BLOCKS.register("whitewood_wood", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASS).strength(2)
                    .sound(SoundType.WOOD).ignitedByLava()));

    public static final RegistrySupplier<Block> WHITEWOOD_PLANKS = AbyssalDecor.BLOCKS.register("whitewood_planks", () -> planks(DyeColor.WHITE.getMapColor()));

    public static final RegistrySupplier<StairBlock> WHITEWOOD_STAIRS = AbyssalDecor.BLOCKS.register("whitewood_stairs",
            () -> stairs(WHITEWOOD_PLANKS.get()));

    public static final RegistrySupplier<SlabBlock> WHITEWOOD_SLAB = AbyssalDecor.BLOCKS.register("whitewood_slab", () -> slab(WHITEWOOD_PLANKS.get()));

    public static final RegistrySupplier<ButtonBlock> WHITEWOOD_BUTTON = AbyssalDecor.BLOCKS.register("whitewood_button",() -> Blocks.woodenButton(ModBlockSetTypes.WHITEWOOD));

    public static final RegistrySupplier<RotatedPillarBlock> WHITEWOOD_TRIM = AbyssalDecor.BLOCKS.register("whitewood_trim",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASS).strength(2)
            .sound(SoundType.WOOD).ignitedByLava()));

    public static final RegistrySupplier<DoorBlock> WHITEWOOD_DOOR = AbyssalDecor.BLOCKS.register("whitewood_door",() ->
            woodDoor(WHITEWOOD_PLANKS.get(),ModBlockSetTypes.WHITEWOOD));

    public static final RegistrySupplier<TrapDoorBlock> WHITEWOOD_TRAPDOOR = AbyssalDecor.BLOCKS.register("whitewood_trapdoor",() ->
            woodTrapdoor(ModBlockSetTypes.WHITEWOOD));

    public static final RegistrySupplier<PressurePlateBlock> WHITEWOOD_PRESSURE_PLATE = AbyssalDecor.BLOCKS.register("whitewood_pressure_plate",() ->
            woodPressurePlate(WHITEWOOD_PLANKS.get(),ModBlockSetTypes.WHITEWOOD));

    public static final RegistrySupplier<FenceBlock> WHITEWOOD_FENCE = AbyssalDecor.BLOCKS.register("whitewood_fence",() ->
            woodFence(WHITEWOOD_PLANKS.get()));

    public static final RegistrySupplier<FenceGateBlock> WHITEWOOD_FENCE_GATE = AbyssalDecor.BLOCKS.register("whitewood_fence_gate",() ->
            woodFenceGate(WHITEWOOD_PLANKS.get(),ModWoodTypes.WHITEWOOD));

    public static final RegistrySupplier<StandingSignBlock> WHITEWOOD_SIGN = AbyssalDecor.BLOCKS.register("whitewood_sign",() ->
            sign(WHITEWOOD_PLANKS.get(),ModWoodTypes.WHITEWOOD));

    public static final RegistrySupplier<WallSignBlock> WHITEWOOD_WALL_SIGN = AbyssalDecor.BLOCKS.register("whitewood_wall_sign",() ->
            wallSign(WHITEWOOD_SIGN.get(),ModWoodTypes.WHITEWOOD));

    public static final RegistrySupplier<CeilingHangingSignBlock> WHITEWOOD_HANGING_SIGN = AbyssalDecor.BLOCKS.register("whitewood_hanging_sign",() ->
            hangingSign(WHITEWOOD_PLANKS.get(),ModWoodTypes.WHITEWOOD));

    public static final RegistrySupplier<WallHangingSignBlock> WHITEWOOD_WALL_HANGING_SIGN = AbyssalDecor.BLOCKS.register("whitewood_wall_hanging_sign",() ->
            wallHangingSign(WHITEWOOD_HANGING_SIGN.get(),ModWoodTypes.WHITEWOOD));

    public static final RegistrySupplier<Block> WHITEWOOD_PLANTER = AbyssalDecor.BLOCKS.register("whitewood_planter", () ->
            Services.PLATFORM.whitewoodPlanter(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS)
                    .strength(2, 3).sound(SoundType.WOOD).ignitedByLava()));


    ///////

    public static final RegistrySupplier<Block> WHITE_PEARL = AbyssalDecor.BLOCKS.register("white_pearl",() -> new PearlBlock(BlockBehaviour.Properties.of().strength(.5f)));

    public static final RegistrySupplier<Block> WHITE_PEARL_BLOCK = AbyssalDecor.BLOCKS.register("white_pearl_block",() -> new Block(BlockBehaviour.Properties.of().strength(.5f).requiresCorrectToolForDrops()));

    public static final RegistrySupplier<StairBlock> WHITE_PEARL_STAIRS = AbyssalDecor.BLOCKS.register("white_pearl_stairs",() -> stairs(WHITE_PEARL.get()));
    public static final RegistrySupplier<SlabBlock> WHITE_PEARL_SLAB = AbyssalDecor.BLOCKS.register("white_pearl_slab",() -> slab(WHITE_PEARL.get()));
    public static final RegistrySupplier<Block> CHISELED_WHITE_PEARL = AbyssalDecor.BLOCKS.register("chiseled_white_pearl",() -> new Block(BlockBehaviour.Properties.copy(WHITE_PEARL_BLOCK.get())));
    public static final RegistrySupplier<WallBlock> WHITE_PEARL_WALL = AbyssalDecor.BLOCKS.register("white_pearl_wall",() -> wall(WHITE_PEARL.get()));
    public static final RegistrySupplier<FancierIronBarsBlock> WHITE_PEARL_BARS = AbyssalDecor.BLOCKS.register("white_pearl_bars",
            () ->new FancierIronBarsBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.METAL).strength(2.0F, 10.0F).noOcclusion()));

    public static final RegistrySupplier<DoorBlock> WHITE_PEARL_DOOR = AbyssalDecor.BLOCKS.register("white_pearl_door",
            () ->woodDoor(WHITE_PEARL.get(),ModBlockSetTypes.WHITE_PEARL));

    public static final RegistrySupplier<TrapDoorBlock> WHITE_PEARL_TRAPDOOR = AbyssalDecor.BLOCKS.register("white_pearl_trapdoor",
            () ->woodTrapdoor(ModBlockSetTypes.WHITE_PEARL));

    public static final RegistrySupplier<RotatedPillarBlock> WHITE_PEARL_PILLAR = AbyssalDecor.BLOCKS.register("white_pearl_pillar",() -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(WHITE_PEARL_BLOCK.get())));
    public static final RegistrySupplier<Block> CUT_WHITE_PEARL_BLOCK = AbyssalDecor.BLOCKS.register("cut_white_pearl_block",() -> new Block(BlockBehaviour.Properties.of().strength(.5f).requiresCorrectToolForDrops()));

    ///////

    public static final RegistrySupplier<Block> WHITE_PEARL_BRICKS = AbyssalDecor.BLOCKS.register("white_pearl_bricks",() -> new Block(BlockBehaviour.Properties.of().strength(.5f).requiresCorrectToolForDrops()));

    public static final RegistrySupplier<StairBlock> WHITE_PEARL_BRICK_STAIRS = AbyssalDecor.BLOCKS.register("white_pearl_brick_stairs",() -> stairs(WHITE_PEARL_BRICKS.get()));
    public static final RegistrySupplier<SlabBlock> WHITE_PEARL_BRICK_SLAB = AbyssalDecor.BLOCKS.register("white_pearl_brick_slab",() -> slab(WHITE_PEARL_BRICKS.get()));
    public static final RegistrySupplier<WallBlock> WHITE_PEARL_BRICK_WALL = AbyssalDecor.BLOCKS.register("white_pearl_brick_wall",() -> wall(WHITE_PEARL_BRICKS.get()));

    public static final RegistrySupplier<SmallBarsBlock> SMALL_WHITE_PEARL_BARS = AbyssalDecor.BLOCKS.register("small_white_pearl_bars",() ->
            smallBars());

    public static final RegistrySupplier<SmallBarsCornerBlock> SMALL_WHITE_PEARL_BARS_CORNER = AbyssalDecor.BLOCKS.register("small_white_pearl_bars_corner",() ->
            new SmallBarsCornerBlock(BlockBehaviour.Properties.copy(SMALL_WHITE_PEARL_BARS.get())));

    ///////

    public static final RegistrySupplier<Block> SMOOTH_WHITE_PEARL_BLOCK = AbyssalDecor.BLOCKS.register("smooth_white_pearl_block",() -> new Block(BlockBehaviour.Properties.of().strength(.5f).requiresCorrectToolForDrops()));

    public static final RegistrySupplier<StairBlock> SMOOTH_WHITE_PEARL_STAIRS = AbyssalDecor.BLOCKS.register("smooth_white_pearl_stairs",() -> stairs(SMOOTH_WHITE_PEARL_BLOCK.get()));
    public static final RegistrySupplier<SlabBlock> SMOOTH_WHITE_PEARL_SLAB = AbyssalDecor.BLOCKS.register("smooth_white_pearl_slab",() -> slab(SMOOTH_WHITE_PEARL_BLOCK.get()));
    public static final RegistrySupplier<WallBlock> SMOOTH_WHITE_PEARL_WALL = AbyssalDecor.BLOCKS.register("smooth_white_pearl_wall",() -> wall(SMOOTH_WHITE_PEARL_BLOCK.get()));

    public static final RegistrySupplier<Block> WHITE_PEARL_TILES = AbyssalDecor.BLOCKS.register("white_pearl_tiles",() -> new Block(BlockBehaviour.Properties.of().strength(.5f).requiresCorrectToolForDrops()));
    public static final RegistrySupplier<CrackedBlock> CRACKED_PEARL_TILES = AbyssalDecor.BLOCKS.register("cracked_pearl_tiles",() -> new CrackedBlock(BlockBehaviour.Properties.of().strength(.5f).requiresCorrectToolForDrops()));
    public static final RegistrySupplier<Block> MIXED_PEARL_TILES = AbyssalDecor.BLOCKS.register("mixed_pearl_tiles",() -> new Block(BlockBehaviour.Properties.of().strength(.5f).requiresCorrectToolForDrops()));
    public static final RegistrySupplier<Block> STARRY_PEARL_TILES = AbyssalDecor.BLOCKS.register("starry_pearl_tiles",() -> new Block(BlockBehaviour.Properties.of().strength(.5f).requiresCorrectToolForDrops()));

    public static final RegistrySupplier<ClamBlock> CLAM = AbyssalDecor.BLOCKS.register("clam",() ->
            new ClamBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.METAL)
                    .strength(1, 10).noOcclusion().randomTicks(),false));
    public static final RegistrySupplier<ClamBlock> CLAM_WITH_PEARL = AbyssalDecor.BLOCKS.register("clam_with_pearl",() ->
            new ClamBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.METAL)
                    .strength(1, 10).noOcclusion(),true));

    public static final RegistrySupplier<FaceAttachedBlock> SHELL = AbyssalDecor.BLOCKS.register("shell",
            () -> new ShellBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.METAL).strength(1).noOcclusion()));

    ///////

    public static final RegistrySupplier<RotatedPillarBlock> PEARLY_GLASS = AbyssalDecor.BLOCKS.register("pearly_glass",
            () -> new RotatedPillarBlock(glassLike()));

    public static final RegistrySupplier<RotatedPillarBlock> SUNNY_PEARLY_GLASS = AbyssalDecor.BLOCKS.register("sunny_pearly_glass",
            () -> new RotatedPillarBlock(glassLike()));

    public static final RegistrySupplier<RotatedPillarBlock> AZURE_PEARLY_GLASS = AbyssalDecor.BLOCKS.register("azure_pearly_glass",
            () -> new RotatedPillarBlock(glassLike()));

    public static final RegistrySupplier<RotatedPillarBlock> VERDANT_PEARLY_GLASS = AbyssalDecor.BLOCKS.register("verdant_pearly_glass",
            () -> new RotatedPillarBlock(glassLike()));

    public static final RegistrySupplier<RotatedPillarBlock> WHITEWOOD_PEARLY_GLASS = AbyssalDecor.BLOCKS.register("whitewood_pearly_glass",
            () -> new RotatedPillarBlock(glassLike()));

    public static final RegistrySupplier<RotatedPillarBlock> BLACKWOOD_PEARLY_GLASS = AbyssalDecor.BLOCKS.register("blackwood_pearly_glass",
            () -> new RotatedPillarBlock(glassLike()));

    public static final RegistrySupplier<IronBarsBlock> PEARLY_GLASS_PANE = AbyssalDecor.BLOCKS.register("pearly_glass_pane",
            () -> new IronBarsBlock(glassLike()));

    public static final RegistrySupplier<IronBarsBlock> SUNNY_PEARLY_GLASS_PANE = AbyssalDecor.BLOCKS.register("sunny_pearly_glass_pane",
            () -> new IronBarsBlock(glassLike()));

    public static final RegistrySupplier<IronBarsBlock> AZURE_PEARLY_GLASS_PANE = AbyssalDecor.BLOCKS.register("azure_pearly_glass_pane",
            () -> new IronBarsBlock(glassLike()));

    public static final RegistrySupplier<IronBarsBlock> VERDANT_PEARLY_GLASS_PANE = AbyssalDecor.BLOCKS.register("verdant_pearly_glass_pane",
            () -> new IronBarsBlock(glassLike()));

    public static final RegistrySupplier<IronBarsBlock> WHITEWOOD_PEARLY_GLASS_PANE = AbyssalDecor.BLOCKS.register("whitewood_pearly_glass_pane",
            () -> new IronBarsBlock(glassLike()));

    public static final RegistrySupplier<IronBarsBlock> BLACKWOOD_PEARLY_GLASS_PANE = AbyssalDecor.BLOCKS.register("blackwood_pearly_glass_pane",
            () -> new IronBarsBlock(glassLike()));

    ////////Almost all of AD’s custom-model blocks should be water-loggable, with the exception of things like plants, hanging webs, grime, etc

    public static final RegistrySupplier<MixedBlock> MIXED_BRICKS = AbyssalDecor.BLOCKS.register("mixed_bricks",
            () -> new MixedBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6)));

    public static final RegistrySupplier<MixedBlock> MOSSY_MIXED_BRICKS = AbyssalDecor.BLOCKS.register("mossy_mixed_bricks",
            () -> new MixedBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6)));

    public static final RegistrySupplier<Block> BRITTLE_TUFF = AbyssalDecor.BLOCKS.register("brittle_tuff",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6)));

    public static final RegistrySupplier<TrapDoorBlock> STONE_TRAPDOOR = AbyssalDecor.BLOCKS.register("stone_trapdoor",() -> woodTrapdoor(BlockSetType.STONE));
    public static final RegistrySupplier<TrapDoorBlock> MOSSY_STONE_TRAPDOOR = AbyssalDecor.BLOCKS.register("mossy_stone_trapdoor",() -> woodTrapdoor(BlockSetType.STONE));
    public static final RegistrySupplier<TrapDoorBlock> ORNATE_STONE_TRAPDOOR = AbyssalDecor.BLOCKS.register("ornate_stone_trapdoor",() -> woodTrapdoor(BlockSetType.STONE));

    public static final RegistrySupplier<SconceBlock> IRON_SCONCE = AbyssalDecor.BLOCKS.register("iron_sconce",
            () -> new SconceBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<IronBarsBlock> ORNATE_IRON_BARS = AbyssalDecor.BLOCKS.register("ornate_iron_bars",() -> ironBars());

    public static final RegistrySupplier<RotatedPillarBlock> CORRUGATED_IRON = AbyssalDecor.BLOCKS.register("corrugated_iron",() ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASS).strength(2)
            .sound(SoundType.WOOD).ignitedByLava()));

    public static final RegistrySupplier<RotatedPillarBlock> IRON_PILLAR = AbyssalDecor.BLOCKS.register("iron_pillar",() ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASS).strength(2)
                    .sound(SoundType.WOOD).ignitedByLava()));


    public static final RegistrySupplier<Block> IRON_DUCT = AbyssalDecor.BLOCKS.register("iron_duct",() ->
            new Block(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASS).strength(2)
                    .sound(SoundType.WOOD).ignitedByLava()));

    public static final RegistrySupplier<DoorBlock> IRON_PUSH_DOOR = AbyssalDecor.BLOCKS.register("iron_push_door",() ->
            new DoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops()
                    .strength(5).noOcclusion().pushReaction(PushReaction.DESTROY), ModBlockSetTypes.PUSH_IRON));


    static IronBarsBlock ironBars() {
        return new IronBarsBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5, 6).sound(SoundType.METAL).noOcclusion());
    }

    static FancyIronBarsBlock fancyIronBars() {
        return new FancyIronBarsBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5, 6).sound(SoundType.METAL).noOcclusion());
    }

    static SmallBarsBlock smallBars() {
        return new SmallBarsBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5, 6).sound(SoundType.METAL).noOcclusion());
    }


    public static final RegistrySupplier<Block> IRON_PANEL = AbyssalDecor.BLOCKS.register("iron_panel",()
            -> new Block(BlockBehaviour.Properties.of().strength(5,6).requiresCorrectToolForDrops()));

    public static final RegistrySupplier<StairBlock> IRON_PANEL_STAIRS = AbyssalDecor.BLOCKS.register("iron_panel_stairs",() -> stairs(IRON_PANEL.get()));
    public static final RegistrySupplier<SlabBlock> IRON_PANEL_SLAB = AbyssalDecor.BLOCKS.register("iron_panel_slab",() -> slab(IRON_PANEL.get()));
    public static final RegistrySupplier<WallBlock> IRON_PANEL_WALL = AbyssalDecor.BLOCKS.register("iron_panel_wall",() -> wall(IRON_PANEL.get()));

    public static final RegistrySupplier<LeverBlock> INDUSTRIAL_LEVER = AbyssalDecor.BLOCKS.register("industrial_lever",() ->
            new IndustrialLeverBlock(BlockBehaviour.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY)));

    public static final RegistrySupplier<FancierIronBarsBlock> DULL_IRON_BARS = AbyssalDecor.BLOCKS.register("dull_iron_bars",() ->
            new FancierIronBarsBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.METAL).strength(2.0F, 10.0F).noOcclusion()));

    public static final RegistrySupplier<SmallBarsBlock> SMALL_DULL_IRON_BARS = AbyssalDecor.BLOCKS.register("small_dull_iron_bars",() ->
            smallBars());

    public static final RegistrySupplier<SmallBarsCornerBlock> SMALL_DULL_IRON_BARS_CORNER = AbyssalDecor.BLOCKS.register("small_dull_iron_bars_corner",() ->
            new SmallBarsCornerBlock(BlockBehaviour.Properties.copy(SMALL_DULL_IRON_BARS.get())));

    public static final RegistrySupplier<SconceBlock> DULL_IRON_SCONCE = AbyssalDecor.BLOCKS.register("dull_iron_sconce",() -> new SconceBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<IronBarsBlock> ORNATE_DULL_IRON_BARS = AbyssalDecor.BLOCKS.register("ornate_dull_iron_bars",() -> ironBars());
    public static final RegistrySupplier<DoorBlock> DULL_IRON_BAR_DOOR = AbyssalDecor.BLOCKS.register("dull_iron_bar_door",() ->
            new DoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops()
            .strength(5).noOcclusion().pushReaction(PushReaction.DESTROY), ModBlockSetTypes.PUSH_IRON));

    public static final RegistrySupplier<TrapDoorBlock> DULL_IRON_BAR_TRAPDOOR = AbyssalDecor.BLOCKS.register("dull_iron_bar_trapdoor",() -> woodTrapdoor(BlockSetType.STONE));

    public static final RegistrySupplier<AbstractDirectionalBlock> IRON_BALL = AbyssalDecor.BLOCKS.register("iron_ball",() ->
            new IronBallBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1, 10)
                    .noOcclusion()));

    public static final RegistrySupplier<Block> DULL_IRON_POST = AbyssalDecor.BLOCKS.register("dull_iron_post",() ->
            new PostBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1, 10)
                    .requiresCorrectToolForDrops().noOcclusion()));


    public static final RegistrySupplier<SconceBlock> GOLD_SCONCE = AbyssalDecor.BLOCKS.register("gold_sconce",
            () -> new SconceBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<FancyIronBarsBlock> GOLD_BARS = AbyssalDecor.BLOCKS.register("gold_bars",() -> fancyIronBars());

    ////////////////

    public static final RegistrySupplier<Block> BLOOD_CORAL_BUD = AbyssalDecor.BLOCKS.register("blood_coral_bud",() ->
            new BloodCoralBudBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE).strength(1)
                    .noCollission().noOcclusion()));

    public static final RegistrySupplier<Block> POLISHED_BLOOD_CORAL = AbyssalDecor.BLOCKS.register("polished_blood_coral",
            () -> new Block(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> SMOOTH_BLOOD_CORAL = AbyssalDecor.BLOCKS.register("smooth_blood_coral",
            () -> new Block(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> BLOOD_CORAL_BRICKS = AbyssalDecor.BLOCKS.register("blood_coral_bricks",
            () -> new Block(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<RotatedPillarBlock> ROUGH_BLOOD_CORAL = AbyssalDecor.BLOCKS.register("rough_blood_coral",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()));


    public static final RegistrySupplier<RotatedPillarBlock> BLOOD_CORAL_PILLAR = AbyssalDecor.BLOCKS.register("blood_coral_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<RotatedPillarBlock> GILDED_BLOOD_CORAL_PILLAR = AbyssalDecor.BLOCKS.register("gilded_blood_coral_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<SconceBlock> BLOOD_CORAL_SCONCE = AbyssalDecor.BLOCKS.register("blood_coral_sconce",
            () -> new SconceBlock(BlockBehaviour.Properties.of()));


    //blood coral lantern

    public static final RegistrySupplier<DoorBlock> BLOOD_CORAL_DOOR = AbyssalDecor.BLOCKS.register("blood_coral_door",() -> woodDoor(SMOOTH_BLOOD_CORAL.get(),BlockSetType.STONE));
    public static final RegistrySupplier<TrapDoorBlock> BLOOD_CORAL_TRAPDOOR = AbyssalDecor.BLOCKS.register("blood_coral_trapdoor",() -> woodTrapdoor(BlockSetType.STONE));

    public static final RegistrySupplier<FancierIronBarsBlock> BLOOD_CORAL_BARS = AbyssalDecor.BLOCKS.register("blood_coral_bars",() -> new FancierIronBarsBlock(
            BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.METAL).strength(2.0F, 10.0F).noOcclusion()));

    public static final RegistrySupplier<SmallBarsBlock> SMALL_BLOOD_CORAL_BARS = AbyssalDecor.BLOCKS.register("small_blood_coral_bars",() ->
            smallBars());

    public static final RegistrySupplier<SmallBarsCornerBlock> SMALL_BLOOD_CORAL_BARS_CORNER = AbyssalDecor.BLOCKS.register("small_blood_coral_bars_corner",() ->
            new SmallBarsCornerBlock(BlockBehaviour.Properties.copy(SMALL_BLOOD_CORAL_BARS.get())));


    public static final RegistrySupplier<SlabBlock> POLISHED_BLOOD_CORAL_SLAB = AbyssalDecor.BLOCKS.register("polished_blood_coral_slab",() -> slab(POLISHED_BLOOD_CORAL.get()));
    public static final RegistrySupplier<StairBlock> POLISHED_BLOOD_CORAL_STAIRS = AbyssalDecor.BLOCKS.register("polished_blood_coral_stairs",() -> stairs(POLISHED_BLOOD_CORAL.get()));
    public static final RegistrySupplier<WallBlock> POLISHED_BLOOD_CORAL_WALL = AbyssalDecor.BLOCKS.register("polished_blood_coral_wall",() -> wall(POLISHED_BLOOD_CORAL.get()));

    public static final RegistrySupplier<SlabBlock> SMOOTH_BLOOD_CORAL_SLAB = AbyssalDecor.BLOCKS.register("smooth_blood_coral_slab",() -> slab(SMOOTH_BLOOD_CORAL.get()));
    public static final RegistrySupplier<StairBlock> SMOOTH_BLOOD_CORAL_STAIRS = AbyssalDecor.BLOCKS.register("smooth_blood_coral_stairs",() -> stairs(SMOOTH_BLOOD_CORAL.get()));
    public static final RegistrySupplier<WallBlock> SMOOTH_BLOOD_CORAL_WALL = AbyssalDecor.BLOCKS.register("smooth_blood_coral_wall",() -> wall(SMOOTH_BLOOD_CORAL.get()));

    public static final RegistrySupplier<SlabBlock> BLOOD_CORAL_BRICK_SLAB = AbyssalDecor.BLOCKS.register("blood_coral_brick_slab",() -> slab(BLOOD_CORAL_BRICKS.get()));
    public static final RegistrySupplier<StairBlock> BLOOD_CORAL_BRICK_STAIRS = AbyssalDecor.BLOCKS.register("blood_coral_brick_stairs",() -> stairs(BLOOD_CORAL_BRICKS.get()));
    public static final RegistrySupplier<WallBlock> BLOOD_CORAL_BRICK_WALL = AbyssalDecor.BLOCKS.register("blood_coral_brick_wall",() -> wall(BLOOD_CORAL_BRICKS.get()));

    /////////


    public static final RegistrySupplier<Block> ROUGH_JADE = AbyssalDecor.BLOCKS.register("rough_jade",() -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()));
    public static final RegistrySupplier<Block> POLISHED_JADE = AbyssalDecor.BLOCKS.register("polished_jade",() -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()));
    public static final RegistrySupplier<Block> JADE_BRICKS = AbyssalDecor.BLOCKS.register("jade_bricks",() -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()));
    public static final RegistrySupplier<RotatedPillarBlock> JADE_PILLAR = AbyssalDecor.BLOCKS.register("jade_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()));
    public static final RegistrySupplier<RotatedPillarBlock> GILDED_JADE_PILLAR = AbyssalDecor.BLOCKS.register("gilded_jade_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()));

    //jade lantern

    public static final RegistrySupplier<RotatedPillarBlock> QUARTZ_BONE = AbyssalDecor.BLOCKS.register("quartz_bone",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()));

    public static final RegistrySupplier<Block> BONEROT = AbyssalDecor.BLOCKS.register("bonerot",() -> new Block(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<AbstractDirectionalBlock> FLAKY_SCALES =
            AbyssalDecor.BLOCKS.register("flaky_scales",() -> new AbstractDirectionalBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT).noCollission().randomTicks().strength(0.2f)
                    .sound(SoundType.VINE).pushReaction(PushReaction.DESTROY)));

    public static final RegistrySupplier<DirectionalBlock> SLUMP_LIGHT =
            AbyssalDecor.BLOCKS.register("slump_light",() -> new AbstractDirectionalBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> SHORTGILLS =
            AbyssalDecor.BLOCKS.register("shortgills",() -> new AbstractDirectionalBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> SERPENT_SCALES = AbyssalDecor.BLOCKS.register("serpent_scales",() ->new Block(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<SlabBlock> SERPENT_SCALE_SLAB = AbyssalDecor.BLOCKS.register("serpent_scale_slab",() ->slab(SERPENT_SCALES.get()));
    public static final RegistrySupplier<FaceAttachedHorizontalDirectionalBlock> SERPENT_SKIN = AbyssalDecor.BLOCKS.register("serpent_skin",() ->new FaceAttachedBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> DAMAGED_SERPENT_SCALES = AbyssalDecor.BLOCKS.register("damaged_serpent_scales",() ->new Block(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<SlabBlock> DAMAGED_SERPENT_SCALE_SLAB = AbyssalDecor.BLOCKS.register("damaged_serpent_scale_slab",() ->slab(DAMAGED_SERPENT_SCALES.get()));
    public static final RegistrySupplier<FaceAttachedHorizontalDirectionalBlock> DAMAGED_SERPENT_SKIN = AbyssalDecor.BLOCKS.register("damaged_serpent_skin",() ->new FaceAttachedBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> SERPENT_FLESH = AbyssalDecor.BLOCKS.register("serpent_flesh",() ->new Block(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<Block> SERPENT_FLESH_SLAB = AbyssalDecor.BLOCKS.register("serpent_flesh_slab",() ->slab(SERPENT_FLESH.get()));

    public static final RegistrySupplier<RotatedPillarBlock> RIBBED_SERPENT_VEINS = AbyssalDecor.BLOCKS.register("ribbed_serpent_veins",() ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()));

    public static final RegistrySupplier<FaceAttachedHorizontalDirectionalBlock> SERPENT_EYE = AbyssalDecor.BLOCKS.register("serpent_eye",() ->
            new FaceAttachedBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()));

    public static final RegistrySupplier<FaceAttachedHorizontalDirectionalBlock> SMALL_SERPENT_EYES = AbyssalDecor.BLOCKS.register("small_serpent_eyes",() ->
            new FaceAttachedBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()));

    public static final RegistrySupplier<Block> NETTED_SERPENT_EYE = AbyssalDecor.BLOCKS.register("netted_serpent_eye",
            () -> new Block(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> SMALL_NETTED_SERPENT_EYE = AbyssalDecor.BLOCKS.register("small_netted_serpent_eye",
            () -> new PearlBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.SHROOMLIGHT)
                    .strength(0.2F, 1).lightLevel((s) -> 10).requiresCorrectToolForDrops().jumpFactor(1.25F)
                    .noOcclusion().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)));

    //////////

    public static final RegistrySupplier<Block> VELVET = AbyssalDecor.BLOCKS.register("velvet",() ->
            new Block(BlockBehaviour.Properties.of().ignitedByLava().mapColor(MapColor.COLOR_RED).sound(SoundType.WOOL).strength(0.8F, 10.0F)));
    public static final RegistrySupplier<Block> GILDED_VELVET = AbyssalDecor.BLOCKS.register("gilded_velvet",() ->
            new Block(BlockBehaviour.Properties.of().ignitedByLava().mapColor(MapColor.COLOR_RED).sound(SoundType.WOOL).strength(0.8F, 10.0F)));

    public static final RegistrySupplier<CarpetBlock> VELVET_CARPET = AbyssalDecor.BLOCKS.register("velvet_carpet",() ->
            new CarpetBlock(BlockBehaviour.Properties.of().ignitedByLava().mapColor(MapColor.COLOR_RED).sound(SoundType.WOOL).strength(0.1F, 10.0F)));
    public static final RegistrySupplier<CarpetBlock> GILDED_VELVET_CARPET = AbyssalDecor.BLOCKS.register("gilded_velvet_carpet",() ->
            new CarpetBlock(BlockBehaviour.Properties.of().ignitedByLava().mapColor(MapColor.COLOR_RED).sound(SoundType.WOOL).strength(0.1F, 10.0F)));

    public static final RegistrySupplier<Block> VELVET_CURTAIN = AbyssalDecor.BLOCKS.register("velvet_curtain",() ->
            new CurtainBlock(BlockBehaviour.Properties.of().ignitedByLava().mapColor(MapColor.COLOR_RED)
                    .sound(SoundType.WOOL).strength(0.1F, 1).noCollission().noOcclusion()
                    ));

    public static final RegistrySupplier<Block> WOOL_CURTAIN = AbyssalDecor.BLOCKS.register("wool_curtain",() ->
            new CurtainBlock(BlockBehaviour.Properties.of().ignitedByLava().mapColor(MapColor.WOOL)
                    .sound(SoundType.WOOL).strength(0.1F, 1).noCollission().noOcclusion()
                    ));

    public static final RegistrySupplier<Block> AMMONITE = AbyssalDecor.BLOCKS.register("ammonite",() ->
            new Block(BlockBehaviour.Properties.of()));


    public static RegistrySupplier<Block> SEABRASS_ORE = AbyssalDecor.BLOCKS.register("seabrass_ore",
            () -> new FallingBlock(Block.Properties.copy(Blocks.GRAVEL)));

    public static final RegistrySupplier<Block> RAW_SEABRASS_BLOCK = AbyssalDecor.BLOCKS.register("raw_seabrass_block",() ->
            new Block(BlockBehaviour.Properties.of().sound(ModSoundTypes.SEABRASS_BLOCK).strength(5.0F, 10.0F)
                    .requiresCorrectToolForDrops()));

    public static final RegistrySupplier<Block> SEABRASS_BLOCK = AbyssalDecor.BLOCKS.register("seabrass_block",() ->
            new Block(BlockBehaviour.Properties.of().sound(ModSoundTypes.SEABRASS_BLOCK)
                    .mapColor(MapColor.COLOR_YELLOW).strength(5.0F, 10.0F).requiresCorrectToolForDrops()));

    public static final RegistrySupplier<Block> RIVETED_SEABRASS = AbyssalDecor.BLOCKS.register("riveted_seabrass",() ->
            new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(ModSoundTypes.SEABRASS_BLOCK)
                    .strength(3.0F, 10.0F).requiresCorrectToolForDrops()));

    public static final RegistrySupplier<RotatedPillarBlock> SEABRASS_TRIM = AbyssalDecor.BLOCKS.register("seabrass_trim",() ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(ModSoundTypes.SEABRASS_BLOCK)
                    .strength(3.0F, 10.0F).requiresCorrectToolForDrops()));

    public static final RegistrySupplier<FaceAttachedBlock> SEABRASS_PILLAR = AbyssalDecor.BLOCKS.register("seabrass_pillar",() ->
            new FaceAttachedBlock(BlockBehaviour.Properties.copy(SEABRASS_BLOCK.get()),true));

    public static final RegistrySupplier<RotatedPillarBlock> LARGE_SEABRASS_PIPE = AbyssalDecor.BLOCKS.register("large_seabrass_pipe",() ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(ModSoundTypes.SEABRASS_BLOCK)
                    .strength(3.0F, 10.0F).requiresCorrectToolForDrops()));

    public static final RegistrySupplier<Block> SMALL_SEABRASS_PIPES = AbyssalDecor.BLOCKS.register("small_seabrass_pipes",() ->
            new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(ModSoundTypes.SEABRASS_BLOCK)
                    .strength(3.0F, 10.0F).requiresCorrectToolForDrops()));

    public static final RegistrySupplier<Block> SEABRASS_PLATING = AbyssalDecor.BLOCKS.register("seabrass_plating",() ->
            new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(ModSoundTypes.SEABRASS_BLOCK)
                    .strength(3.0F, 10.0F).requiresCorrectToolForDrops()));

    public static final RegistrySupplier<Block> SEABRASS_TILES = AbyssalDecor.BLOCKS.register("seabrass_tiles",() ->
            new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(ModSoundTypes.SEABRASS_BLOCK)
                    .strength(3.0F, 10.0F).requiresCorrectToolForDrops()));


    public static RegistrySupplier<LampBlock> SEABRASS_LAMP = AbyssalDecor.BLOCKS.register("seabrass_lamp", () ->
            new LampBlock(lamp(), Shapes.block()));


    public static final RegistrySupplier<SlabBlock> SEABRASS_SLAB = AbyssalDecor.BLOCKS.register("seabrass_slab",() -> slab(SEABRASS_BLOCK.get()));
    public static final RegistrySupplier<StairBlock> SEABRASS_STAIRS = AbyssalDecor.BLOCKS.register("seabrass_stairs",() -> stairs(SEABRASS_BLOCK.get()));
    public static final RegistrySupplier<WallBlock> SEABRASS_WALL = AbyssalDecor.BLOCKS.register("seabrass_wall",() -> wall(SEABRASS_BLOCK.get()));

    public static final RegistrySupplier<ButtonBlock> SEABRASS_BUTTON = AbyssalDecor.BLOCKS.register("seabrass_button",() -> Blocks.stoneButton());
    public static final RegistrySupplier<PressurePlateBlock> SEABRASS_PRESSURE_PLATE = AbyssalDecor.BLOCKS.register("seabrass_pressure_plate",() ->
            new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, BlockBehaviour.Properties.of().mapColor(MapColor.STONE).forceSolidOn()
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY), ModBlockSetTypes.SEABRASS));

    public static final RegistrySupplier<DoorBlock> SEABRASS_DOOR = AbyssalDecor.BLOCKS.register("seabrass_door",() -> woodDoor(SEABRASS_BLOCK.get(),ModBlockSetTypes.SEABRASS));
    public static final RegistrySupplier<TrapDoorBlock> SEABRASS_TRAPDOOR = AbyssalDecor.BLOCKS.register("seabrass_trapdoor",() -> woodTrapdoor(ModBlockSetTypes.SEABRASS));


    public static final RegistrySupplier<SlabBlock> RIVETED_SEABRASS_SLAB = AbyssalDecor.BLOCKS.register("riveted_seabrass_slab",() -> slab(RIVETED_SEABRASS.get()));

    public static final RegistrySupplier<ChainBlock> SEABRASS_CHAIN = AbyssalDecor.BLOCKS.register("seabrass_chain",() ->
            new ChainBlock(BlockBehaviour.Properties.of()
            .forceSolidOn().requiresCorrectToolForDrops().strength(5, 6).sound(SoundType.CHAIN).noOcclusion()));

    public static final RegistrySupplier<SconceBlock> SEABRASS_SCONCE = AbyssalDecor.BLOCKS.register("seabrass_sconce",
            () -> new SconceBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<FancierIronBarsBlock> SEABRASS_BARS = AbyssalDecor.BLOCKS.register("seabrass_bars",
            () -> new FancierIronBarsBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.METAL).strength(2.0F, 10.0F).noOcclusion()));

    public static final RegistrySupplier<IronBarsBlock> ORNATE_SEABRASS_BARS = AbyssalDecor.BLOCKS.register("ornate_seabrass_bars",
            () -> ironBars());

    public static final RegistrySupplier<SmallBarsBlock> SMALL_SEABRASS_BARS = AbyssalDecor.BLOCKS.register("small_seabrass_bars",() ->
            smallBars());

    public static final RegistrySupplier<SmallBarsCornerBlock> SMALL_SEABRASS_BARS_CORNER = AbyssalDecor.BLOCKS.register("small_seabrass_bars_corner",() ->
            new SmallBarsCornerBlock(BlockBehaviour.Properties.copy(SMALL_SEABRASS_BARS.get())));

    public static final RegistrySupplier<Block> SEABRASS_CATALYST = AbyssalDecor.BLOCKS.register("seabrass_catalyst",
            () -> new AbstractDirectionalBlock(BlockBehaviour.Properties.copy(SEABRASS_BLOCK.get()).noOcclusion()));


    //////////////

    public static final RegistrySupplier<Block> DEEPBRONZE_BLOCK = AbyssalDecor.BLOCKS.register("deepbronze_block",() ->
            new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE)
                    .requiresCorrectToolForDrops().strength(3, 6).sound(ModSoundTypes.DEEPBRONZE_BLOCK)));

    public static final RegistrySupplier<Block> RIVETED_DEEPBRONZE = AbyssalDecor.BLOCKS.register("riveted_deepbronze",() ->
            new Block(BlockBehaviour.Properties.copy(DEEPBRONZE_BLOCK.get())));

    public static final RegistrySupplier<RotatedPillarBlock> DEEPBRONZE_TRIM = AbyssalDecor.BLOCKS.register("deepbronze_trim",() ->
            new RotatedPillarBlock(BlockBehaviour.Properties.copy(DEEPBRONZE_BLOCK.get())));

    public static final RegistrySupplier<RotatedPillarBlock> DEEPBRONZE_PILLAR = AbyssalDecor.BLOCKS.register("deepbronze_pillar",() ->
            new RotatedPillarBlock(BlockBehaviour.Properties.copy(DEEPBRONZE_BLOCK.get())));

    public static final RegistrySupplier<RotatedPillarBlock> LARGE_DEEPBRONZE_PIPE = AbyssalDecor.BLOCKS.register("large_deepbronze_pipe",() ->
            new RotatedPillarBlock(BlockBehaviour.Properties.copy(DEEPBRONZE_BLOCK.get())));

    public static final RegistrySupplier<RotatedPillarBlock> SMALL_DEEPBRONZE_PIPES = AbyssalDecor.BLOCKS.register("small_deepbronze_pipes",() ->
            new RotatedPillarBlock(BlockBehaviour.Properties.copy(DEEPBRONZE_BLOCK.get())));

    public static final RegistrySupplier<Block> DEEPBRONZE_PLATING = AbyssalDecor.BLOCKS.register("deepbronze_plating",() ->
            new Block(BlockBehaviour.Properties.copy(DEEPBRONZE_BLOCK.get())));

    public static final RegistrySupplier<Block> DEEPBRONZE_TILES = AbyssalDecor.BLOCKS.register("deepbronze_tiles",() ->
            new Block(BlockBehaviour.Properties.copy(DEEPBRONZE_BLOCK.get())));

    public static final RegistrySupplier<IronBarsBlock> DEEPBRONZE_BARS = AbyssalDecor.BLOCKS.register("deepbronze_bars",
            () -> ironBars());

    public static final RegistrySupplier<IronBarsBlock> ORNATE_DEEPBRONZE_BARS = AbyssalDecor.BLOCKS.register("ornate_deepbronze_bars",
            () -> ironBars());

    public static final RegistrySupplier<SmallBarsBlock> SMALL_DEEPBRONZE_BARS = AbyssalDecor.BLOCKS.register("small_deepbronze_bars",() ->
            smallBars());

    public static final RegistrySupplier<SmallBarsCornerBlock> SMALL_DEEPBRONZE_BARS_CORNER = AbyssalDecor.BLOCKS.register("small_deepbronze_bars_corner",() ->
            new SmallBarsCornerBlock(BlockBehaviour.Properties.copy(SMALL_DEEPBRONZE_BARS.get())));


    public static final RegistrySupplier<FaceAttachedHorizontalDirectionalBlock> DEEPBRONZE_BEAM = AbyssalDecor.BLOCKS.register("deepbronze_beam",
            () -> new BeamBlock(BlockBehaviour.Properties.copy(DEEPBRONZE_BLOCK.get())));


    public static final RegistrySupplier<SlabBlock> DEEPBRONZE_SLAB = AbyssalDecor.BLOCKS.register("deepbronze_slab",() -> slab(DEEPBRONZE_BLOCK.get()));
    public static final RegistrySupplier<StairBlock> DEEPBRONZE_STAIRS = AbyssalDecor.BLOCKS.register("deepbronze_stairs",() -> stairs(DEEPBRONZE_BLOCK.get()));
    public static final RegistrySupplier<WallBlock> DEEPBRONZE_WALL = AbyssalDecor.BLOCKS.register("deepbronze_wall",() -> wall(DEEPBRONZE_BLOCK.get()));

    public static final RegistrySupplier<SlabBlock> RIVETED_DEEPBRONZE_SLAB = AbyssalDecor.BLOCKS.register("riveted_deepbronze_slab",() -> slab(RIVETED_DEEPBRONZE.get()));
    public static final RegistrySupplier<StairBlock> RIVETED_DEEPBRONZE_STAIRS = AbyssalDecor.BLOCKS.register("riveted_deepbronze_stairs",() -> stairs(RIVETED_DEEPBRONZE.get()));
    public static final RegistrySupplier<WallBlock> RIVETED_DEEPBRONZE_WALL = AbyssalDecor.BLOCKS.register("riveted_deepbronze_wall",() -> wall(RIVETED_DEEPBRONZE.get()));

    public static final RegistrySupplier<ButtonBlock> DEEPBRONZE_BUTTON = AbyssalDecor.BLOCKS.register("deepbronze_button",() -> Blocks.stoneButton());
    public static final RegistrySupplier<PressurePlateBlock> DEEPBRONZE_PRESSURE_PLATE = AbyssalDecor.BLOCKS.register("deepbronze_pressure_plate",() ->
            new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, BlockBehaviour.Properties.of().mapColor(MapColor.STONE).forceSolidOn()
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY), ModBlockSetTypes.DEEPBRONZE));

    public static final RegistrySupplier<DoorBlock> DEEPBRONZE_DOOR = AbyssalDecor.BLOCKS.register("deepbronze_door",() -> woodDoor(DEEPBRONZE_BLOCK.get(),ModBlockSetTypes.DEEPBRONZE));
    public static final RegistrySupplier<TrapDoorBlock> DEEPBRONZE_TRAPDOOR = AbyssalDecor.BLOCKS.register("deepbronze_trapdoor",() -> woodTrapdoor(ModBlockSetTypes.DEEPBRONZE));

    public static final RegistrySupplier<ChainBlock> DEEPBRONZE_CHAIN = AbyssalDecor.BLOCKS.register("deepbronze_chain",() ->
            new ChainBlock(BlockBehaviour.Properties.of()
                    .forceSolidOn().requiresCorrectToolForDrops().strength(5, 6).sound(SoundType.CHAIN).noOcclusion()));

    public static final RegistrySupplier<SconceBlock> DEEPBRONZE_SCONCE = AbyssalDecor.BLOCKS.register("deepbronze_sconce",
            () -> new SconceBlock(BlockBehaviour.Properties.copy(DEEPBRONZE_BARS.get())));

    public static final RegistrySupplier<StackedIronBallsBlock> STACKED_IRON_BALLS = AbyssalDecor.BLOCKS.register("stacked_iron_balls",() ->
            new StackedIronBallsBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1, 10)
                    .noOcclusion()));

    /////////

    public static final ColorFamily<AbstractDirectionalBlock> WALLPAPERS =
            ColorFamily.createAndRegister(AbyssalDecor.BLOCKS, AbyssalDecorBlocks::wallpaper,"wallpaper");


    public static AbstractDirectionalBlock wallpaper(DyeColor dyeColor) {
        return new AbstractDirectionalBlock(BlockBehaviour.Properties.of().mapColor(dyeColor).instrument(NoteBlockInstrument.GUITAR)
                .strength(0.8F).sound(SoundType.WOOD).ignitedByLava());
    }

    public static final RegistrySupplier<Block> LAVENTINE = AbyssalDecor.BLOCKS.register("laventine",() -> new GlassBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> LAVENTINE_GLASS = AbyssalDecor.BLOCKS.register("laventine_glass",() -> new LaventineGlassBlock(glassLike()));

    public static final RegistrySupplier<FancyIronBarsBlock> LAVENTINE_GLASS_PANE = AbyssalDecor.BLOCKS.register("laventine_glass_pane",() ->
            new FancyIronBarsBlock(glassLike()));

    /////////

    public static final RegistrySupplier<Block> STARSTONE = AbyssalDecor.BLOCKS.register("starstone",() -> new Block(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> SMOOTH_STARSTONE = AbyssalDecor.BLOCKS.register("smooth_starstone",() -> new Block(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> POLISHED_STARSTONE = AbyssalDecor.BLOCKS.register("polished_starstone",() -> new Block(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<RotatedPillarBlock> GILDED_STARSTONE = AbyssalDecor.BLOCKS.register("gilded_starstone",() -> new RotatedPillarBlock(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<RotatedPillarBlock> STARSTONE_PILLAR = AbyssalDecor.BLOCKS.register("starstone_pillar",() -> new RotatedPillarBlock(BlockBehaviour.Properties.of()));


    public static final RegistrySupplier<SlabBlock> POLISHED_STARSTONE_SLAB = AbyssalDecor.BLOCKS.register("polished_starstone_slab",() -> slab(POLISHED_STARSTONE.get()));
    public static final RegistrySupplier<StairBlock> POLISHED_STARSTONE_STAIRS = AbyssalDecor.BLOCKS.register("polished_starstone_stairs",() -> stairs(POLISHED_STARSTONE.get()));
    public static final RegistrySupplier<WallBlock> POLISHED_STARSTONE_WALL = AbyssalDecor.BLOCKS.register("polished_starstone_wall",() -> wall(POLISHED_STARSTONE.get()));

    public static final RegistrySupplier<Block> CHISELED_STARSTONE = AbyssalDecor.BLOCKS.register("chiseled_starstone",() ->
            new Block(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> STARSTONE_TILES = AbyssalDecor.BLOCKS.register("starstone_tiles",() -> new Block(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<RotatedPillarBlock> LAPIS_PILLAR = AbyssalDecor.BLOCKS.register("lapis_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<RotatedPillarBlock> GILDED_LAPIS_PILLAR = AbyssalDecor.BLOCKS.register("gilded_lapis_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> STARGLASS = AbyssalDecor.BLOCKS.register("starglass",() -> new Block(glassLike()));

    public static final RegistrySupplier<IronBarsBlock> STARGLASS_PANE = AbyssalDecor.BLOCKS.register("starglass_pane",() -> new IronBarsBlock(glassLike()));

    ////////


    public static final RegistrySupplier<RotatedPillarBlock> BLACKWOOD_LOG = AbyssalDecor.BLOCKS.register("blackwood_log", () -> new BlackwoodLogBlock(
            BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).strength(2).randomTicks()));
    public static final RegistrySupplier<RotatedPillarBlock> STRIPPED_BLACKWOOD_LOG = AbyssalDecor.BLOCKS.register("stripped_blackwood_log", () -> Blocks.log(MapColor.SAND, MapColor.QUARTZ));

    public static final RegistrySupplier<RotatedPillarBlock> BLACKWOOD_WOOD = AbyssalDecor.BLOCKS.register("blackwood_wood", () ->
            new BlackwoodLogBlock(BlockBehaviour.Properties.of().mapColor(DyeColor.BLACK).instrument(NoteBlockInstrument.BASS).strength(2)
                    .sound(SoundType.WOOD).ignitedByLava()));

    public static final RegistrySupplier<Block> BLACKWOOD_PLANKS = AbyssalDecor.BLOCKS.register("blackwood_planks", () -> planks(DyeColor.BLACK.getMapColor()));

    public static final RegistrySupplier<StairBlock> BLACKWOOD_STAIRS = AbyssalDecor.BLOCKS.register("blackwood_stairs",
            () -> stairs(BLACKWOOD_PLANKS.get()));

    public static final RegistrySupplier<SlabBlock> BLACKWOOD_SLAB = AbyssalDecor.BLOCKS.register("blackwood_slab", () -> slab(BLACKWOOD_PLANKS.get()));

    public static final RegistrySupplier<ButtonBlock> BLACKWOOD_BUTTON = AbyssalDecor.BLOCKS.register("blackwood_button",() -> Blocks.woodenButton(ModBlockSetTypes.BLACKWOOD));

    public static final RegistrySupplier<RotatedPillarBlock> BLACKWOOD_TRIM = AbyssalDecor.BLOCKS.register("blackwood_trim",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASS).strength(2)
                    .sound(SoundType.WOOD).ignitedByLava()));

    public static final RegistrySupplier<DoorBlock> BLACKWOOD_DOOR = AbyssalDecor.BLOCKS.register("blackwood_door",() ->
            woodDoor(BLACKWOOD_PLANKS.get(),ModBlockSetTypes.BLACKWOOD));

    public static final RegistrySupplier<TrapDoorBlock> BLACKWOOD_TRAPDOOR = AbyssalDecor.BLOCKS.register("blackwood_trapdoor",() ->
            woodTrapdoor(ModBlockSetTypes.BLACKWOOD));

    public static final RegistrySupplier<PressurePlateBlock> BLACKWOOD_PRESSURE_PLATE = AbyssalDecor.BLOCKS.register("blackwood_pressure_plate",() ->
            woodPressurePlate(BLACKWOOD_PLANKS.get(),ModBlockSetTypes.BLACKWOOD));

    public static final RegistrySupplier<FenceBlock> BLACKWOOD_FENCE = AbyssalDecor.BLOCKS.register("blackwood_fence",() ->
            woodFence(BLACKWOOD_PLANKS.get()));

    public static final RegistrySupplier<FenceGateBlock> BLACKWOOD_FENCE_GATE = AbyssalDecor.BLOCKS.register("blackwood_fence_gate",() ->
            woodFenceGate(BLACKWOOD_PLANKS.get(),ModWoodTypes.BLACKWOOD));

    public static final RegistrySupplier<StandingSignBlock> BLACKWOOD_SIGN = AbyssalDecor.BLOCKS.register("blackwood_sign",() ->
            sign(BLACKWOOD_PLANKS.get(),ModWoodTypes.BLACKWOOD));

    public static final RegistrySupplier<WallSignBlock> BLACKWOOD_WALL_SIGN = AbyssalDecor.BLOCKS.register("blackwood_wall_sign",() ->
            wallSign(BLACKWOOD_SIGN.get(),ModWoodTypes.BLACKWOOD));

    public static final RegistrySupplier<CeilingHangingSignBlock> BLACKWOOD_HANGING_SIGN = AbyssalDecor.BLOCKS.register("blackwood_hanging_sign",() ->
            hangingSign(BLACKWOOD_PLANKS.get(),ModWoodTypes.BLACKWOOD));

    public static final RegistrySupplier<WallHangingSignBlock> BLACKWOOD_WALL_HANGING_SIGN = AbyssalDecor.BLOCKS.register("blackwood_wall_hanging_sign",() ->
            wallHangingSign(BLACKWOOD_HANGING_SIGN.get(),ModWoodTypes.BLACKWOOD));

    public static final RegistrySupplier<RotatedPillarBlock> BLACKWOOD_SHINGLES = AbyssalDecor.BLOCKS.register("blackwood_shingles", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(DyeColor.BLACK).instrument(NoteBlockInstrument.BASS).strength(2)
                    .sound(SoundType.WOOD).ignitedByLava()));


    ////////////////////


    public static final RegistrySupplier<Block> BLACK_PEARL = AbyssalDecor.BLOCKS.register("black_pearl",() -> new PearlBlock(BlockBehaviour.Properties.of().strength(.5f)));

    public static final RegistrySupplier<Block> BLACK_PEARL_BLOCK = AbyssalDecor.BLOCKS.register("black_pearl_block",() -> new Block(BlockBehaviour.Properties.of().strength(.5f).requiresCorrectToolForDrops()));

    public static final RegistrySupplier<StairBlock> BLACK_PEARL_STAIRS = AbyssalDecor.BLOCKS.register("black_pearl_stairs",() -> stairs(BLACK_PEARL.get()));
    public static final RegistrySupplier<SlabBlock> BLACK_PEARL_SLAB = AbyssalDecor.BLOCKS.register("black_pearl_slab",() -> slab(BLACK_PEARL.get()));
    public static final RegistrySupplier<Block> CHISELED_BLACK_PEARL = AbyssalDecor.BLOCKS.register("chiseled_black_pearl",() -> new Block(BlockBehaviour.Properties.copy(BLACK_PEARL_BLOCK.get())));
    public static final RegistrySupplier<WallBlock> BLACK_PEARL_WALL = AbyssalDecor.BLOCKS.register("black_pearl_wall",() -> wall(BLACK_PEARL.get()));

    public static final RegistrySupplier<RotatedPillarBlock> BLACK_PEARL_PILLAR = AbyssalDecor.BLOCKS.register("black_pearl_pillar",() -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(BLACK_PEARL_BLOCK.get())));
    public static final RegistrySupplier<Block> CUT_BLACK_PEARL_BLOCK = AbyssalDecor.BLOCKS.register("cut_black_pearl_block",() -> new Block(BlockBehaviour.Properties.of().strength(.5f).requiresCorrectToolForDrops()));

    ///////

    public static final RegistrySupplier<Block> BLACK_PEARL_BRICKS = AbyssalDecor.BLOCKS.register("black_pearl_bricks",() -> new Block(BlockBehaviour.Properties.of().strength(.5f).requiresCorrectToolForDrops()));

    public static final RegistrySupplier<StairBlock> BLACK_PEARL_BRICK_STAIRS = AbyssalDecor.BLOCKS.register("black_pearl_brick_stairs",() -> stairs(BLACK_PEARL_BRICKS.get()));
    public static final RegistrySupplier<SlabBlock> BLACK_PEARL_BRICK_SLAB = AbyssalDecor.BLOCKS.register("black_pearl_brick_slab",() -> slab(BLACK_PEARL_BRICKS.get()));
    public static final RegistrySupplier<WallBlock> BLACK_PEARL_BRICK_WALL = AbyssalDecor.BLOCKS.register("black_pearl_brick_wall",() -> wall(BLACK_PEARL_BRICKS.get()));

    public static final RegistrySupplier<FancierIronBarsBlock> BLACK_PEARL_BARS = AbyssalDecor.BLOCKS.register("black_pearl_bars",() ->
            new FancierIronBarsBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.METAL).strength(2.0F, 10.0F).noOcclusion()));

    public static final RegistrySupplier<SmallBarsBlock> SMALL_BLACK_PEARL_BARS = AbyssalDecor.BLOCKS.register("small_black_pearl_bars",() ->
            smallBars());

    public static final RegistrySupplier<SmallBarsCornerBlock> SMALL_BLACK_PEARL_BARS_CORNER = AbyssalDecor.BLOCKS.register("small_black_pearl_bars_corner",() ->
            new SmallBarsCornerBlock(BlockBehaviour.Properties.copy(SMALL_BLACK_PEARL_BARS.get())));

    ///////

    public static final RegistrySupplier<Block> SMOOTH_BLACK_PEARL_BLOCK = AbyssalDecor.BLOCKS.register("smooth_black_pearl_block",() -> new Block(BlockBehaviour.Properties.of().strength(.5f).requiresCorrectToolForDrops()));

    public static final RegistrySupplier<StairBlock> SMOOTH_BLACK_PEARL_STAIRS = AbyssalDecor.BLOCKS.register("smooth_black_pearl_stairs",() -> stairs(SMOOTH_BLACK_PEARL_BLOCK.get()));
    public static final RegistrySupplier<SlabBlock> SMOOTH_BLACK_PEARL_SLAB = AbyssalDecor.BLOCKS.register("smooth_black_pearl_slab",() -> slab(SMOOTH_BLACK_PEARL_BLOCK.get()));
    public static final RegistrySupplier<WallBlock> SMOOTH_BLACK_PEARL_WALL = AbyssalDecor.BLOCKS.register("smooth_black_pearl_wall",() -> wall(SMOOTH_BLACK_PEARL_BLOCK.get()));

    public static final RegistrySupplier<Block> GILDED_BLACK_PEARL = AbyssalDecor.BLOCKS.register("gilded_black_pearl",() -> new Block(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<Block> GILDED_BLACK_PEARL_DOOR = AbyssalDecor.BLOCKS.register("gilded_black_pearl_door",() -> woodDoor(GILDED_BLACK_PEARL.get(),ModBlockSetTypes.WHITE_PEARL));
    public static final RegistrySupplier<Block> GILDED_BLACK_PEARL_TRAPDOOR = AbyssalDecor.BLOCKS.register("gilded_black_pearl_trapdoor",() -> woodTrapdoor(ModBlockSetTypes.WHITE_PEARL));

    public static final RegistrySupplier<WallGrimeBlock> WALL_GRIME = AbyssalDecor.BLOCKS.register("wall_grime",() -> new WallGrimeBlock(BlockBehaviour.Properties.of().noCollission().noOcclusion()));
    public static final RegistrySupplier<Block> GRIME_CARPET = AbyssalDecor.BLOCKS.register("grime_carpet",() -> new FloorGrimeBlock(BlockBehaviour.Properties.of().noCollission().noOcclusion().dropsLike(WALL_GRIME.get())));

    public static final RegistrySupplier<Block> PAPER_STACK = AbyssalDecor.BLOCKS.register("paper_stack",() -> new Block(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<HorizontalDirectionalBlock> BOOK_BLOCK = AbyssalDecor.BLOCKS.register("book_block",() -> new AbstractHorizontalBlock(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<HorizontalDirectionalBlock> MOLDY_BOOK_BLOCK = AbyssalDecor.BLOCKS.register("moldy_book_block",() -> new AbstractHorizontalBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> BLACK_MOLD = AbyssalDecor.BLOCKS.register("black_mold",() ->
            new MoldBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_BLACK).sound(SoundType.SOUL_SOIL)
            .strength(5, 10).speedFactor(0.1F).randomTicks()));
    public static final RegistrySupplier<Block> BLACK_MOLD_CARPET = AbyssalDecor.BLOCKS.register("black_mold_carpet",() -> new CarpetBlock(BlockBehaviour.Properties.of()
            .ignitedByLava().mapColor(MapColor.COLOR_RED).sound(SoundType.SOUL_SOIL).strength(1).noOcclusion()));

    public static final RegistrySupplier<Block> INACTIVE_MOLD = AbyssalDecor.BLOCKS.register("inactive_mold",() ->
            new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.NYLIUM).strength(10).speedFactor(0.5F)));

    public static final RegistrySupplier<Block> MOLDWEAVE = AbyssalDecor.BLOCKS.register("moldweave",() ->
            new Block(BlockBehaviour.Properties.of().ignitedByLava().sound(SoundType.WOOL).strength(0.8F, 10.0F)));
    public static final RegistrySupplier<Block> MOLDWEAVE_CARPET = AbyssalDecor.BLOCKS.register("moldweave_carpet",() ->
            new CarpetBlock(BlockBehaviour.Properties.of().ignitedByLava().mapColor(MapColor.COLOR_RED)
                    .sound(SoundType.WOOL).strength(0.1F, 10).noOcclusion()));

    public static final RegistrySupplier<Block> BLACKENED_SAND = AbyssalDecor.BLOCKS.register("blackened_sand",() -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistrySupplier<Block> PITCHGLASS = AbyssalDecor.BLOCKS.register("pitchglass",() -> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
    public static final RegistrySupplier<IronBarsBlock> PITCHGLASS_PANE = AbyssalDecor.BLOCKS.register("pitchglass_pane",() -> new IronBarsBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));

    public static final RegistrySupplier<Block> FRAMED_PITCHGLASS = AbyssalDecor.BLOCKS.register("framed_pitchglass",() -> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
    public static final RegistrySupplier<IronBarsBlock> FRAMED_PITCHGLASS_PANE = AbyssalDecor.BLOCKS.register("framed_pitchglass_pane",() -> new IronBarsBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));

    public static final RegistrySupplier<RotatedPillarBlock> MOLDY_FROND_BLOCK = AbyssalDecor.BLOCKS.register("moldy_frond_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<MoldyStalkBlock> MOLDY_STALK = AbyssalDecor.BLOCKS.register("moldy_stalk",
            () -> new MoldyStalkBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.STEM).strength(1, 10).noOcclusion().randomTicks()));

    public static final RegistrySupplier<MoldySaplingBlock> MOLDY_STALK_SPROUT = AbyssalDecor.BLOCKS.register("moldy_stalk_sprout",
            () -> new MoldySaplingBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.STEM).instabreak().noCollission().noOcclusion().randomTicks()));

    public static final RegistrySupplier<Block> MOLDY_SPROUTS = AbyssalDecor.BLOCKS.register("moldy_sprouts",
            () -> Services.PLATFORM.moldBush(BlockBehaviour.Properties.of().sound(SoundType.ROOTS).instabreak().noCollission().noOcclusion()
                    ));

    public static final RegistrySupplier<Block> MOLDY_FUZZ = AbyssalDecor.BLOCKS.register("moldy_fuzz",
            () -> Services.PLATFORM.moldBush(BlockBehaviour.Properties.of().sound(SoundType.ROOTS).instabreak().noCollission().noOcclusion()
                    ));

    public static final RegistrySupplier<Block> MOLDY_HANGER = AbyssalDecor.BLOCKS.register("moldy_hanger",
            () -> new MoldyHangersBlock(BlockBehaviour.Properties.of().sound(SoundType.ROOTS).instabreak().noCollission()
                    .noOcclusion().randomTicks()));

    public static final RegistrySupplier<Block> INACTIVE_MOLDY_HANGER = AbyssalDecor.BLOCKS.register("inactive_moldy_hanger",
            () -> new InactiveMoldyHangersBlock(BlockBehaviour.Properties.of().sound(SoundType.ROOTS).instabreak().noCollission()
                    .noOcclusion().randomTicks()));

    public static final RegistrySupplier<CropBlock> FEVER_BLOSSOM = AbyssalDecor.BLOCKS.register("fever_blossom",
            () -> Services.PLATFORM.feverBlossom(BlockBehaviour.Properties.of().sound(SoundType.ROOTS).instabreak().noCollission()
                    .noOcclusion().randomTicks()
                    .lightLevel(value -> value.getValue(CropBlock.AGE) == CropBlock.MAX_AGE ? 7 : 0))
    );

    public static final RegistrySupplier<DoublePlantBlock> MOLD_FRONDS = AbyssalDecor.BLOCKS.register("mold_fronds",
            () -> new MoldFrondsBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).sound(SoundType.ROOTS).instabreak()
                    .noCollission().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));

    public static final RegistrySupplier<Block> MOLDY_FEATHERS = AbyssalDecor.BLOCKS.register("moldy_feathers",
            () -> Services.PLATFORM.moldBush(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).sound(SoundType.ROOTS).instabreak()
                    .noCollission().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));

    public static final RegistrySupplier<Block> MOLDY_STARSTONE = AbyssalDecor.BLOCKS.register("moldy_starstone",
            () -> new SimpleMoldBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.WATER).sound(SoundType.STONE)
                    .strength(5, 10).requiresCorrectToolForDrops().randomTicks()));

    public static final RegistrySupplier<Block> MOLDIER_STARSTONE = AbyssalDecor.BLOCKS.register("moldier_starstone",
            () -> new SimpleMoldBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM)
                    .mapColor(MapColor.WATER).sound(SoundType.STONE).strength(5, 10).requiresCorrectToolForDrops().randomTicks()));

    public static final RegistrySupplier<Block> POROUS_MOLD = AbyssalDecor.BLOCKS.register("porous_mold",
            () -> new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.NYLIUM).strength(10).speedFactor(0.1F).randomTicks()));

    public static final RegistrySupplier<Block> ECHO_SHARD_BLOCK = AbyssalDecor.BLOCKS.register("echo_shard_block",
            () -> new Block(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> POLISHED_ECHO_SHARD_BLOCK = AbyssalDecor.BLOCKS.register("polished_echo_shard_block",
            () -> new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.WATER)
                    .sound(SoundType.LODESTONE).strength(10).lightLevel((s) -> 3).requiresCorrectToolForDrops().hasPostProcess((bs, br, bp) -> true)
                    .emissiveRendering((bs, br, bp) -> true)));

    //new Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).alwaysEat().build())

    //////////////////////

    public static final RegistrySupplier<RotatedPillarBlock> CINNAMON_LOG = AbyssalDecor.BLOCKS.register("cinnamon_log", () -> Blocks.log(MapColor.SAND, MapColor.QUARTZ));

    public static final RegistrySupplier<RotatedPillarBlock> HEALING_CINNAMON_LOG = AbyssalDecor.BLOCKS.register("healing_cinnamon_log",
            () -> new HealingCinnamonLog(BlockBehaviour.Properties.of().mapColor(DyeColor.BLACK).instrument(NoteBlockInstrument.BASS).strength(2)
                    .sound(SoundType.WOOD).ignitedByLava().randomTicks()));

    public static final RegistrySupplier<RotatedPillarBlock> HEALING_CINNAMON_WOOD = AbyssalDecor.BLOCKS.register("healing_cinnamon_wood", () ->
            new HealingCinnamonLog(BlockBehaviour.Properties.of().mapColor(DyeColor.BLACK).instrument(NoteBlockInstrument.BASS).strength(2)
                    .sound(SoundType.WOOD).ignitedByLava()));

    public static final RegistrySupplier<RotatedPillarBlock> STRIPPED_CINNAMON_LOG = AbyssalDecor.BLOCKS.register("stripped_cinnamon_log", () -> Blocks.log(MapColor.SAND, MapColor.QUARTZ));

    public static final RegistrySupplier<RotatedPillarBlock> CINNAMON_WOOD = AbyssalDecor.BLOCKS.register("cinnamon_wood", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(DyeColor.BLACK).instrument(NoteBlockInstrument.BASS).strength(2)
                    .sound(SoundType.WOOD).ignitedByLava()));

    public static final RegistrySupplier<RotatedPillarBlock> STRIPPED_CINNAMON_WOOD = AbyssalDecor.BLOCKS.register("stripped_cinnamon_wood", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(DyeColor.BLACK).instrument(NoteBlockInstrument.BASS).strength(2)
                    .sound(SoundType.WOOD).ignitedByLava()));

    public static final RegistrySupplier<Block> CINNAMON_PLANKS = AbyssalDecor.BLOCKS.register("cinnamon_planks", () -> planks(DyeColor.BLACK.getMapColor()));

    public static final RegistrySupplier<StairBlock> CINNAMON_STAIRS = AbyssalDecor.BLOCKS.register("cinnamon_stairs",
            () -> stairs(CINNAMON_PLANKS.get()));

    public static final RegistrySupplier<SlabBlock> CINNAMON_SLAB = AbyssalDecor.BLOCKS.register("cinnamon_slab", () -> slab(CINNAMON_PLANKS.get()));

    public static final RegistrySupplier<ButtonBlock> CINNAMON_BUTTON = AbyssalDecor.BLOCKS.register("cinnamon_button",() -> Blocks.woodenButton(ModBlockSetTypes.CINNAMON));

    public static final RegistrySupplier<RotatedPillarBlock> CINNAMON_TRIM = AbyssalDecor.BLOCKS.register("cinnamon_trim",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASS).strength(2)
                    .sound(SoundType.WOOD).ignitedByLava()));

    public static final RegistrySupplier<DoorBlock> CINNAMON_DOOR = AbyssalDecor.BLOCKS.register("cinnamon_door",() ->
            woodDoor(CINNAMON_PLANKS.get(),ModBlockSetTypes.CINNAMON));

    public static final RegistrySupplier<TrapDoorBlock> CINNAMON_TRAPDOOR = AbyssalDecor.BLOCKS.register("cinnamon_trapdoor",() ->
            woodTrapdoor(ModBlockSetTypes.CINNAMON));

    public static final RegistrySupplier<PressurePlateBlock> CINNAMON_PRESSURE_PLATE = AbyssalDecor.BLOCKS.register("cinnamon_pressure_plate",() ->
            woodPressurePlate(CINNAMON_PLANKS.get(),ModBlockSetTypes.CINNAMON));

    public static final RegistrySupplier<FenceBlock> CINNAMON_FENCE = AbyssalDecor.BLOCKS.register("cinnamon_fence",() ->
            woodFence(CINNAMON_PLANKS.get()));

    public static final RegistrySupplier<FenceGateBlock> CINNAMON_FENCE_GATE = AbyssalDecor.BLOCKS.register("cinnamon_fence_gate",() ->
            woodFenceGate(CINNAMON_PLANKS.get(),ModWoodTypes.CINNAMON));

    public static final RegistrySupplier<StandingSignBlock> CINNAMON_SIGN = AbyssalDecor.BLOCKS.register("cinnamon_sign",() ->
            sign(CINNAMON_PLANKS.get(),ModWoodTypes.CINNAMON));

    public static final RegistrySupplier<WallSignBlock> CINNAMON_WALL_SIGN = AbyssalDecor.BLOCKS.register("cinnamon_wall_sign",() ->
            wallSign(CINNAMON_SIGN.get(),ModWoodTypes.CINNAMON));

    public static final RegistrySupplier<CeilingHangingSignBlock> CINNAMON_HANGING_SIGN = AbyssalDecor.BLOCKS.register("cinnamon_hanging_sign",() ->
            hangingSign(CINNAMON_PLANKS.get(),ModWoodTypes.CINNAMON));

    public static final RegistrySupplier<WallHangingSignBlock> CINNAMON_WALL_HANGING_SIGN = AbyssalDecor.BLOCKS.register("cinnamon_wall_hanging_sign",() ->
            wallHangingSign(CINNAMON_HANGING_SIGN.get(),ModWoodTypes.CINNAMON));

    public static final RegistrySupplier<PostBlock> CINNAMON_POST = AbyssalDecor.BLOCKS.register("cinnamon_post",() ->
            new PostBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).strength(1, 10).noOcclusion()));

    public static final RegistrySupplier<Block> CINNAMON_PANELING = AbyssalDecor.BLOCKS.register("cinnamon_paneling",() ->
            new Block(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<LeavesBlock> CINNAMON_LEAVES = AbyssalDecor.BLOCKS.register("cinnamon_leaves",() ->
            cinnamonLeaves(SoundType.GRASS,false));

    public static final RegistrySupplier<LeavesBlock> FLOWERING_CINNAMON_LEAVES = AbyssalDecor.BLOCKS.register("flowering_cinnamon_leaves",() ->
            cinnamonLeaves(SoundType.GRASS,true));

    public static LeavesBlock cinnamonLeaves(SoundType type,boolean flowering) {
        return new CinnamonLeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F)
                .randomTicks().sound(type).noOcclusion().isValidSpawn(Blocks::ocelotOrParrot).isSuffocating(Blocks::never)
                .isViewBlocking(Blocks::never).ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(Blocks::never),flowering);
    }

    public static final RegistrySupplier<CinnamonSaplingBlock> CINNAMON_BUSH = AbyssalDecor.BLOCKS.register("cinnamon_bush",() ->
           new CinnamonSaplingBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));

    public static final RegistrySupplier<TallCinammonSaplingBlock> TALL_CINNAMON_BUSH = AbyssalDecor.BLOCKS.register("tall_cinnamon_bush",() ->
            new TallCinammonSaplingBlock(new CinnamonTreeGrower(),BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks()
                    .instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));

    public static final RegistrySupplier<RotatedPillarBlock> CINNAMON_SHINGLES = AbyssalDecor.BLOCKS.register("cinnamon_shingles", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(DyeColor.BLACK).instrument(NoteBlockInstrument.BASS).strength(2)
                    .sound(SoundType.WOOD).ignitedByLava()));

    public static final RegistrySupplier<SlabBlock> CINNAMON_SHINGLE_SLAB = AbyssalDecor.BLOCKS.register("cinnamon_shingle_slab", () -> slab(CINNAMON_SHINGLES.get()));
    public static final RegistrySupplier<StairBlock> CINNAMON_SHINGLE_STAIRS = AbyssalDecor.BLOCKS.register("cinnamon_shingle_stairs", () -> stairs(CINNAMON_SHINGLES.get()));

    public static final RegistrySupplier<RotatedPillarBlock> MOSSY_CINNAMON_SHINGLES = AbyssalDecor.BLOCKS.register("mossy_cinnamon_shingles", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(DyeColor.BLACK).instrument(NoteBlockInstrument.BASS).strength(2)
                    .sound(SoundType.WOOD).ignitedByLava()));

    public static final RegistrySupplier<SlabBlock> MOSSY_CINNAMON_SHINGLE_SLAB = AbyssalDecor.BLOCKS.register("mossy_cinnamon_shingle_slab",
            () -> slab(MOSSY_CINNAMON_SHINGLES.get()));
    public static final RegistrySupplier<StairBlock> MOSSY_CINNAMON_SHINGLE_STAIRS = AbyssalDecor.BLOCKS.register("mossy_cinnamon_shingle_stairs",
            () -> stairs(MOSSY_CINNAMON_SHINGLES.get()));

    public static final RegistrySupplier<CrackedBlock> CRACKED_BRICKS = AbyssalDecor.BLOCKS.register("cracked_bricks",
            () -> new CrackedBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS)));

    public static final RegistrySupplier<Block> CHISELED_BRICK = AbyssalDecor.BLOCKS.register("chiseled_brick",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));

    public static final RegistrySupplier<Block> BRICK_MOSAIC = AbyssalDecor.BLOCKS.register("brick_mosaic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));

    public static final RegistrySupplier<BrickCapBlock> BRICK_CAP = AbyssalDecor.BLOCKS.register("brick_cap",
            () -> new  BrickCapBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS)));

    public static final RegistrySupplier<SmallBarsBlock> SMALL_CLEAN_IRON_BARS = AbyssalDecor.BLOCKS.register("small_clean_iron_bars",() ->
            smallBars());

    public static final RegistrySupplier<SmallBarsCornerBlock> SMALL_CLEAN_IRON_BARS_CORNER = AbyssalDecor.BLOCKS.register("small_clean_iron_bars_corner",() ->
            new SmallBarsCornerBlock(BlockBehaviour.Properties.copy(SMALL_CLEAN_IRON_BARS.get())));

    public static final RegistrySupplier<FaceAttachedBlock> BRICK_CORNERSTONE = AbyssalDecor.BLOCKS.register("brick_cornerstone",
            () -> new FaceAttachedBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS),true));

    public static final RegistrySupplier<FaceAttachedBlock> BRICK_PILLAR = AbyssalDecor.BLOCKS.register("brick_pillar",
            () -> new FaceAttachedBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS),true));

    public static final RegistrySupplier<TrapDoorBlock> BRICK_TRAPDOOR = AbyssalDecor.BLOCKS.register("brick_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS),ModBlockSetTypes.BRICK));

    public static final RegistrySupplier<RotatedPillarBlock> EFFERVESCENT_PILLAR = AbyssalDecor.BLOCKS.register("effervescent_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS)));

    public static final RegistrySupplier<Block> EFFERVESCENCE = AbyssalDecor.BLOCKS.register("effervescence",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));

    public static final RegistrySupplier<Block> EFFERVESCENT_TILES = AbyssalDecor.BLOCKS.register("effervescent_tiles",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));

    public static final RegistrySupplier<Block> POLISHED_EFFERVESCENCE = AbyssalDecor.BLOCKS.register("polished_effervescence",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));

    public static final RegistrySupplier<IronBarsBlock> WHITEWOOD_PICKET_FENCE = AbyssalDecor.BLOCKS.register("whitewood_picket_fence",
            () -> new IronBarsBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).strength(2, 3)));

    public static final RegistrySupplier<DirectionalBlock> TRASH_BAG = AbyssalDecor.BLOCKS.register("trash_bag",() ->
            new AbstractDirectionalBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS)
                    .sound(ModSoundTypes.TRASH_BAG).strength(2.0F).noOcclusion()));

    public static final RegistrySupplier<Block> FROSTED_GLASS = AbyssalDecor.BLOCKS.register("frosted_glass",() ->
            new FrostedGlassBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.HAT).mapColor(MapColor.TERRACOTTA_WHITE)
                    .sound(SoundType.GLASS).strength(3.0F, 10.0F)
                    .requiresCorrectToolForDrops().noOcclusion()));

    public static final RegistrySupplier<FancierIronBarsBlock> FROSTED_GLASS_PANE = AbyssalDecor.BLOCKS.register("frosted_glass_pane",
            () -> new FancierIronBarsBlock(panes()));

    public static final RegistrySupplier<FancyIronBarsBlock> STONE_BARS = AbyssalDecor.BLOCKS.register("stone_bars",
            () -> new FancyIronBarsBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.METAL).strength(2.0F, 10.0F).noOcclusion()));

    public static final RegistrySupplier<SmallBarsBlock> SMALL_STONE_BARS = AbyssalDecor.BLOCKS.register("small_stone_bars",
            () -> smallBars());

    public static final RegistrySupplier<SmallBarsCornerBlock> SMALL_STONE_BARS_CORNER = AbyssalDecor.BLOCKS.register("small_stone_bars_corner",
            () -> new SmallBarsCornerBlock(BlockBehaviour.Properties.copy(SMALL_STONE_BARS.get())));

    public static final RegistrySupplier<TrapDoorBlock> IRON_VENT_TRAPDOOR = AbyssalDecor.BLOCKS.register("iron_vent_trapdoor",() -> ironTrapdoor(ModBlockSetTypes.PUSH_IRON));

    public static final RegistrySupplier<HorizontalDirectionalBlock> FOGHORN = AbyssalDecor.BLOCKS.register("foghorn",
            () -> new FoghornBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.LANTERN).strength(5.0F, 10.0F).noOcclusion()));

    public static final RegistrySupplier<LampBlock> DEEPBRONZE_LANTERN = AbyssalDecor.BLOCKS.register("deepbronze_lantern",() -> new LampBlock(lamp()));

    public static final RegistrySupplier<CropBlock> SPIDERCORN = AbyssalDecor.BLOCKS.register("spidercorn",() -> new SpiderCornCropBlock(
            BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY).dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ)));

    public static final RegistrySupplier<CropBlock> BOG_APPLE_LEAVES = AbyssalDecor.BLOCKS.register("bog_apple_leaves",() -> new BogAppleLeavesBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY).dynamicShape()));

    public static final RegistrySupplier<Block> HEART_OF_THE_SEA = AbyssalDecor.BLOCKS.register("heart_of_the_sea",() -> new PearlBlock(BlockBehaviour.Properties.of().strength(.5f)));

    static Block planks(MapColor mapColor) {
        return new Block(BlockBehaviour.Properties.of().mapColor(mapColor)
                .instrument(NoteBlockInstrument.BASS).strength(2, 3).sound(SoundType.WOOD).ignitedByLava());
    }

    static StairBlock stairs(Block parent) {
        return new StairBlock(parent.defaultBlockState(), BlockBehaviour.Properties.copy(parent));
    }

    static SlabBlock slab(Block parent) {
        return new SlabBlock(BlockBehaviour.Properties.copy(parent));
    }

    static DoorBlock woodDoor(Block parent,BlockSetType blockSetType) {
       return new DoorBlock(BlockBehaviour.Properties.of().mapColor(parent.defaultMapColor()).instrument(NoteBlockInstrument.BASS)
                .strength(3).noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY), blockSetType);
    }
    static TrapDoorBlock woodTrapdoor(BlockSetType blockSetType) {
        return new TrapDoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(3)
                .noOcclusion().isValidSpawn(Blocks::never).ignitedByLava(), blockSetType);
    }

    static TrapDoorBlock ironTrapdoor(BlockSetType blockSetType) {
        return new TrapDoorBlock(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASS).strength(3).requiresCorrectToolForDrops()
                .noOcclusion().isValidSpawn(Blocks::never).ignitedByLava(), blockSetType);
    }

    static WallBlock wall(Block parent) {
        return  new WallBlock(BlockBehaviour.Properties.copy(parent).forceSolidOn());
    }

    static PressurePlateBlock woodPressurePlate(Block parent,BlockSetType blockSetType) {
        return new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of().mapColor(parent.defaultMapColor()).forceSolidOn()
                .instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), blockSetType);
    }

    static FenceBlock woodFence(Block parent) {
        return new FenceBlock(BlockBehaviour.Properties.of().mapColor(parent.defaultMapColor()).forceSolidOn()
                .instrument(NoteBlockInstrument.BASS).strength(2, 3).sound(SoundType.WOOD).ignitedByLava());
    }

    static FenceGateBlock woodFenceGate(Block parent,WoodType woodType) {
        return new FenceGateBlock(BlockBehaviour.Properties.of().mapColor(parent.defaultMapColor()).forceSolidOn()
                .instrument(NoteBlockInstrument.BASS).strength(2, 3).ignitedByLava(), woodType);
    }

    static StandingSignBlock sign(Block parent, WoodType woodType){
        return new StandingSignBlock(BlockBehaviour.Properties.of().mapColor(parent.defaultMapColor()).instrument(NoteBlockInstrument.BASS)
                .forceSolidOn().noCollission().strength(1), woodType);
    }

    static WallSignBlock wallSign(StandingSignBlock parentSign,WoodType woodType) {
        return new WallSignBlock(BlockBehaviour.Properties.of().mapColor(parentSign.defaultMapColor()).instrument(NoteBlockInstrument.BASS).forceSolidOn().noCollission().strength(1).dropsLike(parentSign), woodType);
    }

    static CeilingHangingSignBlock hangingSign(Block parentLog, WoodType woodType){
        return new CeilingHangingSignBlock(BlockBehaviour.Properties.of().mapColor(parentLog.defaultMapColor())
                .forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1).ignitedByLava(), woodType);
    }

    static WallHangingSignBlock wallHangingSign(CeilingHangingSignBlock parentSign, WoodType woodType) {
        return new WallHangingSignBlock(BlockBehaviour.Properties.of().mapColor(parentSign.defaultMapColor()).forceSolidOn()
                .instrument(NoteBlockInstrument.BASS).noCollission().strength(1).ignitedByLava().dropsLike(parentSign), woodType);
    }


    static BlockBehaviour.Properties glassLike() {
        return BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.HAT).strength(0.3F)
                .sound(SoundType.GLASS).noOcclusion().isValidSpawn(Blocks::never).isRedstoneConductor(Blocks::never)
                .isSuffocating(Blocks::never).isViewBlocking(Blocks::never);
    }

    static BlockBehaviour.Properties panes() {
        return BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.HAT).strength(0.3F).sound(SoundType.GLASS).noOcclusion();
    }

    public static void register() {
        AbyssalDecor.BLOCKS.register();
    }

    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return state -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }
}
