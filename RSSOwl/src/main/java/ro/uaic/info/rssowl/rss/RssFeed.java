package ro.uaic.info.rssowl.rss;

import com.google.common.io.ByteStreams;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.jdom2.Element;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import sun.misc.IOUtils;

public class RssFeed {
    private String url;
    private int hash;

    public RssFeed(String url) {
        this.url = url;
    }

    public boolean hasChanged() throws MalformedURLException, IOException {
        URL url = new URL(this.url);

        // Open a connection(?) on the URL(?) and cast the response(??)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Now it's "open", we can set the request method, headers etc.
        connection.setRequestProperty("accept", "application/rss+xml");

        // This line makes the request
        InputStream in = connection.getInputStream();
        
        byte[] bytes = ByteStreams.toByteArray(in);

        int hash = java.util.Arrays.hashCode(bytes);
        
        if(this.hash != hash){
            this.hash = hash;
            return true;
        }
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
