import java.util.Comparator;

public class CompareOrganisms implements Comparator<Organism> {
    
    @Override
    public int compare(Organism o1, Organism o2) {
        if(Integer.valueOf(o2.getInitiative()).compareTo(Integer.valueOf(o1.getInitiative())) != 0) {
            return Integer.valueOf(o2.getInitiative()).compareTo(Integer.valueOf(o1.getInitiative()));
        } else return Integer.valueOf(o2.getAge()).compareTo(Integer.valueOf(o1.getAge()));
    }
    
}
