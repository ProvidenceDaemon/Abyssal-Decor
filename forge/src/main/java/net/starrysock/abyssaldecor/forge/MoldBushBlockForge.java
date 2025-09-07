package net.starrysock.abyssaldecor.forge;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraftforge.common.PlantType;
import net.starrysock.abyssaldecor.block.MoldBushBlock;

public class MoldBushBlockForge extends MoldBushBlock {


    public MoldBushBlockForge(Properties properties) {
        super(properties);
    }

    @Override
    public PlantType getPlantType(BlockGetter level, BlockPos pos) {
        return FeverBlossomBlockForge.MOLD;
    }
}
