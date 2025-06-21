package net.starrysock.abyssaldecor.block;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.AbyssalUtils;
import net.starrysock.abyssaldecor.block.properties.CornerDirection;
import net.starrysock.abyssaldecor.block.properties.CornerDirectionProperty;

import java.util.Map;

public class SmallBarsCornerBlock extends Block {
    public static final CornerDirectionProperty CORNER = CornerDirectionProperty.INSTANCE;

    public SmallBarsCornerBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(CORNER, CornerDirection.NORTHEAST).setValue(BlockStateProperties.VERTICAL_DIRECTION, Direction.DOWN));
    }

    static VoxelShape UPPER = Shapes.join(box(0,8,0,16,16,16),box(1,8,0,16,16,15), BooleanOp.ONLY_FIRST);


    static VoxelShape LOWER = Shapes.join(box(0,0,0,16,8,16),box(1,0,0,16,8,15), BooleanOp.ONLY_FIRST);


    public static final Map<Direction,VoxelShape> LOWER_SHAPES = Util.make(Maps.newEnumMap(Direction.class), directionVoxelShapeEnumMap -> {
        for (Direction direction : BlockStateProperties.FACING.getPossibleValues()) {
            directionVoxelShapeEnumMap.put(direction,AbyssalUtils.calculateShapes(direction,LOWER));
        }
    });

    public static final Map<Direction,VoxelShape> UPPER_SHAPES = Util.make(Maps.newEnumMap(Direction.class),directionVoxelShapeEnumMap -> {
        for (Direction direction : BlockStateProperties.FACING.getPossibleValues()) {
            directionVoxelShapeEnumMap.put(direction,AbyssalUtils.calculateShapes(direction,UPPER));
        }
    });


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        CornerDirection corner = state.getValue(CORNER);
        Direction dir = corner.getShapeDirection();
        return switch (state.getValue(BlockStateProperties.VERTICAL_DIRECTION)) {
            case UP -> UPPER_SHAPES.get(dir);
            default -> LOWER_SHAPES.get(dir);
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CORNER,BlockStateProperties.VERTICAL_DIRECTION);
    }
}
