import com.tms.factory.Troop;
import com.tms.factory.factory.ElfTroopFactory;
import com.tms.factory.factory.HumanTroopFactory;
import com.tms.factory.factory.OrcTroopFactory;
import com.tms.factory.factory.TroopsFactory;
import com.tms.factory.raceSoldiers.RaceSoldier;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        HumanTroopFactory humanTroopFactory = new HumanTroopFactory();
        OrcTroopFactory orcTroopFactory = new OrcTroopFactory();
        ElfTroopFactory elfTroopFactory = new ElfTroopFactory();

        List<Troop> troops = new ArrayList<>();

        loop : while(true){
            System.out.println("Select race : " +
                    "\n1. Humans." +
                    "\n2. Orcs." +
                    "\n3. Elves." +
                    "\n0. Finish.");

            int selected = in.nextInt();

            switch (selected) {
                case 1 -> troops.add(humanTroopFactory.createTroop());
                case 2 -> troops.add(orcTroopFactory.createTroop());
                case 3 -> troops.add(elfTroopFactory.createTroop());
                case 0 -> {
                    break loop;
                }
            }
        }

        troops.forEach(Troop::printTroopInfo);
    }
}