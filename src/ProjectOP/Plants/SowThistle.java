package ProjectOP.Plants;

import ProjectOP.Organism;
import ProjectOP.Position;
import ProjectOP.WorldBox;

import java.awt.*;
import java.util.Random;

public class SowThistle extends Plant{

    public SowThistle(WorldBox box) {
        this.characterIcon = '*';
        this.age = 0;
        this.strength = 0;
        this.isAlive = true;
        this.box = box;

        Position position = new Position(new Random().nextInt(0, 20), new Random().nextInt(0, 20));
        while(box.getOrganism(position.getX(), position.getY()) != null)
            position = new Position(new Random().nextInt(0, 20), new Random().nextInt(0, 20));

        this.position = position;

    }
    public SowThistle(WorldBox box, Position position) {
        this.characterIcon = '*';
        this.age = 0;
        this.strength = 0;
        this.isAlive = true;
        this.position = position;
        this.box = box;
    }

    @Override
    public void action() {
        for (int i = 0; i <= 3; i++) {
            int random = new Random().nextInt(0, 10);
            if (random == 0) {
                Position newPlantSpot = new Position();
                if (findSpotForNewPlant(newPlantSpot) && box.getNumberOfOrganisms() < 400) {
                    Organism babyPlant = new SowThistle(box, newPlantSpot);
                    box.addOrganism(babyPlant);
                }
            }
        }
    }

    @Override
    public String GetOrganismsName() {
        return "Sow thistle";
    }

    @Override
    public void draw() {
        System.out.print('*');
    }

    @Override
    public Color getColor(){
        return Color.yellow;
    }
}
