package test.raceSoldiers;

import test.Soldiers.Archer;

public class HumanArcher extends Archer implements RaceSoldier {
    @Override
    public void tellRace() {
        System.out.println("I'm human");
    }
}
