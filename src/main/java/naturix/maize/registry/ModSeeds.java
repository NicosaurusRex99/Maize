package naturix.maize.registry;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class ModSeeds {

	public static void getSeedDrops() {
		  MinecraftForge.addGrassSeed(new ItemStack(ModItems.cornSeed, 1, 0), 10);
	}
}
