import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Font;
import java.awt.event.*;

public class Menu implements ActionListener, ChangeListener {

    private static final int SIZE = 500;
    private JFrame frame;
    private JButton newSimulation;
    private JButton load;
    private JSlider size;
    private JLabel currSize;

    public static void main(String[] args) throws Exception {
        new Menu();
    }

    public Menu() {
        size = new JSlider(2,20, 10);
        size.addChangeListener(this);
        currSize = new JLabel("Current size: "+size.getValue());
        size.setPaintTicks(true);
        size.setPaintLabels(true);
        size.setPaintTrack(true);
        size.setMajorTickSpacing(1);
        frame = new JFrame("Simple Life Simulator | Author: Marcel Boxberger");
        newSimulation = new JButton("New simulation");
        load = new JButton("Load simulation"); 
        frame.setSize(SIZE, SIZE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        newSimulation.setBounds(160,250,180,30);
        load.setBounds(160,300,180,30);
        size.setBounds(50,50,400,70);
        currSize.setBounds(50,140,400,30);
        currSize.setFont(new Font("Serif",Font.PLAIN, 24));
        frame.add(currSize);
        frame.add(size);
        frame.add(newSimulation);
        frame.add(load);
        frame.setVisible(true);
        newSimulation.addActionListener(this);
        load.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(source == newSimulation) {
            frame.dispose();
            new Panel(size.getValue(), false);
        } 
        else if(source == load) {
            frame.dispose();
            new Panel(size.getValue(), true);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        currSize.setText("Current size: "+size.getValue());
    }

}
