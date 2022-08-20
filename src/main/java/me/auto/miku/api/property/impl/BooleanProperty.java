//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.property.impl;

import me.auto.miku.api.property.*;
import java.lang.reflect.*;

public class BooleanProperty extends AbstractProperty<Boolean>
{
    public BooleanProperty(final String label, final Object parentObject, final Field value) {
        super(label, parentObject, value);
    }
    
    public void setValue(final String value) {
        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("on")) {
            this.setValue((Boolean) true);
        }
        else if (value.equalsIgnoreCase("false") || value.equalsIgnoreCase("off")) {
            this.setValue((Boolean) false);
        }
    }
}
