import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Guarana extends Plant {
    
    private JButton icon;

    public Guarana(int posY, int posX, World world) {
        super("Guarana", 'I', posY, posX, 0, 0, world);
        icon = new JButton(new ImageIcon("C:/Users/Entroopie/Desktop/javaOOP/lifeSimulator/src/guarana.png"));
    }

    @Override
    public void collision(Organism attacker) {
        String temp = this.getSpecies() + " has been EATEN and BUFFED " + attacker.getSpecies() + "!";
        world.addComment(temp);
        world.setOrganism(attacker.getPosY(), attacker.getPosX(), null);
        world.setOrganism(posY, posX, attacker);
        attacker.setPosY(posY);
        attacker.setPosX(posX);
        world.removeOrganism(this);
        attacker.gainStrength();
    }

    @Override
    public JButton getButton() {
        return icon;
    }

}
