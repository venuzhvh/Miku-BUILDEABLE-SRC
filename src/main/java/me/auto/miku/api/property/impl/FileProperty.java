//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.property.impl;

import me.auto.miku.api.property.*;
import java.io.*;
import java.lang.reflect.*;

public class FileProperty extends AbstractProperty<File>
{
    public FileProperty(final String label, final Object parentObject, final Field value) {
        super(label, parentObject, value);
    }
    
    public void setValue(final String value) {
        this.setValue((File) new File(value));
    }
}
