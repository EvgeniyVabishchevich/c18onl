package test.factory;

import test.Troop;
import test.raceSoldiers.ElfArcher;
import test.raceSoldiers.ElfWarrior;
import test.raceSoldiers.ElfWizard;

public class ElfTroopFactory implements TroopsFactory {
    @Override
    public Troop createTroop() {
        Troop elfTroop = new Troop();
        elfTroop.setWizard(new ElfWizard());
        elfTroop.setArcher(new ElfArcher());
        elfTroop.setWarrior(new ElfWarrior());
        return elfTroop;
    }
}
