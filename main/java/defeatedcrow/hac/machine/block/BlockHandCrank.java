package defeatedcrow.hac.machine.block;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockHandCrank extends BlockTorqueBase {

	public BlockHandCrank(String s) {
		super(Material.ROCK, s, 0);
		this.setHardness(1.5F);
		this.setSoundType(SoundType.METAL);
	}

	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileHandCrank();
	}

	// 手回し式
	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			ItemStack heldItem = player.getHeldItem(hand);
			if (DCUtil.isEmpty(heldItem)) {
				if (!world.isRemote) {
					TileEntity tile = world.getTileEntity(pos);
					if (tile instanceof TileHandCrank) {
						TileHandCrank crank = ((TileHandCrank) tile);
						float add = crank.currentTorque + 0.5F;
						if (add > crank.maxTorque()) {
							add = crank.maxTorque();
						}
						crank.currentTorque = add;
					}
				}
				return true;
			}
		}
		return super.onRightClick(world, pos, state, player, hand, side, hitX, hitY, hitZ);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Requirement ===");
			tooltip.add(DCName.RIGHT_CLICK.getLocalizedName());
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Output ===");
			tooltip.add("1.0F torque/s");
		} else {
			tooltip.add(TextFormatting.ITALIC.toString() + "=== Lshift key: expand tooltip ===");
		}
	}

}
