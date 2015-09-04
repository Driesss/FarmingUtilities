package be.driesstelten.FarmingUtilities.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class Recepies {
	
	public static void init() {
		
		ItemStack compostbag = new ItemStack(ModItems.compostbag);
		ItemStack compostpile = new ItemStack(ModItems.compostpile);
		ItemStack compost = new ItemStack(ModBlocks.compost);
		ItemStack dirt = new ItemStack(Blocks.dirt);
		
		GameRegistry.addShapelessRecipe(compostbag, compostpile, compostpile, compostpile, compostpile );	
		GameRegistry.addShapelessRecipe(compost, dirt, compostbag);
		GameRegistry.addRecipe(new ItemStack(ModItems.woodbucket), "   ", "x x", " x ", 'x', new ItemStack(Blocks.log));
		
		for(int i = 0; i < 6; i++) {
			GameRegistry.addRecipe(new ItemStack(ModBlocks.composter, 1, i),
					"x x",
					"x x",
					"xyx",
					'x', new ItemStack(Blocks.planks, 1, i), 
					'y', new ItemStack(Blocks.wooden_slab, 1, i));
		}
	}

}
