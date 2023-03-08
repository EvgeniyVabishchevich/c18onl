package com.tms.factory.raceSoldiers;

import com.tms.factory.soldiers.Wizard;

public class ElfWizard extends Wizard implements RaceSoldier {
    @Override
    public void tellRace() {
        System.out.println("I'm elf wizard");
    }
}
