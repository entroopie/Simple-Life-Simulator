import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Panel extends JFrame implements ActionListener, KeyListener {

    private static final int HEIGHT = 835;
    private static final int WIDTH = 1200;
    private JButton next;
    private JButton savex;
    private Grid grid;
    private JLabel turns;
    private JLabel whichKey;
    private JPanel text;
    private JPanel box;
    private World world;
    private God creator;

    Panel(int size, boolean load) {
        world = new World(size);
        creator = new God(world);
        this.init();
        if(load) {
            creator.load();
            world = creator.getWorld();
            grid = new Grid(world.getHeight(), world);
            grid.setFocusable(false);
            this.add(grid);
        } else {
            grid = new Grid(size, world);
            grid.setFocusable(false);
            this.add(grid);
            creator.create();
            creator.placeHumanRandomly();
        }
        turns.setText("Turn no: "+Integer.toString(world.getTurn()));
        box.add(turns);
        whichKey.setText("Human's next move: ");
        box.add(whichKey);
        world.drawWorld(grid);
    }

    public void addText(int howManyComments) {
        box.removeAll();
        turns.setText("Turn no: "+Integer.toString(world.getTurn()));
        whichKey.setText("Human's next move: ");
        box.add(turns);
        box.add(whichKey);
        for(int i = 0; i < howManyComments; i++) {
            box.add(world.getComment(i));
        }
        this.revalidate();
        this.repaint();
    }

    private void init() {
        text = new JPanel();
        next = new JButton("Next turn!");
        savex = new JButton("Save current state");
        box = new JPanel();
        turns = new JLabel();
        whichKey = new JLabel();
        turns.setForeground(Color.CYAN);
        whichKey.setForeground(Color.CYAN);
        this.setLayout(null);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        text.setBounds(800, 0,400,830);
        text.setLayout(null);
        text.setBackground(Color.GRAY);
        text.setVisible(true);
        text.setFocusable(false);
        box.setFocusable(false);
        next.setFocusable(false);
        savex.setFocusable(false);
        this.setFocusable(true);
        box.setBounds(25, 150,340,500);
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setBackground(Color.BLACK);
        box.setVisible(true);
        next.setBounds(25, 30,340,30);
        savex.setBounds(25, 90,340,30);
        next.addActionListener(this);
        savex.addActionListener(this);
        text.add(box);
        text.add(next);
        text.add(savex);
        text.add(box);
        this.add(text);
        addKeyListener(this);
    }

    public void newWorld(World world) {
        this.world = world;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == next) {
            world.makeTurn();
            this.addText(world.getHowManyComments());
            world.drawWorld(grid);
            grid.revalidate();
            grid.repaint();
            world.humanDir(0);
        } 
        else if(source == savex) {
            creator.save();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {   

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            whichKey.setText("Human's next move: LEFT");
            world.humanDir(3);
        }

        if (key == KeyEvent.VK_RIGHT) {
            whichKey.setText("Human's next move: RIGHT");
            world.humanDir(4);
        }

        if (key == KeyEvent.VK_UP) {
            whichKey.setText("Human's next move: UP");
            world.humanDir(1);
        }

        if (key == KeyEvent.VK_DOWN) {
            whichKey.setText("Human's next move: DOWN");
            world.humanDir(2);
        }

        if (key == KeyEvent.VK_P) {
            whichKey.setText("Human's next move: POTION");
            world.humanDir(5);
        }
    }

}
