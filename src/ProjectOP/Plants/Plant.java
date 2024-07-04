package ProjectOP.Plants;

import ProjectOP.*;

import java.util.Random;

public abstract  class Plant extends Organism {

    @Override
   public void collision(Organism npc) {}

    boolean findSpotForNewPlant(Position babyPlantPos) {

        int option = 0;
        Position[] allOptions = new Position[9];

        for (int xMove = -1; xMove <= 1; xMove++) {
            for (int yMove = -1; yMove <= 1; yMove++) {
                Position tmp = new Position(position.getX(), position.getY());
                tmp.move(xMove, yMove);
                if (tmp.IsCorrect() && box.getOrganism(tmp.getX(), tmp.getY()) == null) {
                    allOptions[option] = new Position(tmp.getX(), tmp.getY());
                    option++;
                }
            }
        }

        if (option > 0) {
            int random = new Random().nextInt(0, option);
            babyPlantPos.setPosition(allOptions[random]);
            return true;
        }
        else return false;
    }

}
