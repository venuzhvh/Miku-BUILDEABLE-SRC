//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.modules.visual;

import me.auto.miku.api.module.annotations.*;
import me.auto.miku.api.module.*;
import me.auto.miku.client.event.events.render.*;
import java.awt.*;
import me.auto.miku.api.util.*;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.common.eventhandler.*;

@ModuleManifest(label = "BlockOutline", category = Category.VISUAL)
public class BlockOutline extends Module
{
    @SubscribeEvent
    public void onRender3D(final Render3DEvent event) {
        final RayTraceResult ray = BlockOutline.mc.objectMouseOver;
        if (ray != null && ray.typeOfHit == RayTraceResult.Type.BLOCK) {
            final BlockPos blockpos = ray.getBlockPos();
            RenderUtil.drawBlockOutline(blockpos, new Color(ColorUtil.getRainbow(5000, 0, 1.0f)), 1.0f);
        }
    }
}
