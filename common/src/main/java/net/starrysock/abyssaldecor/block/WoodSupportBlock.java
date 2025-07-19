package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.AbyssalUtils;
import org.jetbrains.annotations.Nullable;

public class WoodSupportBlock extends FaceAttachedBlock implements SimpleWaterloggedBlock {


    public static final BooleanProperty CENTERED = BooleanProperty.create("centered");

    public WoodSupportBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(BlockStateProperties.WATERLOGGED, false).setValue(CENTERED, false));

    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return state.getValue(CENTERED) ? switch (state.getValue(FACING)) {
            case NORTH:
                yield switch (state.getValue(FACE)) {
                    case FLOOR -> box(0, 0, 6, 16, 4, 10);
                    case WALL -> box(0, 6, 12, 16, 10, 16);
                    case CEILING -> box(0, 12, 6, 16, 16, 10);
                };
            case EAST:
                yield switch (state.getValue(FACE)) {
                    case FLOOR -> box(6, 0, 0, 10, 4, 16);
                    case WALL -> box(0, 6, 0, 4, 10, 16);
                    case CEILING -> box(6, 12, 0, 10, 16, 16);
                };
            case WEST:
                yield switch (state.getValue(FACE)) {
                    case FLOOR -> box(6, 0, 0, 10, 4, 16);
                    case WALL -> box(12, 6, 0, 16, 10, 16);
                    case CEILING -> box(6, 12, 0, 10, 16, 16);
                };
            default:
                yield switch (state.getValue(FACE)) {
                    case FLOOR -> box(0, 0, 6, 16, 4, 10);
                    case WALL -> box(0, 6, 0, 16, 10, 4);
                    case CEILING -> box(0, 12, 6, 16, 16, 10);
                };
        } : switch (state.getValue(FACING)) {
            case NORTH:
                yield switch (state.getValue(FACE)) {
                    case FLOOR -> box(0, 0, 12, 16, 4, 16);
                    case WALL, CEILING -> box(0, 12, 12, 16, 16, 16);
                };
            case EAST:
                yield switch (state.getValue(FACE)) {
                    case FLOOR -> box(0, 0, 0, 4, 4, 16);
                    case WALL, CEILING -> box(0, 12, 0, 4, 16, 16);
                };
            case WEST:
                yield switch (state.getValue(FACE)) {
                    case FLOOR -> box(12, 0, 0, 16, 4, 16);
                    case WALL, CEILING -> box(12, 12, 0, 16, 16, 16);
                };
            default:
                yield switch (state.getValue(FACE)) {
                    case FLOOR -> box(0, 0, 0, 16, 4, 4);
                    case WALL, CEILING -> box(0, 12, 0, 16, 16, 4);
                };
        };
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState stateForPlacement = super.getStateForPlacement(context);

        if (stateForPlacement != null) {

            AttachFace face = stateForPlacement.getValue(FACE);

            switch (face){
                case WALL -> {
                    Vec3 fraction = AbyssalUtils.getFraction(context.getClickLocation());
                    boolean center = fraction.y < .625;
                    stateForPlacement = stateForPlacement.setValue(CENTERED, center);
                }
            }

        }

        return stateForPlacement;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BlockStateProperties.WATERLOGGED, CENTERED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
}
