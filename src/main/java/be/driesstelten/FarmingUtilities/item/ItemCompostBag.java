package be.driesstelten.FarmingUtilities.item;

import be.driesstelten.FarmingUtilities.creativeTab.CreativeTabFU;
import be.driesstelten.FarmingUtilities.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCompostBag extends ItemFU {

	public ItemCompostBag() {
		super();
		this.setUnlocalizedName("compostbag");
		this.setCreativeTab(CreativeTabFU.FU_TAB);
	}

	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int par7,
			float par8, float par9, float par10) {

		if (!player.canPlayerEdit(x, y, z, par7, itemStack)) {
			return false;
		} else {
			Block block = world.getBlock(x, y, z);
			if (par7 != 0 && world.getBlock(x, y + 1, z).isAir(world, x, y + 1, z) && (block == Blocks.farmland)) {
				Block block1 = ModBlocks.fertilefarmland;
				world.playSoundEffect((double) ((float) x + 0.5F), (double) ((float) y + 0.5F),
						(double) ((float) z + 0.5F), block1.stepSound.getStepResourcePath(),
						(block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getPitch() * 0.8F);
				if (world.isRemote) {
					return true;
				} else {
					world.setBlock(x, y, z, block1);
					itemStack.stackSize--;
					return true;
				}
			} else {
				return false;
			}
		}
	}
}
