package be.driesstelten.FarmingUtilities.init;

import net.minecraft.block.BlockContainer;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import be.driesstelten.FarmingUtilities.block.BlockComposter;
import be.driesstelten.FarmingUtilities.block.BlockFU;
import be.driesstelten.FarmingUtilities.block.BlockCompost;
import be.driesstelten.FarmingUtilities.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {
	
	public static final BlockFU compost = new BlockCompost();
	public static final BlockFU composter = new BlockComposter();
	
	public static void init() {
		
		GameRegistry.registerBlock(compost, "compost");
		GameRegistry.registerBlock(composter, "composter");
	}

}
