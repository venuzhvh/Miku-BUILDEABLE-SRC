//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.keybind.actions;

import me.auto.miku.api.keybind.*;
import me.auto.miku.api.module.*;

public class ToggleModuleAction implements Action
{
    private final Module module;
    
    public ToggleModuleAction(final Module module) {
        this.module = module;
    }
    
    public void execute() {
        this.module.toggle();
    }
}
