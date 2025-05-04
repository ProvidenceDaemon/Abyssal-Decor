package net.starrysock.abyssaldecor.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;

public class AbyssalDecorClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AbyssalDecorClient.setup(BlockRenderLayerMap.INSTANCE::putBlock);
    }
}
