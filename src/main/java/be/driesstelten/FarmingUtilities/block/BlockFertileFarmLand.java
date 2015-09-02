package be.driesstelten.FarmingUtilities.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockReed;
import net.minecraft.block.BlockStem;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

import be.driesstelten.FarmingUtilities.creativeTab.CreativeTabFU;
import be.driesstelten.FarmingUtilities.data.ModData;
import be.driesstelten.FarmingUtilities.utility.FixedRandom;
import be.driesstelten.FarmingUtilities.utility.StemFixedRandom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFertileFarmLand extends BlockFU {

	@SideOnly(Side.CLIENT)
	private IIcon iconDry;
	@SideOnly(Side.CLIENT)
	private IIcon iconWet;
	@SideOnly(Side.CLIENT)
	private IIcon iconSide;
	private Random frnd = new FixedRandom();
	private Random s_prng = new StemFixedRandom();;

	public BlockFertileFarmLand() {

		super(Material.ground);
		this.setBlockName("fertilefarmland");
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
		this.setTickRandomly(true);
		this.setHardness(0.5F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypeGravel);
		this.setCreativeTab(CreativeTabFU.FU_TAB);
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this
	 * box can change after the pool has been cleared to be reused)
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return AxisAlignedBB.getBoundingBox((double) (x + 0), (double) (y + 0), (double) (z + 0), (double) (x + 1),
				(double) (y + 1), (double) (z + 1));
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether
	 * or not to render the shared face of two adjacent blocks and also whether
	 * the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube() {
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False
	 * (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock() {
		return false;
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		// return par1 == 1 ? (par2 > 0 ? this.iconDry : this.iconWet) :
		// Blocks.dirt.getBlockTextureFromSide(par1);
		return par1 == 1 ? (par2 > 0 ? this.iconWet : this.iconDry) : this.iconSide;
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World world, int x, int y, int z, Random random) {
		// get the plant
		Block plant_block = world.getBlock(x, y + 1, z);

		if (plant_block instanceof BlockStem) {
			// Stem-based plants grow slightly differently, we need to generate
			// a
			// proper random number so that the block isn't placed in the same
			// place
			// all the time
			if (world.getBlockMetadata(x, y + 1, z) >= 7)
				plant_block.updateTick(world, x, y + 1, z, this.s_prng);
			else
				plant_block.updateTick(world, x, y + 1, z, this.frnd);
		}
		// Cactus and Sugar Canes too are handled slightly differently
		else if (plant_block instanceof BlockReed || plant_block instanceof BlockCactus) {
			for (int i = 0; world.blockExists(x, y + 1 + i, z) && i < 3; i++) {
				world.getBlock(x, y + 1 + i, z).updateTick(world, x, y + 1 + i, z, this.frnd);
			}
		} else if (plant_block instanceof IPlantable) {
			plant_block.updateTick(world, x, y + 1, z, this.frnd);
		}

		if (!this.isWaterNearby(world, x, y, z) && !world.canLightningStrikeAt(x, y + 1, z)) {
			int l = world.getBlockMetadata(x, y, z);

			if (l > 0) {
				world.setBlockMetadataWithNotify(x, y, z, l - 1, 2);
			} else if (!this.isCropsNearby(world, x, y, z)) {
				world.setBlock(x, y, z, Blocks.dirt);
			}
		} else {
			world.setBlockMetadataWithNotify(x, y, z, 7, 2);
		}
	}

	/**
	 * Block's chance to react to an entity falling on it.
	 */
	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float par6) {
		if (!world.isRemote && world.rand.nextFloat() < par6 - 0.5F) {
			if (!(entity instanceof EntityPlayer) && !world.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
				return;
			}

			world.setBlock(x, y, z, Blocks.dirt);
		}
	}

	private boolean isCropsNearby(World world, int x, int y, int z) {
		byte b0 = 0;

		for (int l = x - b0; l <= x + b0; ++l) {
			for (int i1 = z - b0; i1 <= z + b0; ++i1) {
				Block block = world.getBlock(l, y + 1, i1);

				if (block instanceof IPlantable
						&& canSustainPlant(world, x, y, z, ForgeDirection.UP, (IPlantable) block)) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean isWaterNearby(World world, int x, int y, int z) {
		for (int l = x - 4; l <= x + 4; ++l) {
			for (int i1 = y; i1 <= y + 1; ++i1) {
				for (int j1 = z - 4; j1 <= z + 4; ++j1) {
					if (world.getBlock(l, i1, j1).getMaterial() == Material.water) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which
	 * neighbor changed (coordinates passed are their own) Args: x, y, z,
	 * neighbor Block
	 */
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		super.onNeighborBlockChange(world, x, y, z, block);
		Block block1 = world.getBlock(x, y + 1, z);
		Material material = block1.getMaterial();
		if (material.isSolid()) {
			world.setBlock(x, y, z, Blocks.dirt);
		}
	}

	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction,
			IPlantable plantable) {
		EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);

		return (plantType == EnumPlantType.Crop || plantType == EnumPlantType.Nether || plantType == EnumPlantType.Beach);

	}

	public Item getItemDropped(int par1, Random random, int par3) {
		return Blocks.dirt.getItemDropped(0, random, par3);
	}

	/**
	 * Gets an item for the block being called on. Args: world, x, y, z
	 */
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		return Item.getItemFromBlock(Blocks.dirt);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		this.iconDry = register.registerIcon(ModData.TEXTURE_LOCATION + ":compostDry");
		this.iconWet = register.registerIcon(ModData.TEXTURE_LOCATION + ":compostWet");
		this.iconSide = register.registerIcon(ModData.TEXTURE_LOCATION + ":compost");

	}

}
