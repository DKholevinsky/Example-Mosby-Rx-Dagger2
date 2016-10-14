package by.itmediamobile.template.model.apimodel;

/**
 * Created by Denis Kholevinsky
 */

public class SourceApiModel {

    private String id;
    private String name;
    private String description;
    private String url;
    private String category;
    private UrlsToLogosApiModel urlsToLogos;

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

    public String getCategory() {
        return category;
    }

    public UrlsToLogosApiModel getUrlsToLogos() {
        return urlsToLogos;
    }
}
