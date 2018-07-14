package naturix.maize.registry;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModOreDict {
	public static void initOreDict() {
		OreDictionary.registerOre("corn", new ItemStack(ModItems.corn));
	}
}