package net.starrysock.abyssaldecor.datagen;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.IGeneratedBlockState;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;

import java.util.HashMap;
import java.util.Map;

public class CTMBlockStateVariantBuilder implements IGeneratedBlockState {
    protected final Block owner;
    private final VariantBlockStateBuilder variantBlockStateBuilder;
    protected ResourceLocation loader = new ResourceLocation("athena","ctm");
    protected final Map<String,ResourceLocation> ctmTextures = new HashMap<>();

    public CTMBlockStateVariantBuilder(Block block, VariantBlockStateBuilder variantBlockStateBuilder) {
        owner = block;
        this.variantBlockStateBuilder = variantBlockStateBuilder;
    }

    public CTMBlockStateVariantBuilder loader(ResourceLocation location) {
        this.loader = location;
        return this;
    }

    public CTMBlockStateVariantBuilder ctmTexture(String key,ResourceLocation texture) {
        ctmTextures.put(key, texture);
        return this;
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = variantBlockStateBuilder.toJson();

        json.addProperty("athena:loader",loader.toString());

        JsonObject ctmTexturesJson = new JsonObject();
        ctmTextures.forEach((string, location) -> ctmTexturesJson.addProperty(string,location.toString()));
        json.add("ctm_textures",ctmTexturesJson);

        return json;
    }
}
//{
//  "variants": {
//    "": {
//      "model": "minecraft:block/air"
//    }
//  },
//
//  "athena:loader": "athena:ctm",
//  "ctm_textures": {
//    "center": "chipped:block/amethyst_block/ctm/cut_amethyst_block_column_ctm/3",
//    "empty": "chipped:block/amethyst_block/ctm/cut_amethyst_block_column_ctm/0",
//    "horizontal": "chipped:block/amethyst_block/ctm/cut_amethyst_block_column_ctm/2",
//    "particle": "chipped:block/amethyst_block/cut_amethyst_block_column",
//    "vertical": "chipped:block/amethyst_block/ctm/cut_amethyst_block_column_ctm/1"
//  }
//}