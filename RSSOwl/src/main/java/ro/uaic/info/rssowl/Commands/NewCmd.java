package ro.uaic.info.rssowl.Commands;

import com.rometools.rome.io.FeedException;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.rest.util.Color;
import ro.uaic.info.rssowl.rss.RssEntry;
import ro.uaic.info.rssowl.rss.RssFeed;
import ro.uaic.info.rssowl.rss.RssSources;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class NewCmd implements ICommand {
    @Override
    public void execute(MessageCreateEvent event) {
        try {

            final String content = event.getMessage().getContent();
            final List<String> command = Arrays.asList(content.split(" "));

            RssFeed feed = RssSources.get(command.get(1));
            RssEntry e = feed.getLatest1();


            event.getMessage()
                    .getChannel().block()
                    .createEmbed(spec
                            -> spec.setColor(Color.RED)
                            .setAuthor(e.getAuthor(), e.getUrl(), e.getLogoUrl())
                            .setTitle(e.getTitle())
                            .setUrl(e.getUrl())
                            .setDescription(e.getDescription())
                            .addField("Category:", e.getCategory(), false)
                            .setTimestamp(e.getPublishedAt().toInstant())
                    ).block();

        } catch (IOException | FeedException ioException) {
            ioException.printStackTrace();
        }
    }
}
