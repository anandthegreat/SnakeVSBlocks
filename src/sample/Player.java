package sample;

import java.io.Serializable;

public class Player implements Serializable {

    private int score;
    private int snakeBalls;

    Player(int s){
        setScore(s);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score=score;
    }

    public int getSnakeBalls(){
        return snakeBalls;
    }

    public void setSnakeBalls(int snakeBalls){
        this.snakeBalls=snakeBalls;
    }
}
