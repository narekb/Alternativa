package am.narekb.alternativa.db;


public class Game {
    long id;
    int ourScore;
    int theirScore;

    public long getID() {
        return id;
    }

    public int getOurScore() {
        return ourScore;
    }

    public int getTheirScore() {
        return theirScore;
    }

    public void setID(long newID) {
        id = newID;
    }

    public void setOurScore(long newID) {
        id = newID;
    }



    public Game(int newOurScore, int newTheirScore) {
        ourScore = newOurScore;
        theirScore = newTheirScore;
    }

    public Game(long newID, int newOurScore, int newTheirScore) {
        id = newID;
        ourScore = newOurScore;
        theirScore = newTheirScore;
    }
}
