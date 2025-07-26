package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.block.properties.ModBlockStateProperties;
import net.starrysock.abyssaldecor.block.properties.TriPart;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;
import net.starrysock.abyssaldecor.registry.AbyssalDecorItems;
import net.starrysock.abyssaldecor.registry.ModTags;

public class MoldySaplingBlock extends Block {
    public MoldySaplingBlock(Properties properties) {
        super(properties);
    }

    protected static final VoxelShape SAPLING_SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 12.0D, 12.0D);


    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Vec3 vec3 = state.getOffset(level, pos);
        return SAPLING_SHAPE.move(vec3.x, vec3.y, vec3.z);
    }

    /**
     * Performs a random tick on a block.
     */
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(3) == 0 && level.isEmptyBlock(pos.above()) && level.getRawBrightness(pos.above(), 0) >= 9) {
            this.growBamboo(level, pos);
        }

    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(ModTags.Blocks.MOLDY_PLANT_VALID_BLOCKS);
    }

    /**
     * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately returns its solidified counterpart.
     * Note that this method should ideally consider only the specific direction passed in.
     */
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (!state.canSurvive(level, currentPos)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            if (facing == Direction.UP && facingState.is(AbyssalDecorBlocks.MOLDY_STALK.get())) {
                level.setBlock(currentPos, AbyssalDecorBlocks.MOLDY_STALK.get().defaultBlockState(), 2);
            }

            return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        }
    }

    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(AbyssalDecorItems.MOLDY_STALK.get());
    }

    /**
     * Get the hardness of this Block relative to the ability of the given player
     * @deprecated call via {@link net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase#getDestroyProgress} whenever possible. Implementing/overriding is fine.
     */
    public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        return player.getMainHandItem().getItem() instanceof SwordItem ? 1.0F : super.getDestroyProgress(state, player, level, pos);
    }

    protected void growBamboo(Level level, BlockPos state) {
        level.setBlockAndUpdate(state.above(), AbyssalDecorBlocks.MOLDY_STALK.get()
                .defaultBlockState().setValue(ModBlockStateProperties.TRI_PART, TriPart.TOP));
    }
}
