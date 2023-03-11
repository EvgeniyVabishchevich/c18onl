package com.tms.factory.factory;

import com.tms.factory.Troop;
import com.tms.factory.race_soldiers.ElfArcher;
import com.tms.factory.race_soldiers.ElfWizard;
import com.tms.factory.race_soldiers.ElfWarrior;

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
