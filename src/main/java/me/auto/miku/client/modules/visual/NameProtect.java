//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.modules.visual;

import me.auto.miku.api.module.annotations.*;
import me.auto.miku.api.module.*;
import me.auto.miku.api.property.annotations.*;
import me.auto.miku.client.event.events.render.*;
import org.apache.commons.lang3.*;
import net.minecraftforge.fml.common.eventhandler.*;

@ModuleManifest(label = "NameProtect", category = Category.VISUAL)
public class NameProtect extends Module
{
    @Property("Name")
    public String name;
    
    public NameProtect() {
        this.name = "cockeater";
    }
    
    @SubscribeEvent
    public void onTextRender(final RenderTextEvent event) {
        if (this.isNull()) {
            return;
        }
        if (event.getText().contains(NameProtect.mc.player.getName())) {
            event.setText(StringUtils.replace(event.getText(), NameProtect.mc.player.getName(), this.name));
        }
    }
}
