package com.tms.factory.factory;

import com.tms.factory.Troop;
import com.tms.factory.raceSoldiers.OrcArcher;
import com.tms.factory.raceSoldiers.OrcWizard;
import com.tms.factory.raceSoldiers.OrcWarrior;

public class OrcTroopFactory implements TroopsFactory {

    @Override
    public Troop createTroop() {
        Troop orcTroop = new Troop();
        orcTroop.setArcher(new OrcArcher());
        orcTroop.setWarrior(new OrcWarrior());
        orcTroop.setWizard(new OrcWizard());
        return orcTroop;
    }
}
