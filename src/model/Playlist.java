package model;

public class Playlist {

    private int id;
    private String name;
    private String accountid;

    public Playlist(int id, String name, String accountid) {
        this.id = id;
        this.name = name;
        this.accountid = accountid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }
}
