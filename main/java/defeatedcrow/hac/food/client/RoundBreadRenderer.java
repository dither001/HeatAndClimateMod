package defeatedcrow.hac.food.client;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.core.client.base.ModelRoundBread;
import defeatedcrow.hac.food.entity.BreadRoundEntity;

@SideOnly(Side.CLIENT)
public class RoundBreadRenderer extends DCRenderFoodBase<BreadRoundEntity> {

	private static final ResourceLocation RAW_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/round_bread_raw.png");
	private static final ResourceLocation BAKED_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/round_bread_baked.png");
	private static final ModelRoundBread RAW_MODEL = new ModelRoundBread(false);
	private static final ModelRoundBread BAKED_MODEL = new ModelRoundBread(true);

	public RoundBreadRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getFoodTexture(boolean baked) {
		return baked ? BAKED_TEX : RAW_TEX;
	}

	@Override
	protected DCFoodModelBase getEntityModel(boolean baked) {
		return baked ? BAKED_MODEL : RAW_MODEL;
	}
}
