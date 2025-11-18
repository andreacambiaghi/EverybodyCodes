package ebc2025;

import java.util.List;

public class Day11 extends EBCUtils {

    public Day11() { super("11"); }

    @Override
    void solve(List<String> part1, List<String> part2, List<String> part3) {

        int ROUND = 10;

        List<Integer> ducks = convertToInts(part1);

        int r;
        for(r = 0; r < ROUND; r++) {
            boolean change = firstPhase(ducks);
            if(!change)
                break;
        }//for r

        for( ; r < ROUND; r++)
            secondPhase(ducks);

        solution(checksum(ducks));

        ducks = convertToInts(part2);
        int rounds = 0;

        // TODO Part 3

    }//solve

    boolean firstPhase(List<Integer> ducks) {

        boolean change = false;

        for(int i = 0; i < ducks.size() - 1; i++) {

            if(ducks.get(i) > ducks.get(i+1)) {
                change = true;
                ducks.set(i, ducks.get(i)-1);
                ducks.set(i+1, ducks.get(i+1)+1);
            }//if

        }//for i

        return change;

    }//firstPhase

    boolean secondPhase(List<Integer> ducks) {

        boolean change = false;

        for(int i = 0; i < ducks.size() - 1; i++) {

            if(ducks.get(i) < ducks.get(i+1)) {
                change = true;
                ducks.set(i, ducks.get(i)+1);
                ducks.set(i+1, ducks.get(i+1)-1);
            }//if

        }//for i

        return change;

    }//secondPhase

    int checksum(List<Integer> ducks) {

        int checksum = 0;
        for(int i = 0; i < ducks.size(); i++)
            checksum += (i+1) * ducks.get(i);

        return checksum;

    }//checksum

}//class
