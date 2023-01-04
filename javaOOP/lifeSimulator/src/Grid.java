import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Grid extends JPanel implements ActionListener {

    private int size;
    private World world;
    private JPopupMenu chooseOrganism;
    private int posY;
    private int posX;

    Grid(int size, World world) {
        initChoose();
        this.size = size;
        this.world = world;
        this.setSize(800, 800);
        this.setLayout(new GridLayout(size, size));
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        chooseOrganism.setVisible(false);
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(source == world.getButton(i, j)) {
                    if(world.getField(i, j) == null) {
                        posY = i;
                        posX = j;
                        chooseOrganism.setLocation(world.getButton(i, j).getX(), world.getButton(i, j).getY());
                        chooseOrganism.setVisible(true);
                    }
                }
            }
        }
    }

    public void refresh() {
        world.drawWorld(this);
        this.revalidate();
        this.repaint();
    }

    private void initChoose() {
        chooseOrganism = new JPopupMenu();
        JMenuItem m1 = new JMenuItem("Wolf");
        JMenuItem m2 = new JMenuItem("Sheep");
        JMenuItem m3 = new JMenuItem("Sosnowsky's Hogweed");
        JMenuItem m4 = new JMenuItem("Antelope");
        JMenuItem m5 = new JMenuItem("Fox");
        JMenuItem m6 = new JMenuItem("Turtle");
        JMenuItem m7 = new JMenuItem("Guarana");
        JMenuItem m8 = new JMenuItem("Grass");
        JMenuItem m9 = new JMenuItem("Sow Thistle");
        JMenuItem m10 = new JMenuItem("Belladona");
        chooseOrganism.add(m1);
        chooseOrganism.add(m2);
        chooseOrganism.add(m3);
        chooseOrganism.add(m4);
        chooseOrganism.add(m5);
        chooseOrganism.add(m6);
        chooseOrganism.add(m7);
        chooseOrganism.add(m8);
        chooseOrganism.add(m9);
        chooseOrganism.add(m10);
        m1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                world.createOrganism(new Wolf(posY, posX, world));
                refresh();
                chooseOrganism.setVisible(false);
            }
        });
        m2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                world.createOrganism(new Sheep(posY, posX, world));
                refresh();
                chooseOrganism.setVisible(false);
            }
        });
        m3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                world.createOrganism(new Sosnowsky(posY, posX, world));
                refresh();
                chooseOrganism.setVisible(false);
            }
        });
        m4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                world.createOrganism(new Antelope(posY, posX, world));
                refresh();
                chooseOrganism.setVisible(false);
            }
        });
        m5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                world.createOrganism(new Fox(posY, posX, world));
                refresh();
                chooseOrganism.setVisible(false);
            }
        });
        m6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                world.createOrganism(new Turtle(posY, posX, world));
                refresh();
                chooseOrganism.setVisible(false);
            }
        });
        m7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                world.createOrganism(new Guarana(posY, posX, world));
                refresh();
                chooseOrganism.setVisible(false);
            }
        });
        m8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                world.createOrganism(new Grass(posY, posX, world));
                refresh();
                chooseOrganism.setVisible(false);
            }
        });
        m9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                world.createOrganism(new SowThistle(posY, posX, world));
                refresh();
                chooseOrganism.setVisible(false);
            }
        });
        m10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                world.createOrganism(new Belladona(posY, posX, world));
                refresh();
                chooseOrganism.setVisible(false);
            }
        });
    }

}
