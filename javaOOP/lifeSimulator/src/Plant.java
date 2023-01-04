import java.util.Random;
import javax.swing.JButton;

public class Plant extends Organism {

    public Plant(String species, char sign, int posY, int posX, int strength, int initiative, World world) {
        super(species, sign, posY, posX, strength, initiative, world);
    }

    public void createPlant(char sign, int posY, int posX) {
        switch (sign) {
        case 'M':
            world.createOrganism(new Grass(posY, posX, world));
            break;
        case 'P':
            world.createOrganism(new Belladona(posY, posX, world));
            break;
        case 'I':
            world.createOrganism(new Guarana(posY, posX, world));
            break;
        case 'X':
            world.createOrganism(new Sosnowsky(posY, posX, world));
            break;
        case '%':
            world.createOrganism(new SowThistle(posY, posX, world));
            break;
        }
    }
    
    public void collision(Organism attacker) {
            String temp = this.getSpecies() + " has been EATEN by " + attacker.getSpecies() + "!";
            world.addComment(temp);
            world.setOrganism(attacker.getPosY(), attacker.getPosX(), null);
            world.setOrganism(posY, posX, attacker);
            attacker.setPosY(posY);
            attacker.setPosX(posX);
            world.removeOrganism(this);
    }
    
    public void sow() {
        Random random = new Random();
        int sw = random.nextInt(50);
        if (sw == 0) {
            int counter = 0;
            int y = this.getPosY();
            int x = this.getPosX();
            Boolean setDir = false;
            int direction;
            while (true) {
                if ((world.getField(y - 1, x) != null && world.getField(y + 1, x) != null && world.getField(y, x - 1) != null
                    && world.getField(y, x + 1) != null) || counter > 14) {
                    break;
                }
                direction = random.nextInt(4);
                switch (direction) {
                case 0:
                    if (y - 1 >= 0 && world.getField(y - 1, x) == null) {
                        setDir = true;
                        y--;
                    }
                    break;
                case 1:
                    if (y + 1 < world.getHeight() && world.getField(y + 1, x) == null) {
                        setDir = true;
                        y++;
                    }
                    break;
                case 2:
                    if (x - 1 >= 0 && world.getField(y, x - 1) == null) {
                        setDir = true;
                        x--;
                    }
                    break;
                case 3:
                    if (x + 1 < world.getWidth() && world.getField(y, x + 1) == null) {
                        setDir = true;
                        x++;
                    }
                    break;
                }
                if (setDir) {
                    String temp = this.species + " has succesfully sown!";
                    world.addComment(temp);
                    createPlant(this.draw(), y, x);
                    break;
                }
                counter++;
            }
        }
    }
    
    public void action() {
        sow();
    }

    @Override
    public JButton getButton() {
        return null;
    }
    
}
