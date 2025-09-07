package net.starrysock.abyssaldecor.forge;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraftforge.common.PlantType;
import net.starrysock.abyssaldecor.block.FeverBlossomBlock;

public class FeverBlossomBlockForge extends FeverBlossomBlock {

    public static final PlantType MOLD = PlantType.get("mold");

    public FeverBlossomBlockForge(Properties properties) {
        super(properties);
    }

    @Override
    public PlantType getPlantType(BlockGetter level, BlockPos pos) {
        return MOLD;
    }
}
