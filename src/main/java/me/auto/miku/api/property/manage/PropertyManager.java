//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.property.manage;

import me.auto.miku.api.manage.type.*;
import me.auto.miku.api.property.*;
import com.esotericsoftware.reflectasm.*;
import java.lang.reflect.*;
import java.lang.annotation.*;
import java.awt.*;
import java.io.*;
import me.auto.miku.api.property.impl.string.impl.*;
import me.auto.miku.api.property.impl.string.*;
import me.auto.miku.api.property.impl.*;
import me.auto.miku.api.property.annotations.child.*;
import me.auto.miku.api.property.annotations.*;
import java.util.*;
import java.util.List;

public class PropertyManager extends HashMapManager<Object, List<AbstractProperty>>
{
    private final HashMap<String, List<AbstractProperty>> groups;
    
    public PropertyManager() {
        this.groups = new HashMap<String, List<AbstractProperty>>();
    }
    
    public void scan(final Object object) {
        final FieldAccess access = FieldAccess.get((Class)object.getClass());
        final List<Field> propertyFields = new ArrayList<Field>();
        for (final Field field : object.getClass().getDeclaredFields()) {
            final boolean accessibility = field.isAccessible();
            if (field.isAnnotationPresent((Class<? extends Annotation>)Property.class)) {
                field.setAccessible(true);
                propertyFields.add(field);
                final Property property = field.getAnnotation(Property.class);
                final Object value = access.get(object, field.getName());
                if (value instanceof Boolean) {
                    this.register(object, (AbstractProperty)new BooleanProperty(property.value(), object, field));
                }
                if (value instanceof Color) {
                    this.register(object, (AbstractProperty)new ColorProperty(property.value(), object, field));
                }
                if (value instanceof File) {
                    this.register(object, (AbstractProperty)new FileProperty(property.value(), object, field));
                }
                if (value instanceof String) {
                    if (field.isAnnotationPresent((Class<? extends Annotation>)Mode.class)) {
                        final Mode mode = field.getAnnotation(Mode.class);
                        this.register(object, (AbstractProperty)new ModeStringProperty(property.value(), object, field, mode.value()));
                    }
                    else {
                        this.register(object, (AbstractProperty)new StringProperty(property.value(), object, field));
                    }
                }
                if (value instanceof Number && field.isAnnotationPresent((Class<? extends Annotation>)Clamp.class)) {
                    final Clamp clamp = field.getAnnotation(Clamp.class);
                    if (value instanceof Integer) {
                        this.register(object, (AbstractProperty)new NumberProperty(property.value(), object, field, (Number)Integer.parseInt(clamp.minimum()), (Number)Integer.parseInt(clamp.maximum())));
                    }
                    else if (value instanceof Double) {
                        this.register(object, (AbstractProperty)new NumberProperty(property.value(), object, field, (Number)Double.parseDouble(clamp.minimum()), (Number)Double.parseDouble(clamp.maximum())));
                    }
                    else if (value instanceof Float) {
                        this.register(object, (AbstractProperty)new NumberProperty(property.value(), object, field, (Number)Float.parseFloat(clamp.minimum()), (Number)Float.parseFloat(clamp.maximum())));
                    }
                    else if (value instanceof Long) {
                        this.register(object, (AbstractProperty)new NumberProperty(property.value(), object, field, (Number)Long.parseLong(clamp.minimum()), (Number)Long.parseLong(clamp.maximum())));
                    }
                    else if (value instanceof Short) {
                        this.register(object, (AbstractProperty)new NumberProperty(property.value(), object, field, (Number)Short.parseShort(clamp.minimum()), (Number)Short.parseShort(clamp.maximum())));
                    }
                }
                field.setAccessible(accessibility);
            }
        }
        if (this.getPropertiesFromObject(object) != null) {
            for (final AbstractProperty property2 : this.getPropertiesFromObject(object)) {
                final Field field2 = property2.getField();
                if (field2.isAnnotationPresent((Class<? extends Annotation>)Child.class)) {
                    final Child child = field2.getAnnotation(Child.class);
                    final AbstractProperty parentProperty = this.getProperty(object, child.value()).get();
                    property2.setParent(parentProperty);
                    parentProperty.getChildren().add(property2);
                }
                if (field2.isAnnotationPresent((Class<? extends Annotation>)Group.class) && property2.getValue() instanceof Boolean) {
                    final Group group = field2.getAnnotation(Group.class);
                    this.groups.putIfAbsent(group.value(), new ArrayList<AbstractProperty>());
                    this.groups.get(group.value()).add(property2);
                }
            }
        }
    }
    
    public void register(final Object object, final AbstractProperty property) {
        this.getRegistry().computeIfAbsent(object, collection -> new ArrayList());
        ((List)this.pull(object)).add(property);
    }
    
    public List<AbstractProperty> getPropertiesFromObject(final Object object) {
        if (this.pull(object) != null) {
            return (List<AbstractProperty>)this.pull(object);
        }
        return null;
    }
    
    public Optional<AbstractProperty> getProperty(final Object object, final String label) {
        return this.getPropertiesFromObject(object).stream().filter(property -> property.getLabel().equalsIgnoreCase(label)).findFirst();
    }
    
    public HashMap<String, List<AbstractProperty>> getGroups() {
        return this.groups;
    }
    
    public void load() {
    }
    
    public void unload() {
    }
}
