package ProjectOP;

import java.util.ArrayList;
import java.util.List;

public class WorldBox {

    List<Organism> organisms;
    List<String> commentary;
    Organism[] graveyard = new Organism[400];
    int deaths;

    public WorldBox() {
        organisms = new ArrayList<Organism>();
        commentary = new ArrayList<String>();
        this.deaths = 0;
    }

    public void addOrganism(Organism organism) {

        if (organisms.size()==0) {
            organisms.add(organism);
        }
        else {
            boolean is_set = false;
            for(int i=0; i<organisms.size(); i++) {
                Organism tmp = organisms.get(i);
                if (tmp.getInitiative() < organism.getInitiative()) {
                    organisms.add(i, organism);
                    is_set = true;
                    break;
                }
            }
            if (!is_set)
                organisms.add(organism);
        }
        String comment = getOrganismName(organism) + " appeared in the world at X: " + organism.getX() + " Y: " + organism.getY();
        addComment(comment);
    }

    public  Organism getOrganism(int x, int y) {

        for(int i=0; i<organisms.size(); i++) {
            Organism tmp = organisms.get(i);
            if (tmp.getX() == x && tmp.getY() == y && tmp.getIsAlive())
                return tmp;
        }
        return null;
    }
    public  Organism getOrganism(int index) {
        return organisms.get(index);
    }

    public int getNumberOfOrganisms() {
        return organisms.size();
    }

    public void addComment(String comment) {
        commentary.add(comment);
    }

    public  List<String> getCommentary() {
        return commentary;
    }

    public  String getOrganismName(Organism organism) {

        switch (organism.getIcon()) {
            case 'H':
                return "Human";
            case 'W':
                return "Wolf";
            case 'S':
                return "Sheep";
            case 'T':
                return "Turtle";
            case 'A':
                return "Antelope";
            case 'F':
                return "Fox";
            case '*':
                return "Sow Thistle";
            case ',':
                return "Grass";
            case'@':
                return "Belladonna";
            case'%':
                return "Guarana";
            case'!':
                return "Sosnowsky Hogweed";
            default:
                return "";
        }
    }

    public void putOnGraveyard(int x, int y) {

        Organism tmp = getOrganism(x, y);
        if (tmp != null) {
            tmp.kill();
            graveyard[deaths] = tmp;
            deaths++;
        }
    }

    public  List<Organism> getList() {
        return organisms;
    }

    public  void buryDead() {
        for (int i = 0; i < deaths; i++) {
            organisms.remove(graveyard[i]);
            graveyard[i] = null;
        }
        deaths = 0;
    }
}
