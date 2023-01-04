import java.awt.Color;
import java.util.*;
import javax.swing.*;

public class World {
    private int height;
    private int width;
    private int population;
    private int turn;
    private int howManyComments;
    private int humanDir;

    private Vector<Organism> organisms;
    private Organism[][] board;
    private JButton[][] gui;
    private JLabel[] commentator;

    World(int size) {
        this.height = size;
        this.width = size;
        this.howManyComments = 0;
        this.population = 0;
        this.turn = 0;
        this.humanDir = 0;
        organisms = new Vector<>();
        this.gui = new JButton[height][width];
        this.board = new Organism[height][width];
        this.commentator = new JLabel[height * width];
        for(int i = 0; i < height * width; i++) {
            this.commentator[i] = new JLabel();
        }
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                gui[i][j] = new JButton();
            }
        }
    }

    public void drawWorld(Grid sim) {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(board[i][j] != null) {
                    gui[i][j] = board[i][j].getButton();
                } else gui[i][j] = new JButton();
                gui[i][j].addActionListener(sim);
            }
        }
        sim.removeAll();
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                sim.add(gui[i][j]);
            }
        }
        howManyComments = 0;
    }

    public void makeTurn() {
        turn++;
        for(int i = 0; i < population; i++) {
            organisms.get(i).advanceAge();
            organisms.get(i).advanceCooldown();
            organisms.get(i).action();
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getPopulation() {
        return population;
    }

    public int getTurn() {
        return turn;
    }

    public Organism getField(int y, int x) {
        if(y >= 0 && y < height && x >= 0 && x < width) return board[y][x];
        else return null;
    }

    public Organism getOrganism(int index) {
        return organisms.get(index);
    }

    public JLabel getComment(int index) {
        this.commentator[index].setForeground(Color.YELLOW);
        return this.commentator[index];
    }

    public int getHowManyComments() {
        return this.howManyComments;
    }

    public int getHumanDir() {
        return this.humanDir;
    }

    public JButton getButton(int y, int x) {
        return gui[y][x];
    }

    public void setHeight(int number) {
        this.height = number;
    }

    public void setWidth(int number) {
        this.width = number;
    }

    public void setPopulation(int number) {
        this.population = number;
    } 

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void setOrganism(int y, int x, Organism organism) {
        this.board[y][x] = organism;
    }

    public void reInit(int height, int width) {
        this.gui = new JButton[height][width];
        this.board = new Organism[height][width];
        this.commentator = new JLabel[height * width];
        for(int i = 0; i < height * width; i++) {
            this.commentator[i] = new JLabel();
        }
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                gui[i][j] = new JButton();
            }
        }
    }

    public void humanDir(int dir) {
        this.humanDir = dir;
    }

    public void createOrganism(Organism organism) {
        organisms.add(organism);
        setOrganism(organism.getPosY(), organism.getPosX(), organism);
        population++;
        Collections.sort(organisms, new CompareOrganisms());
    }

    public void removeOrganism(Organism organism) {
        for(int i = 0; i < population; i++) {
            if(organisms.get(i) == organism) {
                organisms.remove(i);
                population--;
                break;
            }
        }
        organism = null;
        Collections.sort(organisms, new CompareOrganisms());
    }

    public void addComment(String comment) {
        commentator[howManyComments].setText(comment);
        howManyComments++;
    }

}
