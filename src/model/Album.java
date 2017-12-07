package model;

public class Album {

    private int albumid;
    private String albumname;
    private String artistname;

    public int getAlbumid() {
        return albumid;
    }

    public void setAlbumid(int albumid) {
        this.albumid = albumid;
    }

    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname;
    }

    public String getArtistname() {
        return artistname;
    }

    public void setArtistname(String artistname) {
        this.artistname = artistname;
    }

    public String getSogname() {
        return sogname;
    }

    public void setSogname(String sogname) {
        this.sogname = sogname;
    }

    public Album(int albumid, String albumname, String artistname, String sogname) {
        this.albumid = albumid;
        this.albumname = albumname;
        this.artistname = artistname;
        this.sogname = sogname;
    }

    private String sogname;



}
