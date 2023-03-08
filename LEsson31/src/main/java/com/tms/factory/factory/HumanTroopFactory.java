package com.tms.factory.factory;

import com.tms.factory.Troop;
import com.tms.factory.raceSoldiers.HumanArcher;
import com.tms.factory.raceSoldiers.HumanWarrior;
import com.tms.factory.raceSoldiers.HumanWizard;

public class HumanTroopFactory implements TroopsFactory {
    @Override
    public Troop createTroop() {
        Troop humanTroop = new Troop();
        humanTroop.setArcher(new HumanArcher());
        humanTroop.setWarrior(new HumanWarrior());
        humanTroop.setWizard(new HumanWizard());
        return humanTroop;
    }
}
