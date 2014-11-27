package be.driesstelten.FarmingUtilities.utility;

import net.minecraft.item.Item;

public class Compostable {

	public String unlocalizedName;
	public Item item;
	public int meta;
	public float value;
	public Color color;
	
	public Compostable(Item item, int meta, float value, Color color) {
		this.item = item;
		this.meta = meta;
		this.value = value;
		this.color = color;
	}
}
