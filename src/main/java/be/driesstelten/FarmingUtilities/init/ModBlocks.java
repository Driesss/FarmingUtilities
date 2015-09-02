package be.driesstelten.FarmingUtilities.init;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import be.driesstelten.FarmingUtilities.block.BlockComposter;
import be.driesstelten.FarmingUtilities.block.BlockFU;
import be.driesstelten.FarmingUtilities.block.itemBlocks.ItemBlockCompost;
import be.driesstelten.FarmingUtilities.block.itemBlocks.ItemBlockComposter;
import be.driesstelten.FarmingUtilities.data.BlockData;
import be.driesstelten.FarmingUtilities.block.BlockCompost;
import be.driesstelten.FarmingUtilities.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {
	
	public static Block compost;
	public static Block composter;
	
	public static void init() {
		
		compost = new BlockCompost();
		GameRegistry.registerBlock(compost, ItemBlockCompost.class, BlockData.COMPOST_KEY);
		
		composter = new BlockComposter();
		GameRegistry.registerBlock(composter, ItemBlockComposter.class, BlockData.COMPOSTER_KEY);
	}

}
