package com.r13a.api.entity;


public class NewsItem {
    private String title;
    private String link;
    private String description;
    private String publicationDate;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    // Constructor
    public NewsItem(String title, String link, String description, String publicationDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.publicationDate = publicationDate;
    }

    // Getters and Setters (omitted for brevity, assume they are present)

    // ... Getters ...
    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "NewsItem{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                '}';
    }
}