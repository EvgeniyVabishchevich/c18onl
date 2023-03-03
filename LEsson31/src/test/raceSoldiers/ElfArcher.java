package test.raceSoldiers;

import test.Soldiers.Archer;

public class ElfArcher extends Archer implements RaceSoldier {
    @Override
    public void tellRace() {
        System.out.println("I'm elf");
    }
}
