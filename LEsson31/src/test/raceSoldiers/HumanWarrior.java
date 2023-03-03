package test.raceSoldiers;

import test.Soldiers.Warrior;

public class HumanWarrior extends Warrior implements RaceSoldier {
    @Override
    public void tellRace() {
        System.out.println("I'm human");
    }
}
