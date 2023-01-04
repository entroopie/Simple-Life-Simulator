import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Antelope extends Animal {
        
    private JButton icon;

    Antelope(int posY, int posX, World world) {
        super("Antelope", 'H', posY, posX, 4, 4, world);
        icon = new JButton(new ImageIcon("C:/Users/Entroopie/Desktop/javaOOP/lifeSimulator/src/anthelope.png"));
    }

    @Override
    public void collision(Organism attacker) {
        if (this.species == attacker.getSpecies() && this.age > 2 && attacker.getAge() > 2
        && this.cooldown == 0 && attacker.getCooldown() == 0 && this.species != "Human") {
            this.setCooldown(5);
            attacker.setCooldown(5);
            breed(attacker.getPosY(), attacker.getPosX());
        }
        else if (this.species != attacker.getSpecies()) {
            Random random = new Random();
            int flee = random.nextInt(2);
            if (flee == 1) fight(attacker);
            else {
                String temp = this.getSpecies() + " FLED from " + attacker.getSpecies() + "!";
                world.addComment(temp);
                world.setOrganism(this.getPosY(), this.getPosX(), null);
                this.setPosY(attacker.getPosY());
                this.setPosX(attacker.getPosX());
                move(2);
            }
        }
    }

    @Override
    public void action() {
        move(2);
    }

    @Override
    public JButton getButton() {
        return icon;
    }
}
