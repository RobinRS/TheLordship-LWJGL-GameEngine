package de.robinschleser.the12lords.funnystuff;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

public class DiscordIntegration {

    private DiscordEventHandlers handlers;
    private DiscordRichPresence.Builder presence;


    public DiscordIntegration() {
        initCreate();
        initCallbacks();
    }

    private void initCreate() {
        this.handlers = new DiscordEventHandlers.Builder().setReadyEventHandler((user) -> {
            System.out.println("Welcome " + user.username + "#" + user.discriminator + "!");
        }).build();
        DiscordRPC.discordInitialize("580075326592516097", handlers, false);
        DiscordRPC.discordRegister("580075326592516097", "");
        presence = new DiscordRichPresence.Builder("Help wanted! DM");
        presence.setDetails("Developing OS base");
        presence.setParty("8881010456", 1, 5);
        presence.setBigImage("icon", "SouthstarOS is a operating system for Project Northstar by LeapMotion");
        presence.setSmallImage("developer", "Developer of SouthstarOS");
        DiscordRPC.discordUpdatePresence(presence.build());
    }

    private void initCallbacks() {
        this.handlers.ready = discordUser -> System.out.println(discordUser.username + " connected ");
        this.handlers.joinGame = s -> System.out.println("User wants to join game " + s);
        this.handlers.disconnected = (errorCode, s) -> System.out.println("User disconnected " + errorCode + " Message: " + s);
        this.handlers.joinRequest = discordUser -> System.out.println(discordUser + " wants to join the game!");
        this.handlers.spectateGame = s -> System.out.println("Spectate " + s);
    }

    public void callCallbacks() {
        DiscordRPC.discordRunCallbacks();
        DiscordRPC.discordUpdatePresence(presence.build());
    }

    public DiscordRichPresence.Builder getDiscordRichPrecenceBuilder() {
        return presence;
    }
}
