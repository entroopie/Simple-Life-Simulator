import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Turtle extends Animal {

    private JButton icon;

    Turtle(int posY, int posX, World world) {
        super("Turtle", 'O', posY, posX, 2, 1, world);
        icon = new JButton(new ImageIcon("C:/Users/Entroopie/Desktop/javaOOP/lifeSimulator/src/turtle.png"));
    }

    @Override
    public void collision(Organism attacker) {
        if (this.species == attacker.getSpecies() && this.age > 2 && attacker.getAge() > 2
        && this.cooldown == 0 && attacker.getCooldown() == 0 && this.species != "Human")  {
            this.setCooldown(5);
            attacker.setCooldown(5);
            breed(attacker.getPosY(), attacker.getPosX());
        }
        else if (this.species != attacker.getSpecies()) {
            if (attacker.getStrength() >= 5) fight(attacker);
            else {
                String temp = this.getSpecies() + " DEFLECTED the attack from " + attacker.getSpecies() + "!";
                world.addComment(temp);
            }
        }
    }
    
    
    public void action() {
        Random random = new Random();
        int	turtleMoves = random.nextInt(4);
        if (turtleMoves == 3) move(1);
        else {
            String temp = this.getSpecies() + " has decided NOT TO MOVE!";
            world.addComment(temp);
        }
    }
    
    public void move(int distance) {
        boolean dirSet = false;
        int direction;
        Random random = new Random();
        int ty = posY;
        int tx = posX;
        while (true) {
            direction = random.nextInt(4);
            switch (direction) {
            case 0:
                if (posY - distance >= 0) {
                    dirSet = true;
                    ty -= distance;
                }
                break;
            case 1:
                if (posY + distance < world.getHeight()) {
                    dirSet = true;
                    ty += distance;
                }
                break;
            case 2:
                if (posX - distance >= 0) {
                    dirSet = true;
                    tx -= distance;
                }
                break;
            case 3:
                if (posX + distance < world.getWidth()) {
                    dirSet = true;
                    tx += distance;
                }
                break;
            }
            if (dirSet) {
                if (world.getField(ty, tx) != null) {
                    world.getField(ty, tx).collision(this);
                }
                else {
                    world.setOrganism(posY, posX, null);
                    this.setPosY(ty);
                    this.setPosX(tx);
                    world.setOrganism(ty, tx, this);
                }
                break;
            }
        }
    }

    @Override
    public JButton getButton() {
        return icon;
    }

}
