package be.driesstelten.FarmingUtilities.handler;

import java.io.File;

import be.driesstelten.FarmingUtilities.reference.Reference;
import be.driesstelten.FarmingUtilities.registries.ColorRegistry;
import be.driesstelten.FarmingUtilities.registries.CompostRegistry;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {
	
	public static Configuration configuration;
	public static boolean testValue = false;
	
	public static void init(File configFile) {
		//create the configuration object from the configuration file
		if (configuration == null) {
			
			configuration = new Configuration(configFile);
			loadConfiguration();
		}
		
		ColorRegistry.load(configuration);
		CompostRegistry.load(configuration);
		
	}
	
	
	@SubscribeEvent
	public void onCofigutationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		
		if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
			//resync configs
			loadConfiguration();
			
		}
		
	}
	
	private static void loadConfiguration() {
		//testValue = configuration.getBoolean("configValue", Configuration.CATEGORY_GENERAL, false, "this is an example config val");
		
		if (configuration.hasChanged()) {
			configuration.save();
		}
	}

}
