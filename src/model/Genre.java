package model;

public class Genre {

    private int genreid;

    public int getGenreid() {
        return genreid;
    }

    public void setGenreid(int genreid) {
        this.genreid = genreid;
    }

    public String getGenrename() {
        return genrename;
    }

    public void setGenrename(String genrename) {
        this.genrename = genrename;
    }

    public Genre(int genreid, String genrename) {
        this.genreid = genreid;
        this.genrename = genrename;
    }

    private String genrename;

}
