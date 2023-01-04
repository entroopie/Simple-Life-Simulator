import javax.swing.JButton;

public abstract class Organism {
    protected int age;
    protected int strength;
    protected int initiative;
    protected int cooldown;
    protected int posY;
    protected int posX;
    protected char sign;
    protected String species;
    protected World world;

    public abstract void action();
    public abstract void collision(Organism attacker);
    public abstract JButton getButton();

    public Organism(String species, char sign, int posY, int posX, int strength, int initiative, World world) {
        this.species = species;
        this.sign = sign;
        this.posY = posY;
        this.posX = posX;
        this.strength = strength;
        this.initiative = initiative;
        this.world = world;
        this.cooldown = 0;
        this.age = 0;
    }

    public char draw() {
        return sign;
    }

    public int getPosY() {
        return posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getAge() {
        return age;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getInitiative() {
        return initiative;
    }

    public String getSpecies() {
        return species;
    }

    public int getStrength() {
        return strength;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void advanceAge() {
        this.age++;
    }

    public void advanceCooldown() {
        if(this.cooldown > 0) this.cooldown--;
    }

    public void gainStrength() {
        this.strength += 3;
    }

    public String getInformation() {
        return this.getSpecies() + ";" + Integer.toString(this.getPosY()) + ";" + Integer.toString(this.getPosX()) +
        ";" + Integer.toString(this.getStrength()) + ";" + Integer.toString(this.getInitiative()) + ";" +
        Integer.toString(this.getAge()) + ";" + Integer.toString(this.getCooldown());
    }
    
}
