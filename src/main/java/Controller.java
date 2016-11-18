import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * Created by Srinivas on 11/14/2016.
 */
public class Controller {

    public static void main(String[] args) throws Exception {

        String searchUrl = args[0];

        try {

            String crawlFolder = "data/crawler";
            int numberOfCrawlers = 1;

            CrawlConfig config = new CrawlConfig();
            config.setCrawlStorageFolder(crawlFolder);
            config.setMaxPagesToFetch(4);
            config.setPolitenessDelay(1000);
            config.setMaxDepthOfCrawling(10);

            PageFetcher pageFetcher = new PageFetcher(config);
            RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
            RobotstxtServer robotstxtServer = new RobotstxtServer(
                    robotstxtConfig, pageFetcher);

            CrawlController controller = new CrawlController(config,
                    pageFetcher, robotstxtServer);

            controller.addSeed(searchUrl);

            controller.start(Crawler4j.class, numberOfCrawlers);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
