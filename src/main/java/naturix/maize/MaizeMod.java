package naturix.maize;

import org.apache.logging.log4j.Logger;

import naturix.maize.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println(NAME + " is loading!");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}