//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.module;

import me.auto.miku.api.wrapper.*;
import me.auto.miku.api.module.annotations.*;
import java.lang.annotation.*;
import me.auto.miku.api.keybind.actions.*;
import me.auto.miku.api.keybind.*;
import me.auto.miku.*;
import net.minecraftforge.common.*;
import com.mojang.realmsclient.gui.*;
import java.util.*;
import com.google.gson.*;
import me.auto.miku.api.property.*;
import me.auto.miku.api.util.*;
import java.util.concurrent.*;

public class Module implements Minecraftable
{
    public boolean sliding;
    public float arrayListOffset;
    public Animation animation;
    private String label;
    private String description;
    private String suffix;
    private String[] aliases;
    private Category category;
    private Keybind keybind;
    private boolean persistent;
    private boolean enabled;
    private boolean hidden;
    
    public Module() {
        this.arrayListOffset = 0.0f;
        this.suffix = "";
        if (this.getClass().isAnnotationPresent((Class<? extends Annotation>)ModuleManifest.class)) {
            final ModuleManifest moduleManifest = this.getClass().getAnnotation(ModuleManifest.class);
            this.label = moduleManifest.label();
            this.description = moduleManifest.description();
            this.category = moduleManifest.category();
            this.hidden = moduleManifest.hidden();
            this.persistent = moduleManifest.persistent();
            this.aliases = moduleManifest.aliases();
            this.keybind = new Keybind(moduleManifest.key(), (Action)new ToggleModuleAction(this));
        }
        if (this.persistent) {
            this.setEnabled(true);
        }
    }
    
    public void init() {
        Miku.INSTANCE.getPropertyManager().scan(this);
    }
    
    public boolean isNull() {
        return Module.mc.player == null || Module.mc.world == null;
    }
    
    public void setSuffix(final String s) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" [");
        stringBuilder.append(s);
        stringBuilder.append("]");
        this.suffix = stringBuilder.toString();
        if (this.animation == null) {
            (this.animation = new Animation(this)).start();
        }
    }
    
    public String getSuffix() {
        return this.suffix;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public String[] getAliases() {
        return this.aliases;
    }
    
    public Keybind getKeybind() {
        return this.keybind;
    }
    
    public String getDisplayLabel() {
        return this.label.replaceAll("_", " ");
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public boolean isHidden() {
        return this.hidden;
    }
    
    public void setHidden(final boolean h) {
        this.hidden = h;
    }
    
    public boolean isPersistent() {
        return !this.persistent;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void toggle() {
        this.setEnabled(!this.isEnabled());
    }
    
    public void onEnable() {
    }
    
    public void onDisable() {
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            MinecraftForge.EVENT_BUS.register((Object)this);
            this.onEnable();
            ChatUtil.sendClientMessage(ChatFormatting.WHITE + this.getLabel() + ChatFormatting.LIGHT_PURPLE + " Enabled.");
        }
        else {
            MinecraftForge.EVENT_BUS.unregister((Object)this);
            this.onDisable();
            ChatUtil.sendClientMessage(ChatFormatting.WHITE + this.getLabel() + ChatFormatting.RED + " Disabled.");
        }
        if (this.animation == null) {
            (this.animation = new Animation(this)).start();
        }
    }
    
    public void save(final JsonObject destination) {
        if (Miku.INSTANCE.getPropertyManager().getPropertiesFromObject(this) != null) {
            Miku.INSTANCE.getPropertyManager().getPropertiesFromObject(this).forEach(property -> destination.addProperty(property.getLabel(), property.getValue().toString()));
        }
        destination.addProperty("Bind", (Number)this.getKeybind().getKey());
        if (this.isPersistent()) {
            destination.addProperty("Enabled", Boolean.valueOf(this.isEnabled()));
        }
        destination.addProperty("Hidden", Boolean.valueOf(this.isHidden()));
    }
    
    public void load(final JsonObject source) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   com/google/gson/JsonObject.entrySet:()Ljava/util/Set;
        //     4: aload_0         /* this */
        //     5: aload_1         /* source */
        //     6: invokedynamic   BootstrapMethod #1, accept:(Lme/auto/miku/api/module/Module;Lcom/google/gson/JsonObject;)Ljava/util/function/Consumer;
        //    11: invokeinterface java/util/Set.forEach:(Ljava/util/function/Consumer;)V
        //    16: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.generateNameForVariable(NameVariables.java:264)
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.assignNamesToVariables(NameVariables.java:198)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:276)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.base/java.lang.Thread.run(Thread.java:829)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private class Animation extends Thread
    {
        ScheduledExecutorService service;
        TimerUtil timer;
        public Module module;
        public float offset;
        public String text;
        
        public Animation(final Module module2) {
            super(module2.getLabel());
            this.service = Executors.newSingleThreadScheduledExecutor();
            this.timer = new TimerUtil();
            this.module = module2;
            this.text = module2.getLabel() + module2.getSuffix();
            this.offset = Minecraftable.mc.fontRenderer.getStringWidth(this.text) / 200.0f;
        }
        
        @Override
        public void run() {
            if (this.module.isEnabled()) {
                if (this.module.arrayListOffset > 0.0f && Minecraftable.mc.world != null) {
                    final Module module = this.module;
                    module.arrayListOffset -= this.offset;
                    Module.this.sliding = true;
                }
                else {
                    Module.this.sliding = false;
                }
            }
            else if (this.module.arrayListOffset < Minecraftable.mc.fontRenderer.getStringWidth(this.text) && Minecraftable.mc.world != null) {
                final Module module2 = this.module;
                module2.arrayListOffset += this.offset;
                Module.this.sliding = true;
            }
            else {
                Module.this.sliding = false;
            }
        }
        
        @Override
        public void start() {
            this.service.scheduleAtFixedRate(this, 0L, 1L, TimeUnit.MILLISECONDS);
        }
    }
}
