package naturix.maize.registry;
import naturix.maize.blocks.MaizeCrop;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {
	public static MaizeCrop maize = new MaizeCrop("crop_maize");
	public static void register(IForgeRegistry<Block> registry) {
		registry.register(maize);
	}

	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		registry.register(
				maize.createItemBlock()
				);
	}

	public static void registerModels() {
		maize.registerItemModel(Item.getItemFromBlock(maize));
	}

}