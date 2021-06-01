package ro.uaic.info.rssowl.rss;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Adrian Plesescu
 */
public class RssSources {
    private static RssSources rs;

    private final HashMap<String, RssFeed> rssSources = new HashMap<>();

    private RssSources() {
    }

    public static RssSources getInstance() {
        if (rs == null) {
            rs = new RssSources();
        }
        return rs;
    }

    public static RssFeed get(String name) {
        return getInstance().rssSources.get(name);
    }

    public static void addRssSource(String rssName, String rssUrl) {
        getInstance().rssSources.put(rssName, new RssFeed(rssUrl));
    }
    
    public static void removeRssSource(String rssName){
        getInstance().rssSources.remove(rssName);
    }
}
