package test.Soldiers;

public class Warrior implements Soldier {
    @Override
    public void tellType() {
        System.out.println("I'm warrior");
    }

    @Override
    public void tellHitType() {
        System.out.println("I can hit with a sword");
    }
}
