package defeatedcrow.hac.main.packet;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class DCMainPacket {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("dcs_main_climate");

	public static void init() {
		INSTANCE.registerMessage(MHandlerReactorButton.class, MessageReactorButton.class, 0, Side.SERVER);
		INSTANCE.registerMessage(MHandlerBiomeGlass.class, MessageBiomeGlass.class, 1, Side.CLIENT);
		INSTANCE.registerMessage(MHandlerMonitor.class, MessageMonitor.class, 2, Side.CLIENT);
		INSTANCE.registerMessage(MHandlerFluidProcessor.class, MessageFluidProcessor.class, 3, Side.CLIENT);
		INSTANCE.registerMessage(MHandlerConveyor.class, MessageConveyor.class, 4, Side.CLIENT);
		INSTANCE.registerMessage(MHandlerSingleTank.class, MessageSingleTank.class, 5, Side.CLIENT);
		INSTANCE.registerMessage(MHandlerMagicParticle.class, MessageMagicParticle.class, 6, Side.CLIENT);
		INSTANCE.registerMessage(MHandlerFluidSwitchButton.class, MessageFluidSwitchButton.class, 7, Side.SERVER);
		INSTANCE.registerMessage(MHandlerOscillator.class, MessageOscillator.class, 8, Side.CLIENT);
	}
}
