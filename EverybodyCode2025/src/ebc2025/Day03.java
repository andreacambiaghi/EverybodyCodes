package ebc2025;

import java.util.*;

public class Day03 extends EBCUtils {

    public Day03() { super("3"); }

    @Override
    void solve(List<String> part1, List<String> part2, List<String> part3) {

        Set<Integer> cratesUnique = new HashSet<>(convertToInts(List.of(part1.getFirst().split(","))));
        int largest = 0;
        for(Integer size : cratesUnique)
            largest += size;

        solution(largest);

        cratesUnique = new HashSet<>(convertToInts(List.of(part2.getFirst().split(","))));
        List<Integer> cratesUniqueOrderd = new ArrayList<>(cratesUnique);
        Collections.sort(cratesUniqueOrderd);

        largest = 0;
        for(int i = 0; i < 20; i++)
            largest += cratesUniqueOrderd.get(i);

        solution(largest);

        Map<Integer, Integer> countDuplicates = new HashMap<>();
        List<Integer> crates = convertToInts(List.of(part3.getFirst().split(",")));
        for(Integer crate : crates)
            countDuplicates.put(crate, countDuplicates.getOrDefault(crate, 0)+1);

        int sets = Collections.max(countDuplicates.values());
        solution(sets);

    }//solve

}//class
