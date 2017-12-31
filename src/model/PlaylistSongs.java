package model;

public class PlaylistSongs {

    private int playlistid;
    private int songid;

    public int getPlaylistid() {
        return playlistid;
    }

    public void setPlaylistid(int playlistid) {
        this.playlistid = playlistid;
    }

    public int getSongid() {
        return songid;
    }

    public void setSongid(int songid) {
        this.songid = songid;
    }

    public PlaylistSongs(int playlistid, int songid) {
        this.playlistid = playlistid;
        this.songid = songid;

    }
}
