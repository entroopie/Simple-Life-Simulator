import java.util.Random;
import javax.swing.JButton;

public class Animal extends Organism {

    Animal(String species, char sign, int posY, int posX, int strength, int initiative, World world) {
        super(species, sign, posY, posX, strength, initiative, world);
    }

    public void createChild(char sign, int posY, int posX) {
        switch(sign) {
        case '^':
            world.createOrganism(new Sheep(posY, posX, world));
            break;
        case '>':
            world.createOrganism(new Wolf(posY, posX, world));
            break;
        case 'v':
            world.createOrganism(new Fox(posY, posX, world));
            break;
        case 'O':
            world.createOrganism(new Turtle(posY, posX, world));
            break;
        case 'H':
            world.createOrganism(new Antelope(posY, posX, world));
            break;
        }
    }

    public void breed(int y, int x) {
        boolean setDir = false;
        int counter = 0;
        int direction;
        Random random = new Random();
        while(true) {
            if((world.getField(y - 1, x) != null && world.getField(y + 1, x) != null 
            && world.getField(y, x - 1) != null && world.getField(y, x + 1) != null) || counter > 14) {
                break;
            }
            else {
                direction = random.nextInt(4);
                switch(direction) {
                case 0:
                    if(y - 1 >= 0 && world.getField(y - 1, x) == null) {
                        setDir = true;
                        y--;
                    }
                    break;
                case 1:
                    if(y + 1 < world.getHeight() && world.getField(y + 1, x) == null) {
                        setDir = true;
                        y++;
                    }
                    break;
                case 2:
                    if(x - 1 >= 0 && world.getField(y, x - 1) == null) {
                        setDir = true;
                        x--;
                    }
                    break;
                case 3:
                    if(x + 1 < world.getWidth() && world.getField(y, x + 1) == null) {
                        setDir = true;
                        x++;
                    }
                    break;
                }
                if(setDir) {
                    String temp = this.species + " has made a new child!";
                    world.addComment(temp);
                    createChild(this.draw(), y, x);
                    break;
                }
            }
            counter++;
        }
    }

    public void fight(Organism attacker) {
        if (this.getStrength() <= attacker.getStrength()) {
            String temp = this.getSpecies() + " LOST the fight with " + attacker.getSpecies() + "!";
            world.addComment(temp);
            world.setOrganism(attacker.getPosY(), attacker.getPosX(), null);
            world.setOrganism(posY, posX, attacker);
            attacker.setPosY(posY);
            attacker.setPosX(posX);
            world.removeOrganism(this);
        }
        else {
            String temp = this.getSpecies() + " WON the fight with " + attacker.getSpecies() + "!";
            world.addComment(temp);
            world.setOrganism(attacker.getPosY(), attacker.getPosX(), null);
            world.removeOrganism(attacker);
        }
    }
    
    @Override
    public void collision(Organism attacker) {
        if (this.species == attacker.getSpecies() && this.age > 2 && attacker.getAge() > 2
        && this.cooldown == 0 && attacker.getCooldown() == 0 && this.species != "Human") {
        this.setCooldown(5);
        attacker.setCooldown(5);
        breed(attacker.getPosY(), attacker.getPosX());
        }
        else if (this.species != attacker.getSpecies()) fight(attacker);
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
    public void action() {
        this.move(1);
    }

    @Override
    public JButton getButton() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
