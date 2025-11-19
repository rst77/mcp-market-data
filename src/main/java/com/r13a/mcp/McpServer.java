package com.r13a.mcp;

import java.util.ArrayList;
import java.util.List;

import com.r13a.api.data.YahooFeedReaderSAX;
import com.r13a.api.entity.NewsItem;

import io.helidon.extensions.mcp.server.Mcp;
import io.helidon.extensions.mcp.server.McpException;
import io.helidon.extensions.mcp.server.McpRole;
import io.helidon.extensions.mcp.server.McpToolContent;
import io.helidon.extensions.mcp.server.McpToolContents;

@Mcp.Server("mcp-market-data")
class McpServer {

    @Mcp.Tool("Retrieve financial news related to the market for the specified tickers.")
    List<McpToolContent> getMarketData(@Mcp.Description("Company ticker symbol") String ticker) {

        if (ticker.isEmpty()) {
            throw new McpException("Missing required argument ticker ID");
        }

        YahooFeedReaderSAX reader = new YahooFeedReaderSAX();
        List<McpToolContent> contents = new ArrayList<>();

        List<NewsItem> newsItems = reader.readFeed(ticker);
        for (NewsItem item : newsItems) {

            contents.add(McpToolContents.textContent(item.toString()));
        }

        return contents;
    }

    @Mcp.Prompt("Prompt description")
    @Mcp.Role(McpRole.USER)
    String getMarketDataPrompt(String ticker) {
        return "Return financial news about the ticker - " + ticker;
    }

}
