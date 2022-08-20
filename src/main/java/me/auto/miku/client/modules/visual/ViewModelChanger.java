//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.modules.visual;

import me.auto.miku.api.module.annotations.*;
import me.auto.miku.api.module.*;
import me.auto.miku.api.property.annotations.*;
import me.auto.miku.client.event.events.render.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.*;
import net.minecraftforge.fml.common.eventhandler.*;

@ModuleManifest(label = "ViewModelChanger", category = Category.VISUAL)
public class ViewModelChanger extends Module
{
    @Clamp(maximum = "1", minimum = "-1")
    @Property("PosX")
    public float posX;
    @Clamp(maximum = "1", minimum = "-1")
    @Property("PosY")
    public float posY;
    @Clamp(maximum = "1", minimum = "-1")
    @Property("PosZ")
    public float posZ;
    @Clamp(maximum = "1", minimum = "-1")
    @Property("OffhandPosX")
    public float offPosX;
    @Clamp(maximum = "1", minimum = "-1")
    @Property("OffhandPosY")
    public float offPosY;
    @Clamp(maximum = "1", minimum = "-1")
    @Property("OffhandPosZ")
    public float offPosZ;
    @Clamp(maximum = "1")
    @Property("Size")
    public float size;
    
    public ViewModelChanger() {
        this.posX = 0.0f;
        this.posY = 0.0f;
        this.posZ = 0.0f;
        this.offPosX = 0.0f;
        this.offPosY = 0.0f;
        this.offPosZ = 0.0f;
        this.size = 1.0f;
    }
    
    @SubscribeEvent
    public void onRenderItem(final RenderItemEvent event) {
        if (!event.getEntity().equals((Object)ViewModelChanger.mc.player)) {
            return;
        }
        if (event.getBrub().equals((Object)ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND) || event.getBrub().equals((Object)ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND)) {
            if (event.getStack().equals(ViewModelChanger.mc.player.getHeldItemOffhand())) {
                GlStateManager.translate(this.offPosX / 4.0f, this.offPosY / 4.0f, ViewModelChanger.mc.player.isHandActive() ? 0.0f : (this.offPosZ / 4.0f));
            }
            else {
                GlStateManager.translate(this.posX / 4.0f, this.posY / 4.0f, ViewModelChanger.mc.player.isHandActive() ? 0.0f : (this.posZ / 4.0f));
            }
            GlStateManager.scale(this.size, this.size, this.size);
        }
    }
}
