//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.modules.visual;

import me.auto.miku.api.module.annotations.*;
import me.auto.miku.api.module.*;
import me.auto.miku.api.property.annotations.*;
import net.minecraft.entity.*;
import me.auto.miku.api.accessors.*;
import java.awt.*;
import me.auto.miku.api.util.*;
import java.util.*;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.item.*;
import org.lwjgl.opengl.*;
import me.auto.miku.client.event.events.render.*;
import me.auto.miku.*;
import net.minecraft.entity.player.*;

@ModuleManifest(label = "EntityESP", category = Category.VISUAL)
public class EntityESP extends Module
{
    @Property("ExpBottles")
    public boolean expBottles;
    @Property("Pearls")
    public boolean pearls;
    @Property("Crystals")
    public boolean crystals;
    @Property("Self")
    public boolean self;
    
    public EntityESP() {
        this.expBottles = true;
        this.pearls = true;
        this.crystals = true;
        this.self = false;
    }
    
    @SubscribeEvent
    public void onRender3D(final Render3DEvent event) {
        for (final Entity entity : EntityESP.mc.world.loadedEntityList) {
            final Vec3d vec = EntityUtil.interpolateEntity(entity, event.getPartialTicks());
            final double posX = vec.x - ((IRenderManager)EntityESP.mc.getRenderManager()).getRenderPosX();
            final double posY = vec.y - ((IRenderManager)EntityESP.mc.getRenderManager()).getRenderPosY();
            final double posZ = vec.z - ((IRenderManager)EntityESP.mc.getRenderManager()).getRenderPosZ();
            final Color clr = new Color(ColorUtil.getRainbow(4500, 0, 1.0f));
            if ((entity.getClass() == EntityExpBottle.class && this.expBottles) || (entity.getClass() == EntityEnderPearl.class && this.pearls)) {
                RenderUtil.drawESPOutline(new AxisAlignedBB(0.0, 0.0, 0.0, (double)entity.width, (double)entity.height, (double)entity.width).offset(posX - entity.width / 2.0f, posY, posZ - entity.width / 2.0f), new Color(ColorUtil.getRainbow(4500, 0, 1.0f)), 2.0f);
            }
        }
    }
    
    @SubscribeEvent
    public void onRenderEntity(final RenderEntityEvent event) {
        if (event.entity instanceof EntityEnderCrystal && this.crystals) {
            GL11.glPushAttrib(1048575);
            GL11.glPolygonMode(1029, 6913);
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            GL11.glEnable(2848);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            ColorUtil.color(new Color(165, 9, 182, 160).getRGB());
            GL11.glLineWidth(1.0f);
            event.renderer.doRender(event.entity, event.x, event.y, event.z, event.entityYaw, event.partialTicks);
            GL11.glPopAttrib();
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderEntityModelEvent event) {
        if (this.isValid(event.entity)) {
            GL11.glPushAttrib(1048575);
            GL11.glPolygonMode(1032, 6913);
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            GL11.glEnable(2848);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            ColorUtil.color(Miku.INSTANCE.getFriendManager().isFriend(event.entity.getName()) ? ColorUtil.getRainbow(4500, 0, 1.0f) : ColorUtil.mainColor());
            GL11.glLineWidth(1.0f);
            event.modelBase.render(event.entity, event.limbSwing, event.limbSwingAmount, event.age, event.headYaw, event.headPitch, event.scale);
            GL11.glPopAttrib();
        }
    }
    
    private boolean isValid(final Entity entity) {
        return entity instanceof EntityPlayer && !entity.isDead && ((EntityPlayer)entity).getHealth() >= 0.0f && (entity != EntityESP.mc.player || this.self);
    }
}
