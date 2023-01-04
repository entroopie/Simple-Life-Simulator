import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Fox extends Animal {

    private JButton icon;

    Fox(int posY, int posX, World world) {
        super("Fox", 'v', posY, posX, 3, 7, world);
        icon = new JButton(new ImageIcon("C:/Users/Entroopie/Desktop/javaOOP/lifeSimulator/src/fox.png"));
    }

    @Override
    public void move(int distance) {
        boolean dirSet = false;
        int direction;
        Random random = new Random();
        int ty = posY;
        int tx = posX;
        while (true) {
            ty = posY;
            tx = posX;
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
                    if (this.getStrength() >= world.getField(ty, tx).getStrength()) {
                        world.getField(ty, tx).collision(this);
                        break;
                    }
                    else {
                        if (world.getField(ty - distance, tx) != null && world.getField(ty + distance, tx) != null && world.getField(ty, tx - distance) != null
                            && world.getField(ty, tx + distance) != null) {
                            String temp = this.getSpecies() + " is not able to move!";
                            world.addComment(temp);
                            break;
                        }
                        else {
                            dirSet = false;
                        }
                    }
                }
                else {
                    world.setOrganism(posY, posX, null);
                    this.setPosY(ty);
                    this.setPosX(tx);
                    world.setOrganism(ty, tx, this);
                    break;
                }
            }
        }
    }

    @Override
    public JButton getButton() {
        return icon;
    }

}
