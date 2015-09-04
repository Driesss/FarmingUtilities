package be.driesstelten.FarmingUtilities.item;

import be.driesstelten.FarmingUtilities.creativeTab.CreativeTabFU;
import be.driesstelten.FarmingUtilities.init.ModItems;
import be.driesstelten.FarmingUtilities.reference.Reference;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import scala.collection.generic.IterableForwarder;

public class ItemWoodBucket extends ItemBucket {
	
	private Block isFull;

	public ItemWoodBucket(Block block) {
		super(block);
		this.isFull = block;
		this.setCreativeTab(CreativeTabFU.FU_TAB);
		if (block == Blocks.flowing_water) {
			this.setUnlocalizedName("waterwoodbucket");
		} else {
			this.setUnlocalizedName("woodbucket");
		}
	}
	
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
        boolean flag = this.isFull == Blocks.air;
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, flag);

        if (movingobjectposition == null) {
            return itemstack;
        } else {
            FillBucketEvent event = new FillBucketEvent(player, itemstack, world, movingobjectposition);
            if (MinecraftForge.EVENT_BUS.post(event)) {
                return itemstack;
            }

            if (event.getResult() == Event.Result.ALLOW) {
                if (player.capabilities.isCreativeMode) {
                    return itemstack;
                }

                if (--itemstack.stackSize <= 0) {
                    return event.result;
                }

                if (!player.inventory.addItemStackToInventory(event.result)) {
                    player.dropPlayerItemWithRandomChoice(event.result, false);
                }

                return itemstack;
            }
            
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                int i = movingobjectposition.blockX;
                int j = movingobjectposition.blockY;
                int k = movingobjectposition.blockZ;

                if (!world.canMineBlock(player, i, j, k)) {
                    return itemstack;
                }

                if (flag) {
                    if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, itemstack)) {
                        return itemstack;
                    }

                    Material material = world.getBlock(i, j, k).getMaterial();
                    int l = world.getBlockMetadata(i, j, k);

                    if (material == Material.water && l == 0) {
                        world.setBlockToAir(i, j, k);
                        return this.giveApropriateItem(itemstack, player, ModItems.waterwoodbucket);
                    }
                } else {
                    if (this.isFull == Blocks.air) {
                        return new ItemStack(ModItems.woodbucket);
                    }

                    if (movingobjectposition.sideHit == 0) {
                        --j;
                    }

                    if (movingobjectposition.sideHit == 1) {
                        ++j;
                    }

                    if (movingobjectposition.sideHit == 2) {
                        --k;
                    }

                    if (movingobjectposition.sideHit == 3) {
                        ++k;
                    }

                    if (movingobjectposition.sideHit == 4) {
                        --i;
                    }

                    if (movingobjectposition.sideHit == 5) {
                        ++i;
                    }

                    if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, itemstack)) {
                        return itemstack;
                    }

                    if (this.tryPlaceContainedLiquid(world, i, j, k) && !player.capabilities.isCreativeMode) {
                        return new ItemStack(ModItems.woodbucket);
                    }
                }
            }

            return itemstack;
        }
    }
    
    private ItemStack giveApropriateItem(ItemStack itemstack, EntityPlayer player, Item item) {
        if (player.capabilities.isCreativeMode) {
            return itemstack;
        }
        else if (--itemstack.stackSize <= 0) {
            return new ItemStack(item);
        } else {
            if (!player.inventory.addItemStackToInventory(new ItemStack(item))) {
                player.dropPlayerItemWithRandomChoice(new ItemStack(item, 1, 0), false);
            }

            return itemstack;
        }
    }
	
	@Override
	public String getUnlocalizedName() {
		
		return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
		
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		
		return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		
		itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
		
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}	

}
