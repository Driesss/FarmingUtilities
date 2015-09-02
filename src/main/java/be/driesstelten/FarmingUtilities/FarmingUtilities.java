package be.driesstelten.FarmingUtilities;

import net.minecraftforge.common.MinecraftForge;
import be.driesstelten.FarmingUtilities.client.handler.KeyInputEventHandler;
import be.driesstelten.FarmingUtilities.data.ModData;
import be.driesstelten.FarmingUtilities.handler.ConfigurationHandler;
import be.driesstelten.FarmingUtilities.init.ModBlocks;
import be.driesstelten.FarmingUtilities.init.ModItems;
import be.driesstelten.FarmingUtilities.init.Recepies;
import be.driesstelten.FarmingUtilities.network.PacketHandler;
import be.driesstelten.FarmingUtilities.reference.Reference;
import be.driesstelten.FarmingUtilities.utility.LogHelper;
import be.driesstelten.FarmingUtilities.proxy.IProxy;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModData.ID, name = ModData.NAME, version = ModData.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class FarmingUtilities {
	
	@Mod.Instance(ModData.ID)
	public static FarmingUtilities instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		ModData.setMetadata(event.getModMetadata());
		
		PacketHandler.init();
		
		ModItems.init();
		ModBlocks.init();
		Recepies.init();
		
		proxy.registerKeyBindings();
		proxy.initializeRenderers();
		
		MinecraftForge.EVENT_BUS.register(this);
		
		LogHelper.info("Pre Initialization Complete!");
		
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		
		FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());
		LogHelper.info("Initialization Complete!");
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
		//for (String oreName : OreDictionary.getOreNames()) {
		//	LogHelper.info(oreName);
		//}
		
		LogHelper.info("Post Initialization Complete!");
		
	}
	

}
