package be.driesstelten.FarmingUtilities.init;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import be.driesstelten.FarmingUtilities.block.BlockCompost;
import be.driesstelten.FarmingUtilities.block.BlockComposter;
import be.driesstelten.FarmingUtilities.block.itemBlocks.ItemBlockFertileFarmLand;
import be.driesstelten.FarmingUtilities.block.itemBlocks.ItemBlockComposter;
import be.driesstelten.FarmingUtilities.data.BlockData;
import be.driesstelten.FarmingUtilities.block.BlockFertileFarmLand;
import be.driesstelten.FarmingUtilities.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {
	
	public static Block fertilefarmland;
	public static Block composter;
	public static Block compost;
	
	public static void init() {
		
		fertilefarmland = new BlockFertileFarmLand();
		GameRegistry.registerBlock(fertilefarmland, ItemBlockFertileFarmLand.class, BlockData.FERTILEFARMLAND_KEY);
		
		composter = new BlockComposter();
		GameRegistry.registerBlock(composter, ItemBlockComposter.class, BlockData.COMPOSTER_KEY);
		
		compost = new BlockCompost();
		GameRegistry.registerBlock(compost, BlockData.COMPOST_KEY);
	}

}
