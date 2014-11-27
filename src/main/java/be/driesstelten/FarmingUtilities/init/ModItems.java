package be.driesstelten.FarmingUtilities.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import be.driesstelten.FarmingUtilities.item.ItemCompostPile;
import be.driesstelten.FarmingUtilities.item.ItemFU;
import be.driesstelten.FarmingUtilities.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
	
	public static final ItemFU compostpile = new ItemCompostPile();
	
	public static void init() {
		
		GameRegistry.registerItem(compostpile, "compostpile");
		
	}

}
