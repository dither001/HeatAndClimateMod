package defeatedcrow.hac.main.block.device;

import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileBellow extends TileTorqueBase implements ITorqueReceiver {

	@SideOnly(Side.CLIENT)
	private static defeatedcrow.hac.main.client.model.ModelBellow MODEL;

	@Override
	@SideOnly(Side.CLIENT)
	protected void createModel() {
		if (MODEL == null)
			MODEL = new defeatedcrow.hac.main.client.model.ModelBellow();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public defeatedcrow.hac.core.client.base.DCTileModelBase getModel() {
		return MODEL;
	}

	@Override
	public boolean isInputSide(EnumFacing side) {
		return side == getBaseSide().getOpposite();
	}

	@Override
	public boolean isOutputSide(EnumFacing side) {
		return side == getBaseSide();
	}

	@Override
	public boolean canReceiveTorque(float amount, EnumFacing side) {
		if (this.currentTorque >= this.maxTorque()) {
			return false;
		}
		return this.isInputSide(side);
	}

	@Override
	public float receiveTorque(float amount, EnumFacing side, boolean sim) {
		float f = maxTorque() - currentTorque;
		float ret = Math.min(amount, f);
		if (!sim) {
			currentTorque += ret;
		}
		return ret;
	}

	@Override
	public float getGearTier() {
		return 1.0F;
	}

	@Override
	public float maxSpeed() {
		return 10.0F;
	}

	@Override
	public float maxTorque() {
		return 4.0F;
	}

	// 描画用
	public int count = 0;
	public int lastcount = 0;
	public boolean back = false;

	@Override
	public void onTickUpdate() {
		super.onTickUpdate();

		// フイゴの描画用
		if (worldObj.isRemote) {
			if (this.prevSpeed > 0.0F) {
				lastcount = count;
				if (back) {
					if (count <= 0) {
						back = false;
					} else {
						count--;
					}
				} else {
					if (count >= 10) {
						back = true;
					} else {
						count++;
					}
				}
			} else {
				count = 0;
				lastcount = 0;
			}
		}
	}

}