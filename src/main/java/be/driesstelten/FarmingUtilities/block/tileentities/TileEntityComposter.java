package be.driesstelten.FarmingUtilities.block.tileentities;

import be.driesstelten.FarmingUtilities.init.ModBlocks;
import be.driesstelten.FarmingUtilities.init.ModItems;
import be.driesstelten.FarmingUtilities.registries.ColorRegistry;
import be.driesstelten.FarmingUtilities.registries.CompostRegistry;
import be.driesstelten.FarmingUtilities.utility.Color;
import be.driesstelten.FarmingUtilities.utility.Compostable;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

public class TileEntityComposter extends TileEntity implements ISidedInventory {
	
	public enum ComposterMode {
		EMPTY(0, false), 
		COMPOST(2, false), 
		DONE(3, true); 

		private ComposterMode(int v, boolean extract) {
			this.value = v;
			this.canExtract = extract;
		}
		
		public int value;
		public boolean canExtract;
	}
	
	private static final float MIN_RENDER_CAPACITY = 0.1f;
	private static final float MAX_RENDER_CAPACITY = 0.9f;
	private static final int MAX_COMPOSTING_TIME = 1000;
	private static final int UPDATE_INTERVAL = 10;
	
	private float volume;
	private int timer;
	private ComposterMode mode;
	public Color color;
	private Color colorBase;
	public IIcon icon;
	
	private boolean needsUpdate = false;
	private int updateTimer = 0;
	
	public ComposterMode getMode() {
		return mode;
	}
	
	public void setMode(ComposterMode mode) {
		this.mode = mode;
		this.needsUpdate = true;
	}
	
	public TileEntityComposter() {
		color = ColorRegistry.color("white");
		colorBase = color;
		setMode(ComposterMode.EMPTY);
		volume = 0;
		timer = 0;
	}

	
	@Override
	public void updateEntity() {
		
		if (updateTimer >= UPDATE_INTERVAL)
		{
			updateTimer = 0;
			if (needsUpdate)
			{
				needsUpdate = false;
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			}
		}
		else
		{
			updateTimer++;
		}
		
		switch(this.getMode()) {
		case EMPTY:
			//be empty
			break;
		case COMPOST:
			//do compost things
			if (volume >= 1.0F)
			{
				timer++;

				//Change color
				Color colorDirt = ColorRegistry.color("dirt");
				color = Color.average(colorBase, colorDirt, (float)timer / (float)MAX_COMPOSTING_TIME);

				//Are we done yet?
				if(timer >= TileEntityComposter.MAX_COMPOSTING_TIME)
				{
					setMode(ComposterMode.DONE);
					timer = 0;
					color = ColorRegistry.color("white");
					worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				}
			}
			break;
		default:
			break;
		}	
		
	}
	
	public boolean addCompostItem(Compostable item) {
		if (getMode() == ComposterMode.EMPTY)
		{
			setMode(ComposterMode.COMPOST);
			timer = 0;
		}

		if (getMode() == ComposterMode.COMPOST && volume < 1.0f)
		{
			volume += item.value;

			if (volume > 1.0f)
			{
				volume = 1.0f;
			}

			//Calculate the average of the colors
			float weightA = item.value / volume;
			float weightB = 1.0f - weightA;

			float r = weightA * item.color.r + weightB * color.r;
			float g = weightA * item.color.g + weightB * color.g;
			float b = weightA * item.color.b + weightB * color.b;
			float a = weightA * item.color.a + weightB * color.a;

			color = new Color(r,g,b,a);

			//Set the starting color that will be used in the cooking process.
			if (volume == 1.0f)
			{
				colorBase = color;
			}

			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			//needsUpdate = true;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean isFull() {
		if (volume >= 1.0f)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public boolean isDone() {
		return timer >= MAX_COMPOSTING_TIME;
	}

	public void resetColor() {
		colorBase = ColorRegistry.color("white");
		color = ColorRegistry.color("white");
	}

	public void giveAppropriateItem() {
		giveItem(getExtractItem());
	}
	
	private void giveItem(ItemStack item)
	{
		if(!worldObj.isRemote)
		{
			EntityItem entityitem = new EntityItem(worldObj, (double)xCoord + 0.5D, (double)yCoord + 1.5D, (double)zCoord + 0.5D, item);

			double f3 = 0.05F;
			entityitem.motionX = worldObj.rand.nextGaussian() * f3;
			entityitem.motionY = (0.2d);
			entityitem.motionZ = worldObj.rand.nextGaussian() * f3;

			worldObj.spawnEntityInWorld(entityitem);

			timer = 0;
		}

		resetComposter();
	}
	
	private ItemStack getExtractItem() {
		//geef compost
		if (getMode() == ComposterMode.DONE) {
			return new ItemStack(ModItems.compostpile);
		} else {
			return null;
		}
	}
	
	public float getVolume() {
		return volume;
	}
	
	public int getTimer() {
		return timer;
	}

	public float getAdjustedVolume() {
		float capacity = MAX_RENDER_CAPACITY - MIN_RENDER_CAPACITY;
		float adjusted = volume * capacity;		
		adjusted += MIN_RENDER_CAPACITY;
		return adjusted;
	}
	
	private void resetComposter() {
		volume = 0;
		color = ColorRegistry.color("white");
		colorBase = ColorRegistry.color("white");
		setMode(ComposterMode.EMPTY);
		needsUpdate = true;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		
		super.readFromNBT(compound);

		switch (compound.getInteger("mode"))
		{
		case 0:
			setMode(ComposterMode.EMPTY);
			break;

		case 1:
			setMode(ComposterMode.COMPOST);
			break;

		case 2:
			setMode(ComposterMode.DONE);
			break;
		}

		volume = compound.getFloat("volume");
		timer = compound.getInteger("timer");

		color = new Color(compound.getInteger("color"));
		colorBase = new Color (compound.getInteger("colorBase"));
		needsUpdate = true;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		
		super.writeToNBT(compound);
		compound.setInteger("mode", getMode().value);
		compound.setFloat("volume", volume);
		compound.setInteger("timer", timer);
		//compound.setInteger("color", color.toInt());
		//compound.setInteger("colorBase", colorBase.toInt());
	}
	
	@Override
	public Packet getDescriptionPacket() {
		
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);

		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, tag);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		
		NBTTagCompound tag = pkt.func_148857_g();
		readFromNBT(tag);
	}
	
	@Override
	public int getSizeInventory() {
		return 2;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		if (slot == 0)
		{
			return getExtractItem();
		}

		return null;
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (slot == 0)
		{
			ItemStack item = getExtractItem();

			resetComposter();
			return item;
		}

		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return null;
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		//pipe stuffz
		Item item = stack.getItem();
		int meta = stack.getItemDamage();
		
		if (slot == 0)
		{
			if (item == null)
			{
				resetComposter();
			}
		}

		if (slot == 1)
		{
			if (getMode() == ComposterMode.COMPOST || getMode() == ComposterMode.EMPTY)
			{
				if(CompostRegistry.containsItem(item, meta))
				{
					this.addCompostItem(CompostRegistry.getItem(item, meta));
				}
			}
		}

		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	@Override
	public String getInventoryName() {
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return false;
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item) {	
		if (slot == 1)
		{
			return isItemValid(item);
		}

		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		if (side == 0)
		{
			return new int[]{0};
		}else if (side == 1)
		{
			return new int[]{1};
		}

		return new int[0];
	}
	
	@Override
	public boolean canInsertItem(int slot, ItemStack item, int side) {
		if (side == 1 && slot == 1)
		{
			return isItemValid(item);
		}

		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack item, int side) {
		if (side == 0 && slot == 0)
		{
			if (getMode().canExtract == true)
			{
				return true;
			}
		}

		return false;
	}
	
	public boolean isItemValid(ItemStack stack)
	{
		///XXX isItemValid
		Item item = stack.getItem();
		int meta = stack.getItemDamage();
		
		if (!this.isFull() && getMode() == ComposterMode.COMPOST || getMode() == ComposterMode.EMPTY) {
			
			if(CompostRegistry.containsItem(item, meta)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

}
