package ProjectOP;

import ProjectOP.Animals.*;
import ProjectOP.Plants.*;
import ProjectOP.Views.MainView;
import org.codehaus.groovy.tools.StringHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.lang.reflect.AnnotatedType;
import java.util.List;

public class World {
    private int number_of_turns;
    private WorldBox box;
    private MainView mainView;
    public JFrame viewFrame;

    public World() {

        box = new WorldBox();
        viewFrame = new JFrame();
        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewFrame.setLayout(new GridLayout(1,1,0,0));
        viewFrame.setSize(1000,700);
        mainView = new MainView(this);
        mainView.setVisible(true);
        viewFrame.add(mainView);

        viewFrame.setVisible(true);

        box.addOrganism( new Wolf(box));
        box.addOrganism( new Sheep(box));
        box.addOrganism( new Antelope(box));
        box.addOrganism( new Fox(box));
        box.addOrganism( new Turtle(box));
        box.addOrganism( new Grass(box));
        box.addOrganism( new SowThistle(box));
        box.addOrganism( new Belladonna(box));
        box.addOrganism( new SosnowskyHogweed(box));
        box.addOrganism( new Guarana(box));
        box.addOrganism( new Wolf(box));
        box.addOrganism( new Sheep(box));
        box.addOrganism( new Antelope(box));
        box.addOrganism( new Fox(box));
        box.addOrganism( new Turtle(box));
        box.addOrganism( new Grass(box));
        box.addOrganism( new SowThistle(box));
        box.addOrganism( new Belladonna(box));
        box.addOrganism( new SosnowskyHogweed(box));
        box.addOrganism( new Guarana(box));
        box.addOrganism( new Human(box));
        number_of_turns = 0;
    }

    public WorldBox getWorldBox() {
        return box;
    }

    public void drawWorld() {
        List<Organism> characters = getWorldBox().getList();
        List<String> comments = getWorldBox().getCommentary();
        System.out.println( "Turn : " + number_of_turns + "   Number of organisms: " + box.getNumberOfOrganisms());

        for(int y=0; y<20;y++)
        {
            for(int x=0; x<20;x++)
            {
                Organism organism = box.getOrganism(x, y);
                if(organism != null)
                    organism.draw();
                else
                    System.out.print('.');
            }
            System.out.println();
        }

        viewFrame.repaint();
        number_of_turns++;
    }

    public void takeTurn(KeyEvent move) {
        for(int i=0; i< getWorldBox().getList().size(); i++) {
            if (getWorldBox().getList().get(i).getIsAlive() && !getWorldBox().getList().get(i).getWasBuried()) {
                if (getWorldBox().getList().get(i).getIcon() == 'H')
                    getWorldBox().getList().get(i).action(move);
                else
                    getWorldBox().getList().get(i).action();
            }
        }
        box.buryDead();
        drawWorld();
        viewFrame.invalidate();
        viewFrame.validate();
        viewFrame.repaint();
    }

    public void save() {
        try {
            String path = "world.txt";
            File file = new File(path);
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));

            writer.write(number_of_turns + " " + getWorldBox().getNumberOfOrganisms());
            writer.newLine();
            for(int i=0;i<getWorldBox().getNumberOfOrganisms(); i++){
                Organism org = getWorldBox().getOrganism(i);
                writer.write(org.save());
                writer.newLine();
            }
        writer.close();
        }
        catch (Exception ex) {

        }
    }

    public int getNumberOfTurns () {
        return number_of_turns;
    }

    public void load(){
        try {
            String path = "world.txt";
            File file = new File(path);
            if(file.exists()){
                System.out.println("Loading...");
                box = new WorldBox();
                BufferedReader reader = new BufferedReader(new FileReader(path));
                String line = reader.readLine();
                String[] elements = line.split(" ");

                number_of_turns = Integer.parseInt(elements[0]);
                int numberOfOrgs = Integer.parseInt(elements[1]);

                for(int i=0 ;i< numberOfOrgs; i++){
                    line = reader.readLine();
                    elements = line.split(" ");
                    char symbol = elements[0].toCharArray()[0];
                    int x = Integer.parseInt(elements[1]);
                    int y = Integer.parseInt(elements[2]);
                    int age = Integer.parseInt(elements[3]);
                    int strength = Integer.parseInt(elements[4]);

                    if(symbol == 'A'){
                        Antelope org = new Antelope(box, new Position(x,y));
                        org.setAge(age);
                        org.setStrength(strength);
                        box.addOrganism(org);
                    }
                    if(symbol == 'F'){
                        Fox org = new Fox(box, new Position(x,y));
                        org.setAge(age);
                        org.setStrength(strength);
                        box.addOrganism(org);
                    }
                    if(symbol == 'S'){
                        Sheep org = new Sheep(box, new Position(x,y));
                        org.setAge(age);
                        org.setStrength(strength);
                        box.addOrganism(org);
                    }
                    if(symbol == 'T'){
                        Turtle org = new Turtle(box, new Position(x,y));
                        org.setAge(age);
                        org.setStrength(strength);
                        box.addOrganism(org);
                    }
                    if(symbol == 'W'){
                        Wolf org = new Wolf(box, new Position(x,y));
                        org.setAge(age);
                        org.setStrength(strength);
                        box.addOrganism(org);
                    }
                    if(symbol == '@'){
                        Belladonna org = new Belladonna(box, new Position(x,y));
                        org.setAge(age);
                        org.setStrength(strength);
                        box.addOrganism(org);
                    }
                    if(symbol == ','){
                        Grass org = new Grass(box, new Position(x,y));
                        org.setAge(age);
                        org.setStrength(strength);
                        box.addOrganism(org);
                    }
                    if(symbol == '%'){
                        Guarana org = new Guarana(box, new Position(x,y));
                        org.setAge(age);
                        org.setStrength(strength);
                        box.addOrganism(org);
                    }
                    if(symbol == '!'){
                        SosnowskyHogweed org = new SosnowskyHogweed(box, new Position(x,y));
                        org.setAge(age);
                        org.setStrength(strength);
                        box.addOrganism(org);
                    }
                    if(symbol == '*'){
                        SowThistle org = new SowThistle(box, new Position(x,y));
                        org.setAge(age);
                        org.setStrength(strength);
                        box.addOrganism(org);
                    }
                    if(symbol == 'H'){
                        int boostTime = Integer.parseInt(elements[5]);
                        int timeToBoost = Integer.parseInt(elements[6]);
                        Human org = new Human(box, new Position(x,y));
                        org.setAge(age);
                        org.setStrength(strength);
                        org.setBoostTime(boostTime);
                        org.setTimeToBoost(timeToBoost);
                        box.addOrganism(org);
                    }
                }
            }
            viewFrame.repaint();
        }
        catch (Exception ex){

        }
    }
}
