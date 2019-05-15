package model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Objects;

@XStreamAlias("item")
public class ItemData {
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public ItemData withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemData itemData = (ItemData) o;
        return Objects.equals(name, itemData.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "{\"name\": " + name + "}";
    }
}
