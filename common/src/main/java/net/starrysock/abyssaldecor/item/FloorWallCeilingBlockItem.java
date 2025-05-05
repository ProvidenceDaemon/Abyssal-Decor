package net.starrysock.abyssaldecor.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Map;

public class FloorWallCeilingBlockItem extends BlockItem {
    private final Block wallBlock;
    private final Block ceilingBlock;

    public FloorWallCeilingBlockItem(Block block, Block wallBlock, Block ceilingBlock, Properties properties) {
        super(block, properties);
        this.wallBlock = wallBlock;
        this.ceilingBlock = ceilingBlock;
    }

    @Override
    @Nullable
    protected BlockState getPlacementState(BlockPlaceContext context) {
        Block block = switch (context.getClickedFace()) {
            case DOWN -> ceilingBlock;
            case UP -> getBlock();
            case NORTH, SOUTH, WEST, EAST -> wallBlock;
        };

        return block.getStateForPlacement(context);
    }

    @Override
    public void registerBlocks(Map<Block, Item> blockToItemMap, Item item) {
        super.registerBlocks(blockToItemMap, item);
        blockToItemMap.put(this.wallBlock, item);
        blockToItemMap.put(this.ceilingBlock, item);
    }

    /*public void removeFromBlockToItemMap(Map<Block, Item> blockToItemMap, Item itemIn) {
        super.removeFromBlockToItemMap(blockToItemMap, itemIn);
        blockToItemMap.remove(this.wallBlock);
    }*/
}
