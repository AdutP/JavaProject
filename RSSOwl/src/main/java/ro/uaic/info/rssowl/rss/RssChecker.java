package ro.uaic.info.rssowl.rss;

import com.rometools.rome.io.FeedException;
import java.io.IOException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

// https://stackoverflow.com/questions/12908412/print-hello-world-every-x-seconds
public class RssChecker extends TimerTask {

    @Override
    public void run() {
        System.out.println("Rechecking feeds...");
        for (RssFeed feed : RssSources.getRssSources().values()) {
            try {
                if (feed.hasChanged()) {
                    feed.getLatest1();
                }
            } catch (IOException ex) {
                Logger.getLogger(RssChecker.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FeedException ex) {
                Logger.getLogger(RssChecker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
