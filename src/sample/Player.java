package sample;

public class Player {

    private String name;
    private int age;
    private String gender;
    private int score;

    Player(String n, int a, String g, int s){
        setName(n);
        setAge(a);
        setGender(g);
        setScore(s);
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getGender() {
        return gender;
    }
    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name=name;
    }
    public void setAge(int age) {
        this.age=age;
    }
    public void setGender(String gender) {
        this.gender=gender;
    }
    public void setScore(int score) {
        this.score=score;
    }
}
