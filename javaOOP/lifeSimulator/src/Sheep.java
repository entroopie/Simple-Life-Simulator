import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Sheep extends Animal {

    private JButton icon;

    Sheep(int posY, int posX, World world) {
        super("Sheep", '^', posY, posX, 4, 4, world);
        icon = new JButton(new ImageIcon("C:/Users/Entroopie/Desktop/javaOOP/lifeSimulator/src/Sheep.png"));
    }

    @Override
    public JButton getButton() {
        return icon;
    }
    
}
