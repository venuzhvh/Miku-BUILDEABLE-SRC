//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.util.friend;

import me.auto.miku.api.manage.type.*;
import com.google.gson.*;
import com.google.common.reflect.*;
import java.io.*;
import java.util.*;
import net.minecraft.entity.player.*;

public class FriendManager extends ListManager<Friend>
{
    private File directory;
    
    public void load() {
        if (!this.directory.exists()) {
            try {
                this.directory.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.loadFriends();
    }
    
    public void unload() {
        this.saveFriends();
    }
    
    public void setDirectory(final File directory) {
        this.directory = directory;
    }
    
    public void saveFriends() {
        if (this.directory.exists()) {
            try (final Writer writer = new FileWriter(this.directory)) {
                writer.write(new GsonBuilder().setPrettyPrinting().create().toJson((Object)this.getRegistry()));
            }
            catch (IOException e) {
                this.directory.delete();
            }
        }
    }
    
    public void loadFriends() {
        if (!this.directory.exists()) {
            return;
        }
        try (final FileReader inFile = new FileReader(this.directory)) {
            this.setRegistry((List)new GsonBuilder().setPrettyPrinting().create().fromJson((Reader)inFile, new TypeToken<ArrayList<Friend>>() {}.getType()));
            if (this.getRegistry() == null) {
                this.setRegistry((List)new ArrayList());
            }
        }
        catch (Exception ex) {}
    }
    
    public void addFriend(final String name) {
        this.include((Friend) new Friend(name));
    }
    
    public Friend getFriend(final String ign) {
        for (final Friend friend : this.getRegistry()) {
            if (friend.getName().equalsIgnoreCase(ign)) {
                return friend;
            }
        }
        return null;
    }
    
    public boolean isFriend(final String ign) {
        return this.getFriend(ign) != null;
    }
    
    public boolean isFriend(final EntityPlayer ign) {
        return this.getFriend(ign.getName()) != null;
    }
    
    public void clearFriends() {
        this.clear();
    }
    
    public void removeFriend(final String name) {
        final Friend f = this.getFriend(name);
        if (f != null) {
            this.remove((Friend) f);
        }
    }
}
