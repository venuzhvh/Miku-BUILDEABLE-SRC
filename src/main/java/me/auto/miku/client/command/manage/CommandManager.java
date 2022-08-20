//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.command.manage;

import me.auto.miku.api.manage.type.*;
import me.auto.miku.client.command.*;
import java.util.*;
import me.auto.miku.client.command.impl.*;

public class CommandManager extends HashMapManager<String, Command>
{
    private final HashMap<String, Command> aliasMap;
    
    public CommandManager() {
        this.aliasMap = new HashMap<String, Command>();
    }
    
    public void load() {
        this.initialize();
    }
    
    public void unload() {
    }
    
    public void initialize() {
        this.register((Command)new FriendCommand(), (Command)new HideCommand());
    }
    
    public HashMap<String, Command> getAliasMap() {
        return this.aliasMap;
    }
    
    public void register(final Command... commands) {
        for (final Command command : commands) {
            this.include((String) command.getLabel().toLowerCase(), (Command) command);
            if (command.getAlias().length > 0) {
                for (final String com : command.getAlias()) {
                    this.aliasMap.put(com.toLowerCase(), command);
                }
            }
        }
    }
    
    public void dispatch(final String s) {
        final String[] command = s.split(" ");
        final Command c = this.getRegistry().get(command[0]);
        if (c != null) {
            c.onRun(command);
        }
        final Command cc = this.getAliasMap().get(command[0]);
        if (cc != null) {
            cc.onRun(command);
        }
    }
}
