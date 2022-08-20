//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.mixin;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;
import me.auto.miku.client.event.events.render.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ FontRenderer.class })
public class MixinFontRenderer
{
    @ModifyVariable(method = { "renderString" }, at = @At("HEAD"))
    private String renderString(final String string) {
        if (string == null) {
            return null;
        }
        final RenderTextEvent event = new RenderTextEvent(string);
        MinecraftForge.EVENT_BUS.post((Event)event);
        return event.getText();
    }
}
