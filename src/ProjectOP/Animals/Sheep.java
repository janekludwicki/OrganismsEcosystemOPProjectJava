package ProjectOP.Animals;

import ProjectOP.Organism;
import ProjectOP.Position;
import ProjectOP.WorldBox;

import java.awt.*;
import java.util.Random;

public class Sheep extends Animal{

    public Sheep(WorldBox box) {
        this.characterIcon = 'S';
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

    public Sheep(WorldBox box, Position position) {
        this.characterIcon = 'S';
        this.age = 0;
        this.initiative = 4;
        this.strength = 4;
        this.position = position;
        this.isAlive = true;
        this.box = box;
    }

    @Override
    public void spawnBaby(Organism partner) {
        Position babySpace = new Position();
        if (findPlaceToSpawnBaby(babySpace, partner.getPosition())) {
            Organism baby = new Sheep(box, babySpace);
            box.addOrganism(baby);
        }
    }
    @Override
    public void draw(){
        System.out.print("S");
    }

    @Override
    public Color getColor(){
        return Color.GRAY;
    }

    @Override
    public String GetOrganismsName() {
        return "Sheep";
    }
}
