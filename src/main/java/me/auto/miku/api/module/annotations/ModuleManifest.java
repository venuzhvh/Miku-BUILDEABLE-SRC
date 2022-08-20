//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.module.annotations;

import java.lang.annotation.*;
import me.auto.miku.api.module.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface ModuleManifest {
    String label() default "";
    
    String[] aliases() default {};
    
    Category category();
    
    int key() default 0;
    
    boolean hidden() default false;
    
    boolean persistent() default false;
    
    String description() default "";
}
