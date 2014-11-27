package be.driesstelten.FarmingUtilities.block;

import net.minecraft.block.material.Material;
import be.driesstelten.FarmingUtilities.creativeTab.CreativeTabFU;

public class BlockCompost extends BlockFU {
	
	public BlockCompost() {
		
		super(Material.ground);
		this.setBlockName("compost");
		this.setHardness(0.5F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypeGravel);
		this.setCreativeTab(CreativeTabFU.FU_TAB);
	}

}
