package model;

public class User {
    public String id;
    public boolean team;

    public User (String id) {
        this.id = id;
        this.team = true;
    }

    public boolean isTeam() {
        return this.team;
    }

    public void setTeam(boolean team) {
        this.team = team;
    }
}
