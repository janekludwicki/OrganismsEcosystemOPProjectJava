package ProjectOP.Plants;

import ProjectOP.Organism;
import ProjectOP.Position;
import ProjectOP.WorldBox;

import java.awt.*;
import java.util.Random;

public class SosnowskyHogweed extends Plant{

    public SosnowskyHogweed(WorldBox box) {
        this.characterIcon = '!';
        this.age = 0;
        this.strength = 10;
        this.isAlive = true;
        this.box = box;

        Position position = new Position(new Random().nextInt(0, 20), new Random().nextInt(0, 20));
        while(box.getOrganism(position.getX(), position.getY()) != null)
            position = new Position(new Random().nextInt(0, 20), new Random().nextInt(0, 20));

        this.position = position;
    }

    public SosnowskyHogweed(WorldBox box, Position position) {
        this.characterIcon = '!';
        this.age = 0;
        this.strength = 10;
        this.position = position;
        this.isAlive = true;
        this.box = box;
    }

    @Override
    public void action() {

        for (int xMove = -1; xMove <= 1; xMove++) {
            for (int yMove = -1; yMove <= 1; yMove++) {
                Position tmp = new Position(position.getX(), position.getY());
                tmp.move(xMove, yMove);
                if (tmp.IsCorrect()) {
                    Organism npc = box.getOrganism(tmp.getX(), tmp.getY());
                    if (npc != null && npc.getIcon() != ',' && npc.getIcon() != '*' && npc.getIcon() != '@' && npc.getIcon() != '%' && npc.getIcon() != '!') {
                        String comment = box.getOrganismName(npc) + " was near Sosnowsky Hogweed !! It died !!";
                        box.addComment(comment);
                        box.putOnGraveyard(npc.getX(), npc.getY());
                    }
                }
            }
        }
    }

    @Override
    public void collision(Organism npc) {
        String comment = box.getOrganismName(npc) + " has eaten " + box.getOrganismName(this) + " It died afterward !!!";
        box.addComment(comment);
        box.putOnGraveyard(npc.getX(), npc.getY());
        box.putOnGraveyard(this.getX(), this.getY());
    }

    @Override
    public void draw() {
        System.out.print('!');
    }

    @Override
    public String GetOrganismsName() {
        return "Sosnowsky's hogweed";
    }

    @Override
    public Color getColor(){
        return Color.LIGHT_GRAY;
    }

}
