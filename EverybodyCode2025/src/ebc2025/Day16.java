package ebc2025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day16 extends EBCUtils {

    public Day16() { super("16"); }

    @Override
    void solve(List<String> part1, List<String> part2, List<String> part3) {

        List<Integer> wall = new ArrayList<>(Collections.nCopies(90, 0));
        for(Integer spell : convertToInts(List.of(part1.getFirst().split(","))))
            for(int i = spell; i <= 90; i += spell)
                wall.set(i-1, wall.get(i-1) + 1);

        int length  = 0;
        for(int spell : wall)
            length += spell;

        solution(length);

        wall = convertToInts(List.of(part2.getFirst().split(",")));
        List<Integer> spells = new ArrayList<>();
        for(int i = 1; i <= wall.size(); i++) {
            while(wall.get(i-1) > 0) {
                for(int j = i; j <= wall.size(); j+=i) {
                    wall.set(j-1, wall.get(j-1) - 1);
                }//for j
                spells.add(i);
            }//while
        }//for

        long fragment = 1L;
        for(int spell : spells)
            fragment *= spell;

        solution(fragment);

        wall = convertToInts(List.of(part3.getFirst().split(",")));
        spells = new ArrayList<>();
        for(int i = 1; i <= wall.size(); i++) {
            while(wall.get(i-1) > 0) {
                for(int j = i; j <= wall.size(); j+=i) {
                    wall.set(j-1, wall.get(j-1) - 1);
                }//for j
                spells.add(i);
            }//while
        }//for

        long blocks = 202520252025000L;
        long low = 0L;
        long high = blocks;

        while(low < high) {
            long mid = (low + high) / 2;

            long cnt = 0L;
            for(int spell : spells)
                cnt += mid / spell;

            if(cnt == blocks) break;
            if(cnt > blocks) high = mid - 1;
            else low = mid + 1;

        }//while

        solution(low);

    }//solve

}//class
