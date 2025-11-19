// This is a generated file (powered by Helidon). Do not edit or extend from this artifact as it is subject to change at any time!

package com.r13a.ext.mcp;

import java.util.List;
import java.util.function.Function;

import io.helidon.common.Generated;
import io.helidon.extensions.mcp.server.McpParameters;
import io.helidon.extensions.mcp.server.McpRequest;
import io.helidon.extensions.mcp.server.McpServerConfig;
import io.helidon.extensions.mcp.server.McpTool;
import io.helidon.extensions.mcp.server.McpToolAnnotations;
import io.helidon.extensions.mcp.server.McpToolContent;
import io.helidon.service.registry.GlobalServiceRegistry;
import io.helidon.service.registry.Service;
import io.helidon.webserver.http.HttpFeature;
import io.helidon.webserver.http.HttpRouting;

@Generated(value = "io.helidon.extensions.mcp.codegen.McpCodegen", trigger = "com.r13a.ext.mcp.McpServer")
@Service.Singleton
class McpServer__McpServer implements HttpFeature {

    private McpServer delegate;

    public McpServer__McpServer() {
        try {
            delegate = GlobalServiceRegistry.registry().get(McpServer.class);
        } catch (Exception e) {
            delegate = new McpServer();
        }
    }

    private static List<String> toList(List<McpParameters> list) {
        return list == null ? List.of()
            : list.stream().map(p -> p.as(java.lang.String.class))
                .map(p -> p.get()).toList();
    }

    @Override
    public void setup(HttpRouting.Builder routing) {
        McpServerConfig.Builder builder = McpServerConfig.builder();
        builder.name("mcp-market-data");
        builder.addTool(new McpServer__McpServer.getMarketData__Tool());
        builder.build().setup(routing);
    }

    private class getMarketData__Tool implements McpTool {

        @Override
        public String name() {
            return "getMarketData";
        }

        @Override
        public String description() {
            return "Retrieve financial news from market related for the specified tickers.";
        }

        @Override
        public String schema() {
            var builder = new StringBuilder();
            builder.append("{");
            builder.append("\"type\": \"object\", \"properties\": {");
            builder.append("\"tickers\": {");
            builder.append("\"description\": \"Company ticker symbol\",");
            builder.append("\"type\": \"array\",");
            builder.append("\"items\": {\"type\": \"string\" }");
            builder.append("}");
            builder.append("}}");
            return builder.toString();
        }

        @Override
        public Function<McpRequest, List<McpToolContent>> tool() {
            return request -> {
                McpParameters parameters = request.parameters();
                var tickers = toList(parameters.get("tickers").asList().orElse(null));
                return delegate.getMarketData(tickers);
            };
        }

        @Override
        public McpToolAnnotations annotations() {
            var builder = McpToolAnnotations.builder();
            builder.title("")
                .readOnlyHint(false)
                .destructiveHint(true)
                .idempotentHint(false)
                .openWorldHint(true);
            return builder.build();
        }

    }

}
