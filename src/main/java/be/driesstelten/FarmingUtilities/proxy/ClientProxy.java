package be.driesstelten.FarmingUtilities.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import be.driesstelten.FarmingUtilities.block.models.ModelComposter;
import be.driesstelten.FarmingUtilities.block.renderers.RenderComposter;
import be.driesstelten.FarmingUtilities.block.renderers.blockItems.ItemRenderComposter;
import be.driesstelten.FarmingUtilities.block.tileentities.TileEntityComposter;
import be.driesstelten.FarmingUtilities.init.ModBlocks;
import be.driesstelten.FarmingUtilities.utility.LogHelper;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void initializeRenderers() {
		
		ModelComposter composter = new ModelComposter();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityComposter.class, new RenderComposter(composter));
		//LogHelper.info("****** Shoud register itemrenderer now");
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.composter), new ItemRenderComposter(composter));
		//LogHelper.info("****** Shoud be done registring");
		
	}

	@Override
	public void registerKeyBindings() {
		
		
	}
	
	public World getWorld()
	{
		World world = null;
		try
		{
			world = Minecraft.getMinecraft().theWorld;
		}
		catch (Exception ex) {}
		return world;
	}


}
