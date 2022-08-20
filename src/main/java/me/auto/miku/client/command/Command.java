//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.command;

import net.minecraft.client.*;
import me.auto.miku.client.command.annotations.*;
import java.lang.annotation.*;

public class Command
{
    public Minecraft mc;
    private String label;
    private String[] alias;
    
    public Command() {
        this.mc = Minecraft.getMinecraft();
        if (this.getClass().isAnnotationPresent((Class<? extends Annotation>)CommandManifest.class)) {
            final CommandManifest moduleManifest = this.getClass().getAnnotation(CommandManifest.class);
            this.label = moduleManifest.label();
            this.alias = moduleManifest.aliases();
        }
    }
    
    public void onRun(final String[] s) {
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public String[] getAlias() {
        return this.alias;
    }
}
