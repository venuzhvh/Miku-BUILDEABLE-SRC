//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.property;

import java.lang.reflect.*;
import java.util.*;

public abstract class AbstractProperty<T> implements IProperty<T>
{
    private final String label;
    private final Object parentObject;
    private final Field value;
    private AbstractProperty parent;
    private List<AbstractProperty> children;
    
    public AbstractProperty(final String label, final Object parentObject, final Field value) {
        this.children = new ArrayList<AbstractProperty>();
        this.label = label;
        this.parentObject = parentObject;
        this.value = value;
    }
    
    public Field getField() {
        return this.value;
    }
    
    public List<AbstractProperty> getChildren() {
        return (List<AbstractProperty>)this.children;
    }
    
    public void setChildren(final List<AbstractProperty> children) {
        this.children = children;
    }
    
    public AbstractProperty getParent() {
        return this.parent;
    }
    
    public void setParent(final AbstractProperty property) {
        this.parent = property;
    }
    
    @Override
    public T getValue() {
        Object foundValue = null;
        final boolean accessible = this.value.isAccessible();
        this.value.setAccessible(true);
        try {
            foundValue = this.value.get(this.parentObject);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        this.value.setAccessible(accessible);
        return (T)foundValue;
    }
    
    @Override
    public void setValue(final T value) {
        final boolean accessible = this.value.isAccessible();
        this.value.setAccessible(true);
        try {
            this.value.set(this.parentObject, value);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        this.value.setAccessible(accessible);
    }
    
    @Override
    public String getLabel() {
        return this.label;
    }
}
