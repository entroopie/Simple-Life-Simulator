import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Sosnowsky extends Plant {

    private JButton icon;

    public Sosnowsky(int posY, int posX, World world) {
        super("Sosnowsky's Hogweed", 'X', posY, posX, 10, 0, world);
        icon = new JButton(new ImageIcon("C:/Users/Entroopie/Desktop/javaOOP/lifeSimulator/src/sosnowsky.png"));
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
    public void action() {
        int west = Math.max(posX - 1, 0);
        int east = Math.min(posX + 1, world.getWidth() - 1);
        int north = Math.max(posY - 1, 0);
        int south = Math.min(posY + 1, world.getHeight() - 1);
    
        if (world.getField(north, posX) != null && world.getField(north, posX) != this && !(world.getField(north, posX) instanceof Plant)) {
            String temp = this.getSpecies() + " DEVOURED " + world.getField(north, posX).getSpecies() + "!";
            world.addComment(temp);
            world.removeOrganism(world.getField(north, posX));
            world.setOrganism(north, posX, null);
        }
        if (world.getField(south, posX) != null && world.getField(south, posX) != this && !(world.getField(south, posX) instanceof Plant)) {
            String temp = this.getSpecies() + " DEVOURED " + world.getField(south, posX).getSpecies() + "!";
            world.addComment(temp);
            world.removeOrganism(world.getField(south, posX));
            world.setOrganism(south, posX, null);
        }
        if (world.getField(posY, west) != null && world.getField(posY, west) != this && !(world.getField(posY, west) instanceof Plant)) {
            String temp = this.getSpecies() + " DEVOURED " + world.getField(posY, west).getSpecies() + "!";
            world.addComment(temp);
            world.removeOrganism(world.getField(posY, west));
            world.setOrganism(posY, west, null);
        }
        if (world.getField(posY, east) != null && world.getField(posY, east) != this && !(world.getField(posY, east) instanceof Plant)) {
            String temp = this.getSpecies() + " DEVOURED " + world.getField(posY, east).getSpecies() + "!";
            world.addComment(temp);
            world.removeOrganism(world.getField(posY, east));
            world.setOrganism(posY, east, null);
        }
        sow();
    }

    @Override
    public JButton getButton() {
        return icon;
    }

}
