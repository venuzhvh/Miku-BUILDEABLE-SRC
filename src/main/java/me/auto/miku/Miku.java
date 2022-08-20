//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku;

import me.auto.miku.api.wrapper.*;
import net.minecraftforge.fml.common.*;
import me.auto.miku.api.property.manage.*;
import java.io.*;
import me.auto.miku.api.module.manage.*;
import me.auto.miku.client.command.manage.*;
import me.auto.miku.api.util.friend.*;
import me.auto.miku.api.util.*;
import net.minecraft.client.tutorial.*;

@Mod(modid = "miku", name = "Miku", version = "1.0")
public class Miku implements Minecraftable
{
    @Mod.Instance
    public static Miku INSTANCE;
    public static final String VERSION = "1.0";
    public static final String NAME = "Miku";
    public final PropertyManager propertyManager;
    private final File directory;
    private final ModuleManager moduleManager;
    private final RotationManager rotationManager;
    private final CommandManager commandManager;
    private final FriendManager friends;
    private final TickRate tickRateManager;
    private final PopManager popManager;
    
    public Miku() {
        this.propertyManager = new PropertyManager();
        this.directory = new File(Miku.mc.gameDir, "Miku");
        this.moduleManager = new ModuleManager(new File(this.directory, "modules"));
        this.rotationManager = new RotationManager();
        this.commandManager = new CommandManager();
        this.friends = new FriendManager();
        this.tickRateManager = new TickRate();
        this.popManager = new PopManager();
    }
    
    public void initiate() {
        if (!this.directory.exists()) {
            this.directory.mkdir();
        }
        this.moduleManager.load();
        this.commandManager.load();
        this.tickRateManager.load();
        this.friends.setDirectory(new File(this.directory, "friends.json"));
        this.friends.load();
        this.popManager.load();
        Miku.mc.gameSettings.tutorialStep = TutorialSteps.NONE;
        Runtime.getRuntime().addShutdownHook(new Thread(Miku.INSTANCE::exit));
        System.out.println("Miku loaded.");
    }
    
    public void exit() {
        this.moduleManager.unload();
        this.friends.unload();
        this.commandManager.unload();
    }
    
    public TickRate getTickRateManager() {
        return this.tickRateManager;
    }
    
    public CommandManager getCommandManager() {
        return this.commandManager;
    }
    
    public FriendManager getFriendManager() {
        return this.friends;
    }
    
    public PropertyManager getPropertyManager() {
        return this.propertyManager;
    }
    
    public ModuleManager getModuleManager() {
        return this.moduleManager;
    }
    
    public RotationManager getRotationManager() {
        return this.rotationManager;
    }
    
    static {
        Miku.INSTANCE = new Miku();
    }
}
