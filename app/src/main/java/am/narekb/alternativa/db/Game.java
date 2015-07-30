package am.narekb.alternativa.db;


public class Game {
    int id;
    int ourScore;
    int theirScore;
    String ourName;
    String theirName;

    public int getID() {
        return id;
    }

    public int getOurScore() {
        return ourScore;
    }

    public int getTheirScore() {
        return theirScore;
    }

    public String getOurName() {
        return ourName;
    }

    public String getTheirName() {
        return theirName;
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

    public void setOurName(String newOurName) {
        ourName = newOurName;
    }

    public void setTheirName(String newTheirName) {
        theirName = newTheirName;
    }


    public Game(int newOurScore, int newTheirScore, String newOurName, String newTheirName) {
        ourScore = newOurScore;
        theirScore = newTheirScore;
        ourName = newOurName;
        theirName = newTheirName;
    }

    public Game(int newID, int newOurScore, int newTheirScore, String newOurName, String newTheirName) {
        id = newID;
        ourScore = newOurScore;
        theirScore = newTheirScore;
        ourName = newOurName;
        theirName = newTheirName;
    }
}
