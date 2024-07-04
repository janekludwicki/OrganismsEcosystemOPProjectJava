package ProjectOP.Animals;

import ProjectOP.Organism;
import ProjectOP.Position;
import ProjectOP.WorldBox;

import java.awt.*;
import java.util.Random;

public class Wolf extends Animal{
    public Wolf(WorldBox box) {
        this.characterIcon = 'W';
        this.age = 0;
        this.initiative = 5;
        this.strength = 9;
        this.isAlive = true;
        this.box = box;

        Position position = new Position(new Random().nextInt(0, 20), new Random().nextInt(0, 20));
        while(box.getOrganism(position.getX(), position.getY()) != null)
            position = new Position(new Random().nextInt(0, 20), new Random().nextInt(0, 20));

        this.position = position;
    }

    public Wolf(WorldBox box, Position position) {
        this.characterIcon = 'W';
        this.age = 0;
        this.initiative = 5;
        this.strength = 9;
        this.position = position;
        this.isAlive = true;
        this.box = box;
    }

    @Override
    public void spawnBaby(Organism partner) {
        Position babySpace = new Position();
        if (findPlaceToSpawnBaby(babySpace, partner.getPosition())) {
            Organism baby = new Wolf(box, babySpace);
            box.addOrganism(baby);
        }
    }

    @Override
    public String GetOrganismsName() {
        return "Wolf";
    }

    @Override
    public void draw(){
        System.out.print("W");
    }

    @Override
    public Color getColor(){
        return Color.black;
    }
}
