package be.driesstelten.FarmingUtilities.proxy;

import be.driesstelten.FarmingUtilities.block.tileentities.TileEntityComposter;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy{
	
	public void registerTileEntities() {

		GameRegistry.registerTileEntity(TileEntityComposter.class, TileEntityComposter.);
		
	}

}
