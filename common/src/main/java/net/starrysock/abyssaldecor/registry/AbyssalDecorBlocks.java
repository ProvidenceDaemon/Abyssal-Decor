package net.starrysock.abyssaldecor.registry;

import dev.architectury.registry.registries.RegistrySupplier;
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
import net.starrysock.abyssaldecor.MixedBlock;
import net.starrysock.abyssaldecor.block.*;
import net.starrysock.abyssaldecor.content.abstraction.lamps.InteractibleRedstoneLampBlock;

import java.util.function.ToIntFunction;

public class AbyssalDecorBlocks {
    public static RegistrySupplier<Block> SEABRASS_ORE = AbyssalDecor.BLOCKS.register("seabrass_ore", () -> new FallingBlock(Block.Properties.copy(Blocks.GRAVEL)));

    //section: Lamps
    public static RegistrySupplier<Block> FRESNEL_LAMP = AbyssalDecor.BLOCKS.register("fresnel_lamp", () -> new InteractibleRedstoneLampBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));

    // Lightbulb [directional], Wall Bulb Lamp [wall mounted], Tube Lamp [tube], Iron Lamp [directional], Flower Lamp [wall mounted], Frosted Lamp [directional], Quartz Lamp [directional], Jade Lamp [directional], Seaglass Lamp [directional], Blaze Lamp [wall mounted], Rainbow Lamp [directional]
    public static RegistrySupplier<Block> LIGHTBULB = AbyssalDecor.BLOCKS.register("lightbulb", () -> new ToggleableDirectionalLampBlock(lamp()));
    public static RegistrySupplier<Block> WALL_BULB_LAMP = AbyssalDecor.BLOCKS.register("wall_bulb_lamp", () -> new HorizontalLampBlock(lamp(), null));
    public static RegistrySupplier<Block> TUBE_LAMP = AbyssalDecor.BLOCKS.register("tube_lamp", () -> new HorizontalLampBlock(lamp(),
            Block.box(0, 0, 0, 16, 2, 2)));
    public static RegistrySupplier<Block> IRON_LAMP = AbyssalDecor.BLOCKS.register("iron_lamp", () -> new LampBlock(lamp(), Shapes.or(Block.box(6.5, 2, 6.5, 9.5, 16, 9.5), Block.box(3, 6, 3, 13, 8, 13))));
    public static RegistrySupplier<Block> WALL_IRON_LAMP = AbyssalDecor.BLOCKS.register("wall_iron_lamp", () -> new HorizontalLampBlock(lamp().dropsLike(IRON_LAMP.get()), null));

    static BlockBehaviour.Properties lamp() {
        return BlockBehaviour.Properties.of().lightLevel(litBlockEmission(15)).strength(0.3F).sound(SoundType.GLASS);
    }

    public static RegistrySupplier<Block> FLOWER_LAMP = AbyssalDecor.BLOCKS.register("flower_lamp", () -> new HorizontalLampBlock(lamp(), null));
    public static RegistrySupplier<Block> FROSTED_LAMP = AbyssalDecor.BLOCKS.register("frosted_lamp", () -> new ToggleableDirectionalLampBlock(lamp()));
    public static RegistrySupplier<Block> QUARTZ_LAMP = AbyssalDecor.BLOCKS.register("quartz_lamp", () -> new LampBlock(lamp(), Shapes.or(Block.box(5, 0, 5, 11, 16, 11), Block.box(2, 10, 2, 14, 12, 14))));
    public static RegistrySupplier<Block> WALL_QUARTZ_LAMP = AbyssalDecor.BLOCKS.register("wall_quartz_lamp", () -> new HorizontalLampBlock(lamp().dropsLike(QUARTZ_LAMP.get()), null));
    public static RegistrySupplier<Block> CEILING_QUARTZ_LAMP = AbyssalDecor.BLOCKS.register("ceiling_quartz_lamp", () -> new LampBlock(lamp().dropsLike(QUARTZ_LAMP.get()), Shapes.or(Block.box(6.5, 2, 6.5, 9.5, 16, 9.5), Block.box(5, 4, 5, 11, 9, 11), Block.box(2, 10, 2, 14, 12, 14))));


    public static RegistrySupplier<Block> JADE_LAMP = AbyssalDecor.BLOCKS.register("jade_lamp", () -> new HorizontalLampBlock(lamp(), null));
    public static RegistrySupplier<Block> WALL_JADE_LAMP = AbyssalDecor.BLOCKS.register("wall_jade_lamp", () -> new HorizontalLampBlock(lamp().dropsLike(JADE_LAMP.get()), null));


    public static RegistrySupplier<Block> SEAGLASS_LAMP = AbyssalDecor.BLOCKS.register("seaglass_lamp", () -> new ToggleableDirectionalLampBlock(lamp()));
    public static RegistrySupplier<Block> BLAZE_LAMP = AbyssalDecor.BLOCKS.register("blaze_lamp", () -> new BlazeLampBlock(BlockBehaviour.Properties.of().lightLevel(s -> 15).strength(0.3F).sound(SoundType.GLASS)));
    public static RegistrySupplier<Block> RAINBOW_LAMP = AbyssalDecor.BLOCKS.register("rainbow_lamp", () -> new DirectionalLampBlock(BlockBehaviour.Properties.of().lightLevel(s -> 15).strength(0.3F).sound(SoundType.GLASS)));
    public static RegistrySupplier<Block> BULKHEAD_LAMP = AbyssalDecor.BLOCKS.register("bulkhead_lamp", () -> new ButtonLampBlock(lamp()));

    public static RegistrySupplier<Block> JADE_LANTERN = AbyssalDecor.BLOCKS.register("jade_lantern", () -> new LampBlock(lamp()));
    public static RegistrySupplier<Block> ABYSSAL_LANTERN = AbyssalDecor.BLOCKS.register("abyssal_lantern", () -> new LampBlock(lamp()));
    public static RegistrySupplier<Block> IRON_LANTERN = AbyssalDecor.BLOCKS.register("iron_lantern", () -> new LampBlock(lamp()));
    //end section

    //section : Barriers
    public static RegistrySupplier<Block> VELVET_BARRIER = AbyssalDecor.BLOCKS.register("velvet_barrier", () -> new BarrierPoleBlock(Block.Properties.of().pushReaction(PushReaction.DESTROY)));
    public static RegistrySupplier<Block> IRON_BARRIER = AbyssalDecor.BLOCKS.register("iron_barrier", () -> new BarrierPoleBlock(Block.Properties.copy(Blocks.IRON_BLOCK)));
    public static RegistrySupplier<Block> ROPE_BARRIER = AbyssalDecor.BLOCKS.register("rope_barrier", () -> new BarrierPoleBlock(Block.Properties.copy(Blocks.OAK_PLANKS)));
    public static RegistrySupplier<Block> BARBED_WIRE_BARRIER = AbyssalDecor.BLOCKS.register("barbed_wire_barrier", () -> new BarrierPoleBlock(Block.Properties.copy(Blocks.IRON_BLOCK)));

    // public static RegistrySupplier<Block> VELVET_BARRIER_RIBBON = AbyssalDecor.BLOCKS.register("velvet_barrier_ribbon", () -> new BarrierTieBlock(Block.Properties.copy(Blocks.RED_WOOL), false));
    // public static RegistrySupplier<Block> IRON_BARRIER_RIBBON = AbyssalDecor.BLOCKS.register("iron_barrier_ribbon", () -> new BarrierTieBlock(Block.Properties.copy(Blocks.GRAY_WOOL), false));
    // public static RegistrySupplier<Block> ROPE_BARRIER_RIBBON = AbyssalDecor.BLOCKS.register("rope_barrier_ribbon", () -> new BarrierTieBlock(Block.Properties.copy(Blocks.BROWN_WOOL), false));
    // public static RegistrySupplier<Block> BARBED_WIRE_RIBBON = AbyssalDecor.BLOCKS.register("barbed_wire_barrier_ribbon", () -> new BarrierTieBlock(Block.Properties.copy(Blocks.IRON_BARS), true));

    //blocks
    public static final RegistrySupplier<Block> SOLAR_ROD = AbyssalDecor.BLOCKS.register("solar_rod", () -> new DirectionalRodBlock(BlockBehaviour.Properties.of().forceSolidOff().instabreak().lightLevel(blockState -> 14).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistrySupplier<Block> STELLAR_ROD = AbyssalDecor.BLOCKS.register("stellar_rod", () -> new DirectionalRodBlock(BlockBehaviour.Properties.of().forceSolidOff().instabreak().lightLevel(blockState -> 14).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistrySupplier<Block> TERRESTRIAL_ROD = AbyssalDecor.BLOCKS.register("terrestrial_rod", () -> new DirectionalRodBlock(BlockBehaviour.Properties.of().forceSolidOff().instabreak().lightLevel(blockState -> 14).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistrySupplier<Block> LUNAR_ROD = AbyssalDecor.BLOCKS.register("lunar_rod", () -> new DirectionalRodBlock(BlockBehaviour.Properties.of().forceSolidOff().instabreak().lightLevel(blockState -> 14).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistrySupplier<Block> ETHEREAL_ROD = AbyssalDecor.BLOCKS.register("ethereal_rod", () -> new DirectionalRodBlock(BlockBehaviour.Properties.of().forceSolidOff().instabreak().lightLevel(blockState -> 14).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistrySupplier<Block> HANGING_MOSS = AbyssalDecor.BLOCKS.register("hanging_moss", () -> new VineBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().randomTicks().strength(0.2f).sound(SoundType.VINE).ignitedByLava().pushReaction(PushReaction.DESTROY)));

    public static final RegistrySupplier<Block> DAFFODIL = AbyssalDecor.BLOCKS.register("daffodil", () -> new FlowerBlock(MobEffects.REGENERATION, 8, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
    public static final RegistrySupplier<Block> ASTER = AbyssalDecor.BLOCKS.register("aster", () -> new FlowerBlock(MobEffects.REGENERATION, 8, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
    public static final RegistrySupplier<Block> SNAPLEAF = AbyssalDecor.BLOCKS.register("snapleaf", () -> new FlowerBlock(MobEffects.REGENERATION, 8, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));

    public static final RegistrySupplier<Block> AMARANTH = AbyssalDecor.BLOCKS.register("amaranth", () -> new AmaranthBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY), ModTags.Blocks.AMARANTH_GROWABLE));
    public static final RegistrySupplier<DoublePlantBlock> TALL_AMARANTH = AbyssalDecor.BLOCKS.register("tall_amaranth", () -> new TallAmaranthBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.DESTROY)));
    public static final RegistrySupplier<Block> AMARANTH_CRATE = AbyssalDecor.BLOCKS.register("amaranth_crate", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistrySupplier<Block> MUCKROOT = AbyssalDecor.BLOCKS.register("muckroot", () -> new MuckrootBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY), ModTags.Blocks.MUCKROOT_GROWABLE));

    public static final RegistrySupplier<Block> BARK_ORCHID = AbyssalDecor.BLOCKS.register("bark_orchid", () -> new BarkOrchidBlock(BlockBehaviour.Properties.of().sound(SoundType.GRASS).instabreak().noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));

    //todo wisteria and elder wisteria

    public static final RegistrySupplier<Block> WISTERIA_PETALS = AbyssalDecor.BLOCKS.register("wisteria_petals", () -> Blocks.leaves(SoundType.GRASS));
    public static final RegistrySupplier<Block> ELDER_WISTERIA_PETALS = AbyssalDecor.BLOCKS.register("elder_wisteria_petals", () -> Blocks.leaves(SoundType.GRASS));
    public static final RegistrySupplier<Block> ELDER_WISTERIA_LEAVES = AbyssalDecor.BLOCKS.register("elder_wisteria_leaves", () -> Blocks.leaves(SoundType.GRASS));

    public static final RegistrySupplier<RotatedPillarBlock> ANCIENT_BIRCH_LOG = AbyssalDecor.BLOCKS.register("ancient_birch_log", () -> Blocks.log(MapColor.SAND, MapColor.QUARTZ));
    public static final RegistrySupplier<RotatedPillarBlock> STRIPPED_ANCIENT_BIRCH_LOG = AbyssalDecor.BLOCKS.register("stripped_ancient_birch_log", () -> Blocks.log(MapColor.SAND, MapColor.QUARTZ));

    public static final RegistrySupplier<RotatedPillarBlock> FOXY_PILLAR = AbyssalDecor.BLOCKS.register("foxy_pillar", () -> Blocks.log(MapColor.PODZOL, MapColor.COLOR_BROWN));

    public static final RegistrySupplier<Block> SCRIMSHAW = AbyssalDecor.BLOCKS.register("scrimshaw", () -> new ScrimshawBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.LODESTONE)
            .strength(2.0F, 10.0F).lightLevel((s) -> 4).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistrySupplier<Block> DESK_BELL = AbyssalDecor.BLOCKS.register("desk_bell", () -> new DeskBellBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> STARFISH = AbyssalDecor.BLOCKS.register("starfish", () -> new StarfishBlock(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<Block> DRIED_STARFISH = AbyssalDecor.BLOCKS.register("dried_starfish", () -> new DriedStarfishBlock(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<Block> STARLIGHT = AbyssalDecor.BLOCKS.register("starlight", () -> new Block(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> LIFE_PRESERVER = AbyssalDecor.BLOCKS.register("life_preserver", () -> new AbstractDirectionalBlock(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<Block> WOOD_SUPPORT = AbyssalDecor.BLOCKS.register("wood_support", () -> new WoodSupportBlock(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<Block> SHIP_WHEEL = AbyssalDecor.BLOCKS.register("ship_wheel", () -> new AbstractDirectionalBlock(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<Block> WOODEN_DRAGON_HEAD = AbyssalDecor.BLOCKS.register("wooden_dragon_head", () -> new AbstractHorizontalBlock(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<Block> WOODEN_FROG = AbyssalDecor.BLOCKS.register("wooden_frog", () -> new AbstractHorizontalBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> LION_STATUE = AbyssalDecor.BLOCKS.register("lion_statue", () -> new LionStatueBlock(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<Block> GARGOYLE = AbyssalDecor.BLOCKS.register("gargoyle", () -> new HorizontalDoubleBlock(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<Block> NITHING_POLE = AbyssalDecor.BLOCKS.register("nithing_pole", () -> new LionStatueBlock(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<Block> TELESCOPE = AbyssalDecor.BLOCKS.register("telescope", () -> new LionStatueBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> HANGING_WEB = AbyssalDecor.BLOCKS.register("hanging_web",
            () -> new WebBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).forceSolidOn().noCollission()
                    .requiresCorrectToolForDrops().strength(4.0F).pushReaction(PushReaction.DESTROY)));

    public static final RegistrySupplier<Block> WALL_HANGING_WEB = AbyssalDecor.BLOCKS.register("wall_hanging_web",
            () -> new WallHangingWebBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).forceSolidOn().noCollission()
                    .requiresCorrectToolForDrops().strength(4.0F).pushReaction(PushReaction.DESTROY).dropsLike(HANGING_WEB.get())));

    public static final RegistrySupplier<Block> DANGLING_WEB = AbyssalDecor.BLOCKS.register("dangling_web", () -> new HangingDoubleBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).forceSolidOn().noCollission()
            .requiresCorrectToolForDrops().strength(4.0F).pushReaction(PushReaction.DESTROY)));

    public static final RegistrySupplier<Block> WALL_DANGLING_WEB = AbyssalDecor.BLOCKS.register("wall_dangling_web", () -> new WallHangingWebBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).forceSolidOn().noCollission()
            .requiresCorrectToolForDrops().strength(4.0F).pushReaction(PushReaction.DESTROY).dropsLike(DANGLING_WEB.get())));

    public static final RegistrySupplier<Block> PRISMARINE_CRYSTAL_BLOCK = AbyssalDecor.BLOCKS.register("prismarine_crystal_block", () -> new GlassBlock(glassLike()));

    public static final RegistrySupplier<IronBarsBlock> PRISMARINE_CRYSTAL_PANE = AbyssalDecor.BLOCKS.register("prismarine_crystal_pane",
            () -> new IronBarsBlock(panes()));

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
            new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASS).strength(2.0F)
                    .sound(SoundType.WOOD).ignitedByLava()));

    public static final RegistrySupplier<Block> WHITEWOOD_PLANKS = AbyssalDecor.BLOCKS.register("whitewood_planks", () -> planks(DyeColor.WHITE.getMapColor()));

    public static final RegistrySupplier<StairBlock> WHITEWOOD_STAIRS = AbyssalDecor.BLOCKS.register("whitewood_stairs",
            () -> stairs(WHITEWOOD_PLANKS.get()));

    public static final RegistrySupplier<SlabBlock> WHITEWOOD_SLAB = AbyssalDecor.BLOCKS.register("whitewood_slab", () -> slab(WHITEWOOD_PLANKS.get()));

    public static final RegistrySupplier<ButtonBlock> WHITEWOOD_BUTTON = AbyssalDecor.BLOCKS.register("whitewood_button",() -> Blocks.woodenButton(ModBlockSetTypes.WHITEWOOD));

    public static final RegistrySupplier<RotatedPillarBlock> WHITEWOOD_TRIM = AbyssalDecor.BLOCKS.register("whitewood_trim",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASS).strength(2.0F)
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

    ///////

    public static final RegistrySupplier<Block> WHITE_PEARL = AbyssalDecor.BLOCKS.register("white_pearl",() -> new AbstractDirectionalBlock(BlockBehaviour.Properties.of().strength(.5f)));

    public static final RegistrySupplier<Block> WHITE_PEARL_BLOCK = AbyssalDecor.BLOCKS.register("white_pearl_block",() -> new Block(BlockBehaviour.Properties.of().strength(.5f).requiresCorrectToolForDrops()));

    public static final RegistrySupplier<StairBlock> WHITE_PEARL_STAIRS = AbyssalDecor.BLOCKS.register("white_pearl_stairs",() -> stairs(WHITE_PEARL.get()));
    public static final RegistrySupplier<SlabBlock> WHITE_PEARL_SLAB = AbyssalDecor.BLOCKS.register("white_pearl_slab",() -> slab(WHITE_PEARL.get()));
    public static final RegistrySupplier<Block> CHISELED_WHITE_PEARL = AbyssalDecor.BLOCKS.register("chiseled_white_pearl",() -> new Block(BlockBehaviour.Properties.copy(WHITE_PEARL_BLOCK.get())));
    public static final RegistrySupplier<WallBlock> WHITE_PEARL_WALL = AbyssalDecor.BLOCKS.register("white_pearl_wall",() -> wall(WHITE_PEARL.get()));
    public static final RegistrySupplier<IronBarsBlock> WHITE_PEARL_BARS = AbyssalDecor.BLOCKS.register("white_pearl_bars",
            () ->new IronBarsBlock(panes()));

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

    ///////

    public static final RegistrySupplier<Block> SMOOTH_WHITE_PEARL_BLOCK = AbyssalDecor.BLOCKS.register("smooth_white_pearl_block",() -> new Block(BlockBehaviour.Properties.of().strength(.5f).requiresCorrectToolForDrops()));

    public static final RegistrySupplier<StairBlock> SMOOTH_WHITE_PEARL_STAIRS = AbyssalDecor.BLOCKS.register("smooth_white_pearl_stairs",() -> stairs(SMOOTH_WHITE_PEARL_BLOCK.get()));
    public static final RegistrySupplier<SlabBlock> SMOOTH_WHITE_PEARL_SLAB = AbyssalDecor.BLOCKS.register("smooth_white_pearl_slab",() -> slab(SMOOTH_WHITE_PEARL_BLOCK.get()));
    public static final RegistrySupplier<WallBlock> SMOOTH_WHITE_PEARL_WALL = AbyssalDecor.BLOCKS.register("smooth_white_pearl_wall",() -> wall(SMOOTH_WHITE_PEARL_BLOCK.get()));

    public static final RegistrySupplier<Block> WHITE_PEARL_TILES = AbyssalDecor.BLOCKS.register("white_pearl_tiles",() -> new Block(BlockBehaviour.Properties.of().strength(.5f).requiresCorrectToolForDrops()));
    public static final RegistrySupplier<CrackedBlock> CRACKED_PEARL_TILES = AbyssalDecor.BLOCKS.register("cracked_pearl_tiles",() -> new CrackedBlock(BlockBehaviour.Properties.of().strength(.5f).requiresCorrectToolForDrops()));
    public static final RegistrySupplier<Block> MIXED_PEARL_TILES = AbyssalDecor.BLOCKS.register("mixed_pearl_tiles",() -> new Block(BlockBehaviour.Properties.of().strength(.5f).requiresCorrectToolForDrops()));
    public static final RegistrySupplier<Block> STARRY_PEARL_TILES = AbyssalDecor.BLOCKS.register("starry_pearl_tiles",() -> new Block(BlockBehaviour.Properties.of().strength(.5f).requiresCorrectToolForDrops()));

    public static final RegistrySupplier<ClamBlock> CLAM = AbyssalDecor.BLOCKS.register("clam",() -> new ClamBlock(BlockBehaviour.Properties.of(),false));
    public static final RegistrySupplier<ClamBlock> CLAM_WITH_PEARL = AbyssalDecor.BLOCKS.register("clam_with_pearl",() -> new ClamBlock(BlockBehaviour.Properties.of(),true));

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

    ////////

    public static final RegistrySupplier<MixedBlock> MIXED_BRICKS = AbyssalDecor.BLOCKS.register("mixed_bricks",
            () -> new MixedBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

    public static final RegistrySupplier<MixedBlock> MOSSY_MIXED_BRICKS = AbyssalDecor.BLOCKS.register("mossy_mixed_bricks",
            () -> new MixedBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

    public static final RegistrySupplier<Block> BRITTLE_TUFF = AbyssalDecor.BLOCKS.register("brittle_tuff",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

    public static final RegistrySupplier<TrapDoorBlock> STONE_TRAPDOOR = AbyssalDecor.BLOCKS.register("stone_trapdoor",() -> woodTrapdoor(BlockSetType.STONE));
    public static final RegistrySupplier<TrapDoorBlock> MOSSY_STONE_TRAPDOOR = AbyssalDecor.BLOCKS.register("mossy_stone_trapdoor",() -> woodTrapdoor(BlockSetType.STONE));
    public static final RegistrySupplier<TrapDoorBlock> ORNATE_STONE_TRAPDOOR = AbyssalDecor.BLOCKS.register("ornate_stone_trapdoor",() -> woodTrapdoor(BlockSetType.STONE));

    public static final RegistrySupplier<IronSconceBlock> IRON_SCONCE = AbyssalDecor.BLOCKS.register("iron_sconce",
            () -> new IronSconceBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<IronBarsBlock> ORNATE_IRON_BARS = AbyssalDecor.BLOCKS.register("ornate_iron_bars",() -> ironBars());

    public static final RegistrySupplier<RotatedPillarBlock> CORRUGATED_IRON = AbyssalDecor.BLOCKS.register("corrugated_iron",() ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASS).strength(2.0F)
            .sound(SoundType.WOOD).ignitedByLava()));

    public static final RegistrySupplier<RotatedPillarBlock> IRON_PILLAR = AbyssalDecor.BLOCKS.register("iron_pillar",() ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASS).strength(2.0F)
                    .sound(SoundType.WOOD).ignitedByLava()));


    public static final RegistrySupplier<Block> IRON_DUCT = AbyssalDecor.BLOCKS.register("iron_duct",() ->
            new Block(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASS).strength(2.0F)
                    .sound(SoundType.WOOD).ignitedByLava()));

    public static final RegistrySupplier<DoorBlock> IRON_PUSH_DOOR = AbyssalDecor.BLOCKS.register("iron_push_door",() ->
            new DoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops()
                    .strength(5.0F).noOcclusion().pushReaction(PushReaction.DESTROY), ModBlockSetTypes.PUSH_IRON));


    static IronBarsBlock ironBars() {
        return new IronBarsBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion());
    }


    public static final RegistrySupplier<Block> IRON_PANEL = AbyssalDecor.BLOCKS.register("iron_panel",()
            -> new Block(BlockBehaviour.Properties.of().strength(5,6).requiresCorrectToolForDrops()));

    public static final RegistrySupplier<StairBlock> IRON_PANEL_STAIRS = AbyssalDecor.BLOCKS.register("iron_panel_stairs",() -> stairs(IRON_PANEL.get()));
    public static final RegistrySupplier<SlabBlock> IRON_PANEL_SLAB = AbyssalDecor.BLOCKS.register("iron_panel_slab",() -> slab(IRON_PANEL.get()));
    public static final RegistrySupplier<WallBlock> IRON_PANEL_WALL = AbyssalDecor.BLOCKS.register("iron_panel_wall",() -> wall(IRON_PANEL.get()));

    //industrial lever

    public static final RegistrySupplier<IronBarsBlock> DULL_IRON_BARS = AbyssalDecor.BLOCKS.register("dull_iron_bars",() -> ironBars());

    public static final RegistrySupplier<IronSconceBlock> DULL_IRON_SCONCE = AbyssalDecor.BLOCKS.register("dull_iron_sconce",() -> new IronSconceBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<IronBarsBlock> ORNATE_DULL_IRON_BARS = AbyssalDecor.BLOCKS.register("ornate_dull_iron_bars",() -> ironBars());
    public static final RegistrySupplier<DoorBlock> DULL_IRON_BAR_DOOR = AbyssalDecor.BLOCKS.register("dull_iron_bar_door",() ->
            new DoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops()
            .strength(5.0F).noOcclusion().pushReaction(PushReaction.DESTROY), ModBlockSetTypes.PUSH_IRON));

    public static final RegistrySupplier<TrapDoorBlock> DULL_IRON_BAR_TRAPDOOR = AbyssalDecor.BLOCKS.register("dull_iron_bar_trapdoor",() -> woodTrapdoor(BlockSetType.STONE));

    public static final RegistrySupplier<AbstractDirectionalBlock> IRON_BALL = AbyssalDecor.BLOCKS.register("iron_ball",() ->
            new AbstractDirectionalBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> DULL_IRON_POST = AbyssalDecor.BLOCKS.register("dull_iron_post",() ->
            new DullIronPostBlock(BlockBehaviour.Properties.of()));


    public static final RegistrySupplier<IronSconceBlock> GOLD_SCONCE = AbyssalDecor.BLOCKS.register("gold_sconce",
            () -> new IronSconceBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<IronBarsBlock> GOLD_BARS = AbyssalDecor.BLOCKS.register("gold_bars",() -> ironBars());

    ////////////////

    public static final RegistrySupplier<Block> BLOOD_CORAL_BUD = AbyssalDecor.BLOCKS.register("blood_coral_bud",() ->
            new BloodCoralBudBlock(BlockBehaviour.Properties.of()));

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

    public static final RegistrySupplier<RotatedPillarBlock> GLIDED_BLOOD_CORAL_PILLAR = AbyssalDecor.BLOCKS.register("glided_blood_coral_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<IronSconceBlock> BLOOD_CORAL_SCONCE = AbyssalDecor.BLOCKS.register("blood_coral_sconce",
            () -> new IronSconceBlock(BlockBehaviour.Properties.of()));


    //blood coral lantern

    public static final RegistrySupplier<DoorBlock> BLOOD_CORAL_DOOR = AbyssalDecor.BLOCKS.register("blood_coral_door",() -> woodDoor(SMOOTH_BLOOD_CORAL.get(),BlockSetType.STONE));
    public static final RegistrySupplier<TrapDoorBlock> BLOOD_CORAL_TRAPDOOR = AbyssalDecor.BLOCKS.register("blood_coral_trapdoor",() -> woodTrapdoor(BlockSetType.STONE));

    public static final RegistrySupplier<IronBarsBlock> BLOOD_CORAL_BARS = AbyssalDecor.BLOCKS.register("blood_coral_bars",() -> ironBars());

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

    public static final RegistrySupplier<AbstractHorizontalBlock> FLAKY_SCALES =
            AbyssalDecor.BLOCKS.register("flaky_scales",() -> new AbstractHorizontalBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> SLUMP_LIGHT =
            AbyssalDecor.BLOCKS.register("slump_light",() -> new Block(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> SHORTGILLS =
            AbyssalDecor.BLOCKS.register("shortgills",() -> new Block(BlockBehaviour.Properties.of()));

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
            () -> new Block(BlockBehaviour.Properties.of()));

    //////////

    public static final RegistrySupplier<Block> VELVET = AbyssalDecor.BLOCKS.register("velvet",() -> new Block(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<Block> GILDED_VELVET = AbyssalDecor.BLOCKS.register("gilded_velvet",() -> new Block(BlockBehaviour.Properties.of()));

    /////////


    public static final RegistrySupplier<RotatedPillarBlock> BLACKWOOD_LOG = AbyssalDecor.BLOCKS.register("blackwood_log", () -> Blocks.log(MapColor.SAND, MapColor.QUARTZ));
    public static final RegistrySupplier<RotatedPillarBlock> STRIPPED_BLACKWOOD_LOG = AbyssalDecor.BLOCKS.register("stripped_blackwood_log", () -> Blocks.log(MapColor.SAND, MapColor.QUARTZ));

    public static final RegistrySupplier<RotatedPillarBlock> BLACKWOOD_WOOD = AbyssalDecor.BLOCKS.register("blackwood_wood", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASS).strength(2.0F)
                    .sound(SoundType.WOOD).ignitedByLava()));

    public static final RegistrySupplier<Block> BLACKWOOD_PLANKS = AbyssalDecor.BLOCKS.register("blackwood_planks", () -> planks(DyeColor.WHITE.getMapColor()));

    public static final RegistrySupplier<StairBlock> BLACKWOOD_STAIRS = AbyssalDecor.BLOCKS.register("blackwood_stairs",
            () -> stairs(BLACKWOOD_PLANKS.get()));

    public static final RegistrySupplier<SlabBlock> BLACKWOOD_SLAB = AbyssalDecor.BLOCKS.register("blackwood_slab", () -> slab(BLACKWOOD_PLANKS.get()));

    public static final RegistrySupplier<ButtonBlock> BLACKWOOD_BUTTON = AbyssalDecor.BLOCKS.register("blackwood_button",() -> Blocks.woodenButton(ModBlockSetTypes.BLACKWOOD));

    public static final RegistrySupplier<RotatedPillarBlock> BLACKWOOD_TRIM = AbyssalDecor.BLOCKS.register("blackwood_trim",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASS).strength(2.0F)
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

    ////////////////////


    public static final RegistrySupplier<Block> BLACK_PEARL = AbyssalDecor.BLOCKS.register("black_pearl",() -> new AbstractDirectionalBlock(BlockBehaviour.Properties.of().strength(.5f)));

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

    ///////

    public static final RegistrySupplier<Block> SMOOTH_BLACK_PEARL_BLOCK = AbyssalDecor.BLOCKS.register("smooth_black_pearl_block",() -> new Block(BlockBehaviour.Properties.of().strength(.5f).requiresCorrectToolForDrops()));

    public static final RegistrySupplier<StairBlock> SMOOTH_BLACK_PEARL_STAIRS = AbyssalDecor.BLOCKS.register("smooth_black_pearl_stairs",() -> stairs(SMOOTH_BLACK_PEARL_BLOCK.get()));
    public static final RegistrySupplier<SlabBlock> SMOOTH_BLACK_PEARL_SLAB = AbyssalDecor.BLOCKS.register("smooth_black_pearl_slab",() -> slab(SMOOTH_BLACK_PEARL_BLOCK.get()));
    public static final RegistrySupplier<WallBlock> SMOOTH_BLACK_PEARL_WALL = AbyssalDecor.BLOCKS.register("smooth_black_pearl_wall",() -> wall(SMOOTH_BLACK_PEARL_BLOCK.get()));

    static Block planks(MapColor mapColor) {
        return new Block(BlockBehaviour.Properties.of().mapColor(mapColor)
                .instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava());
    }

    static StairBlock stairs(Block parent) {
        return new StairBlock(parent.defaultBlockState(), BlockBehaviour.Properties.copy(parent));
    }

    static SlabBlock slab(Block parent) {
        return new SlabBlock(BlockBehaviour.Properties.copy(parent));
    }

    static DoorBlock woodDoor(Block parent,BlockSetType blockSetType) {
       return new DoorBlock(BlockBehaviour.Properties.of().mapColor(parent.defaultMapColor()).instrument(NoteBlockInstrument.BASS)
                .strength(3.0F).noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY), blockSetType);
    }
    static TrapDoorBlock woodTrapdoor(BlockSetType blockSetType) {
        return new TrapDoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(3.0F)
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
                .instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava());
    }

    static FenceGateBlock woodFenceGate(Block parent,WoodType woodType) {
        return new FenceGateBlock(BlockBehaviour.Properties.of().mapColor(parent.defaultMapColor()).forceSolidOn()
                .instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava(), woodType);
    }

    static StandingSignBlock sign(Block parent, WoodType woodType){
        return new StandingSignBlock(BlockBehaviour.Properties.of().mapColor(parent.defaultMapColor()).instrument(NoteBlockInstrument.BASS)
                .forceSolidOn().noCollission().strength(1.0F), woodType);
    }

    static WallSignBlock wallSign(StandingSignBlock parentSign,WoodType woodType) {
        return new WallSignBlock(BlockBehaviour.Properties.of().mapColor(parentSign.defaultMapColor()).instrument(NoteBlockInstrument.BASS).forceSolidOn().noCollission().strength(1.0F).dropsLike(parentSign), woodType);
    }

    static CeilingHangingSignBlock hangingSign(Block parentLog, WoodType woodType){
        return new CeilingHangingSignBlock(BlockBehaviour.Properties.of().mapColor(parentLog.defaultMapColor())
                .forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava(), woodType);
    }

    static WallHangingSignBlock wallHangingSign(CeilingHangingSignBlock parentSign, WoodType woodType) {
        return new WallHangingSignBlock(BlockBehaviour.Properties.of().mapColor(parentSign.defaultMapColor()).forceSolidOn()
                .instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava().dropsLike(parentSign), woodType);
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
