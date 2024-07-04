package ProjectOP.Animals;

import ProjectOP.*;

import java.util.Random;

public abstract class Animal extends Organism {

    boolean findPlaceToSpawnBaby(Position baby_pos, Position partner_pos) {

        int option = 0;
        Position[] allOptions = new Position[16];
        Position tmp, tmpPart;

        for (int xMove = -1; xMove <= 1; xMove++) {
            for (int yMove = -1; yMove <= 1; yMove++) {
                tmp = position;
                tmpPart = partner_pos;
                tmp.move(xMove, yMove);
                tmpPart.move(xMove, yMove);

                if (tmp.IsCorrect() && box.getOrganism(tmp.getX(), tmp.getY()) == null) {
                    allOptions[option].setPosition(tmp);
                    option++;
                }
                if (tmpPart.IsCorrect() && box.getOrganism(tmpPart.getX(), tmpPart.getY()) == null) {
                    allOptions[option].setPosition(tmpPart);
                    option++;
                }
            }
        }
        if (option > 0) {
            int random = new Random().nextInt(0, option);
            baby_pos.setPosition(allOptions[random]);
            return true;
        }
        else return false;
    }

    @Override
   public void action() {
        int option = 0;
        Position[] allOptions = new Position[9];
        Position tmp, newTmp;

        for (int xMove = -1; xMove <= 1; xMove++) {
            for (int yMove = -1; yMove <= 1; yMove++) {
                tmp = new Position(position.getX(), position.getY());
                tmp.move(xMove, yMove);
                if (tmp.IsCorrect()) {
                    allOptions[option] = new Position(tmp.getX(), getPosition().getY());
                    option++;
                }
            }
        }
        if (option > 0) {
            int random = new Random().nextInt(0, option );
            newTmp =allOptions[random];
            Organism npc = box.getOrganism(newTmp.getX(), newTmp.getY());
            if (npc == null || npc == this){
                System.out.println("setting new position from " + position.getX() + "," + position.getY());
                System.out.println("to " + newTmp.getX() + "," + newTmp.getY());
                position.setPosition(newTmp);
            }
            else
                collision(npc);
        }
    }

    @Override
    public void collision(Organism npc) {
        Position npcPos= new Position(npc.getX(), npc.getY());

        if (npc.getIcon() == characterIcon) {
            if (box.getNumberOfOrganisms() < 400)
                spawnBaby(npc);
        }
        else if (npc.getIcon() == '@')
            npc.collision(this);
        else if (npc.getIcon() == '%')
            npc.collision(this);
        else if (npc.getIcon() == '!')
            npc.collision(this);
        else if (npc.getIcon() == 'A')
            npc.collisionSpecial(this);
        else {
            if (strength >= npc.getStrength()) {
                if (npc.getIcon() == 'T') {
                    npc.collisionSpecial(this);
                    if (strength <= 5) {
                        String comment = box.getOrganismName(npc) + " has blocked the attack of " + box.getOrganismName(this) + " !!!";
                        box.addComment(comment);
                    }
                }
                else {
                    String comment = box.getOrganismName(npc) + " was eaten by " + box.getOrganismName(this);
                    box.addComment(comment);
                    box.putOnGraveyard(npcPos.getX(), npcPos.getY());
                    npc.setPosition(npcPos);
                }
            }
            else {
                String comment = box.getOrganismName(this) + " was eaten by " + box.getOrganismName(npc);
                box.addComment(comment);
                box.putOnGraveyard(position.getX(), position.getY());
            }
        }
        ageing();
    }

    void spawnBaby(Organism partner) {}
}
