package io.spring.moviecatalogservice.models;

public class CatalogItem {

    private String name;
    private String overView;
    private int id;


    public CatalogItem(String name, String overView, int id) {
        this.name = name;
        this.overView = overView;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return overView;
    }

    public void setDesc(String overView) {
        this.overView = overView;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
