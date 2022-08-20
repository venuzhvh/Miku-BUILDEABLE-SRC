//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.property.impl.string.impl;

import me.auto.miku.api.property.impl.string.*;
import java.lang.reflect.*;

public class ModeStringProperty extends StringProperty
{
    private final String[] modes;
    
    public ModeStringProperty(final String label, final Object parentObject, final Field value, final String[] modes) {
        super(label, parentObject, value);
        this.modes = modes;
    }
    
    public void setValue(final String value) {
        for (final String mode : this.modes) {
            if (value.equalsIgnoreCase(mode)) {
                super.setValue((String) value);
            }
            else {
                super.setValue(this.getValue());
            }
        }
    }
    
    public String[] getModes() {
        return this.modes;
    }
    
    public void increment() {
        final String currentMode = (String)this.getValue();
        for (final String mode : this.modes) {
            if (mode.equalsIgnoreCase(currentMode)) {
                final int ordinal = this.getOrdinal(mode, this.modes);
                String newValue;
                if (ordinal == this.modes.length - 1) {
                    newValue = this.modes[0];
                }
                else {
                    newValue = this.modes[ordinal + 1];
                }
                this.setValue(newValue);
                return;
            }
        }
    }
    
    public void decrement() {
        final String currentMode = (String)this.getValue();
        for (final String mode : this.modes) {
            if (mode.equalsIgnoreCase(currentMode)) {
                final int ordinal = this.getOrdinal(mode, this.modes);
                String newValue;
                if (ordinal == 0) {
                    newValue = this.modes[this.modes.length - 1];
                }
                else {
                    newValue = this.modes[ordinal - 1];
                }
                this.setValue(newValue);
                return;
            }
        }
    }
    
    private int getOrdinal(final String value, final String[] array) {
        for (int i = 0; i <= array.length - 1; ++i) {
            final String indexString = array[i];
            if (indexString.equalsIgnoreCase(value)) {
                return i;
            }
        }
        return 0;
    }
}
