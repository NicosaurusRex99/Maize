package naturix.maize.items;

import naturix.maize.MaizeMod;
import naturix.maize.registry.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;

public class MaizeSeed extends ItemSeeds{

	public MaizeSeed() {
		super(ModBlocks.maize, Blocks.FARMLAND);
		setUnlocalizedName("corn_seeds");
		setRegistryName("corn_seeds");
		this.setCreativeTab(MaizeMod.maizetab);
		this.maxStackSize = 16;
	}
	
	public void registerItemModel(Item item) {
		MaizeMod.proxy.registerItemRenderer(item, 0, "corn_seeds");
	}

}