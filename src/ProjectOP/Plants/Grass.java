package ProjectOP.Plants;

import ProjectOP.Organism;
import ProjectOP.Position;
import ProjectOP.WorldBox;

import java.awt.*;
import java.util.Random;

public class Grass extends Plant{

    public Grass(WorldBox box) {
        this.age = 0;
        this.strength = 0;
        this.isAlive = true;
        this.characterIcon = ',';
        this.box = box;

        Position position = new Position(new Random().nextInt(0, 20), new Random().nextInt(0, 20));
        while(box.getOrganism(position.getX(), position.getY()) != null)
            position = new Position(new Random().nextInt(0, 20), new Random().nextInt(0, 20));

        this.position = position;
    }

    public Grass(WorldBox box, Position position) {
        this.age = 0;
        this.strength = 0;
        this.isAlive = true;
        this.characterIcon = ',';
        this.box = box;
        this.position = position;
    }

    @Override
    public void action() {
        int random = new Random().nextInt(0, 10);
        if (random == 0) {
            Position newPlantSpot = new Position();
            if (findSpotForNewPlant(newPlantSpot) && box.getNumberOfOrganisms() < 400) {
                Organism babyPlant = new Grass(box, newPlantSpot);
                box.addOrganism(babyPlant);
            }
        }
    }

    @Override
    public Color getColor(){
        return Color.GREEN;
    }

    @Override
    public String GetOrganismsName() {
        return "Grass";
    }

    @Override
    public void draw(){
        System.out.print(',');
    }
}
