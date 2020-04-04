package me.hal989.halsaddon;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.bstats.bukkit.Metrics;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
public class HALsAddon extends JavaPlugin implements SlimefunAddon {
	
	@Override
	public void onEnable() {
		// Read something from your config.yml
		Config cfg = new Config(this);

		if (cfg.getBoolean("options.auto-update")) {
			// You could start an Auto-Updater for example
		}

		// Slimefun4 also already comes with a bundled version of bStats
		// You can use bStats to collect usage data about your plugin
		// More info: https://bstats.org/getting-started
		// Set bStatsId to the id of your plugin
		int bStatsId = -1;
		new Metrics(this, bStatsId);


		// Create a new Category
		// This Category will use this ItemStack
		NamespacedKey categoryId = new NamespacedKey(this, "ender_items");
		CustomItem categoryItem = new CustomItem(Material.END_STONE, "&5Addon Jam: The End");

		Category category = new Category(categoryId, categoryItem);

		// Create a new Slimefun ItemStack
		// This class has many constructors, it is very important that you give each item a unique id.

//Defining Items
		SlimefunItemStack end_staff = new SlimefunItemStack("ENDER_STAFF", Material.STICK, "&6Elemental Staff - &5&oEnder", "", "&7Element: &5&oEnder","","&eRight Click &7to teleport.");
		SlimefunItemStack levitation_bow = new SlimefunItemStack("LEVITATION_BOW", Material.BOW, "&dShulker Bow", "","&fAny enemies hit by arrows fired by this bow are launched into the air!");
			end_staff.addUnsafeEnchantment(Enchantment.CHANNELING, 1);
		SlimefunItemStack FrozenBar_1 = new SlimefunItemStack("FROZEN_BAR_1", Material.NETHER_BRICK, "&5Frozen Bar &d(33%)", "");
		SlimefunItemStack EndFragment = new SlimefunItemStack("END_FRAGMENT", Material.IRON_NUGGET, "&dEnder Fragment", "");
		SlimefunItemStack Compressed_EndFragment = new SlimefunItemStack("COMPRESSED_END_FRAGMENT", Material.IRON_NUGGET, "&dCompressed Ender Fragment", "");
		SlimefunItemStack FrozenBar_2 = new SlimefunItemStack("FROZEN_BAR_2", Material.NETHER_BRICK, "&5Frozen Bar &d(66%)", "");
		SlimefunItemStack FrozenBar_Full = new SlimefunItemStack("FROZEN_BAR_FULL", Material.NETHER_BRICK, "&5Frozen Bar", "");




// Recipes
		ItemStack[] enderStaff_recipe = {
				null, null, SlimefunItems.RUNE_ENDER,
				null, SlimefunItems.STAFF_ELEMENTAL, null,
				SlimefunItems.STAFF_ELEMENTAL, null, null
		};

		EnderStaff endStaff = new EnderStaff(category, end_staff, RecipeType.MAGIC_WORKBENCH, enderStaff_recipe);
	endStaff.register(this);
		ItemStack[] shulkerBow_recipe = {
				null, SlimefunItems.STAFF_ELEMENTAL, new ItemStack(Material.CHORUS_FRUIT),
				end_staff, null, new ItemStack(Material.CHORUS_FRUIT),
				null, SlimefunItems.STAFF_ELEMENTAL, new ItemStack(Material.CHORUS_FRUIT)
		};
		ShulkerBow shulker_bow = new ShulkerBow(levitation_bow,shulkerBow_recipe); //Shulker Bow
		shulker_bow.register(this);
// End Bars & Fragments

		ItemStack[] EndFragment_recipe = {
				SlimefunItems.ENDER_LUMP_3, new ItemStack(Material.END_STONE), SlimefunItems.ENDER_LUMP_3,
				new ItemStack(Material.END_STONE), new ItemStack(Material.CHORUS_FRUIT), new ItemStack(Material.END_STONE),
				SlimefunItems.ENDER_LUMP_3, new ItemStack(Material.END_STONE), SlimefunItems.ENDER_LUMP_3
		};
		SlimefunItem EndFragment_item = new SlimefunItem(category, EndFragment, RecipeType.COMPRESSOR, EndFragment_recipe);
		EndFragment_item.register(this); //End Fragment
		ItemStack[] FrozenBar1_recipe = {
				SlimefunItems.GOLD_24K, EndFragment, null,
				null, null, null,
				null, null, null
		};
		SlimefunItem FrozenBar1_item = new SlimefunItem(category, FrozenBar_1, RecipeType.PRESSURE_CHAMBER, FrozenBar1_recipe);
		FrozenBar1_item.register(this); //Frozen Bar (33%)
		ItemStack[] FrozenBar2_recipe = {
				null, new ItemStack(Material.SHULKER_SHELL), null,
				new ItemStack(Material.SHULKER_SHELL), FrozenBar_1, new ItemStack(Material.SHULKER_SHELL),
				null, new ItemStack(Material.SHULKER_SHELL), null
		};
		SlimefunItem FrozenBar2_item = new SlimefunItem(category, FrozenBar_2, RecipeType.PRESSURE_CHAMBER, FrozenBar2_recipe);
		FrozenBar2_item.register(this); //Frozen Bar (66%)
		ItemStack[] FrozenBar_Full_recipe = {
				new ItemStack(Material.DRAGON_BREATH), new ItemStack(Material.DRAGON_BREATH), new ItemStack(Material.DRAGON_BREATH),
				new ItemStack(Material.DRAGON_BREATH), FrozenBar_2, new ItemStack(Material.DRAGON_BREATH),
				new ItemStack(Material.DRAGON_BREATH), new ItemStack(Material.DRAGON_BREATH), new ItemStack(Material.DRAGON_BREATH)
		};
		SlimefunItem FrozenBar_Full_item = new SlimefunItem(category, FrozenBar_Full, RecipeType.PRESSURE_CHAMBER, FrozenBar_Full_recipe);
		FrozenBar_Full_item.register(this); //Frozen Bar (Full)
		ItemStack[] CompressedEndFragment_recipe = {
				EndFragment, EndFragment, null,
				EndFragment, EndFragment, null,
				null, null, null,
		};
		SlimefunItem CompressedEndFragment_Item = new SlimefunItem(category, Compressed_EndFragment, RecipeType.COMPRESSOR, CompressedEndFragment_recipe);

		//Research
		NamespacedKey ender_fragments_research_id = new NamespacedKey(this, "ender_fragments_r");
		Research ender_fragments_research = new Research(ender_fragments_research_id, 425989, "Ender Fragments", 13);
		ender_fragments_research.addItems(EndFragment_item, CompressedEndFragment_Item);
		ender_fragments_research.register();

		NamespacedKey frozen_bars_research_id = new NamespacedKey(this, "frozen_bars_research");
		Research frozen_bars_research = new Research(ender_fragments_research_id, 425990, "Frozen Bars", 40);
		ender_fragments_research.addItems(FrozenBar1_item,FrozenBar2_item,FrozenBar_Full_item);
		ender_fragments_research.register();

		NamespacedKey ShulkerBow_research_id = new NamespacedKey(this, "shulkerbow_research");
		Research ShulkerBow_research = new Research(ShulkerBow_research_id, 425991, "Shulker Bow", 20);
		ShulkerBow_research.addItems(shulker_bow);
		ShulkerBow_research.register();

		NamespacedKey EndStaff_research_id = new NamespacedKey(this, "enderstaff_research");
		Research EnderStaff_research = new Research(EndStaff_research_id, 425992, "Ender Staff", 21);
		EnderStaff_research.addItems(endStaff);
		EnderStaff_research.register();
	}



	@Override
	public void onDisable() {
		// Logic for disabling the plugin...
	}

	@Override
	public String getBugTrackerURL() {
		// You can return a link to your Bug Tracker instead of null here
		return null;
	}

	@Override
	public JavaPlugin getJavaPlugin() {
		return this;
	}

}

