package nicusha.maize.data;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import nicusha.maize.registry.BlockRegistry;
import nicusha.maize.registry.ItemRegistry;

import static nicusha.maize.MaizeMod.MODID;

public class ModModelProvider extends ModelProvider {

    public ModModelProvider(PackOutput output) {
        super(output, MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators gen, ItemModelGenerators itemModels) {

        itemModels.generateFlatItem(ItemRegistry.corn.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ItemRegistry.corn_flour.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ItemRegistry.corn_bread.get(), ModelTemplates.FLAT_ITEM);
//        itemModels.generateFlatItem(ItemRegistry.corn_seed.get(), ModelTemplates.FLAT_ITEM);

        gen.createCropBlock(BlockRegistry.maize_crop.get(), BlockStateProperties.AGE_7,0, 1, 2, 3, 4, 5, 6, 7);
    }
}