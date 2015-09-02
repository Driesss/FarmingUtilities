package be.driesstelten.FarmingUtilities.init;

import cpw.mods.fml.common.registry.GameRegistry;
import be.driesstelten.FarmingUtilities.item.ItemCompostBag;
import be.driesstelten.FarmingUtilities.item.ItemCompostPile;
import be.driesstelten.FarmingUtilities.item.ItemFU;
import be.driesstelten.FarmingUtilities.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
	
	public static final ItemFU compostpile = new ItemCompostPile();
	public static final ItemFU compostbag = new ItemCompostBag();
	
	public static void init() {
		
		GameRegistry.registerItem(compostpile, "compostpile");
		GameRegistry.registerItem(compostbag, "compostbag");
		
	}

}
