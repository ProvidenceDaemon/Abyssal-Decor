package net.starrysock.abyssaldecor.datagen;

import com.google.gson.JsonObject;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.IGeneratedBlockState;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;

public class CTMBlockStateVariantBuilder implements IGeneratedBlockState {
    protected final Block owner;
    private final VariantBlockStateBuilder variantBlockStateBuilder;

    public CTMBlockStateVariantBuilder(Block block, VariantBlockStateBuilder variantBlockStateBuilder) {
        owner = block;
        this.variantBlockStateBuilder = variantBlockStateBuilder;
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = variantBlockStateBuilder.toJson();

        json.addProperty("atnena:loader","athena_ctm");

        JsonObject ctmTextures = new JsonObject();
        json.add("ctm_textures",ctmTextures);

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