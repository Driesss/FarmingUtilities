package be.driesstelten.FarmingUtilities.block.itemBlocks;

import be.driesstelten.FarmingUtilities.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockComposter extends ItemBlock {
	
	public ItemBlockComposter(Block block) {
		super(block);
		setHasSubtypes(true);
	}
	
	public String getUnlocalizedName(ItemStack itemstack)
	{
		return Reference.MOD_ID + "." + Reference.COMPOSTER_UNLOCALIZED_NAMES[itemstack.getItemDamage()];
	}
	
	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}

}
