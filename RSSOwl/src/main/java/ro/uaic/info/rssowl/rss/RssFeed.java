package ro.uaic.info.rssowl.rss;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.jdom2.Element;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class RssFeed {
    private String url;

    public RssFeed(String url) {
        this.url = url;
    }

    public boolean hasChanged() {
        return false;
    }

    public RssEntry getLatest1() throws IOException, FeedException {
        XmlReader xml = new XmlReader(new URL(url));
        SyndFeed feed = new SyndFeedInput().build(xml);
        System.out.println(feed.getTitle());

        List<Element> m = feed.getForeignMarkup();
        List<SyndEntry> lse = feed.getEntries();

        SyndEntry se = feed.getEntries().get(0);
        return new RssEntry(se.getTitle(), se.getDescription().getValue(), se.getCategories().get(0).getName(), se.getAuthor(), se.getPublishedDate(), se.getLink(), feed.getImage().getUrl());
    }

    public List<RssEntry> getLatest() {
        return null;
    }
}
