//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.command.impl;

import me.auto.miku.client.command.*;
import me.auto.miku.client.command.annotations.*;
import me.auto.miku.api.util.*;
import me.auto.miku.*;
import me.auto.miku.api.util.friend.*;

@CommandManifest(label = "Friend", aliases = { "f" })
public class FriendCommand extends Command
{
    public void onRun(final String[] args) {
        if (args.length <= 1) {
            ChatUtil.sendNonDeletableMessage("Not enough args.");
            return;
        }
        final String s = args[1];
        switch (s) {
            case "add": {
                if (args.length <= 1) {
                    break;
                }
                if (Miku.INSTANCE.getFriendManager().isFriend(args[2])) {
                    ChatUtil.sendNonDeletableMessage(args[2] + " is already your friend.");
                    return;
                }
                if (args.length < 4) {
                    ChatUtil.sendNonDeletableMessage("Added " + args[2] + " to your friends list.");
                    Miku.INSTANCE.getFriendManager().addFriend(args[2]);
                    break;
                }
                break;
            }
            case "del":
            case "remove": {
                if (args.length <= 1) {
                    break;
                }
                if (!Miku.INSTANCE.getFriendManager().isFriend(args[2])) {
                    ChatUtil.sendNonDeletableMessage(args[2] + " is not your friend.");
                    return;
                }
                if (Miku.INSTANCE.getFriendManager().isFriend(args[2])) {
                    ChatUtil.sendNonDeletableMessage("Removed " + args[2] + " from your friends list.");
                    Miku.INSTANCE.getFriendManager().removeFriend(args[2]);
                    break;
                }
                break;
            }
            case "clear": {
                if (Miku.INSTANCE.getFriendManager().getRegistry().isEmpty()) {
                    ChatUtil.sendNonDeletableMessage("Your friends list is already empty.");
                    return;
                }
                ChatUtil.sendNonDeletableMessage("Your have cleared your friends list. Friends removed: " + Miku.INSTANCE.getFriendManager().getRegistry().size());
                Miku.INSTANCE.getFriendManager().clearFriends();
                break;
            }
            case "list": {
                if (Miku.INSTANCE.getFriendManager().getRegistry().isEmpty()) {
                    ChatUtil.sendNonDeletableMessage("Your friends list is empty.");
                    return;
                }
                ChatUtil.sendNonDeletableMessage("Your current friends are: ");
                Miku.INSTANCE.getFriendManager().getRegistry().forEach(friend -> ChatUtil.sendNonDeletableMessage("Username: " + friend.getName()));
                break;
            }
        }
    }
}
