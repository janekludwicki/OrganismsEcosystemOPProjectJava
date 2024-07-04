package ProjectOP.Animals;

import ProjectOP.Organism;
import ProjectOP.Position;
import ProjectOP.WorldBox;

import java.awt.*;
import java.util.Random;

public class Turtle extends Animal{

    public Turtle(WorldBox box) {
        this.characterIcon = 'T';
        this.age = 0;
        this.initiative = 1;
        this.strength = 2;
        this.isAlive = true;
        this.box = box;

        Position position = new Position(new Random().nextInt(0, 20), new Random().nextInt(0, 20));
        while(box.getOrganism(position.getX(), position.getY()) != null)
            position = new Position(new Random().nextInt(0, 20), new Random().nextInt(0, 20));

        this.position = position;
    }

    public Turtle(WorldBox box, Position position) {
        this.characterIcon = 'T';
        this.age = 0;
        this.initiative = 1;
        this.strength = 2;
        this.isAlive = true;
        this.box = box;
        this.position = position;
    }

    @Override
    public void spawnBaby(Organism partner) {
        Position babySpace = new Position() ;
        if (findPlaceToSpawnBaby(babySpace, partner.getPosition())) {
            Organism baby = new Turtle(box, babySpace);
            box.addOrganism(baby);
        }
    }

    @Override
    public void action() {
        int random = new Random().nextInt(0, 4);
        if (random == 0) {
            int option = 0;
            Position[] allOptions = new Position[9];
            for (int xMove = -1; xMove <= 1; xMove++) {
                for (int yMove = -1; yMove <= 1; yMove++) {
                    Position tmp = new Position(position.getX(), position.getY());
                    tmp.move(xMove, yMove);
                    if (tmp.IsCorrect()) {
                        allOptions[option] = new Position(tmp.getX(), tmp.getY());
                        option++;
                    }
                }
            }
            if (option > 0) {
                int random1 = new Random().nextInt(0, option);
                Position newTmp = allOptions[random1];
                Organism npc = box.getOrganism(newTmp.getX(), newTmp.getY());
                if (npc == null || npc == this)
                    position.setPosition(newTmp);
                else
                    collision(npc);
            }
        }
        ageing();
    }

    @Override
    public void collisionSpecial(Organism npc) {
        if (npc.getStrength() > 5) {
            String comment = box.getOrganismName(this) + " was eaten by " + box.getOrganismName(npc);
            box.addComment(comment);
            box.putOnGraveyard(this.getX(), this.getY());
            npc.setPosition(position);
        }
    }

    @Override
    public Color getColor(){
        Color c1 = new Color(0,153,0);
        return c1;
    }

    @Override
    public String GetOrganismsName() {
        return "Turtle";
    }

    @Override
    public void draw(){
        System.out.print("T");
    }
}
