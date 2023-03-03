package test.raceSoldiers;

import test.Soldiers.Wizard;

public class HumanWizard extends Wizard implements RaceSoldier {
    @Override
    public void tellRace() {
        System.out.println("I'm human");
    }
}
