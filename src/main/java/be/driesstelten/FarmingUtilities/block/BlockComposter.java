package be.driesstelten.FarmingUtilities.block;

import org.apache.logging.log4j.Level;

import be.driesstelten.FarmingUtilities.block.tileentities.TileEntityComposter;
import be.driesstelten.FarmingUtilities.block.tileentities.TileEntityComposter.ComposterMode;
import be.driesstelten.FarmingUtilities.creativeTab.CreativeTabFU;
import be.driesstelten.FarmingUtilities.reference.Reference;
import be.driesstelten.FarmingUtilities.reference.RenderIds;
import be.driesstelten.FarmingUtilities.registries.CompostRegistry;
import be.driesstelten.FarmingUtilities.utility.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockComposter extends BlockFU implements ITileEntityProvider {
	
	@SideOnly(Side.CLIENT)
	public static IIcon iconCompost;
	
	public BlockComposter() {
		super(Material.wood);
		setCreativeTab(CreativeTabFU.FU_TAB);
		setHardness(2.0f);
		setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 1.0F, 0.9F);
		this.setBlockName("composter");
		GameRegistry.registerTileEntity(TileEntityComposter.class, this.getUnlocalizedName());
		
	}
	
	public BlockComposter(Material material) {
		super(material);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		
		return new TileEntityComposter();
	}
	
	@Override
	public int damageDropped (int metadata) {
		return metadata;
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		
		super.onBlockAdded(world, x, y, z);
		
		int meta = world.getBlockMetadata(x, y, z);
	
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (player == null) {
			LogHelper.info("player == null");
			return false;
		}
		
		LogHelper.info("player");
		LogHelper.info(player);
		
		TileEntityComposter composter = (TileEntityComposter) world.getTileEntity(x, y, z);
		
		LogHelper.info("tileEntity");
		LogHelper.info("\t" + composter);
		LogHelper.info("composter mode");
		LogHelper.info("\t" + composter.getMode());
		LogHelper.info("comoster can extract");
		LogHelper.info("\t" + composter.getMode().canExtract);
		LogHelper.info("equipped item");
		LogHelper.info("\t" + player.getCurrentEquippedItem());

		if (composter.getMode().canExtract == true) {
			
			composter.giveAppropriateItem();
			
		} else if (player.getCurrentEquippedItem() != null) {
			
			ItemStack item = player.getCurrentEquippedItem();
			if (item!=null) {
				//COMPOST!
				if ((composter.getMode() == ComposterMode.EMPTY || composter.getMode() == ComposterMode.COMPOST) && !composter.isFull()) {
					LogHelper.info(CompostRegistry.containsItem(item.getItem(), item.getItemDamage()));
					if (CompostRegistry.containsItem(item.getItem(), item.getItemDamage())) {
						composter.addCompostItem(CompostRegistry.getItem(item.getItem(), item.getItemDamage()));
						if (!player.capabilities.isCreativeMode) {
							item.stackSize -= 1;
							if (item.stackSize == 0) {
								item = null;
							}
						}
					} else {
						System.out.println("Item not registered for compost: " + item.getItem() + ":" + item.getItemDamage());	
					}
				}
			}
		}
		//Return true to keep buckets from pouring all over the damn place.
		return true;
	}
	
	public void useItem(EntityPlayer player) {
		if (!player.capabilities.isCreativeMode) {

			ItemStack item = player.inventory.mainInventory[player.inventory.currentItem];
			item.stackSize -= 1;
			if (item.stackSize == 0) {
				item = null;
			}
		}
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register) {
		blockIcon = Blocks.planks.getIcon(0, 0);
		iconCompost = register.registerIcon(Reference.MOD_ID + ":" + "IconBarrelCompost");
	}
	
	@Override
	public int getRenderType() {
		return RenderIds.composter;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean hasTileEntity(int meta) {
		return true;
	}
	
	private ItemStack getContainer(ItemStack item) {
		if (item.stackSize == 1) {
			if (item.getItem().hasContainerItem(item))  {
				return item.getItem().getContainerItem(item);
			} else  {
				return null;
			}
		} else {
			item.splitStack(1);
			return item;
		}
	}

	@Override
	public int getLightValue() {
		
		return 0;
	}


}
