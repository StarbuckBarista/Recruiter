package out.starbuckbarista.recruiter.commands;

import out.starbuckbarista.recruiter.utils.MeetsRequirements;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class RecruitCommand extends CommandBase {

    @Override
    public String getCommandName () {
        return "recruit";
    }

    @Override
    public String getCommandUsage (ICommandSender sender) {
        return "/recruit (player)";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {

        if (args.length != 1) {

            ChatComponentText chatComponentText0 = new ChatComponentText("Usage: " + getCommandUsage(sender));
            chatComponentText0.getChatStyle().setColor(EnumChatFormatting.RED);

            Minecraft.getMinecraft().thePlayer.addChatMessage(chatComponentText0);
        } else {

            ChatComponentText chatComponentText1 = new ChatComponentText("[Recruiter] Results:");
            chatComponentText1.getChatStyle().setColor(EnumChatFormatting.LIGHT_PURPLE);
            chatComponentText1.getChatStyle().setBold(true);

            Minecraft.getMinecraft().thePlayer.addChatMessage(chatComponentText1);

            if (MeetsRequirements.playerMeetsRequirements(args[0])) {

                ChatComponentText chatComponentText2 = new ChatComponentText(args[0] + " meets our requirements!");
                chatComponentText2.getChatStyle().setColor(EnumChatFormatting.GOLD);

                Minecraft.getMinecraft().thePlayer.addChatMessage(chatComponentText2);
            } else {

                ChatComponentText chatComponentText3 = new ChatComponentText(args[0] +
                        " does not meet our requirements!");
                chatComponentText3.getChatStyle().setColor(EnumChatFormatting.RED);

                Minecraft.getMinecraft().thePlayer.addChatMessage(chatComponentText3);
            }
        }
    }

    @Override
    public boolean canCommandSenderUseCommand (ICommandSender sender) {

        return true;
    }
}
