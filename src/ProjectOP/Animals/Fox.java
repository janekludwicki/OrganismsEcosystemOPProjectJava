package ProjectOP.Animals;

import ProjectOP.Organism;
import ProjectOP.Position;
import ProjectOP.WorldBox;

import java.awt.*;
import java.util.Random;

public class Fox extends Animal{

    public Fox(WorldBox box) {
        this.characterIcon = 'F';
        this.age = 0;
        this.initiative = 7;
        this.strength = 3;
        this.isAlive = true;
        this.box = box;
        Position position = new Position(new Random().nextInt(0, 20), new Random().nextInt(0, 20));
        while(box.getOrganism(position.getX(), position.getY()) != null)
            position = new Position(new Random().nextInt(0, 20), new Random().nextInt(0, 20));

        this.position = position;
    }

    public Fox(WorldBox box, Position position) {
        this.characterIcon = 'F';
        this.age = 0;
        this.initiative = 7;
        this.strength = 3;
        this.isAlive = true;
        this.box = box;
        this.position = position;
    }

    @Override
    public void spawnBaby(Organism partner) {
        Position babySpace = new Position();
        if (findPlaceToSpawnBaby(babySpace, partner.getPosition())) {
            Organism baby = new Fox(box, babySpace);
            box.addOrganism(baby);
        }
    }

    @Override
    public void action() {

        int option = 0;
        Position[] allOptions = new Position[9];

        for (int xMove = -1; xMove <= 1; xMove++) {
            for (int yMove = -1; yMove <= 1; yMove++) {
                Position tmp = new Position(position.getX(), position.getY());
                tmp.move(xMove, yMove);
                if (tmp.IsCorrect()) {
                    Organism npc = box.getOrganism(tmp.getX(), tmp.getY());
                    if (npc == null || npc.getStrength() <= strength) {
                        allOptions[option] = new Position(tmp.getX(), tmp.getY());
                        option++;
                    }
                }
            }
        }
        if (option > 0) {
            int random = new Random().nextInt(0, option );
           Position newTmp = allOptions[random];
            Organism npc = box.getOrganism(newTmp.getX(), newTmp.getY());
            if (npc == null || npc == this) {
                this.setPosition(newTmp);
            }
            else {
                collision(npc);
            }
        }
        ageing();
    }

    @Override
    public Color getColor(){
        return Color.orange;
    }

    @Override
    public String GetOrganismsName() {
        return "Fox";
    }

    @Override
    public void draw(){
        System.out.print("F");
    }
}
