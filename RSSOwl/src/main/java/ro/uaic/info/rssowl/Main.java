/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.uaic.info.rssowl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import ro.uaic.info.rssowl.rss.RssChecker;
import ro.uaic.info.rssowl.rss.RssSources;

import java.util.Timer;

/**
 *
 * @author Adrian Plesescu
 */
public final class Main {

    public static void main(final String[] args) throws IllegalArgumentException, FileNotFoundException, IOException {

        InputStream responseStream = new FileInputStream("C:\\Users\\ana_p\\Documents\\NetBeansProjects\\RSSOwl\\src\\main\\java\\ro\\uaic\\info\\rssowl\\Config.json");

        // Manually converting the response body InputStream to RCluntry using Jackson
        ObjectMapper mapper = new ObjectMapper();
        Config c = mapper.readValue(responseStream, Config.class);

        DiscordBot discordBot = new DiscordBot(c.token);
        discordBot.connect();
        discordBot.listen();

        for (String k : c.rssSources.keySet()) {
            RssSources.addRssSource(k, c.rssSources.get(k));
        }
        Timer timer = new Timer();
        timer.schedule(new RssChecker(), 0, 20 * 60 * 1000); // 20 min

        discordBot.block();
    }
}
