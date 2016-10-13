package by.itmediamobile.template.model;

/**
 * Created by Denis Kholevinsky
 */

public class NewsFeed extends Feed {

    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
