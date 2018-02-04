package model;

public class Songs {

    private int id;
    private String length;
    private String name;


    public Songs(int id, String length, String name) {
        this.id = id;
        this.length = length;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
