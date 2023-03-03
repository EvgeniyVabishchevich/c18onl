package test;

import test.raceSoldiers.RaceSoldier;

public class Troop {
    private RaceSoldier warrior;
    private RaceSoldier archer;
    private RaceSoldier wizard;

    public void printTroopInfo() {
        warrior.printInfo();
        archer.printInfo();
        wizard.printInfo();
    }

    public void setWarrior(RaceSoldier warrior) {
        this.warrior = warrior;
    }

    public void setArcher(RaceSoldier archer) {
        this.archer = archer;
    }

    public void setWizard(RaceSoldier wizard) {
        this.wizard = wizard;
    }
}
