package model;

public class Songs {

    private int id;
    private int artistid;
    private String length;
    private String name;
    private int genreid;

    public Songs(int id, int artistid, String length, String name, int genreid) {
        this.id = id;
        this.artistid = artistid;
        this.length = length;
        this.name = name;
        this.genreid = genreid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArtistid() {
        return artistid;
    }

    public void setArtistid(int artistid) {
        this.artistid = artistid;
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

    public int getGenreid() {
        return genreid;
    }

    public void setGenreid(int genreid) {
        this.genreid = genreid;
    }
}
