package be.driesstelten.FarmingUtilities.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import be.driesstelten.FarmingUtilities.item.ItemCompostBag;
import be.driesstelten.FarmingUtilities.item.ItemCompostPile;
import be.driesstelten.FarmingUtilities.item.ItemFU;
import be.driesstelten.FarmingUtilities.item.ItemWoodBucket;
import be.driesstelten.FarmingUtilities.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
	
	public static final Item compostpile = new ItemCompostPile();
	public static final Item compostbag = new ItemCompostBag();
	public static final Item woodbucket = (new ItemWoodBucket(Blocks.air)).setMaxStackSize(16);
	public static final Item waterwoodbucket = new ItemWoodBucket(Blocks.flowing_water).setContainerItem(woodbucket);
	
	public static void init() {
		
		GameRegistry.registerItem(compostpile, "compostpile");
		GameRegistry.registerItem(compostbag, "compostbag");
		GameRegistry.registerItem(woodbucket, "woodbucket");
		GameRegistry.registerItem(waterwoodbucket, "waterwoodbucket");
		
	}

}
