package ProjectOP.Views;

import ProjectOP.*;
import ProjectOP.Animals.*;
import ProjectOP.Plants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainView extends JPanel implements KeyListener, ActionListener, MouseListener {

    private World world;
    private Organism[] organismsUi;
    private char selectedOrganism;
    public JTextArea area = new JTextArea();
    public JLabel label2  = new JLabel();
    public JLabel label3 = new JLabel();

    public MainView(World world){
        this.world = world;
        this.selectedOrganism = '\0';
        organismsUi = new Organism[] {  new Antelope(world.getWorldBox()), new Fox(world.getWorldBox()),
                new Sheep(world.getWorldBox()), new Turtle(world.getWorldBox()),
                new Wolf(world.getWorldBox()), new Belladonna(world.getWorldBox()),
                new Grass(world.getWorldBox()), new Guarana(world.getWorldBox()),
                new SosnowskyHogweed(world.getWorldBox()), new SowThistle(world.getWorldBox()), new Human(world.getWorldBox())};

        addKeyListener(this);

        //Button's initializations
        JButton nextTurnButton = new JButton("Next turn");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");

        //Number of Turns and Organisms
        label2  = new JLabel( "TURN: " + world.getNumberOfTurns());
        label2.setBounds(800, 20, 140, label2.getPreferredSize().height);
        add(label2);
        label2.setVisible(true);

        label3  = new JLabel( "ORGANISMS: " + world.getWorldBox().getNumberOfOrganisms());
        label3.setBounds(800, 50, 140, label2.getPreferredSize().height);
        add(label3);
        label3.setVisible(true);

        JLabel label4 = new JLabel("COMMENTARY:");
        label4.setBounds(620, 360, 140, label2.getPreferredSize().height);
        add(label4);
        label4.setVisible(true);

        // JTextArea
        area = new JTextArea();
        area.setEditable(false);
        area.setBounds(620, 380 , 350 , 280);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        add(area);
        area.setVisible(true);

        //Action listeners
        nextTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.takeTurn(null);
                System.out.println("Next turn button clicked!");
                area.setText(null);
                requestFocus();
            }
        });
        saveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                world.save();
                System.out.println("Save button");
                requestFocus();
            }
        });
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.load();
                System.out.println("Load button");
                requestFocus();
            }
        });

        //button's sizes
        setLayout(null);
        nextTurnButton.setBounds(20, 625, 100, 25);
        saveButton.setBounds(140, 625, 100, 25);
        loadButton.setBounds(260, 625, 100, 25);

        //Adding buttons to menu
        add(nextTurnButton);
        add(saveButton);
        add(loadButton);

        nextTurnButton.setVisible(true);
        saveButton.setVisible(true);
        loadButton.setVisible(true);

        addMouseListener(this);

        invalidate();
        validate();
        repaint();
    }

    public void appendcommentary () {
        for( int i = 0; i < world.getWorldBox().getCommentary().size(); i++ )
        {
            area.append(world.getWorldBox().getCommentary().get((i)) + "\n");
        }
    }

    public void keyPressed(KeyEvent e){
        world.takeTurn(e);
    }
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}

    public void actionPerformed(ActionEvent e){
        repaint();
    }

    public void paint(Graphics g){
        super.paint(g);
        for(int y = 0; y < 20; y++){
            for(int x = 0; x < 20; x++){
                Organism organism = world.getWorldBox().getOrganism(x, y);
                if(organism != null) {
                    g.setColor(organism.getColor());
                    g.fillRect(x*30, y * 30, 30,30);
                }
                else{
                    Color c1 = new Color(102,51,0);
                    g.setColor(c1);
                    g.fillRect(x*30, y * 30, 30,30);
                }
            }
        }

        for(int i = 0; i<11; i++){
                JLabel label = new JLabel(organismsUi[i].GetOrganismsName());
                label.setBounds(660, 20 + (30*i), 140, label.getPreferredSize().height);
                add(label);
                label.setVisible(true);
                g.setColor(organismsUi[i].getColor());
                g.fillRect(620, 20 + (30*i), 30,30);
            }

        label2.setText( "TURN: " + world.getNumberOfTurns());
        label3.setText( "ORGANISMS: " + world.getWorldBox().getNumberOfOrganisms());

        // Update
        // JTextArea
        appendcommentary();
        world.getWorldBox().getCommentary().clear();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(e.getX() >= 620 && e.getX() <= 650 && e.getY() >=20 && e.getY() <= 320){
            selectedOrganism = organismsUi[(e.getY() - 20) / 30].getIcon();
            System.out.println("Selected organism: "+ selectedOrganism);
            return;
        }

        if(selectedOrganism == '\0')
            return;

        Position pos = new Position(e.getX() / 30, e.getY()/30);

        if(pos.getY()<0 || pos.getY() >= 20 || pos.getX() < 0 || pos.getX() >= 20)
            return;

        if(world.getWorldBox().getOrganism(pos.getX(), pos.getY()) != null)
            return;

        if(selectedOrganism == 'A')
            world.getWorldBox().addOrganism(new Antelope(world.getWorldBox(), pos));
        else if(selectedOrganism == ',')
            world.getWorldBox().addOrganism(new Grass(world.getWorldBox(), pos));
        else if(selectedOrganism == 'F')
            world.getWorldBox().addOrganism(new Fox(world.getWorldBox(), pos));
        else if(selectedOrganism == 'H')
            world.getWorldBox().addOrganism(new Human(world.getWorldBox(), pos));
        else if(selectedOrganism == 'S')
            world.getWorldBox().addOrganism(new Sheep(world.getWorldBox(), pos));
        else if(selectedOrganism == 'T')
            world.getWorldBox().addOrganism(new Turtle(world.getWorldBox(), pos));
        else if(selectedOrganism == 'W')
            world.getWorldBox().addOrganism(new Wolf(world.getWorldBox(), pos));
        else if(selectedOrganism == '@')
            world.getWorldBox().addOrganism(new Belladonna(world.getWorldBox(), pos));
        else if(selectedOrganism == '%')
            world.getWorldBox().addOrganism(new Guarana(world.getWorldBox(), pos));
        else if(selectedOrganism == '!')
            world.getWorldBox().addOrganism(new SosnowskyHogweed(world.getWorldBox(), pos));
        else if(selectedOrganism == '*')
            world.getWorldBox().addOrganism(new SowThistle(world.getWorldBox(), pos));
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
