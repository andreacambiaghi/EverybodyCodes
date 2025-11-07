package ebc2025;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Map<Integer, Double> turns = new HashMap<>();
        
        for (int i = 1; i < gears.size() - 1; i += 2) {

            double rate = (double) gears.get(i-1) / gears.get(i);
            turns.put(i, turns.getOrDefault(i, 1.0) * rate * turns.getOrDefault(i-1, 1.0));
            turns.put(i + 1, turns.get(i));

        }//for i

        solution((long) ((gears.get(gears.size() - 2) / (double) gears.getLast()) * turns.get(gears.size() - 2) * 100.0));

    }//solve

}//class
