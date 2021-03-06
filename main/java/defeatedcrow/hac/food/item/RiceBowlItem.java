package defeatedcrow.hac.food.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.EntityRiceBall;
import defeatedcrow.hac.food.entity.EntityRiceBallMiso;
import defeatedcrow.hac.food.entity.EntityRiceBallSeaweed;
import defeatedcrow.hac.food.entity.EntityRiceBowl;
import defeatedcrow.hac.food.entity.EntityRiceMushroom;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RiceBowlItem extends FoodItemBase {

	public RiceBowlItem(boolean isWolfFood) {
		super(isWolfFood);
	}

	@Override
	public int getMaxMeta() {
		return 4;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 4);
		String s = "items/food/rice_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/rice_" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
				"boiled",
				"mushroom",
				"ball",
				"ball_seaweed",
				"ball_miso"
		};
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FoodEntityBase ret = new EntityRiceBowl(world, x, y, z, player);
		switch (i) {
		case 1:
			ret = new EntityRiceMushroom(world, x, y, z, player);
			break;
		case 2:
			ret = new EntityRiceBall(world, x, y, z, player);
			break;
		case 3:
			ret = new EntityRiceBallSeaweed(world, x, y, z, player);
			break;
		case 4:
			ret = new EntityRiceBallMiso(world, x, y, z, player);
		}

		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		return meta == 0 ? 5 : 6;
	}

	@Override
	public float getSaturation(int meta) {
		return 0.4F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(DCName.PLACEABLE_ENTITY.getLocalizedName());
	}

}
