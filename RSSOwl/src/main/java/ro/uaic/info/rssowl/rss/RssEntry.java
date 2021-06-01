package ro.uaic.info.rssowl.rss;

import java.util.Date;

/**
 *
 * @author Adrian Plesescu
 */
public class RssEntry {
    private String title;
    private String description;
    private String category;

    private String author;
    private Date publishedAt;

    private String url;
    private String logoUrl;


    public RssEntry(String title, String description, String category, String author, Date createdAt, String url, String logoUrl) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.author = author;
        this.publishedAt = createdAt;
        this.url = url;
        this.logoUrl = logoUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public String getUrl() {
        return url;
    }

    public String getLogoUrl() {
        return logoUrl;
    }
}
