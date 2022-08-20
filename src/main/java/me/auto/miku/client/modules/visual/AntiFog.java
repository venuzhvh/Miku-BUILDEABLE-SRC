//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.modules.visual;

import me.auto.miku.api.module.annotations.*;
import me.auto.miku.api.module.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;

@ModuleManifest(label = "AntiFog", category = Category.VISUAL)
public class AntiFog extends Module
{
    @SubscribeEvent
    public void onFogDensitySet(final EntityViewRenderEvent.FogDensity event) {
        event.setDensity(0.0f);
    }
}
