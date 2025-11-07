package ebc2025;

import java.util.ArrayList;
import java.util.List;

public class Day04 extends EBCUtils {

    public Day04() { super("4"); }

    @Override
    void solve(List<String> part1, List<String> part2, List<String> part3) {

        List<Integer> gears = convertToInts(part1);
        solution((int) ((float) gears.getFirst() / gears.getLast() * 2025));

        gears = convertToInts(part2);
        solution((long) Math.ceil((double) gears.getLast() / gears.getFirst() * 10000000000000L));

        gears = new ArrayList<>();
        for(String row : part3)
            gears.addAll(convertToInts(new ArrayList<>(List.of(row.split("\\|")))));

        double turns = 1.0;
        for(int i = 1; i < gears.size(); i += 2)
            turns *= (double) gears.get(i - 1) / gears.get(i);

        solution((long) (turns*100));

    }//solve

}//class
