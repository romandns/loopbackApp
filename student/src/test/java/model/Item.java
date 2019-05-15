package model;


public class Item {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public Item withId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Item withName(String name) {
        this.name = name;
        return this;
    }
}
