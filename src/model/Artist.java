package model;

public class Artist {

    private int artistid;
    private String artistname;

    public int getArtistid() {
        return artistid;
    }

    public void setArtistid(int artistid) {
        this.artistid = artistid;
    }

    public String getArtistname() {
        return artistname;
    }

    public void setArtistname(String artistname) {
        this.artistname = artistname;
    }

    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname;
    }

    public Artist(int artistid, String artistname, String albumname) {
        this.artistid = artistid;
        this.artistname = artistname;
        this.albumname = albumname;
    }

    private String albumname;


}
