package by.itmediamobile.template.model;

/**
 * Created by Denis Kholevinsky
 */

public class Source {

    private String id;
    private String name;
    private String description;
    private String url;
    private String image;

    public Source(String id, String name, String description, String url, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }
}
