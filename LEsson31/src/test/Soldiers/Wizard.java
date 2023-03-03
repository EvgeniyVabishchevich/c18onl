package test.Soldiers;

public class Wizard implements Soldier {
    @Override
    public void tellType() {
        System.out.println("I'm wizard");
    }

    @Override
    public void tellHitType() {
        System.out.println("I can cast spells");
    }
}
