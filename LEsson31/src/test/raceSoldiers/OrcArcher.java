package test.raceSoldiers;

import test.Soldiers.Archer;

public class OrcArcher extends Archer implements RaceSoldier {
    @Override
    public void tellRace() {
        System.out.println("I'm orc");
    }
}
