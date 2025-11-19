package com.r13a.api.data;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.r13a.api.entity.NewsItem;

import java.util.ArrayList;
import java.util.List;

public class NewsFeedHandler extends DefaultHandler {

    private final List<NewsItem> newsList = new ArrayList<>();
    private NewsItem currentItem = null;
    private StringBuilder currentCharacters = null;
    private boolean isItem = false;

    // Tags we are interested in
    private static final String ITEM = "item";
    private static final String TITLE = "title";
    private static final String LINK = "link";
    private static final String DESCRIPTION = "description";
    private static final String PUB_DATE = "pubDate";

    public List<NewsItem> getNewsItems() {
        return newsList;
    }

    /**
     * Called when the parser encounters the start of an element.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // Initialize the StringBuilder to capture text content
        currentCharacters = new StringBuilder();

        if (qName.equalsIgnoreCase(ITEM)) {
            isItem = true;
            // Create a new NewsItem when <item> tag is found
            currentItem = new NewsItem(null, null, null, null); 
        }
    }

    /**
     * Called when the parser finds character data (the text between tags).
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // Append the character data if we are currently inside an <item>
        if (isItem) {
            currentCharacters.append(ch, start, length);
        }
    }

    /**
     * Called when the parser encounters the end of an element.
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase(ITEM)) {
            // End of an item. Add the completed NewsItem to the list.
            newsList.add(currentItem);
            isItem = false;
        } else if (isItem && currentItem != null) {
            String text = currentCharacters.toString().trim();

            if (qName.equalsIgnoreCase(TITLE)) {
                currentItem.setTitle(text);
            } else if (qName.equalsIgnoreCase(LINK)) {
                currentItem.setLink(text);
            } else if (qName.equalsIgnoreCase(DESCRIPTION)) {
                currentItem.setDescription(text);
            } else if (qName.equalsIgnoreCase(PUB_DATE)) {
                currentItem.setPublicationDate(text);
            }
        }
    }
}