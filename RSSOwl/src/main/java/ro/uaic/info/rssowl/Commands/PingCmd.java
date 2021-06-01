package ro.uaic.info.rssowl.Commands;

import discord4j.core.event.domain.message.MessageCreateEvent;


public class PingCmd implements ICommand {
    @Override
    public void execute(MessageCreateEvent event) {
        event.getMessage()
                .getChannel().block()
                .createMessage("Bong!").block();
    }
}
