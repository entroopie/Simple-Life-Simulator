import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class God {
    
    private World world;

    God(World world) {
        this.world = world;
    }

    public void placeHumanRandomly() {
        int ry, rx;
        Random random = new Random();
        while (true) {
            ry = random.nextInt(world.getHeight());
            rx = random.nextInt(world.getWidth());
            if(world.getField(ry, rx) == null) {
                world.createOrganism(new Human(ry, rx, world));
                break;
            }
        }
    }
    
    public void create() {
        Random random = new Random();
        int sw;
        for (int i = 1; i < (world).getHeight(); i++) {
            for (int j = 1; j < (world).getWidth(); j++) {
                sw = random.nextInt(7);
                if (sw == 0) {
                    sw = random.nextInt(10);
                    switch (sw) {
                    case 0:
                        (world).createOrganism(new Sheep(i, j, (world)));
                        break;
                    case 1:
                        (world).createOrganism(new Wolf(i, j, (world)));
                        break;
                    case 2:
                        (world).createOrganism(new Belladona(i, j, (world)));
                        break;
                    case 3:
                        (world).createOrganism(new SowThistle(i, j, (world)));
                        break;
                    case 4:
                        (world).createOrganism(new Grass(i, j, (world)));
                        break;
                    case 5:
                        (world).createOrganism(new Turtle(i, j, (world)));
                        break;
                    case 6:
                        (world).createOrganism(new Guarana(i, j, (world)));
                        break;
                    case 7:
                        (world).createOrganism(new Sosnowsky(i, j, (world)));
                        break;
                    case 8:
                        (world).createOrganism(new Antelope(i, j, (world)));
                        break;
                    case 9:
                        (world).createOrganism(new Fox(i, j, (world)));
                        break;
                    }
                }
            }
        }
    }

    public void save() {
        try {
            FileWriter saveFile = new FileWriter("save.txt");
            FileWriter saveFileW = new FileWriter("saveW.txt");
            saveFileW.write(Integer.toString(world.getHeight()) + ";" + Integer.toString(world.getWidth()) + ";" +
            Integer.toString(world.getTurn()));
            for(int i = 0; i < world.getPopulation(); i++) {
                saveFile.write(world.getOrganism(i).getInformation()+"\r\n");
            }
            saveFile.close();
            saveFileW.close();
            System.out.println("Successfully wrote to the files.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

        
    public void loadOrganism(String name, int posY, int posX, int strength, int initiative, int age, int cooldown) {
        if (name.equals("Wolf")) {
            world.createOrganism(new Wolf(posY, posX, world));
        }
        else if (name.equals("Sheep")) {
            world.createOrganism(new Sheep(posY, posX, world));
        }
        else if (name.equals("Fox")) {
            world.createOrganism(new Fox(posY, posX, world));
        }
        else if (name.equals("Antelope")) {
            world.createOrganism(new Antelope(posY, posX, world));
        }
        else if (name.equals("Turtle")) {
            world.createOrganism(new Turtle(posY, posX, world));
        }
        else if (name.equals("Grass")) {
            world.createOrganism(new Grass(posY, posX, world));
        }
        else if (name.equals("SowThistle")) {
            world.createOrganism(new SowThistle(posY, posX, world));
        }
        else if (name.equals("Belladona")) {
            world.createOrganism(new Belladona(posY, posX, world));
        }
        else if (name.equals("Guarana")) {
            world.createOrganism(new Guarana(posY, posX, world));
        }
        else if (name.equals("Sosnowsky's Hogweed")) {
            world.createOrganism(new Sosnowsky(posY, posX, world));
        }
        else if (name.equals("Human")) {
            world.createOrganism(new Human(posY, posX, world));
        }
        Organism org = world.getField(posY, posX);
        if (org != null) {
            org.setAge(age);
            org.setCooldown(cooldown);
            org.setStrength(strength);
            org.setInitiative(initiative);
        }
    }
    
    public void load() {
        int next = 0;
        String name = "", y = "", x = "", strength = "", initiative = "", age = "", cooldown = "";
        String height = "", width = "", turn = "";
        try {
            File file = new File("saveW.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                next = 0;
                for(int i = 0; i < data.length(); i++) {
                    if(data.charAt(i) == ';') {
                        i++;
                        next++;
                    }
                    switch (next) {
                        case 0:
                            height += data.charAt(i);
                            break;
                        case 1:
                            width += data.charAt(i);
                            break;
                        case 2:
                            turn += data.charAt(i);
                            break;
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        world.setTurn(Integer.parseInt(turn));
        world.setHeight(Integer.parseInt(height));
        world.setWidth(Integer.parseInt(width));
        world.reInit(Integer.parseInt(height), Integer.parseInt(width));
        try {
            File file = new File("save.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                next = 0;
                name = "";
                y = "";
                x = "";
                strength = "";
                initiative = "";
                age = "";
                cooldown = "";
                for (int i = 0; i < data.length(); i++) {
                    if (data.charAt(i) == ';') {
                        i++;
                        next++;
                    }
                    switch (next) {
                    case 0:
                        name += data.charAt(i);
                        break;
                    case 1:
                        y += data.charAt(i);
                        break;
                    case 2:
                        x += data.charAt(i);
                        break;
                    case 3:
                        strength += data.charAt(i);
                        break;
                    case 4:
                        initiative += data.charAt(i);
                        break;
                    case 5:
                        age += data.charAt(i);
                        break;
                    case 6:
                        cooldown += data.charAt(i);
                        break;
                    }
                }
                this.loadOrganism(name, Integer.parseInt(y), Integer.parseInt(x), Integer.parseInt(strength), Integer.parseInt(initiative), Integer.parseInt(age), Integer.parseInt(cooldown));
            }
            System.out.println("Successfully loaded the files.");
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public World getWorld() {
        return this.world;
    }

}
