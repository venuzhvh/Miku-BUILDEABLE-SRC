//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.event.events.render;

import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;

public class RenderEntityModelEvent extends Event
{
    public final ModelBase modelBase;
    public final Entity entity;
    public final float limbSwing;
    public final float limbSwingAmount;
    public final float age;
    public final float headYaw;
    public final float headPitch;
    public final float scale;
    
    public RenderEntityModelEvent(final ModelBase modelBase, final Entity entity, final float limbSwing, final float limbSwingAmount, final float age, final float headYaw, final float headPitch, final float scale) {
        this.entity = entity;
        this.modelBase = modelBase;
        this.limbSwing = limbSwing;
        this.limbSwingAmount = limbSwingAmount;
        this.age = age;
        this.headPitch = headPitch;
        this.scale = scale;
        this.headYaw = headYaw;
    }
}
