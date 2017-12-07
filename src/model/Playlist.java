package model;

public class Playlist {

    private int playlistid;
    private String songname;
    private int accountid;

    public int getPlaylistid() {
        return playlistid;
    }

    public void setPlaylistid(int playlistid) {
        this.playlistid = playlistid;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Playlist(int playlistid, String songname, int accountid, String firstname, String lastname) {
        this.playlistid = playlistid;
        this.songname = songname;
        this.accountid = accountid;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    private String firstname;
    private String lastname;


}
