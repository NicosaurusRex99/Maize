package naturix.maize.proxy;

import naturix.maize.registry.ModSeeds;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class CommonProxy {
    private World world;

	public void preInit(FMLPreInitializationEvent e) {
    }

    public void init(FMLInitializationEvent e) {
    	onBlocksAndItemsLoaded();
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SideOnly(Side.CLIENT)
	public void registerItemRenderer(Item item, int meta, String id) {
	}
    public void onBlocksAndItemsLoaded() {
		ModSeeds.getSeedDrops();
    }
}