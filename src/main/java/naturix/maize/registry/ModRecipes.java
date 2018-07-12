package naturix.maize.registry;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {

	public static void init() 
{
		GameRegistry.addSmelting(ModItems.maizeflour, new ItemStack(ModItems.breadMaize), 0.2f);
		
}
}