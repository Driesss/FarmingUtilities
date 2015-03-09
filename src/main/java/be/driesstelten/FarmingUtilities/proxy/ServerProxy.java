package be.driesstelten.FarmingUtilities.proxy;

import be.driesstelten.FarmingUtilities.utility.LogHelper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class ServerProxy extends CommonProxy {

	@Override
	public void registerKeyBindings() {
		//NOP
	}

	@Override
	public void initializeRenderers() {
		//NOP
		
	}
	
	public World getWorld()
	{
		World world = null;
		try
		{
			world = MinecraftServer.getServer().worldServers[0];
		}
		catch (Exception ex) 
		{
		  LogHelper.info("Error while getting server side world reference");
		}
		return world;
	}

}
