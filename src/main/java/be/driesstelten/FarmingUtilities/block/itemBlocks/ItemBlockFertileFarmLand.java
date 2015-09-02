package be.driesstelten.FarmingUtilities.block.itemBlocks;

import be.driesstelten.FarmingUtilities.data.ModData;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockFertileFarmLand extends ItemBlock {
	
	public ItemBlockFertileFarmLand(Block block) {
		super(block);
		setHasSubtypes(false);
	}
	
	@Override
	public String getUnlocalizedName() {
		
		return String.format("tile.%s%s", ModData.ID + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
		
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
