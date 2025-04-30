package net.starrysock.abyssaldecor.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.starrysock.abyssaldecor.AbyssalDecor;
import net.starrysock.abyssaldecor.block.*;
import net.starrysock.abyssaldecor.content.abstraction.barriers.BarrierTieBlock;
import net.starrysock.abyssaldecor.content.abstraction.barriers.TiedBarrierBlock;
import net.starrysock.abyssaldecor.content.abstraction.lamps.DirectionalInteractibleLampBlock;
import net.starrysock.abyssaldecor.content.abstraction.lamps.InteractibleRedstoneLampBlock;
import net.starrysock.abyssaldecor.content.abstraction.lamps.WallMountedInteractibleLampBlock;
import net.starrysock.abyssaldecor.content.lamps.BulkheadLampBlock;
import net.starrysock.abyssaldecor.content.lamps.TubeLampBlock;

public class AbyssalDecorBlocks {
    public static RegistrySupplier<Block> SEABRASS_ORE = AbyssalDecor.BLOCKS.register("seabrass_ore", () -> new FallingBlock(Block.Properties.copy(Blocks.GRAVEL)));

    //section: Lamps
    public static RegistrySupplier<Block> FRESNEL_LAMP = AbyssalDecor.BLOCKS.register("fresnel_lamp", () -> new InteractibleRedstoneLampBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));

    // Lightbulb [directional], Wall Bulb Lamp [wall mounted], Tube Lamp [tube], Iron Lamp [directional], Flower Lamp [wall mounted], Frosted Lamp [directional], Quartz Lamp [directional], Jade Lamp [directional], Seaglass Lamp [directional], Blaze Lamp [wall mounted], Rainbow Lamp [directional]
    public static RegistrySupplier<Block> LIGHTBULB = AbyssalDecor.BLOCKS.register("lightbulb", () -> new DirectionalInteractibleLampBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
    public static RegistrySupplier<Block> WALL_BULB_LAMP = AbyssalDecor.BLOCKS.register("wall_bulb_lamp", () -> new WallMountedInteractibleLampBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
    public static RegistrySupplier<Block> TUBE_LAMP = AbyssalDecor.BLOCKS.register("tube_lamp", () -> new TubeLampBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
    public static RegistrySupplier<Block> IRON_LAMP = AbyssalDecor.BLOCKS.register("iron_lamp", () -> new DirectionalInteractibleLampBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static RegistrySupplier<Block> FLOWER_LAMP = AbyssalDecor.BLOCKS.register("flower_lamp", () -> new WallMountedInteractibleLampBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
    public static RegistrySupplier<Block> FROSTED_LAMP = AbyssalDecor.BLOCKS.register("frosted_lamp", () -> new DirectionalInteractibleLampBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
    public static RegistrySupplier<Block> QUARTZ_LAMP = AbyssalDecor.BLOCKS.register("quartz_lamp", () -> new DirectionalInteractibleLampBlock(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)));
    public static RegistrySupplier<Block> JADE_LAMP = AbyssalDecor.BLOCKS.register("jade_lamp", () -> new DirectionalInteractibleLampBlock(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK)));
    public static RegistrySupplier<Block> SEAGLASS_LAMP = AbyssalDecor.BLOCKS.register("seaglass_lamp", () -> new DirectionalInteractibleLampBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
    public static RegistrySupplier<Block> BLAZE_LAMP = AbyssalDecor.BLOCKS.register("blaze_lamp", () -> new WallMountedInteractibleLampBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS)));
    public static RegistrySupplier<Block> RAINBOW_LAMP = AbyssalDecor.BLOCKS.register("rainbow_lamp", () -> new DirectionalInteractibleLampBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
    public static RegistrySupplier<Block> BULKHEAD_LAMP = AbyssalDecor.BLOCKS.register("bulkhead_lamp", () -> new BulkheadLampBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static RegistrySupplier<Block> JADE_LANTERN = AbyssalDecor.BLOCKS.register("jade_lantern", () -> new InteractibleRedstoneLampBlock(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK)));
    public static RegistrySupplier<Block> ABYSSAL_LANTERN = AbyssalDecor.BLOCKS.register("abyssal_lantern", () -> new InteractibleRedstoneLampBlock(BlockBehaviour.Properties.copy(Blocks.PRISMARINE)));
    public static RegistrySupplier<Block> IRON_LANTERN = AbyssalDecor.BLOCKS.register("iron_lantern", () -> new InteractibleRedstoneLampBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    //end section

    //section : Barriers
    public static RegistrySupplier<Block> VELVET_BARRIER = AbyssalDecor.BLOCKS.register("velvet_barrier", () -> new TiedBarrierBlock(Block.Properties.copy(Blocks.COPPER_BLOCK)));
    public static RegistrySupplier<Block> IRON_BARRIER = AbyssalDecor.BLOCKS.register("iron_barrier", () -> new TiedBarrierBlock(Block.Properties.copy(Blocks.IRON_BLOCK)));
    public static RegistrySupplier<Block> ROPE_BARRIER = AbyssalDecor.BLOCKS.register("rope_barrier", () -> new TiedBarrierBlock(Block.Properties.copy(Blocks.OAK_PLANKS)));
    public static RegistrySupplier<Block> BARBED_WIRE_BARRIER = AbyssalDecor.BLOCKS.register("barbed_wire_barrier", () -> new TiedBarrierBlock(Block.Properties.copy(Blocks.IRON_BLOCK)));

    public static RegistrySupplier<Block> VELVET_BARRIER_TIE = AbyssalDecor.BLOCKS.register("velvet_barrier_tie", () -> new BarrierTieBlock(Block.Properties.copy(Blocks.RED_WOOL), false));
    public static RegistrySupplier<Block> IRON_BARRIER_TIE = AbyssalDecor.BLOCKS.register("iron_barrier_tie", () -> new BarrierTieBlock(Block.Properties.copy(Blocks.GRAY_WOOL), false));
    public static RegistrySupplier<Block> ROPE_BARRIER_TIE = AbyssalDecor.BLOCKS.register("rope_barrier_tie", () -> new BarrierTieBlock(Block.Properties.copy(Blocks.BROWN_WOOL), false));
    public static RegistrySupplier<Block> BARBED_WIRE_BARRIER_TIE = AbyssalDecor.BLOCKS.register("barbed_wire_barrier_tie", () -> new BarrierTieBlock(Block.Properties.copy(Blocks.IRON_BARS), true));

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


    public static void register() {
        AbyssalDecor.BLOCKS.register();
    }
}
