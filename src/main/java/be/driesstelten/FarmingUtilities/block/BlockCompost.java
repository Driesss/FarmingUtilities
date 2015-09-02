package be.driesstelten.FarmingUtilities.block;

import java.util.Random;

import be.driesstelten.FarmingUtilities.creativeTab.CreativeTabFU;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockCompost extends BlockFU {

	public BlockCompost() {
		
		super(Material.ground);
		this.setBlockName("compost");
		this.setTickRandomly(true);
		this.setHardness(0.5F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypeGravel);
		this.setCreativeTab(CreativeTabFU.FU_TAB);
	}
	
	public void updateTick(World world, int x, int y, int z, Random random)
    {			
			if (world.getBlock(x, y + 1, z) instanceof BlockFertileFarmLand) {
				world.getBlock(x, y + 1, z).updateTick(world, x, y + 1, z, random);
				
			} else if ((world.getBlock(x, y + 2, z) instanceof BlockFertileFarmLand) && (world.getBlock(x, y + 1, z) instanceof BlockCompost)) {
				world.getBlock(x, y + 2, z).updateTick(world, x, y + 2, z, random);
				
			} else if ((world.getBlock(x, y + 3, z) instanceof BlockFertileFarmLand) && (world.getBlock(x, y + 1, z) instanceof BlockCompost)) {
				world.getBlock(x, y + 3, z).updateTick(world, x, y + 3, z, random);
				
			} else if ((world.getBlock(x, y + 4, z) instanceof BlockFertileFarmLand) && (world.getBlock(x, y + 1, z) instanceof BlockCompost)) {
				world.getBlock(x, y + 4, z).updateTick(world, x, y + 4, z, random);
			}
            
    }
	
}
