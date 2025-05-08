package net.starrysock.abyssaldecor.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.Shapes;
import net.starrysock.abyssaldecor.AbyssalDecor;
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
            Block.box(0,0,0,16,2,2)));
    public static RegistrySupplier<Block> IRON_LAMP = AbyssalDecor.BLOCKS.register("iron_lamp", () -> new LampBlock(lamp(),Shapes.or(Block.box(6.5,2,6.5,9.5,16,9.5),Block.box(3,6,3,13,8,13))));
    public static RegistrySupplier<Block> WALL_IRON_LAMP = AbyssalDecor.BLOCKS.register("wall_iron_lamp", () -> new HorizontalLampBlock(lamp().dropsLike(IRON_LAMP.get()), null));

    static BlockBehaviour.Properties lamp() {
        return BlockBehaviour.Properties.of().lightLevel(litBlockEmission(15)).strength(0.3F).sound(SoundType.GLASS);
    }

    public static RegistrySupplier<Block> FLOWER_LAMP = AbyssalDecor.BLOCKS.register("flower_lamp", () -> new HorizontalLampBlock(lamp(), null));
    public static RegistrySupplier<Block> FROSTED_LAMP = AbyssalDecor.BLOCKS.register("frosted_lamp", () -> new ToggleableDirectionalLampBlock(lamp()));
    public static RegistrySupplier<Block> QUARTZ_LAMP = AbyssalDecor.BLOCKS.register("quartz_lamp", () -> new LampBlock(lamp(), Shapes.or(Block.box(5,0,5,11,16,11),Block.box(2,10,2,14,12,14))));
    public static RegistrySupplier<Block> WALL_QUARTZ_LAMP = AbyssalDecor.BLOCKS.register("wall_quartz_lamp", () -> new HorizontalLampBlock(lamp().dropsLike(QUARTZ_LAMP.get()), null));
    public static RegistrySupplier<Block> CEILING_QUARTZ_LAMP = AbyssalDecor.BLOCKS.register("ceiling_quartz_lamp", () -> new LampBlock(lamp().dropsLike(QUARTZ_LAMP.get()),Shapes.or(Block.box(6.5,2,6.5,9.5,16,9.5),Block.box(5,4,5,11,9,11),Block.box(2,10,2,14,12,14))));


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
    public static final RegistrySupplier<Block> SOLAR_ROD = AbyssalDecor.BLOCKS.register("solar_rod",() -> new DirectionalRodBlock(BlockBehaviour.Properties.of().forceSolidOff().instabreak().lightLevel(blockState -> 14).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistrySupplier<Block> STELLAR_ROD = AbyssalDecor.BLOCKS.register("stellar_rod",() -> new DirectionalRodBlock(BlockBehaviour.Properties.of().forceSolidOff().instabreak().lightLevel(blockState -> 14).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistrySupplier<Block> TERRESTRIAL_ROD = AbyssalDecor.BLOCKS.register("terrestrial_rod",() -> new DirectionalRodBlock(BlockBehaviour.Properties.of().forceSolidOff().instabreak().lightLevel(blockState -> 14).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistrySupplier<Block> LUNAR_ROD = AbyssalDecor.BLOCKS.register("lunar_rod",() -> new DirectionalRodBlock(BlockBehaviour.Properties.of().forceSolidOff().instabreak().lightLevel(blockState -> 14).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistrySupplier<Block> ETHEREAL_ROD = AbyssalDecor.BLOCKS.register("ethereal_rod",() -> new DirectionalRodBlock(BlockBehaviour.Properties.of().forceSolidOff().instabreak().lightLevel(blockState -> 14).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistrySupplier<Block> HANGING_MOSS = AbyssalDecor.BLOCKS.register("hanging_moss",() -> new VineBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().randomTicks().strength(0.2f).sound(SoundType.VINE).ignitedByLava().pushReaction(PushReaction.DESTROY)));

    public static final RegistrySupplier<Block> DAFFODIL = AbyssalDecor.BLOCKS.register("daffodil",() -> new FlowerBlock(MobEffects.REGENERATION, 8, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
    public static final RegistrySupplier<Block> ASTER = AbyssalDecor.BLOCKS.register("aster",() -> new FlowerBlock(MobEffects.REGENERATION, 8, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
    public static final RegistrySupplier<Block> SNAPLEAF = AbyssalDecor.BLOCKS.register("snapleaf",() -> new FlowerBlock(MobEffects.REGENERATION, 8, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));

    public static final RegistrySupplier<Block> AMARANTH = AbyssalDecor.BLOCKS.register("amaranth",() -> new AmaranthBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY),ModTags.Blocks.AMARANTH_GROWABLE));
    public static final RegistrySupplier<DoublePlantBlock> TALL_AMARANTH = AbyssalDecor.BLOCKS.register("tall_amaranth",() -> new TallAmaranthBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.DESTROY)));
    public static final RegistrySupplier<Block> AMARANTH_CRATE = AbyssalDecor.BLOCKS.register("amaranth_crate",() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistrySupplier<Block> MUCKROOT = AbyssalDecor.BLOCKS.register("muckroot",() -> new MuckrootBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY),ModTags.Blocks.MUCKROOT_GROWABLE));

    public static final RegistrySupplier<Block> BARK_ORCHID = AbyssalDecor.BLOCKS.register("bark_orchid",() -> new BarkOrchidBlock(BlockBehaviour.Properties.of().sound(SoundType.GRASS).instabreak().noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));

    //todo wisteria and elder wisteria

    public static final RegistrySupplier<Block> WISTERIA_PETALS = AbyssalDecor.BLOCKS.register("wisteria_petals",() -> Blocks.leaves(SoundType.GRASS));
    public static final RegistrySupplier<Block> ELDER_WISTERIA_PETALS = AbyssalDecor.BLOCKS.register("elder_wisteria_petals",() -> Blocks.leaves(SoundType.GRASS));
    public static final RegistrySupplier<Block> ELDER_WISTERIA_LEAVES = AbyssalDecor.BLOCKS.register("elder_wisteria_leaves",() -> Blocks.leaves(SoundType.GRASS));

    public static final RegistrySupplier<RotatedPillarBlock> ANCIENT_BIRCH_LOG = AbyssalDecor.BLOCKS.register("ancient_birch_log",() -> Blocks.log(MapColor.SAND, MapColor.QUARTZ));
    public static final RegistrySupplier<RotatedPillarBlock> STRIPPED_ANCIENT_BIRCH_LOG = AbyssalDecor.BLOCKS.register("stripped_ancient_birch_log",() -> Blocks.log(MapColor.SAND, MapColor.QUARTZ));

    public static final RegistrySupplier<RotatedPillarBlock> FOXY_PILLAR = AbyssalDecor.BLOCKS.register("foxy_pillar",() -> Blocks.log(MapColor.PODZOL, MapColor.COLOR_BROWN));

    public static final RegistrySupplier<Block> SCRIMSHAW = AbyssalDecor.BLOCKS.register("scrimshaw",() -> new ScrimshawBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.LODESTONE).strength(2.0F, 10.0F).lightLevel((s) -> 4).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistrySupplier<Block> DESK_BELL = AbyssalDecor.BLOCKS.register("desk_bell",() -> new DeskBellBlock(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> STARFISH = AbyssalDecor.BLOCKS.register("starfish",() -> new StarfishBlock(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<Block> DRIED_STARFISH = AbyssalDecor.BLOCKS.register("dried_starfish",() -> new DriedStarfishBlock(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<Block> STARLIGHT = AbyssalDecor.BLOCKS.register("starlight",() -> new Block(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> LIFE_PRESERVER = AbyssalDecor.BLOCKS.register("life_preserver",() -> new AbstractDirectionalBlock(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<Block> WOOD_SUPPORT = AbyssalDecor.BLOCKS.register("wood_support",() -> new WoodSupportBlock(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<Block> SHIP_WHEEL = AbyssalDecor.BLOCKS.register("ship_wheel",() -> new AbstractDirectionalBlock(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<Block> WOODEN_DRAGON_HEAD = AbyssalDecor.BLOCKS.register("wooden_dragon_head",() -> new AbstractHorizontalBlock(BlockBehaviour.Properties.of()));
    public static final RegistrySupplier<Block> WOODEN_FROG = AbyssalDecor.BLOCKS.register("wooden_frog",() -> new AbstractHorizontalBlock(BlockBehaviour.Properties.of()));



    public static void register() {
        AbyssalDecor.BLOCKS.register();
    }

    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return (p_50763_) -> {
            return (Boolean)p_50763_.getValue(BlockStateProperties.LIT) ? lightValue : 0;
        };
    }
}
