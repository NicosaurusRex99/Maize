package naturix.maize.items;

import naturix.maize.MaizeMod;
import net.minecraft.item.Item;

public class ItemBase extends Item {

	protected String name;

	public ItemBase(String name) {
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		this.setCreativeTab(MaizeMod.maizetab);
	}
	
	public void registerItemModel() {
		MaizeMod.proxy.registerItemRenderer(this, 0, name);
	}
}