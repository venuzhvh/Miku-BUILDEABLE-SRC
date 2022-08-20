//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.util;

import me.auto.miku.api.wrapper.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.util.text.*;

public class ChatUtil implements Minecraftable
{
    private static final String prefix;
    
    public static void sendClientMessage(final String message) {
        if (ChatUtil.mc.player == null) {
            return;
        }
        final ITextComponent itc = (ITextComponent)new TextComponentString(ChatUtil.prefix + ChatFormatting.LIGHT_PURPLE + message);
        ChatUtil.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(itc, 5936);
    }
    
    public static void sendNonDeletableMessage(final String message) {
        if (ChatUtil.mc.player == null) {
            return;
        }
        final ITextComponent itc = (ITextComponent)new TextComponentString(ChatUtil.prefix + ChatFormatting.LIGHT_PURPLE + message);
        ChatUtil.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(itc, 0);
    }
    
    public static void sendDeletableMessageWithCustomId(final String message, final int id) {
        final ITextComponent itc = (ITextComponent)new TextComponentString(ChatUtil.prefix + ChatFormatting.LIGHT_PURPLE + message);
        ChatUtil.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(itc, id);
    }
    
    static {
        prefix = ChatFormatting.WHITE + "[" + ChatFormatting.LIGHT_PURPLE + "Miku" + ChatFormatting.WHITE + "] ";
    }
}
