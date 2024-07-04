package ProjectOP.Animals;

import ProjectOP.*;

import java.awt.*;
import java.util.Random;

public class Antelope extends  Animal {

    public Antelope(WorldBox box) {
        this.characterIcon = 'A';
        this.age = 0;
        this.initiative = 4;
        this.strength = 4;
        this.isAlive = true;
        this.box = box;

        Position position = new Position(new Random().nextInt(0, 20), new Random().nextInt(0, 20));
        while(box.getOrganism(position.getX(), position.getY()) != null)
            position = new Position(new Random().nextInt(0, 20), new Random().nextInt(0, 20));

        this.position = position;
    }

    public Antelope(WorldBox box, Position position) {
        this.characterIcon = 'A';
        this.age = 0;
        this.initiative = 4;
        this.strength = 4;
        this.isAlive = true;
        this.position = position;
        this.box = box;
    }

    @Override
    public Color getColor(){
        return Color.BLUE;
    }

    @Override
    public void spawnBaby(Organism partner) {
        Position babySpace = new Position();
        if (findPlaceToSpawnBaby(babySpace, partner.getPosition())) {
            Organism baby = new Antelope(box, babySpace);
            box.addOrganism(baby);
        }
    }

    @Override
    public void action() {

        for (int i = 0; i < 2; i++) {
            int option = 0;
            Position[] allOptions = new Position[9];


            for (int xMove = -1; xMove <= 1; xMove++) {
                for (int yMove = -1; yMove <= 1; yMove++) {
                    Position tmp = new Position(position.getX(), position.getY()) ;
                    tmp.move(xMove, yMove);
                    if (tmp.IsCorrect()) {
                        allOptions[option] = new Position(tmp.getX(), tmp.getY());
                        option++;
                    }
                }
            }

            if (option > 0) {
                int random = new Random().nextInt(0, option);
                Position newTmp = allOptions[random];
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

        int random = new Random().nextInt(0, 2);
        boolean escaped = false;

        if (random == 1) {

            int option = 0;
            Position[] allOptions = new Position[9];
            Position tmp =new Position( position.getX(), position.getY());

            for (int xMove = -1; xMove <= 1; xMove++) {
                for (int yMove = -1; yMove <= 1; yMove++) {
                    tmp.move(xMove, yMove);

                    if (tmp.IsCorrect() && box.getOrganism(tmp.getX(), tmp.getY()) == null) {
                        allOptions[option] = new Position(tmp.getX(), tmp.getY());
                        option++;
                    }
                }
            }
            if (option > 0) {
                String comment = "Antolepe managed to escape the fight !!!";
                int random2 = new Random().nextInt(0, option);
                box.addComment(comment);
                npc.setPosition(position);
                this.setPosition(allOptions[random2]);
                escaped = true;
            }
        }
        if (!escaped) {

            if (strength > npc.getStrength()) {
                String comment = box.getOrganismName(npc) + " was eaten by " + box.getOrganismName(this);
                box.addComment(comment);
                box.putOnGraveyard(npc.getX(), npc.getY());
            }
            else {
                String comment = box.getOrganismName(this) + " was eaten by " + box.getOrganismName(npc);
                box.addComment(comment);
                box.putOnGraveyard(this.getX(), this.getY());
                npc.setPosition(position);
            }
        }
    }

    @Override
    public String GetOrganismsName() {
        return "Antelope";
    }

    @Override
    public void draw(){
        System.out.print("A");

    }
}
