package test.raceSoldiers;

import test.Soldiers.Warrior;

public class ElfWarrior extends Warrior implements RaceSoldier {
    @Override
    public void tellRace() {
        System.out.println("I'm elf");
    }
}
