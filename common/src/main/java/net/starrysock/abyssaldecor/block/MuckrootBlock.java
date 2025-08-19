package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.registry.AbyssalDecorItems;

public class MuckrootBlock extends CropBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
    private final TagKey<Block> allowed;

    private static final VoxelShape[] M_SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(3, 0, 3, 13, 4, 13),
            Block.box(3, 0, 3, 13, 8, 13),
            Block.box(3, 0, 3, 13, 12, 13)};


    public MuckrootBlock(Properties properties, TagKey<Block> allowed) {
        super(properties);
        this.allowed = allowed;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return M_SHAPE_BY_AGE[this.getAge(state)];
    }

    @Override
    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockState.is(allowed);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return BogAppleLeavesBlock.tryRightClickHarvest(this,state,level,pos,player,hand);
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 2;
    }

    @Override
    protected int getBonemealAgeIncrease(Level level) {
        return Mth.nextInt(level.random, 1, 2);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return AbyssalDecorItems.MUCKROOT.get();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);//don't call super!
    }
}
