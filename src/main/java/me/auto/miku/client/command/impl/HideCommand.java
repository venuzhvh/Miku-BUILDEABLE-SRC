//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.command.impl;

import me.auto.miku.client.command.*;
import me.auto.miku.client.command.annotations.*;
import me.auto.miku.*;
import me.auto.miku.api.util.*;
import me.auto.miku.api.module.*;

@CommandManifest(label = "Hide", aliases = { "drawn" })
public class HideCommand extends Command
{
    public void onRun(final String[] args) {
        if (args.length == 0) {
            return;
        }
        final Module module = Miku.INSTANCE.getModuleManager().findByLabel(args[1]);
        if (module != null) {
            module.setHidden(!module.isHidden());
            ChatUtil.sendClientMessage("brub");
        }
    }
}
