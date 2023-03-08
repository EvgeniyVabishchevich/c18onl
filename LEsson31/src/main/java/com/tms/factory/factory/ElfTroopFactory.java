package com.tms.factory.factory;

import com.tms.factory.Troop;
import com.tms.factory.raceSoldiers.ElfArcher;
import com.tms.factory.raceSoldiers.ElfWizard;
import com.tms.factory.raceSoldiers.ElfWarrior;

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
