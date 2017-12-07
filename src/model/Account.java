package model;

public class Account {

    private int accountid;
    private String firstname;
    private String lastname;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isMembership() {
        return membership;
    }

    public void setMembership(boolean membership) {
        this.membership = membership;
    }

    public String getReigon() {
        return reigon;
    }

    public void setReigon(String reigon) {
        this.reigon = reigon;
    }

    public Account(int accountid, String firstname, String lastname, String email, boolean membership, String reigon) {
        this.accountid = accountid;
        this.firstname = firstname;

        this.lastname = lastname;
        this.email = email;
        this.membership = membership;
        this.reigon = reigon;
    }

    private String email;
    private boolean membership;
    private String reigon;

}
