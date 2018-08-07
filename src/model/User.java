package model;

public class User {

    /* Filed */
    public String id;
    private boolean team;
    private boolean play;

    /* Constructor */
    public User (String id) {
        this.id = id;
        this.team = true;
        this.play = false;
    }

    /* is team */
    public boolean isTeam() {
        return this.team;
    }

    /* set team */
    public void setTeam(boolean team) {
        this.team = team;
    }

    /* is play */
    public boolean isPlay() {
        return play;
    }

    /* set play */
    public void setPlay(boolean play) {
        this.play = play;
    }


}
