import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Belladona extends Plant {
    
    private JButton icon;

    public Belladona(int posY, int posX, World world) {
        super("Belladona", 'P', posY, posX, 99, 0, world);
        icon = new JButton(new ImageIcon("C:/Users/Entroopie/Desktop/javaOOP/lifeSimulator/src/belladona.png"));
    }

    @Override
    public void collision(Organism attacker) {
        String temp = this.getSpecies() + " POISONED to death " + attacker.getSpecies() + "!";
        world.addComment(temp);
        world.setOrganism(attacker.getPosY(), attacker.getPosX(), null);
        world.setOrganism(posY, posX, null);
        world.removeOrganism(attacker);
        world.removeOrganism(this);
    }

    @Override
    public JButton getButton() {
        return icon;
    }

}
