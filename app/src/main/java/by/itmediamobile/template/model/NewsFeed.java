package by.itmediamobile.template.model;

/**
 * Created by Denis Kholevinsky
 */

public class NewsFeed extends Feed {

    private String title;
    private String description;
    private String author;
    private String image;

    public NewsFeed(String title, String description, String image, String author) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getImage() {
        return image;
    }
}
