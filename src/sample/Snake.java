package sample;

public class Snake {
    private int numBalls;
    private Movement movement;

    Snake(){
        setNumBalls(3);
        movement=new Movement();
    }

    public int getNumBalls() {
        return numBalls;
    }

    public void setNumBalls(int numBalls) {
        this.numBalls = numBalls;
    }
}


class Movement{

    public void turnLeft() {

    }
    public void turnRight() {

    }
}
