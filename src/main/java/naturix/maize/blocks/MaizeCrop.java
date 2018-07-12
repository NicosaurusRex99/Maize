package naturix.maize.blocks;

import naturix.maize.MaizeMod;
import naturix.maize.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class MaizeCrop extends BlockCrops {

	protected String name;

	public MaizeCrop(String name) {
		super();
	
		this.name = name;
	
		setUnlocalizedName(name);
		setRegistryName(name);
	}
	
	public void registerItemModel(Item itemBlock) {
		MaizeMod.proxy.registerItemRenderer(itemBlock, 0, name);
	}
	
	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}
	@Override
	protected Item getSeed() {
		return ModItems.cornSeed;
	}
	
	@Override
	protected Item getCrop() {
		return ModItems.corn;
	}
}