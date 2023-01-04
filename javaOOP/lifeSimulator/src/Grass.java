import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Grass extends Plant {

    private JButton icon;

    public Grass(int posY, int posX, World world) {
        super("Grass", 'M', posY, posX, 0, 0, world);
        icon = new JButton(new ImageIcon("C:/Users/Entroopie/Desktop/javaOOP/lifeSimulator/src/grass.png"));
    }
    
    @Override
    public JButton getButton() {
        return icon;
    }

}
