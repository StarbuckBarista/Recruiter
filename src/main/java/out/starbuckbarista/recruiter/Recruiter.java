package out.starbuckbarista.recruiter;

import out.starbuckbarista.recruiter.commands.RecruitCommand;
import out.starbuckbarista.recruiter.commands.RecruitAllCommand;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;

import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Recruiter {

	@Mod.EventHandler
	public static void onPreFMLInitialization (FMLPreInitializationEvent FMLPreInitializationEvent) {

		Logger logger = FMLPreInitializationEvent.getModLog();
		logger.info("Recruiter Initialized");
	}

	@Mod.EventHandler
	public void onFMLInitialization (FMLInitializationEvent FMLInitializationEvent) {

		ClientCommandHandler.instance.registerCommand(new RecruitCommand());
		ClientCommandHandler.instance.registerCommand(new RecruitAllCommand());
	}
}
