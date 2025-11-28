package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.starrysock.abyssaldecor.registry.AbyssalDecorItems;

public class CinnamonLogBlock extends RotatedPillarBlock {
    public CinnamonLogBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        spawnCinnamonStick(level, pos, player, hand);
        return super.use(state, level, pos, player, hand, hit);
    }

    static void spawnCinnamonStick(Level level,BlockPos pos, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(ItemTags.AXES) && !level.isClientSide) {
            Vec3 vec3 = Vec3.atLowerCornerWithOffset(pos, 0.5D, 1.01D, 0.5D).offsetRandom(level.random, 0.7F);
            ItemEntity itementity = new ItemEntity(level, vec3.x(), vec3.y(), vec3.z(), new ItemStack(AbyssalDecorItems.CINNAMON_STICK.get()));
            itementity.setDefaultPickUpDelay();
            level.addFreshEntity(itementity);
        }
    }
}
