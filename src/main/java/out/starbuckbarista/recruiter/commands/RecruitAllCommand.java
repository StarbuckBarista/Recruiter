package out.starbuckbarista.recruiter.commands;

import out.starbuckbarista.recruiter.utils.MeetsRequirements;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.List;

public class RecruitAllCommand extends CommandBase {

    @Override
    public String getCommandName() {

        return "recruitall";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {

        return "/recruitall";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {

        List<Entity> entityList = sender.getEntityWorld().getLoadedEntityList();
        List<EntityPlayer> playerList = new ArrayList<EntityPlayer>();

        for (Entity entity : entityList) {

            if (entity instanceof EntityPlayer) {

                playerList.add((EntityPlayer) entity);
            }
        }

        ChatComponentText chatComponentText0 = new ChatComponentText("[Recruiter] I have detected " +
                playerList.size() +
                " player(s) in your lobby, please stay in your current lobby and do not go AFK.");
        chatComponentText0.getChatStyle().setColor(EnumChatFormatting.GREEN);

        Minecraft.getMinecraft().thePlayer.addChatMessage(chatComponentText0);

        ArrayList<EntityPlayer> toRemove = new ArrayList<EntityPlayer>();
        int requestCount = 0;

        for (EntityPlayer entityPlayer : playerList) {

            if (requestCount != 55) {

                if (!(MeetsRequirements.playerMeetsRequirements(entityPlayer.getDisplayNameString()))) {

                    toRemove.add(entityPlayer);
                }

                requestCount += 1;
            }
        }

        playerList.removeAll(toRemove);

        ChatComponentText chatComponentText1 = new ChatComponentText("[Recruiter] Results:");
        chatComponentText1.getChatStyle().setColor(EnumChatFormatting.LIGHT_PURPLE);
        chatComponentText1.getChatStyle().setBold(true);

        Minecraft.getMinecraft().thePlayer.addChatMessage(chatComponentText1);

        for (EntityPlayer entityPlayer : playerList) {

            ChatComponentText chatComponentText2 = new ChatComponentText(entityPlayer.getDisplayNameString() +
                    " meets our requirements!");
            chatComponentText2.getChatStyle().setColor(EnumChatFormatting.GOLD);

            Minecraft.getMinecraft().thePlayer.addChatMessage(chatComponentText2);
        }

        if (playerList.size() == 0) {

            ChatComponentText chatComponentText3 = new ChatComponentText("Nobody in this lobby meets our requirements :(");
            chatComponentText3.getChatStyle().setColor(EnumChatFormatting.RED);

            Minecraft.getMinecraft().thePlayer.addChatMessage(chatComponentText3);
        }

        if (requestCount == 55) {

            ChatComponentText chatComponentText4 = new ChatComponentText("The limit of 55 requests a minute was used, " +
                    "please do not check stats for another minute.");
            chatComponentText4.getChatStyle().setColor(EnumChatFormatting.DARK_RED);
            Minecraft.getMinecraft().thePlayer.addChatMessage(chatComponentText4);
        }
    }

    @Override
    public boolean canCommandSenderUseCommand (ICommandSender sender) {

        return true;
    }
}
