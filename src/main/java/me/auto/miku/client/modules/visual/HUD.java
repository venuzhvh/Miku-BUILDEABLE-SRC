//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.modules.visual;

import me.auto.miku.api.module.annotations.*;
import me.auto.miku.api.module.*;
import me.auto.miku.api.property.annotations.*;
import me.auto.miku.client.event.events.render.*;
import net.minecraft.client.gui.*;
import me.auto.miku.*;
import org.lwjgl.opengl.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

@ModuleManifest(label = "HUD", category = Category.VISUAL, hidden = true)
public class HUD extends Module
{
    @Property("ArrayList")
    public boolean arrayList;
    @Property("ShowWatermark")
    public boolean watermark;
    @Property("Watermark")
    public String dumbBitch;
    private FontRenderer fontRenderer;
    
    public HUD() {
        this.arrayList = true;
        this.watermark = true;
        this.dumbBitch = "Chimpware";
        this.fontRenderer = HUD.mc.fontRenderer;
    }
    
    @SubscribeEvent
    public void onRender2D(final Render2DEvent event) {
        final ScaledResolution sr = new ScaledResolution(HUD.mc);
        int offset = 0;
        if (this.arrayList) {
            final List<Module> modules = (List<Module>)Miku.INSTANCE.getModuleManager().findEnabledModules();
            modules.sort(Comparator.comparingInt(m -> -this.fontRenderer.getStringWidth(m.getLabel())));
            for (final Module module : modules) {
                this.fontRenderer.drawStringWithShadow(module.getLabel(), sr.getScaledWidth() - this.fontRenderer.getStringWidth(module.getLabel()) + module.arrayListOffset - 2.0f, (float)(2 + offset), -1);
                offset += 10;
            }
        }
        if (this.watermark) {
            GL11.glPushMatrix();
            GL11.glScalef(1.4f, 1.4f, 1.4f);
            this.fontRenderer.drawStringWithShadow(this.dumbBitch, 2.0f, 2.0f, -1);
            GL11.glPopMatrix();
        }
    }
}
