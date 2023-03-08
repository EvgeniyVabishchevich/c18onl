package test.raceSoldiers;

import test.soldiers.Wizard;

public class ElfWizard extends Wizard implements RaceSoldier {
    @Override
    public void tellRace() {
        System.out.println("I'm elf");
    }
}
