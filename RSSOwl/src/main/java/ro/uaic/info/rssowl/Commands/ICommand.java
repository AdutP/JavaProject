package ro.uaic.info.rssowl.Commands;

import discord4j.core.event.domain.message.MessageCreateEvent;

/**
 *
 * @author Adrian Plesescu
 */
public interface ICommand {
    void execute(MessageCreateEvent event);
}
