package be.driesstelten.FarmingUtilities.registries;

import java.util.Hashtable;

import be.driesstelten.FarmingUtilities.utility.Color;
import be.driesstelten.FarmingUtilities.utility.Compostable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

public class CompostRegistry {
	
public static Hashtable<String, Compostable> entries = new Hashtable<String, Compostable>();
	
	//Value is how much it fills a barrel. 0.01 = 1%, 1 = 100%;
	public static void register(Item item, int meta, float value, Color color) {
		Compostable entry = new Compostable(item, meta, value, color);
		entries.put(item + ":" + meta, entry);
	}
	
	public static boolean containsItem(Item item, int meta) {
		return entries.containsKey(item + ":" + meta);
	}
	
	
	public static Compostable getItem(Item item, int meta) {
		return entries.get(item + ":" + meta);
	}
	
	public static void load(Configuration config) {
		
		//saplings
		register(Item.getItemFromBlock(Blocks.sapling), 0, 0.125f, ColorRegistry.color("oak"));
		register(Item.getItemFromBlock(Blocks.sapling), 1, 0.125f, ColorRegistry.color("spruce"));
		register(Item.getItemFromBlock(Blocks.sapling), 2, 0.125f, ColorRegistry.color("birch"));
		register(Item.getItemFromBlock(Blocks.sapling), 3, 0.125f,  ColorRegistry.color("jungle")); 
		register(Item.getItemFromBlock(Blocks.sapling), 4, 0.125f,  ColorRegistry.color("acacia")); 
		register(Item.getItemFromBlock(Blocks.sapling), 5, 0.125f,  ColorRegistry.color("dark_oak")); 		
		
		//leaves
		register(Item.getItemFromBlock(Blocks.leaves), 0, 0.125f, ColorRegistry.color("oak"));
		register(Item.getItemFromBlock(Blocks.leaves), 1, 0.125f, ColorRegistry.color("spruce"));
		register(Item.getItemFromBlock(Blocks.leaves), 2, 0.125f, ColorRegistry.color("birch"));
		register(Item.getItemFromBlock(Blocks.leaves), 3, 0.125f,  ColorRegistry.color("jungle"));
		register(Item.getItemFromBlock(Blocks.leaves2), 0, 0.125f,  ColorRegistry.color("acacia_leaves")); 
		register(Item.getItemFromBlock(Blocks.leaves2), 1, 0.125f,  ColorRegistry.color("dark_oak")); 
		
		//rotten flesh
		register(Items.rotten_flesh, 0, 0.10f, ColorRegistry.color("rotten_flesh"));
		//spider eye
		register(Items.spider_eye, 0, 0.08f, ColorRegistry.color("spider_eye"));
		
		//wheat
		register(Items.wheat, 0, 0.08f, ColorRegistry.color("wheat"));
		//bread
		register(Items.bread, 0, 0.16f, ColorRegistry.color("bread"));
		
		//dandelion
		register(Item.getItemFromBlock(Blocks.yellow_flower), 0, 0.10f, ColorRegistry.color("dandelion"));
		//poppy
		register(Item.getItemFromBlock(Blocks.red_flower), 0, 0.10f, ColorRegistry.color("poppy"));
		//blue orchid
		register(Item.getItemFromBlock(Blocks.red_flower), 1, 0.10f, ColorRegistry.color("blue_orchid"));
		//allium
		register(Item.getItemFromBlock(Blocks.red_flower), 2, 0.10f, ColorRegistry.color("allium"));
		//azure bluet
		register(Item.getItemFromBlock(Blocks.red_flower), 3, 0.10f, ColorRegistry.color("azure_bluet"));
		//red_tulip
		register(Item.getItemFromBlock(Blocks.red_flower), 4, 0.10f, ColorRegistry.color("red_tulip"));
		//orange tulip
		register(Item.getItemFromBlock(Blocks.red_flower), 5, 0.10f, ColorRegistry.color("orange_tulip"));
		//white tulip
		register(Item.getItemFromBlock(Blocks.red_flower), 6, 0.10f, ColorRegistry.color("white_tulip"));
		//pink tulip
		register(Item.getItemFromBlock(Blocks.red_flower), 7, 0.10f, ColorRegistry.color("pink_tulip"));
		//oxeye daisy
		register(Item.getItemFromBlock(Blocks.red_flower), 8, 0.10f, ColorRegistry.color("oxeye_daisy"));
		
		//sunflower
		register(Item.getItemFromBlock(Blocks.double_plant), 0, 0.10f, ColorRegistry.color("sunflower"));
		//lilac
		register(Item.getItemFromBlock(Blocks.double_plant), 1, 0.10f, ColorRegistry.color("lilac"));
		//rose buse
		register(Item.getItemFromBlock(Blocks.double_plant), 4, 0.10f, ColorRegistry.color("rose"));
		//peony
		register(Item.getItemFromBlock(Blocks.double_plant), 5, 0.10f, ColorRegistry.color("peony"));
		
		//mushroom_brown
		register(Item.getItemFromBlock(Blocks.brown_mushroom), 0, 0.10f, ColorRegistry.color("mushroom_brown"));
		//mushroom_red
		register(Item.getItemFromBlock(Blocks.red_mushroom), 0, 0.10f, ColorRegistry.color("mushroom_red"));
		
		//pumpkin pie
		register(Items.pumpkin_pie, 0, 0.16f, ColorRegistry.color("pumpkin_pie"));
		
		//pork
		register(Items.porkchop, 0, 0.2f, ColorRegistry.color("pork_raw"));
		//cooked pork
		register(Items.cooked_porkchop, 0, 0.2f, ColorRegistry.color("pork_cooked"));
		
		//beef
		register(Items.beef, 0, 0.2f, ColorRegistry.color("beef_raw"));
		//cooked beef
		register(Items.cooked_beef, 0, 0.2f, ColorRegistry.color("beef_cooked"));
		
		//chicken
		register(Items.chicken, 0, 0.2f, ColorRegistry.color("chicken_raw"));
		//cooked chicken
		register(Items.cooked_chicken, 0, 0.2f, ColorRegistry.color("chicken_cooked"));
		
		//fish
		register(Items.fish, 0, 0.15f, ColorRegistry.color("fish_raw"));
		//cooked fish
		register(Items.cooked_fished, 0, 0.15f, ColorRegistry.color("fish_cooked"));
		
		//salmon
		register(Items.fish, 1, 0.15f, ColorRegistry.color("salmon_raw"));
		//cooked salmon
		register(Items.cooked_fished, 1, 0.15f, ColorRegistry.color("salmon_cooked"));
		
		//clownfish
		register(Items.fish, 2, 0.15f, ColorRegistry.color("clownfish"));
		//blowfish
		register(Items.fish, 3, 0.15f, ColorRegistry.color("pufferfish"));
		
		//apple
		register(Items.apple, 0, 0.10f, ColorRegistry.color("apple"));
		//melon slice
		register(Items.melon, 0, 0.04f, ColorRegistry.color("melon"));
		//melon
		register(Item.getItemFromBlock(Blocks.melon_block), 0, 1.0f / 6, ColorRegistry.color("melon"));
		//pumpkin
		register(Item.getItemFromBlock(Blocks.pumpkin), 0, 1.0f / 6, ColorRegistry.color("pumpkin"));
		//jack o lantern
		register(Item.getItemFromBlock(Blocks.lit_pumpkin), 0, 1.0f / 6, ColorRegistry.color("pumpkin"));
		//cactus
		register(Item.getItemFromBlock(Blocks.cactus), 0, 0.10f, ColorRegistry.color("cactus"));
		
		//carrot
		register(Items.carrot, 0, 0.08f, ColorRegistry.color("carrot"));
		//potato
		register(Items.potato, 0, 0.08f, ColorRegistry.color("potato"));
		//baked potato
		register(Items.baked_potato, 0, 0.08f, ColorRegistry.color("potato_baked"));
		//poison potato
		register(Items.poisonous_potato, 0, 0.08f, ColorRegistry.color("potato_poison"));
		
		//waterlily
		register(Item.getItemFromBlock(Blocks.waterlily), 0, 0.10f, ColorRegistry.color("waterlily"));
		//vine
		register(Item.getItemFromBlock(Blocks.vine), 0, 0.10f, ColorRegistry.color("vine"));
		//tall grass
		register(Item.getItemFromBlock(Blocks.tallgrass), 1, 0.08f, ColorRegistry.color("tall_grass"));
		//egg
		register(Items.egg, 0, 0.08f, ColorRegistry.color("egg"));
		//netherwart
		register(Items.nether_wart, 0, 0.10f, ColorRegistry.color("netherwart"));
		//sugar cane
		register(Items.reeds, 0, 0.08f, ColorRegistry.color("sugar_cane"));
		//string
		register(Items.string, 0, 0.04f, ColorRegistry.color("white"));
	}

}
