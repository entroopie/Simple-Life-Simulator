import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Wolf extends Animal {

    private JButton icon;

    Wolf(int posY, int posX, World world) {
        super("Wolf", '>', posY, posX, 9, 5, world);
        icon = new JButton(new ImageIcon("C:/Users/Entroopie/Desktop/javaOOP/lifeSimulator/src/wolf.png"));
    }

    @Override
    public JButton getButton() {
        return icon;
    }
    
}
