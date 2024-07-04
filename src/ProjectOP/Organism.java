package ProjectOP;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class Organism {

    protected int strength;
    protected  int initiative;
    protected int age;
    protected Position position;
    protected char characterIcon;
    protected boolean isAlive;
    protected boolean wasBuried;
    protected  WorldBox box;

    public abstract void action() ;

    public  void action(KeyEvent move){}

    public abstract  void collision(Organism npc);

    public abstract Color getColor();

    public void collisionSpecial(Organism npc){}

    public   int getX() {
        return position.getX();
    }

    public   int getY() {
        return position.getY();
    }

    public   boolean getIsAlive() {
        return isAlive;
    }

    public  char getIcon() {
        return characterIcon;
    }

    public int getStrength() {
        return strength;
    }

    public  int getInitiative() {
        return initiative;
    }

    public  void ageing() {
        age++;
    }

    public   boolean getWasBuried() {
        return wasBuried;
    }

    public  void kill() {
        isAlive = false;
    }

    public void bury() {
        wasBuried = true;
    }

    public  void powerUpStrength() {
        strength += 3;
    }

    public   Position getPosition() {
        return position;
    }

    public  void setPosition(Position position) {
        this.position.setPosition(position);
    }

    public abstract String GetOrganismsName();

    public abstract void draw();

    public String save(){
      return  getIcon() + " " + getX() + " " + getY() + " " + age  + " " + getStrength();
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setStrength(int strength){
        this.strength = strength;
    }
}
