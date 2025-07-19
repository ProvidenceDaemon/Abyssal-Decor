package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.registry.AbyssalDecorItems;
import net.starrysock.abyssaldecor.registry.ModTags;

public class SpiderCornCropBlock extends CropBlock {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;

    public SpiderCornCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Vec3 offset = state.getOffset(world, pos);
        VoxelShape shape = switch (state.getValue(getAgeProperty())) {
            case 0 -> box(4.0, 11.0, 4.0, 12.0, 16.0, 12.0);
            case 1 ->  box(2.0, 4.0, 2.0, 14.0, 16.0, 14.0);
            case 2 -> Shapes.block();
            default -> throw new IllegalStateException("Unexpected value: " + state.getValue(getAgeProperty()));
        };

        return shape.move(offset.x, offset.y, offset.z);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return BogAppleLeavesBlock.tryRightClickHarvest(this,state,level,pos,player,hand);
    }

    @Override
    public int getMaxAge() {
        return getAgeProperty().getPossibleValues().size()-1;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos above = pos.above();
        return mayPlaceOn(level.getBlockState(pos),level,above);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return AbyssalDecorItems.SPIDERCORN.get();
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos).is(ModTags.Blocks.SPIDERCORN_GROWABLE);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
