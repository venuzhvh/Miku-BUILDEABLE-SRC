//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.mixin;

import me.auto.miku.api.wrapper.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.auto.miku.client.event.events.render.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import com.google.common.base.*;
import me.auto.miku.*;
import me.auto.miku.client.modules.player.*;
import me.auto.miku.api.module.*;
import net.minecraft.init.*;
import java.util.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.block.state.*;
import net.minecraft.block.material.*;
import me.auto.miku.client.modules.visual.*;

@Mixin({ EntityRenderer.class })
public abstract class MixinEntityRenderer implements Minecraftable
{
    @Inject(method = { "renderWorldPass" }, at = { @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;renderHand:Z") })
    private void renderWorldPass(final int pass, final float partialTicks, final long finishTimeNano, final CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post((Event)new Render3DEvent(partialTicks));
    }
    
    @Inject(method = { "updateCameraAndRender" }, at = { @At(value = "INVOKE", target = "net/minecraft/client/gui/GuiIngame.renderGameOverlay(F)V") })
    private void updateCameraAndRender$renderGameOverlay(final float partialTicks, final long nanoTime, final CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post((Event)new Render2DEvent(partialTicks));
    }
    
    @Redirect(method = { "getMouseOver" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;getEntitiesInAABBexcluding(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/AxisAlignedBB;Lcom/google/common/base/Predicate;)Ljava/util/List;"))
    public List getEntitiesInAABBexcluding(final WorldClient worldClient, final Entity entityIn, final AxisAlignedBB boundingBox, final Predicate predicate) {
        if (Miku.INSTANCE.getModuleManager().findByClass(NoEntityTrace.class).isEnabled() && MixinEntityRenderer.mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_PICKAXE) {
            return new ArrayList();
        }
        return worldClient.getEntitiesInAABBexcluding(entityIn, boundingBox, predicate);
    }
    
    @Redirect(method = { "setupFog" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;getMaterial()Lnet/minecraft/block/material/Material;"))
    private Material setupFog$getMaterial(final IBlockState iblockstate) {
        final Material m = iblockstate.getMaterial();
        if (Miku.INSTANCE.getModuleManager().findByClass(AntiFog.class).isEnabled() && (m == Material.WATER || m == Material.LAVA)) {
            return Material.AIR;
        }
        return m;
    }
}
