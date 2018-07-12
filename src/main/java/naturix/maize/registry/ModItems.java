package naturix.maize.registry;

import naturix.maize.items.FoodBase;
import naturix.maize.items.ItemBase;
import naturix.maize.items.MaizeSeed;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
	public static MaizeSeed cornSeed = new MaizeSeed();
	public static FoodBase corn = new FoodBase("corn", 2, 1.5f, false);
	public static ItemBase maizeflour = new ItemBase("corn_flour");
	public static FoodBase breadMaize = new FoodBase("bread_maize", 5, 3.5f, false);
	
	public static void register(IForgeRegistry<Item> registry) {
		registry.registerAll(
				cornSeed,
				corn,
				maizeflour,
				breadMaize
		);
	}

	public static void registerModels() {
		cornSeed.registerItemModel(cornSeed);
		corn.registerItemModel();
		maizeflour.registerItemModel();
		breadMaize.registerItemModel();
	}
}
