package com.r13a.api.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

public class Channel {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "item")
    private List<NewsItem> items;

    public List<NewsItem> getItems() {
        return items;
    }

    public void setItems(List<NewsItem> items) {
        this.items = items;
    }
}
