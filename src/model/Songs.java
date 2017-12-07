package model;

public class Songs {

    private int songid;
    private String artist;
    private String length;

    public int getSongid() {
        return songid;
    }

    public void setSongid(int songid) {
        this.songid = songid;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getGenrename() {
        return genrename;
    }

    public void setGenrename(String genrename) {
        this.genrename = genrename;
    }

    public Songs(int songid, String artist, String length, String songname, String genrename) {
        this.songid = songid;
        this.artist = artist;
        this.length = length;
        this.songname = songname;
        this.genrename = genrename;
    }

    private String songname;
    private String genrename;


}
