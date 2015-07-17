package am.narekb.alternativa.db;


public class Game {
    int id;
    int ourScore;
    int theirScore;

    public int getID() {
        return id;
    }

    public int getOurScore() {
        return ourScore;
    }

    public int getTheirScore() {
        return theirScore;
    }

    public void setID(int newID) {
        id = newID;
    }

    public void setOurScore(int newOurScore) {
        ourScore = newOurScore;
    }

    public void setTheirScore(int newTheirScore) {
        theirScore = newTheirScore;
    }



    public Game(int newOurScore, int newTheirScore) {
        ourScore = newOurScore;
        theirScore = newTheirScore;
    }

    public Game(int newID, int newOurScore, int newTheirScore) {
        id = newID;
        ourScore = newOurScore;
        theirScore = newTheirScore;
    }
}
