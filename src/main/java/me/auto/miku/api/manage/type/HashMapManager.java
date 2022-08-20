//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.manage.type;

import me.auto.miku.api.manage.*;
import java.util.*;

public abstract class HashMapManager<K, V> implements IManager
{
    protected final Map<K, V> registry;
    
    public HashMapManager() {
        this.registry = new HashMap<K, V>();
    }
    
    public Map<K, V> getRegistry() {
        return this.registry;
    }
    
    public Collection<V> getValues() {
        return this.registry.values();
    }
    
    public boolean has(final K check) {
        return this.registry.containsKey(check);
    }
    
    public void include(final K key, final V val) {
        if (!this.has(key)) {
            this.registry.put(key, val);
        }
    }
    
    public void exclude(final K key) {
        if (this.has(key)) {
            this.registry.remove(key);
        }
    }
    
    public V pull(final K key) {
        return this.registry.get(key);
    }
}
