//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.modules.persistent;

import me.auto.miku.api.module.annotations.*;
import me.auto.miku.api.module.*;
import me.auto.miku.client.event.events.*;
import me.auto.miku.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

@ModuleManifest(label = "Keybinds", category = Category.MISC, hidden = true, persistent = true)
public class Keybinds extends Module
{
    @SubscribeEvent
    public void onKey(final KeyEvent event) {
        for (final Module m : Miku.INSTANCE.getModuleManager().getValues()) {
            if (m.getKeybind().getKey() == event.getKey()) {
                m.toggle();
            }
        }
    }
}
