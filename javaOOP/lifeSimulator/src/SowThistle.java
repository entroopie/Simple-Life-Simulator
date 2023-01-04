import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SowThistle extends Plant {

    private JButton icon;

    public SowThistle(int posY, int posX, World world) {
        super("SowThistle", '%', posY, posX, 0, 0, world);
        icon = new JButton(new ImageIcon("C:/Users/Entroopie/Desktop/javaOOP/lifeSimulator/src/SowThistle.png"));
    }
    
    @Override
    public void action() {
        for(int i = 0; i < 3; i++) sow();
    }

    @Override
    public JButton getButton() {
        return icon;
    }

}
