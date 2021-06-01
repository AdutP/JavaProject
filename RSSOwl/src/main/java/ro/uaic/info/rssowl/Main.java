/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.uaic.info.rssowl;

import ro.uaic.info.rssowl.rss.RssChecker;
import ro.uaic.info.rssowl.rss.RssSources;

import java.util.Timer;

/**
 *
 * @author Adrian Plesescu
 */
public final class Main {

    public static void main(final String[] args) throws IllegalArgumentException {
        DiscordBot discordBot = new DiscordBot(Config.token);
        discordBot.connect();
        discordBot.listen();

        RssSources.addRssSource("Adevarul", "https://adevarul.ro/ex/yrssv2");

        Timer timer = new Timer();
        timer.schedule(new RssChecker(), 0, 20 * 60 * 1000); // 20 min

        discordBot.block();
    }
}
