package defeatedcrow.hac.food.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.client.particle.ParticleCloudDC;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public class ChickenPlateEntity extends FoodEntityBase {

	public ChickenPlateEntity(World worldIn) {
		super(worldIn);
	}

	public ChickenPlateEntity(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
	}

	public ChickenPlateEntity(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
	}

	@Override
	protected ItemStack[] drops() {
		return new ItemStack[] {
				new ItemStack(FoodInit.plateMeal, 1, 4),
				new ItemStack(FoodInit.plateMeal, 1, 5)
		};
	}

	// particle
	@Override
	public void onUpdate() {
		super.onUpdate();
		if (world.isRemote) {
			addParticle();
		}
	}

	protected void addParticle() {
		if (!this.getRaw()) {
			int c = ClimateMain.proxy.getParticleCount();
			if (ClimateMain.proxy.getParticleCount() > 0 && world.rand.nextInt(c) == 0) {
				double x = posX - 0.25D + world.rand.nextDouble() * 0.5D;
				double y = posY + world.rand.nextDouble() * 0.25D;
				double z = posZ - 0.25D + world.rand.nextDouble() * 0.5D;
				double dx = 0D;
				double dy = 0D;
				double dz = 0D;
				Particle cloud = new ParticleCloudDC.Factory().createParticle(0, world, x, y, z, dx, dy, dz, null);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(cloud);
			}
		}
	}
}
