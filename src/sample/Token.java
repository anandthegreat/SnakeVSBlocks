package sample;

public class Token {
    private String name;

    Token(String n){
        setName(n);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}


class Magnet extends Token{

    private int timelimit;
    private int range;
    Magnet(String n) {
        super(n);
        setTimelimit(5);
        setRange(100);
    }

    public int getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(int timelimit) {
        this.timelimit = timelimit;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void attractCoins() {

    }

}

class Shield extends Token{

    private int timelimit;

    Shield(String n) {
        super(n);
        setTimelimit(5);
    }

    public int getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(int timelimit) {
        this.timelimit = timelimit;
    }

    public void protectSnake() {

    }

}

class Destroy_Blocks extends Token{

    Destroy_Blocks(String n) {
        super(n);
    }

    public void destroyBlocks() {

    }

}

class Ball extends Token{
    private int value;

    Ball(String n) {
        super(n);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void increaseBalls() {

    }

}