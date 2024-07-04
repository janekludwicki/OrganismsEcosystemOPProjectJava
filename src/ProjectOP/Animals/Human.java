package ProjectOP.Animals;

import ProjectOP.Organism;
import ProjectOP.Position;
import ProjectOP.WorldBox;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Human extends Animal{

    private boolean isBoostReady;
    private boolean isBoosted;
    private  int boostTime;
    private int timeToBoost;
    private String warning;

    public Human(WorldBox box) {
        this.characterIcon = 'H';
        this.age = 0;
        this.initiative = 4;
        this.strength = 5;
        this.isAlive = true;
        this.box = box;
        this.boostTime = 0;
        this.timeToBoost = 0;
        this.isBoosted = false;
        this.isBoostReady = true;

        Position position = new Position(new Random().nextInt(0, 20), new Random().nextInt(0, 20));
        while(box.getOrganism(position.getX(), position.getY()) != null)
            position = new Position(new Random().nextInt(0, 20), new Random().nextInt(0, 20));

        this.position = position;
    }
    public Human(WorldBox box, Position position) {
        this.characterIcon = 'H';
        this.age = 0;
        this.initiative = 4;
        this.strength = 5;
        this.isAlive = true;
        this.position = position;
        this.box = box;
        this.boostTime = 0;
        this.timeToBoost = 0;
        this.isBoosted = false;
        this.isBoostReady = true;

    }

    public void loadBoost() {
        if (!isBoosted && timeToBoost > 0)
            timeToBoost--;
        else if (!isBoosted && timeToBoost == 0)
            isBoostReady = true;
    }

    public void activateBoost() {
        if (!isBoosted && isBoostReady) {
            box.addComment("Human has activated his ability MAGICAL POTION");
            boostTime = 5;
            isBoosted = true;
            isBoostReady = false;

        }
        else {
            String message = "Ability not ready to use !";
            System.out.print(message);
        }
    }

    public void fillWarning(String warn) {
        warning = warn;
    }

    @Override
    public void draw() {
       System.out.print("SPECIAL ABILITY: ");
        if (isBoosted) {
            String com = "ON   Human strenght: " + getStrength();
            System.out.print(com);
        }
        else if (isBoostReady) {
            System.out.print("READY");
        }
        else {
            System.out.print("NOT READY");
            String com = "Turns to boost: " + timeToBoost;
            System.out.print(com);
        }
    }

    public void boost() {
        if (boostTime == 5) {
            strength += boostTime;
        }
        else if (boostTime >= 0 && isBoosted) {
            strength--;
        }

    }

    @Override
    public void action(KeyEvent move) {

        if (isBoosted) {
            boostTime--;
            if (boostTime == 0) {
                isBoosted = false;
                timeToBoost = 5;
            }
        }
        loadBoost();
        int xMove = 0, yMove = 0;
        if(move == null)
            return;
        switch ( move.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                try {
                    activateBoost();
                }
                catch (Exception exception) {
                    fillWarning(exception.getMessage());
                }
            case KeyEvent.VK_DOWN:
                yMove = 1;
                break;
            case KeyEvent.VK_UP:
                yMove = -1;
                break;
            case KeyEvent.VK_LEFT:
                xMove = -1;
                break;
            case KeyEvent.VK_RIGHT:
                xMove = 1;
                break;
        }
        boost();
        Position moved = new Position(position.getX(), position.getY());
        moved.move(xMove, yMove);

        if (moved.IsCorrect()) {
            if (box.getOrganism(moved.getX(), moved.getY()) == null || box.getOrganism(moved.getX(), moved.getY()) == this) {
                position.setPosition(moved);
            }
            else {
                Organism npc = box.getOrganism(moved.getX(), moved.getY());
                collision(npc);
            }
        }
        ageing();
    }
    @Override
    public Color getColor(){
        return Color.PINK;
    }

    public String save(){
        return  getIcon() + " " + getX() + " " + getY() + " " + age  + " " + getStrength() + " " +
                boostTime + " " + timeToBoost;
    }

    public void setBoostTime(int boostTime){
        this.boostTime = boostTime;
    }

    @Override
    public String GetOrganismsName() {
        return "Human";
    }

    public void setTimeToBoost(int timeToBoost){
        this.timeToBoost = timeToBoost;
    }
}
