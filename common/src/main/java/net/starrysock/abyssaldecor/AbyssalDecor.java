package net.starrysock.abyssaldecor;

import com.google.common.base.Suppliers;
import com.mojang.logging.LogUtils;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrarManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.starrysock.abyssaldecor.mixin.BlockEntityTypeAccessor;
import net.starrysock.abyssaldecor.platform.Services;
import net.starrysock.abyssaldecor.registry.*;
import net.starrysock.abyssaldecor.worldgen.ModFoliagePlacers;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class AbyssalDecor {
    public static final String MOD_ID = "abyssaldecor";

    public static final Logger LOGGER = LogUtils.getLogger();

    // We can use this if we don't want to use DeferredRegister
    public static final Supplier<RegistrarManager> REGISTRIES = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

    // Registering a new creative tab
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registries.BLOCK);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);

    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(MOD_ID, Registries.PAINTING_VARIANT);

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(MOD_ID, Registries.SOUND_EVENT);

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = DeferredRegister.create(MOD_ID,Registries.FOLIAGE_PLACER_TYPE);

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(MOD_ID,Registries.MOB_EFFECT);

    public static void init() {

        AbyssalDecorBlocks.register();
        AbyssalDecorItems.register();
        AbyssalPaintings.register();
        AbyssalCreativeTabs.register();
        AbyssalDecorSounds.register();
        ModFoliagePlacers.register();
        AbyssalDecorMobEffects.register();

        System.out.println(Services.PLATFORM.getConfigDirectory().toAbsolutePath().normalize());


    }

    public static InteractionResult rightClickBlock(Player entity, InteractionHand hand, BlockPos pos, Direction face) {
        ItemStack stack = entity.getItemInHand(hand);
        BlockPos placePos = pos.relative(face);
        if (stack.is(Items.HEART_OF_THE_SEA) && entity.level().getBlockState(placePos).canBeReplaced()) {
            BlockState state = AbyssalDecorBlocks.HEART_OF_THE_SEA.get().defaultBlockState().setValue(DirectionalBlock.FACING,face);//getPlacementState(new BlockPlaceContext(pl));

            if (!entity.level().isClientSide) {
                boolean b = entity.level().setBlockAndUpdate(placePos, state);
                if (b && !entity.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            }

            return InteractionResult.sidedSuccess(entity.level().isClientSide);
        }
        return InteractionResult.PASS;
    }

    protected static BlockState getPlacementState(BlockPlaceContext context) {
        BlockState blockstate = AbyssalDecorBlocks.HEART_OF_THE_SEA.get().getStateForPlacement(context);
        return blockstate != null && canPlace(context, blockstate) ? blockstate : null;
    }

    protected static boolean canPlace(BlockPlaceContext context, BlockState blockstate) {
        return true;
    }


    public static void setup() {
        BlockEntityTypeAccessor sign = (BlockEntityTypeAccessor) BlockEntityType.SIGN;
        HashSet<Block> blocks = new HashSet<>(sign.getValidBlocks());
        blocks.addAll(Set.of(AbyssalDecorBlocks.BLACKWOOD_SIGN.get(),AbyssalDecorBlocks.BLACKWOOD_WALL_SIGN.get(),
                AbyssalDecorBlocks.CINNAMON_SIGN.get(),AbyssalDecorBlocks.CINNAMON_WALL_SIGN.get(),
                AbyssalDecorBlocks.WHITEWOOD_SIGN.get(),AbyssalDecorBlocks.WHITEWOOD_WALL_SIGN.get()));
        sign.setValidBlocks(blocks);

        BlockEntityTypeAccessor hangingsign = (BlockEntityTypeAccessor) BlockEntityType.HANGING_SIGN;
        blocks = new HashSet<>(hangingsign.getValidBlocks());
        blocks.addAll(Set.of(AbyssalDecorBlocks.BLACKWOOD_HANGING_SIGN.get(),AbyssalDecorBlocks.BLACKWOOD_WALL_HANGING_SIGN.get(),
                AbyssalDecorBlocks.CINNAMON_HANGING_SIGN.get(),AbyssalDecorBlocks.CINNAMON_WALL_HANGING_SIGN.get(),
                AbyssalDecorBlocks.WHITEWOOD_HANGING_SIGN.get(),AbyssalDecorBlocks.WHITEWOOD_WALL_HANGING_SIGN.get()));
        hangingsign.setValidBlocks(blocks);

        AxeItem.STRIPPABLES = new HashMap<>(AxeItem.STRIPPABLES);
        AxeItem.STRIPPABLES.put(AbyssalDecorBlocks.BLACKWOOD_LOG.get(),AbyssalDecorBlocks.STRIPPED_BLACKWOOD_LOG.get());

        AxeItem.STRIPPABLES.put(AbyssalDecorBlocks.CINNAMON_LOG.get(),AbyssalDecorBlocks.HEALING_CINNAMON_LOG.get());
        AxeItem.STRIPPABLES.put(AbyssalDecorBlocks.HEALING_CINNAMON_LOG.get(),AbyssalDecorBlocks.STRIPPED_CINNAMON_LOG.get());

        AxeItem.STRIPPABLES.put(AbyssalDecorBlocks.CINNAMON_WOOD.get(),AbyssalDecorBlocks.HEALING_CINNAMON_WOOD.get());
        AxeItem.STRIPPABLES.put(AbyssalDecorBlocks.HEALING_CINNAMON_WOOD.get(),AbyssalDecorBlocks.STRIPPED_CINNAMON_WOOD.get());
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID,path);
    }
}
