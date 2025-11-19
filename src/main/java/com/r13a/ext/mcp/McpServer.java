package com.r13a.ext.mcp;

import java.util.ArrayList;
import java.util.List;

import com.r13a.api.data.YahooFeedReaderSAX;
import com.r13a.api.entity.NewsItem;

import io.helidon.extensions.mcp.server.Mcp;
import io.helidon.extensions.mcp.server.McpToolContent;
import io.helidon.extensions.mcp.server.McpToolContents;
import jakarta.json.bind.annotation.JsonbProperty;

@Mcp.Server("mcp-market-data")
class McpServer {

    @Mcp.Tool("Retrieve financial news from market related for the specified tickers.")
    List<McpToolContent> getMarketData(@Mcp.Description("Company ticker symbol") List<String> tickers) {
        YahooFeedReaderSAX reader = new YahooFeedReaderSAX();
        List<McpToolContent> contents = new ArrayList<>();

        for (String ticker : tickers) {
            List<NewsItem> newsItems = reader.readFeed(ticker);
            for (NewsItem item : newsItems) {

                contents.add(McpToolContents.textContent(item.toString()));
            }
        }

        return contents;
    }

    public record Alert(@JsonbProperty("features") List<Feature> features) {

        public record Feature(@JsonbProperty("properties") Properties properties) {
        }

        public record Properties(@JsonbProperty("title") String event,
                @JsonbProperty("link") String link,
                @JsonbProperty("description") String description,
                @JsonbProperty("publicationDate") String publicationDate) {
        }
    }
}
