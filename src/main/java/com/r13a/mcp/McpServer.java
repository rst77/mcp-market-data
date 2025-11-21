package com.r13a.mcp;

import java.util.List;

import com.r13a.api.data.YahooFeedReader;
import com.r13a.api.entity.NewsItem;

import io.helidon.extensions.mcp.server.Mcp;
import io.helidon.extensions.mcp.server.McpException;
import io.helidon.extensions.mcp.server.McpRole;
import io.helidon.extensions.mcp.server.McpToolContent;
import io.helidon.extensions.mcp.server.McpToolContents;
import io.helidon.service.registry.Service.Inject;

@Mcp.Server("mcp-market-data")
public class McpServer {

    @Inject
    YahooFeedReader reader;

    @Mcp.Tool("Retrieve financial news related to the market for the specified tickers.")
    List<McpToolContent> getMarketData(@Mcp.Description("Company ticker symbol") String ticker) {

        if (ticker.isEmpty()) {
            throw new McpException("Missing required argument ticker ID");
        }

        List<NewsItem> newsItems = reader.readFeed(ticker);
        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append("Found ").append(newsItems.size()).append(" news items for ").append(ticker)
                .append(":\n\n");

        for (NewsItem item : newsItems) {
            responseBuilder.append("- **Title**: ").append(item.title()).append("\n");
            responseBuilder.append("  **Link**: ").append(item.link()).append("\n");
            responseBuilder.append("  **Date**: ").append(item.publicationDate()).append("\n");
            responseBuilder.append("  **Description**: ").append(item.description()).append("\n\n");
        }

        return List.of(McpToolContents.textContent(responseBuilder.toString()));
    }

    @Mcp.Prompt("Prompt description")
    @Mcp.Role(McpRole.USER)
    String getMarketDataPrompt(String ticker) {
        return "Return financial news about the ticker - " + ticker;
    }

}
