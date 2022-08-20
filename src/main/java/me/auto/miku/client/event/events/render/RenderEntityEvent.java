//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.event.events.render;

import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.*;

public class RenderEntityEvent extends Event
{
    public final Render<Entity> renderer;
    public final Entity entity;
    public final double x;
    public final double y;
    public final double z;
    public final float entityYaw;
    public final float partialTicks;
    
    public RenderEntityEvent(final Render<Entity> renderer, final Entity entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks) {
        this.entity = entity;
        this.renderer = renderer;
        this.x = x;
        this.y = y;
        this.z = z;
        this.entityYaw = entityYaw;
        this.partialTicks = partialTicks;
    }
}
