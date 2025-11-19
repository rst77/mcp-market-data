package com.r13a.api.data;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.r13a.api.entity.NewsItem;

public class YahooFeedReaderSAX {

    private static final String YAHOO_FINANCE_BASE_URL = 
        "https://feeds.finance.yahoo.com/rss/2.0/headline?s=%s&region=US&lang=en-US";

    /**
     * Accesses the RSS feed URL, uses the SAX parser, and returns a list of NewsItem objects.
     *
     * @param stockId The stock ticker symbol (e.g., "AAPL", "GOOGL").
     * @return A list of NewsItem objects.
     */
    public List<NewsItem> readFeed(String stockId) {
        String feedUrl = String.format(YAHOO_FINANCE_BASE_URL, stockId);
        NewsFeedHandler handler = new NewsFeedHandler();

        try {
            // 1. Setup SAX Factory and Parser
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // 2. Use modern HTTP client to access the URL with proper headers
            HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(feedUrl))
                .header("User-Agent", "Mozilla/5.0 (compatible; RSS Reader)")
                .timeout(Duration.ofSeconds(30))
                .GET()
                .build();

            System.out.println("Attempting to connect to: " + feedUrl);
            
            HttpResponse<InputStream> response = client.send(request, 
                HttpResponse.BodyHandlers.ofInputStream());
            
            if (response.statusCode() == 200) {
                System.out.println("Successfully connected, parsing RSS feed...");
                
                try (InputStream inputStream = response.body()) {
                    // 3. Start parsing, passing the InputStream and the custom Handler
                    saxParser.parse(inputStream, handler);
                    System.out.println("Parsing completed successfully.");
                }
            } else {
                System.err.println("HTTP " + response.statusCode() + " error accessing feed for stock: " + stockId);
                System.err.println("URL attempted: " + feedUrl);
            }

        } catch (java.io.IOException e) {
            System.err.println("Network error accessing feed for stock: " + stockId);
            System.err.println("Error: " + e.getMessage());
        } catch (javax.xml.parsers.ParserConfigurationException | org.xml.sax.SAXException e) {
            System.err.println("XML parsing error for stock: " + stockId);
            System.err.println("Error: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Request interrupted for stock: " + stockId);
        }

        // Return the list built by the handler during the parsing events
        return handler.getNewsItems();
    }

    public static void main(String[] args) {
        YahooFeedReaderSAX reader = new YahooFeedReaderSAX();
        String stockToSearch = "AMZN"; // Example: Amazon.com, Inc.

        System.out.println("Fetching news for stock ID: " + stockToSearch + " using SAX Parser...");
        
        List<NewsItem> newsList = reader.readFeed(stockToSearch);

        System.out.println("\n--- Found News Items (" + newsList.size() + ") ---");
        
        for (NewsItem item : newsList) {
            System.out.println(item);
        }
    }
}