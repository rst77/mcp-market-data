package com.r13a.api.data;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.r13a.api.entity.NewsItem;
import com.r13a.api.entity.RssFeed;

import io.helidon.config.Config;
import io.helidon.service.registry.Service;

@Service.Singleton
public class YahooFeedReader {

    private static final Logger LOGGER = Logger.getLogger(YahooFeedReader.class.getName());
    private static final String DEFAULT_FEED_URL = "https://feeds.finance.yahoo.com/rss/2.0/headline?s=%s&region=US&lang=en-US";

    private final String feedUrlTemplate;
    private final HttpClient client;
    private final XmlMapper xmlMapper;

    @Service.Inject
    public YahooFeedReader(Config config) {
        this(config, HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build());
    }

    // Constructor for testing
    public YahooFeedReader(Config config, HttpClient client) {
        this.feedUrlTemplate = config.get("app.yahoo.feed-url").asString().orElse(DEFAULT_FEED_URL);
        this.client = client;
        this.xmlMapper = new XmlMapper();
        this.xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Accesses the RSS feed URL, uses Jackson XML to parse, and returns a list of
     * NewsItem objects.
     *
     * @param stockId The stock ticker symbol (e.g., "AAPL", "GOOGL").
     * @return A list of NewsItem objects.
     */
    public List<NewsItem> readFeed(String stockId) {
        String feedUrl = String.format(feedUrlTemplate, stockId);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(feedUrl))
                    .header("User-Agent", "Mozilla/5.0 (compatible; RSS Reader)")
                    .timeout(Duration.ofSeconds(30))
                    .GET()
                    .build();

            LOGGER.info(() -> "Attempting to connect to: " + feedUrl);

            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

            if (response.statusCode() == 200) {
                LOGGER.info("Successfully connected, parsing RSS feed...");
                try (InputStream inputStream = response.body()) {
                    RssFeed rssFeed = xmlMapper.readValue(inputStream, RssFeed.class);
                    if (rssFeed != null && rssFeed.getChannel() != null && rssFeed.getChannel().getItems() != null) {
                        LOGGER.info("Parsing completed successfully.");
                        return rssFeed.getChannel().getItems();
                    }
                }
            } else {
                LOGGER.log(Level.WARNING, "HTTP {0} error accessing feed for stock: {1}",
                        new Object[] { response.statusCode(), stockId });
                return Collections.emptyList();
            }

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Network or IO error: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.log(Level.WARNING, "Request interrupted: " + e.getMessage());
        }

        return Collections.emptyList();
    }
}