package test.raceSoldiers;

import test.soldiers.Warrior;

public class OrcWarrior extends Warrior implements RaceSoldier {
    @Override
    public void tellRace() {
        System.out.println("I'm orc");
    }
}
