import test.Troop;
import test.factory.ElfTroopFactory;
import test.factory.HumanTroopFactory;
import test.factory.OrcTroopFactory;
import test.factory.TroopsFactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Select race : " +
                "\n1. Humans." +
                "\n2. Orcs." +
                "\n3. Elves.");

        Scanner in = new Scanner(System.in);
        int selected = in.nextInt();

        switch (selected) {
            case 1:
                new HumanTroopFactory().createTroop().printTroopInfo();
                break;
            case 2:
                new OrcTroopFactory().createTroop().printTroopInfo();
                break;
            case 3:
                new ElfTroopFactory().createTroop().printTroopInfo();
                break;
        }
    }
}