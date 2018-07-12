package naturix.maize.items;

import naturix.maize.MaizeMod;
import net.minecraft.item.ItemFood;

public class FoodBase extends ItemFood {

	protected String name;
	public FoodBase(String name, int amount, float saturation, boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(MaizeMod.maizetab);
	}
	
	public void registerItemModel() {
		MaizeMod.proxy.registerItemRenderer(this, 0, name);
	}
}