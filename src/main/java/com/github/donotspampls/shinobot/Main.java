package com.github.donotspampls.shinobot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.event.message.MessageCreateEvent;

public class Main {

    private static final String RLME_ANNOUNCEMENTS = "374244548735401995";
    private static final String OWO_GENERAL = "256993540523950101";

    public static void main(String[] args) {
        DiscordApi api = new DiscordApiBuilder().setToken("TOKEN").login().join();
        api.updateActivity("Asa's money offers", ActivityType.LISTENING);
        System.out.println("Logged in to Discord account: " + api.getYourself().getDiscriminatedName());
        api.addMessageCreateListener(Main::onMessageCreateListener);
    }

    private static void onMessageCreateListener(MessageCreateEvent ev) {
        TextChannel channel = ev.getChannel();
        DiscordApi api = ev.getApi();
        MessageAuthor user = ev.getMessage().getAuthor();

        if (channel.getIdAsString().equalsIgnoreCase(RLME_ANNOUNCEMENTS) && user.getIdAsString().equals("420272844757270538")) {
            String msg = ev.getMessage().getContent();
            api.getChannelById(OWO_GENERAL).ifPresent(owo -> owo.asServerTextChannel().ifPresent(general -> general.updateTopic(msg)));
        }
    }

}
