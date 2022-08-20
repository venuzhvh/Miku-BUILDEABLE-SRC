//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.property.impl;

import me.auto.miku.api.property.*;
import java.lang.reflect.*;
import me.auto.miku.api.property.util.*;
import org.apache.commons.lang3.math.*;

public class NumberProperty<T extends Number> extends AbstractProperty<T>
{
    private final Class cls;
    private final T minimum;
    private final T maximum;
    
    public NumberProperty(final String label, final Object parentObject, final Field value, final T minimum, final T maximum) {
        super(label, parentObject, value);
        this.minimum = minimum;
        this.maximum = maximum;
        this.cls = value.getType();
    }
    
    public T getMaximum() {
        return this.maximum;
    }
    
    public T getMinimum() {
        return this.minimum;
    }
    
    public void setValue(final T value) {
        super.setValue((T) MathUtil.clamp(value, this.minimum, this.maximum));
    }
    
    public void setValue(final String value) {
        if (this.cls == Integer.class || this.cls == Integer.TYPE) {
            this.setValue((T) NumberUtils.createInteger(value));
        }
        else if (this.cls == Double.class || this.cls == Double.TYPE) {
            this.setValue((T) NumberUtils.createDouble(value));
        }
        else if (this.cls == Float.class || this.cls == Float.TYPE) {
            this.setValue((T) NumberUtils.createFloat(value));
        }
        else if (this.cls == Long.class || this.cls == Long.TYPE) {
            this.setValue((T) NumberUtils.createLong(value));
        }
    }
}
