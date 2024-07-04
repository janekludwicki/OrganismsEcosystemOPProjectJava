package ProjectOP.Plants;

import ProjectOP.Organism;
import ProjectOP.Position;
import ProjectOP.WorldBox;

import java.awt.*;
import java.util.Random;

public class Guarana extends Plant{

    public Guarana(WorldBox box) {
        this.characterIcon = '%';
        this.age = 0;
        this.strength = 0;
        this.isAlive = true;
        this.box = box;

        Position position = new Position(new Random().nextInt(0, 20), new Random().nextInt(0, 20));
        while(box.getOrganism(position.getX(), position.getY()) != null)
            position = new Position(new Random().nextInt(0, 20), new Random().nextInt(0, 20));

        this.position = position;
    }

    public Guarana(WorldBox box, Position position) {
        this.characterIcon = '%';
        this.age = 0;
        this.strength = 0;
        this.isAlive = true;
        this.box = box;

        this.position = position;
    }

    @Override
    public void collision(Organism npc) {
        npc.powerUpStrength();
        String comment = box.getOrganismName(npc) + " has eaten " + box.getOrganismName(this) + " It got boosted ! Its strenght is now: " + npc.getStrength();
        box.addComment(comment);
        box.putOnGraveyard(this.getX(), this.getY());
        npc.setPosition(position);
    }

    @Override
    public void action() {
        int random = new Random().nextInt(0, 5);
        if (random == 0) {
            Position newPlantSpot = new Position();
            if (findSpotForNewPlant(newPlantSpot) && box.getNumberOfOrganisms() < 400) {
                Organism babyPlant = new Guarana(box, newPlantSpot);
                box.addOrganism(babyPlant);
            }
        }
    }

    @Override
    public void draw() {
        System.out.print('*');
    }

    @Override
    public String GetOrganismsName() {
        return "Guarana";
    }

    @Override
    public Color getColor(){
        return Color.RED;
    }
}
