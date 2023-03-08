package com.tms.factory.race_soldiers;

import com.tms.factory.soldiers.Wizard;

public class OrcWizard extends Wizard implements RaceSoldier {
    @Override
    public void tellRace() {
        System.out.println("I'm orc wizard");
    }
}
