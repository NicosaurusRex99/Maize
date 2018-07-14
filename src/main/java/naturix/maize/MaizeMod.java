package naturix.maize;

import org.apache.logging.log4j.Logger;

import naturix.maize.proxy.CommonProxy;
import naturix.maize.registry.ModBlocks;
import naturix.maize.registry.ModItems;
import naturix.maize.registry.ModOreDict;
import naturix.maize.registry.ModRecipes;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = MaizeMod.MODID, name = MaizeMod.NAME, version = MaizeMod.VERSION)
public class MaizeMod
{
    public static final String MODID = "maize";
    public static final String NAME = "Maize Mod";
    public static final String VERSION = "1.12.2.0";

    private static Logger logger;
    
    @SidedProxy(clientSide = "naturix.maize.proxy.ClientProxy", serverSide = "naturix.maize.proxy.ServerProxy")
    public static CommonProxy proxy;
    
    @Mod.Instance(MODID)
	public static MaizeMod instance;
    public static final CreativeTabs maizetab = new CreativeTabs("tabmaize")
    {

        @Override
		@SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModItems.cornSeed);
        }
		
    };
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		ModRecipes.init();
		ModOreDict.initOreDict();
        MinecraftForge.addGrassSeed(new ItemStack(ModItems.cornSeed, 1, 0), 10);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}
	@Mod.EventBusSubscriber
	public static class RegistrationHandler {
	
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			ModBlocks.registerItemBlocks(event.getRegistry());
			ModItems.register(event.getRegistry());
		}
		@SubscribeEvent
		public static void registerItems(ModelRegistryEvent event) {
			ModBlocks.registerModels();
			ModItems.registerModels();
			
		}
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			ModBlocks.register(event.getRegistry());
		}

}
}