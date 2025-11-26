package net.starrysock.abyssaldecor.block.lamp;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.AbyssalUtils;
import net.starrysock.abyssaldecor.block.AbstractHorizontalBlock;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;

public class HorizontalLampBlock extends AbstractHorizontalBlock implements SimpleWaterloggedBlock {
    private final VoxelShape shape;

    private final EnumMap<Direction,VoxelShape> shapes = new EnumMap<>(Direction.class);

    public HorizontalLampBlock(Properties properties,VoxelShape shape) {//note, use the south facing hitbox, rest will be generated
        super(properties);
        this.shape = shape;
        this.registerDefaultState(this.defaultBlockState().setValue(RedstoneLampBlock.LIT, false)
                .setValue(BlockStateProperties.WATERLOGGED, false));

        if (shape != null) {
            for (Direction direction : FACING.getPossibleValues()) {
                shapes.put(direction, AbyssalUtils.calculateShapes(direction, shape));
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(RedstoneLampBlock.LIT,BlockStateProperties.WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {

        if (shape != null) {
            return shapes.get(state.getValue(FACING));
        }

 ;
        VoxelShape shape3 = Shapes.block();//Shapes.or(shape1,shape2);

        return AbyssalUtils.calculateShapes(state.getValue(FACING), shape3);

    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        return ToggleableDirectionalLampBlock.toggleLamp(blockState,level,blockPos);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = this.defaultBlockState();
        LevelReader levelreader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();

        for(Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockstate = blockstate.setValue(FACING, direction);
                if (blockstate.canSurvive(levelreader, blockpos)) {
                    blockstate = AbyssalUtils.waterLog(blockstate,context.getLevel().getFluidState(context.getClickedPos()));
                    return blockstate;
                }
            }
        }
        return null;
    }

}
