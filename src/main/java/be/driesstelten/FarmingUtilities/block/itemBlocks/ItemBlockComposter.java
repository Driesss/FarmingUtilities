package be.driesstelten.FarmingUtilities.block.itemBlocks;

import be.driesstelten.FarmingUtilities.data.BlockData;
import be.driesstelten.FarmingUtilities.data.ModData;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockComposter extends ItemBlock {
	
	public ItemBlockComposter(Block block) {
		super(block);
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		
		return String.format("tile.%s%s", ModData.ID + ":", BlockData.COMPOSTER_UNLOCALIZED_NAMES[itemstack.getItemDamage()]);
		
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}	
	
	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}

}
