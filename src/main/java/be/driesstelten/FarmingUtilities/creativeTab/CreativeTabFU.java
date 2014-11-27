package be.driesstelten.FarmingUtilities.creativeTab;

import be.driesstelten.FarmingUtilities.init.ModItems;
import be.driesstelten.FarmingUtilities.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabFU {
	
	public static final CreativeTabs FU_TAB = new CreativeTabs(Reference.MOD_ID) {
		
		@Override
		public Item getTabIconItem() {
			return ModItems.compostpile;
		}
		
	};

}
