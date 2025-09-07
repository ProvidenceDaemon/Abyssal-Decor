package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;
import net.starrysock.abyssaldecor.registry.AbyssalDecorMobEffects;
import net.starrysock.abyssaldecor.registry.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class MoldBlock extends Block {
    public MoldBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.literal("Spreads in darkness"));
    }

    private static boolean canBeMold(LevelReader levelReader, BlockPos pos) {
        BlockPos blockpos = pos.above();
        int i = levelReader.getMaxLocalRawBrightness(blockpos);
        return i <= 8;
    }

    private static boolean canPropagate(LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.above();
        return canBeMold(level, pos) && !level.getFluidState(blockpos).is(FluidTags.WATER);
    }


    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        tickMold(level, pos, random);
    }

    static final double MOLD_PLANT_CHANCE = 1/3d;

    public static void tickMold(ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.getMaxLocalRawBrightness(pos.above()) <= 8) {
            for (int i = 0; i < 4; ++i) {
                BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                if (canPropagate(level, blockpos)) {
                    BlockState converted = getConvertedBlock(level, blockpos, random);
                    if (converted != null) {
                        level.setBlockAndUpdate(blockpos, converted);

                        if (canGrowMoldPlants(converted) && level.getBlockState(blockpos.above()).isAir()) {
                            if (random.nextDouble() < MOLD_PLANT_CHANCE) {
                                spawnPlant(level, blockpos.above());
                            }
                        } if (converted.is(ModTags.Blocks.MOLDY_PLANT_VALID_BLOCKS)
                                &&level.getBlockState(blockpos.below()).canBeReplaced() && random.nextDouble() <.25) {
                            level.setBlockAndUpdate(blockpos.below(), AbyssalDecorBlocks.MOLDY_HANGER.get().defaultBlockState());
                        }
                    }
                }
            }
        }
    }

    public static boolean canGrowMoldPlants(BlockState state) {
        return state.is(AbyssalDecorBlocks.BLACK_MOLD.get()) || state.is(AbyssalDecorBlocks.MOLDY_STARSTONE.get())||
                state.is(AbyssalDecorBlocks.MOLDIER_STARSTONE.get());
    }

    public static void spawnPlant(ServerLevel level, BlockPos above) {
        BlockState state;
        double random = Math.random();
        if (random <= 0.25) {
            state = AbyssalDecorBlocks.MOLDY_SPROUTS.get().defaultBlockState();
        } else if (random > 0.25 && random <= 0.5) {
            state = AbyssalDecorBlocks.MOLDY_FUZZ.get().defaultBlockState();
        } else if (random > 0.5 && random <= 0.75) {
            state = AbyssalDecorBlocks.MOLDY_FEATHERS.get().defaultBlockState();
        } else if (random > 0.75 && random <= 0.85) {
            state = AbyssalDecorBlocks.MOLDY_STALK_SPROUT.get().defaultBlockState();
        } else if (random > 0.85 && random <= 0.95) {
            if (level.getBlockState(above.above()).canBeReplaced()) {
                DoublePlantBlock.placeAt(level, AbyssalDecorBlocks.MOLD_FRONDS.get().defaultBlockState(), above, Block.UPDATE_ALL);
            }
            return;
        } else {
            state = AbyssalDecorBlocks.FEVER_BLOSSOM.get().defaultBlockState();
        }

        level.setBlockAndUpdate(above, state);
    }

    public void stepOn(Level world, BlockPos pos, BlockState blockstate, Entity entity) {
        super.stepOn(world, pos, blockstate, entity);
        if (!world.isClientSide) {
            walkOnMold(entity);
        }
    }

    public static void walkOnMold(Entity entity) {
        if (entity instanceof LivingEntity living) {
            if (living instanceof Player player) {
                if (player.getAbilities().instabuild) {
                    return;
                }
            }

            if (living.hasEffect(AbyssalDecorMobEffects.SPORE_PROTECTION.get()) || living.getMobType() == MobType.WATER ||
                    living.getMobType() == MobType.UNDEAD || living.getMobType() == MobType.ARTHROPOD)
                return;


            living.addEffect(new MobEffectInstance(MobEffects.WITHER, 40, 0));
            if (living instanceof Player player) {
                player.causeFoodExhaustion(0.1F);
            }
        }
    }

    public static BlockState copyProperties(BlockState original, Block block) {
        BlockState newState = block.defaultBlockState();
        for (Map.Entry<Property<?>, Comparable<?>> entry : newState.getValues().entrySet()) {
            Property<?> property = entry.getKey();
            if (original.hasProperty(property)) {
                newState = newState.setValue((Property) property, (Comparable) original.getValue(property));
            }
        }
        return newState;
    }

    @Nullable
    public static BlockState getConvertedBlock(ServerLevel level, BlockPos pos, RandomSource random) {
        BlockState toConvert = level.getBlockState(pos);
        //if (toConvert.hasBlockEntity()) return null;

        if (toConvert.is(ModTags.Blocks.MOLD_IMMUNE)) {
            return null;
        }

        if (toConvert.is(BlockTags.LOGS)) {
            Block block = AbyssalDecorBlocks.BLACKWOOD_LOG.get();

            return copyProperties(toConvert, block);

        } else if (toConvert.is(BlockTags.MINEABLE_WITH_PICKAXE)) {
            return random.nextDouble() < .90 ? Blocks.DEEPSLATE.defaultBlockState() :
                    Blocks.DEEPSLATE_COAL_ORE.defaultBlockState();

        } else if (toConvert.is(BlockTags.PLANKS)) {
            return AbyssalDecorBlocks.BLACKWOOD_PLANKS.get().defaultBlockState();
        } else if (toConvert.is(BlockTags.WOODEN_STAIRS)) {
            return copyProperties(toConvert,AbyssalDecorBlocks.BLACKWOOD_STAIRS.get());
        } else if (toConvert.is(BlockTags.WOODEN_SLABS)) {
            return copyProperties(toConvert,AbyssalDecorBlocks.BLACKWOOD_SLAB.get());
        } else if (toConvert.is(BlockTags.SAND) || toConvert.is(Blocks.GRAVEL) || toConvert.is(Blocks.SOUL_SAND)) {
            return AbyssalDecorBlocks.BLACKENED_SAND.get().defaultBlockState();
        } else if (toConvert.is(ModTags.Blocks.MOLD_SPREADABLES)) {
            if (random.nextDouble() < .95) {
                return AbyssalDecorBlocks.BLACK_MOLD.get().defaultBlockState();
            } else {
                return random.nextDouble() < .7 ? AbyssalDecorBlocks.MOLDIER_STARSTONE.get().defaultBlockState() :
                        AbyssalDecorBlocks.MOLDY_STARSTONE.get().defaultBlockState();
            }
        }
        return null;
    }

}
