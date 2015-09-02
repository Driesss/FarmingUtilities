package be.driesstelten.FarmingUtilities.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

public class Recepies {
	
	public static void init() {
		
		ItemStack compostbag = new ItemStack(ModItems.compostbag);
		ItemStack compostpile = new ItemStack(ModItems.compostpile);
		ItemStack compost = new ItemStack(ModBlocks.compost);
		
		GameRegistry.addShapelessRecipe(compostbag, compostpile, compostpile, compostpile, compostpile );	
		GameRegistry.addShapelessRecipe(compost, compostbag);
	}

}
