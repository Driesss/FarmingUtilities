package be.driesstelten.FarmingUtilities.block;

import be.driesstelten.FarmingUtilities.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockFU extends Block {
	
	public BlockFU(Material material) {
		
		super(material);
	}
	
	public BlockFU() {
	
		this(Material.ground);
	}
	
	@Override
	public String getUnlocalizedName() {
		
		return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		
		//LogHelper.info("!!!!!!!!!!!!!!" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "!!!!!!!!!!!!!!!!");
		blockIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
		
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}

}
