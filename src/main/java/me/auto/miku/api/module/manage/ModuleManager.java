package me.auto.miku.api.module.manage;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import me.auto.miku.api.manage.type.HashMapManager;
import me.auto.miku.api.module.Category;
import me.auto.miku.api.module.Module;
import me.auto.miku.client.modules.combat.AutoCrystal;
import me.auto.miku.client.modules.combat.Offhand;
import me.auto.miku.client.modules.combat.Velocity;
import me.auto.miku.client.modules.movement.ReverseStep;
import me.auto.miku.client.modules.persistent.Commands;
import me.auto.miku.client.modules.persistent.Keybinds;
import me.auto.miku.client.modules.player.FastInteract;
import me.auto.miku.client.modules.player.MiddleClickFriends;
import me.auto.miku.client.modules.player.NoEntityTrace;
import me.auto.miku.client.modules.visual.AntiFireWork;
import me.auto.miku.client.modules.visual.AntiFog;
import me.auto.miku.client.modules.visual.BlockOutline;
import me.auto.miku.client.modules.visual.ClickGUI;
import me.auto.miku.client.modules.visual.EnchantColor;
import me.auto.miku.client.modules.visual.EntityESP;
import me.auto.miku.client.modules.visual.HUD;
import me.auto.miku.client.modules.visual.HoleESP;
import me.auto.miku.client.modules.visual.NameProtect;
import me.auto.miku.client.modules.visual.ViewModelChanger;
import me.auto.miku.client.modules.world.FakePlayer;

public class ModuleManager extends HashMapManager<String, Module> {
    private final File directory;

    public ModuleManager(File directory) {
        this.directory = directory;
        if (!directory.exists()) {
            directory.mkdir();
        }

    }

    public void load() {
        this.register(new Keybinds(), new Commands(), new HUD(), new ClickGUI(), new FakePlayer(), new MiddleClickFriends(), new EntityESP(), new FastInteract(), new ReverseStep(), new NoEntityTrace(), new AutoCrystal(), new AntiFog(), new EnchantColor(), new ViewModelChanger(), new AntiFireWork(), new NameProtect(), new BlockOutline(), new HoleESP(), new Velocity(), new Offhand());
        this.getRegistry().values().forEach(Module::init);
        this.loadCheats();
    }

    public ArrayList<Module> findEnabledModules() {
        ArrayList<Module> mods = new ArrayList();
        Iterator var2 = this.getValues().iterator();

        while(true) {
            Module m;
            do {
                if (!var2.hasNext()) {
                    return mods;
                }

                m = (Module)var2.next();
            } while(!m.isEnabled() && !m.sliding);

            mods.add(m);
        }
    }

    public void unload() {
        this.saveCheats();
    }

    public void register(Module... modules) {
        Module[] var2 = modules;
        int var3 = modules.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Module cheat = var2[var4];
            if (cheat.getLabel() != null) {
                this.include(cheat.getLabel(), cheat);
            }
        }

    }

    public Module findByClass(Class<? extends Module> clazz) {
        return (Module)this.getValues().stream().filter((m) -> {
            return m.getClass() == clazz;
        }).findFirst().orElse((Module) null);
    }

    public Module findByLabel(String string) {
        return (Module)this.getValues().stream().filter((m) -> {
            return m.getLabel().equalsIgnoreCase(string);
        }).findFirst().orElse((Module) null);
    }

    public ArrayList<Module> findByCategory(Category category) {
        ArrayList<Module> mods = new ArrayList();
        Iterator var3 = this.getRegistry().values().iterator();

        while(var3.hasNext()) {
            Module m = (Module)var3.next();
            if (m.getCategory().equals(category)) {
                mods.add(m);
            }
        }

        return mods;
    }

    public void saveCheats() {
        if (this.getRegistry().values().isEmpty()) {
            this.directory.delete();
        }

        File[] files = this.directory.listFiles();
        if (!this.directory.exists()) {
            this.directory.mkdir();
        } else if (files != null) {
            File[] var2 = files;
            int var3 = files.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                File file = var2[var4];
                file.delete();
            }
        }

        this.getRegistry().values().forEach((module) -> {
            File file = new File(this.directory, module.getLabel() + ".json");
            JsonObject node = new JsonObject();
            module.save(node);
            if (!node.entrySet().isEmpty()) {
                try {
                    file.createNewFile();
                } catch (IOException var17) {
                    var17.printStackTrace();
                    return;
                }

                try {
                    Writer writer = new FileWriter(file);
                    Throwable var5 = null;

                    try {
                        writer.write((new GsonBuilder()).setPrettyPrinting().create().toJson(node));
                    } catch (Throwable var16) {
                        var5 = var16;
                        throw var16;
                    } finally {
                        if (writer != null) {
                            if (var5 != null) {
                                try {
                                    writer.close();
                                } catch (Throwable var15) {
                                    var5.addSuppressed(var15);
                                }
                            } else {
                                writer.close();
                            }
                        }

                    }
                } catch (IOException var19) {
                    var19.printStackTrace();
                    file.delete();
                }

            }
        });
        files = this.directory.listFiles();
        if (files == null || files.length == 0) {
            this.directory.delete();
        }

    }

    public void loadCheats() {
        this.getRegistry().values().forEach((module) -> {
            File file = new File(this.directory, module.getLabel() + ".json");
            if (file.exists()) {
                try {
                    Reader reader = new FileReader(file);
                    Throwable var4 = null;

                    try {
                        JsonElement node = (new JsonParser()).parse(reader);
                        if (node.isJsonObject()) {
                            module.load(node.getAsJsonObject());
                            return;
                        }
                    } catch (Throwable var16) {
                        var4 = var16;
                        throw var16;
                    } finally {
                        if (reader != null) {
                            if (var4 != null) {
                                try {
                                    reader.close();
                                } catch (Throwable var15) {
                                    var4.addSuppressed(var15);
                                }
                            } else {
                                reader.close();
                            }
                        }

                    }

                } catch (IOException var18) {
                    System.out.println(var18 + "While trying to load module settings.");
                }
            }
        });
    }
}