package test.Soldiers;

public class Archer implements Soldier {
    @Override
    public void tellType() {
        System.out.println("I'm archer");
    }

    @Override
    public void tellHitType() {
        System.out.println("I can shoot a bow");
    }
}
