package com.r13a.api.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public record NewsItem(
        String title,
        String link,
        String description,
        @JacksonXmlProperty(localName = "pubDate") String publicationDate) {
}