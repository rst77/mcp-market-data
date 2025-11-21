package com.r13a.api.data;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.helidon.config.Config;

@ExtendWith(MockitoExtension.class)
class YahooFeedReaderTest {

    @Mock
    private Config config;

    @Mock
    private Config urlConfig;

    @Mock
    private HttpClient httpClient;

    @Mock
    private HttpResponse<InputStream> httpResponse;

    private YahooFeedReader reader;

    @BeforeEach
    void setUp() {
        when(config.get("app.yahoo.feed-url")).thenReturn(urlConfig);
        io.helidon.config.ConfigValue<String> mockValue = mock(io.helidon.config.ConfigValue.class);
        when(mockValue.orElse(any())).thenReturn("http://test-url/%s");
        when(urlConfig.asString()).thenReturn(mockValue);

        reader = new YahooFeedReader(config, httpClient);
    }

    @Test
    void testReadFeedSuccess() throws Exception {
        String xmlResponse = """
                <rss version="2.0">
                    <channel>
                        <item>
                            <title>Test News</title>
                            <link>http://test.com</link>
                            <description>Test Description</description>
                            <pubDate>Mon, 01 Jan 2024 00:00:00 GMT</pubDate>
                        </item>
                    </channel>
                </rss>
                """;

        InputStream inputStream = new java.io.ByteArrayInputStream(xmlResponse.getBytes());

        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn(inputStream);
        when(httpClient.send(any(java.net.http.HttpRequest.class), any(java.net.http.HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponse);

        var items = reader.readFeed("TEST");

        assertTrue(items != null);
        assertTrue(items.size() == 1);
        assertTrue(items.get(0).title().equals("Test News"));
        assertTrue(items.get(0).publicationDate().equals("Mon, 01 Jan 2024 00:00:00 GMT"));
    }

    @Test
    void testReadFeedError() throws Exception {
        when(httpResponse.statusCode()).thenReturn(404);
        when(httpClient.send(any(java.net.http.HttpRequest.class), any(java.net.http.HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponse);

        var items = reader.readFeed("TEST");

        assertTrue(items.isEmpty());
    }
}
