package defeatedcrow.hac.main.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ModuleConfig {

	private ModuleConfig() {}

	public static final ModuleConfig INSTANCE = new ModuleConfig();
	private final String BR = System.getProperty("line.separator");

	public static boolean machine = true;
	public static boolean magic = true;
	public static boolean food = true;

	public static boolean machine_advanced = true;
	public static boolean magic_advanced = true;
	public static boolean food_advanced = true;
	public static boolean build_advanced = true;
	public static boolean weapon_advanced = true;

	public static boolean ffm = true;
	public static boolean mek = true;
	public static boolean ic2 = true;
	public static boolean bop = true;

	public void load(Configuration cfg) {

		try {
			cfg.load();

			cfg.addCustomCategoryComment("module setting",
					"This setting is for module parmission. " + BR
							+ "If you set false, that module will not add recipes and creative tab items." + BR
							+ "Please understand that you can not delete item/block registerations by this setting.");

			cfg.addCustomCategoryComment("plugin setting", "This setting is for plugin installation parmission. " + BR
					+ "If you set false, that plugin will be disabled.");

			Property machine_b = cfg.get("module setting", "EnableMachineModule", machine);
			Property magic_b = cfg.get("module setting", "EnableMagicModule", magic);
			Property food_b = cfg.get("module setting", "EnableFoodModule", food);

			Property machine_a = cfg.get("module setting", "EnableAdvancedMachine", machine_advanced);
			Property magic_a = cfg.get("module setting", "EnableAdvancedMagic", magic_advanced);
			Property food_a = cfg.get("module setting", "EnableAdvancedFood", food_advanced);
			Property weapon_a = cfg.get("module setting", "EnableAdvancedWeapon", weapon_advanced);
			Property build_a = cfg.get("module setting", "EnableAdvancedBuildingBlocks", build_advanced);

			Property mek_b = cfg.get("plugin setting", "MekanismPlugin", mek);
			Property for_b = cfg.get("plugin setting", "ForestryPlugin", ffm);
			Property ic2_b = cfg.get("plugin setting", "IndustrialCraft2Plugin", ic2);
			Property bop_b = cfg.get("plugin setting", "BiomesOPlentyPlugin", bop);

			machine = machine_b.getBoolean();
			magic = magic_b.getBoolean();
			food = food_b.getBoolean();

			machine_advanced = machine_a.getBoolean();
			magic_advanced = magic_a.getBoolean();
			food_advanced = food_a.getBoolean();
			weapon_advanced = weapon_a.getBoolean();
			build_advanced = build_a.getBoolean();

			mek = mek_b.getBoolean();
			ffm = for_b.getBoolean();
			ic2 = ic2_b.getBoolean();
			bop = bop_b.getBoolean();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cfg.save();
		}

	}

}
