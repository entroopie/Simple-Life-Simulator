import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Human extends Animal {

    private JButton icon;
    private boolean potion;

    Human(int posY, int posX, World world) {
        super("Human", 'T', posY, posX, 5, 4, world);
        this.potion = false;
        icon = new JButton(new ImageIcon("C:/Users/Entroopie/Desktop/javaOOP/lifeSimulator/src/human.png"));
    }

    public void strengthPotion() {
        String temp = "Human wyzerowal potion and his strength is 10 for 5 turns!";
        world.addComment(temp);
        this.setCooldown(10 - this.strength);
        this.strength = 10;
        this.potion = true;
    }
    
    public void move(int direction) {
        int ty, tx;
        ty = posY;
        tx = posX;
        switch (direction) {
        case 2:
            if (posY + 1 < world.getHeight()) {
                ty++;
            }
            break;
        case 1:
            if (posY - 1 >= 0) {
                ty--;
            }
            break;
        case 3:
            if (posX - 1 >= 0) {
                tx--;
            }
            break;
        case 4:
            if (posX + 1 < world.getWidth()) {
                tx++;
            }
            break;
        default:
            String temp = "Human doesn't move!";
            world.addComment(temp);
            break;
        }
        if (world.getField(ty, tx) != null) {
            world.getField(ty, tx).collision(this);
        }
        else {
            world.setOrganism(posY, posX, null);
            this.setPosY(ty);
            this.setPosX(tx);
            world.setOrganism(ty, tx, this);
        }
    }
    
    public void action() {
        if (this.cooldown == 0 && this.potion) {
            String temp = "The strength potion worn off! Strength is back to normal.";
            world.addComment(temp);
            this.strength--;
            this.setCooldown(5);
            this.potion = false;
        }
        else if (this.potion) this.strength--;
        if (world.getHumanDir() == 5 && this.cooldown == 0 && !this.potion) strengthPotion();
        else move(world.getHumanDir());
    }

    @Override
    public JButton getButton() {
        return icon;
    }

}
