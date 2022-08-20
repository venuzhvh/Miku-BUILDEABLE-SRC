//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.manage.type;

import me.auto.miku.api.manage.*;
import java.util.*;

public class ListManager<T> implements IManager
{
    protected List<T> registry;
    protected List<Class> classRegistry;
    
    public ListManager() {
        this.registry = new ArrayList<T>();
    }
    
    public List<T> getRegistry() {
        return this.registry;
    }
    
    public void setRegistry(final List<T> registry) {
        this.registry = registry;
    }
    
    public List<Class> getClassRegistry() {
        return this.classRegistry;
    }
    
    public boolean has(final T check) {
        return this.registry.contains(check);
    }
    
    public boolean has(final Class check) {
        return this.classRegistry.contains(check);
    }
    
    public void include(final T add) {
        if (!this.has(add)) {
            this.registry.add(add);
        }
    }
    
    public void include(final Class add) {
        if (!this.has(add)) {
            this.classRegistry.add(add);
        }
    }
    
    public void remove(final T remove) {
        if (this.has(remove)) {
            this.registry.remove(remove);
        }
    }
    
    public void remove(final Class remove) {
        if (this.has(remove)) {
            this.classRegistry.remove(remove);
        }
    }
    
    public void register(final T... queue) {
        for (final T type : queue) {
            this.include(type);
        }
    }
    
    public void register(final Class... queue) {
        for (final Class type : queue) {
            this.include(type);
        }
    }
    
    public T pull(final Class<? extends T> clazz) {
        return this.getRegistry().stream().filter(m -> m.getClass() == clazz).findFirst().orElse(null);
    }
    
    public void clear() {
        if (!this.getRegistry().isEmpty()) {
            this.getRegistry().clear();
        }
    }
    
    public void load() {
    }
    
    public void unload() {
    }
}
