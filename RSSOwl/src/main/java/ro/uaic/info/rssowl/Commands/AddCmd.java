package ro.uaic.info.rssowl.Commands;

import discord4j.core.event.domain.message.MessageCreateEvent;
import ro.uaic.info.rssowl.rss.RssSources;

import java.util.Arrays;
import java.util.List;

public class AddCmd implements ICommand {
    @Override
    public void execute(MessageCreateEvent event) {
        final String content = event.getMessage().getContent();
        final List<String> command = Arrays.asList(content.split(" "));
        RssSources r = RssSources.getInstance();
    }
}
