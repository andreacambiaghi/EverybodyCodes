package ebc2025;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 extends EBCUtils {

    public Day14() { super("14"); }

    @Override
    void solve(List<String> part1, List<String> part2, List<String> part3) {

        solution(simulate(part1, 10));
        solution(simulate(part2, 2025));

        Boolean[][] floor = new Boolean[34][34];
        for(int i = 0; i < floor.length; i++)
            Arrays.fill(floor[i], Boolean.FALSE);

        Boolean[][] pattern = new Boolean[part3.size()][part3.get(0).length()];
        for(int i = 0; i < part3.size(); i++)
            for(int j = 0; j < part3.get(i).length(); j++)
                pattern[i][j] = part3.get(i).charAt(j) == '#' ? Boolean.TRUE : Boolean.FALSE;

        long finalActiveTails = 0;
        Map<String, List<Long>> cache = new HashMap<>();
        long r;
        for(r = 0; r < 1000000000; r++) {
            Boolean[][] copyFloor = Arrays.stream(floor).map(Boolean[]::clone).toArray(Boolean[][]::new);
            for(int i = 0; i < floor.length; i++) {
                for(int j = 0; j < floor[i].length; j++) {
                    int diag = countActiveDiag(copyFloor, i, j);
                    if(copyFloor[i][j])
                        floor[i][j] = diag % 2 == 1 ? Boolean.TRUE : Boolean.FALSE;
                    else
                        floor[i][j] = diag % 2 == 0 ? Boolean.TRUE : Boolean.FALSE;
                }//for j
            }//for i
            if(containPattern(floor, pattern))
                finalActiveTails += countActive(floor);
            if(cache.containsKey(matrixToString(floor)))
                break;
            cache.put(matrixToString(floor), List.of(finalActiveTails, r));
        }//for r

        List<Long> old = cache.get(matrixToString(floor));
        long firstOccurrenceRound = old.get(1);
        long sumBeforeCycle = old.get(0);

        long cycleLength = r - firstOccurrenceRound;
        long sumInCycle = finalActiveTails - sumBeforeCycle;

        long remainingCycles = (1000000000L - firstOccurrenceRound) / cycleLength;
        finalActiveTails = sumBeforeCycle + sumInCycle * remainingCycles;

        for(int k = 0; k < (1000000000L - firstOccurrenceRound) % cycleLength; k++) {
            Boolean[][] copyFloor = Arrays.stream(floor)
                    .map(Boolean[]::clone)
                    .toArray(Boolean[][]::new);
            for(int i = 0; i < floor.length; i++) {
                for(int j = 0; j < floor[i].length; j++) {
                    int diag = countActiveDiag(copyFloor, i, j);
                    if (copyFloor[i][j])
                        floor[i][j] = diag % 2 == 1 ? Boolean.TRUE : Boolean.FALSE;
                    else
                        floor[i][j] = diag % 2 == 0 ? Boolean.TRUE : Boolean.FALSE;
                }//for j
            }//for i
            if(containPattern(floor, pattern))
                finalActiveTails += countActive(floor);
        }//for r

        solution(finalActiveTails);

    }//solve

    public static String matrixToString(Boolean[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for(Boolean[] row : matrix) {
            for(Boolean cell : row) {
                sb.append(Boolean.TRUE.equals(cell) ? '#' : '.');
            }//for
            sb.append('\n');
        }//for
        return sb.toString();
    }//matrixToString

    int simulate(List<String> input, int rounds) {

        Boolean[][] floor = new Boolean[input.size()][input.get(0).length()];
        for(int i = 0; i < input.size(); i++)
            for(int j = 0; j < input.get(i).length(); j++)
                floor[i][j] = input.get(i).charAt(j) == '#' ? Boolean.TRUE : Boolean.FALSE;

        int finalActiveTails = 0;

        for(int r = 0; r < rounds; r++) {
            Boolean[][] copyFloor = Arrays.stream(floor)
                    .map(Boolean[]::clone)
                    .toArray(Boolean[][]::new);
            for(int i = 0; i < floor.length; i++) {
                for(int j = 0; j < floor[i].length; j++) {
                    int diag = countActiveDiag(copyFloor, i, j);
                    if(copyFloor[i][j])
                        floor[i][j] = diag % 2 == 1 ? Boolean.TRUE : Boolean.FALSE;
                    else
                        floor[i][j] = diag % 2 == 0 ? Boolean.TRUE : Boolean.FALSE;
                }//for j
            }//for i
            finalActiveTails += countActive(floor);
        }//for r

        return finalActiveTails;

    }//simulate

    int countActiveDiag(Boolean[][] floor, int i, int j) {

        int count = 0;
        int n = floor.length;
        int m = floor[0].length;

        if(i - 1 >= 0 && j - 1 >= 0 && floor[i - 1][j - 1]) count++;
        if(i - 1 >= 0 && j + 1 < m && floor[i - 1][j + 1]) count++;
        if(i + 1 < n && j - 1 >= 0 && floor[i + 1][j - 1]) count++;
        if(i + 1 < n && j + 1 < m && floor[i + 1][j + 1]) count++;

        return count;

    }//countActiveDiag

    int countActive(Boolean[][] floor) {

        int count = 0;

        for(Boolean[] row : floor)
            for (Boolean b : row)
                count += b ? 1 : 0;

        return count;

    }//countActive

    boolean containPattern(Boolean[][] floor, Boolean[][] pattern) {

        for(int i = floor.length/2 - pattern.length/2, k = 0; i < floor.length/2 + pattern.length/2; i++, k++)
            for(int j = floor.length/2 - pattern[0].length/2, h = 0; j < floor.length/2 + pattern[0].length/2; j++, h++)
                if(floor[i][j] != pattern[k][h])
                    return false;

        return true;

    }//containPattern

}//class
