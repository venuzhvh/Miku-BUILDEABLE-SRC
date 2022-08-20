//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.modules.persistent;

import me.auto.miku.api.module.annotations.*;
import me.auto.miku.api.module.*;
import me.auto.miku.client.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.auto.miku.*;
import me.auto.miku.client.command.*;
import me.auto.miku.api.util.*;
import java.util.*;

@ModuleManifest(label = "Commands", persistent = true, category = Category.MISC, hidden = true)
public class Commands extends Module
{
    @SubscribeEvent
    public void onSendPacket(final PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketChatMessage) {
            this.checkCommands(((CPacketChatMessage)event.getPacket()).getMessage(), (PacketEvent)event);
        }
    }
    
    private void checkCommands(final String message, final PacketEvent event) {
        if (message.startsWith(".")) {
            final String[] args = message.split(" ");
            final String input = message.split(" ")[0].substring(1);
            for (final Command command : Miku.INSTANCE.getCommandManager().getValues()) {
                if (input.equalsIgnoreCase(command.getLabel())) {
                    event.setCanceled(true);
                    command.onRun(args);
                }
                else {
                    for (final String alias : command.getAlias()) {
                        if (input.equalsIgnoreCase(alias)) {
                            event.setCanceled(true);
                            command.onRun(args);
                        }
                    }
                }
            }
            if (!event.isCanceled()) {
                ChatUtil.sendNonDeletableMessage("Command \"" + message + "\" was not found!");
                event.setCanceled(true);
            }
            event.setCanceled(true);
        }
    }
}
