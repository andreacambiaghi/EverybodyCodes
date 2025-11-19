package ebc2025;

import java.util.*;

public class Day12 extends EBCUtils {

    public Day12() { super("12"); }

    private record Barrel(int value, boolean destroy){}

    @Override
    void solve(List<String> part1, List<String> part2, List<String> part3) {

        Barrel[][] barrels = new Barrel[part1.size()][part1.getFirst().length()];
        for(int i = 0; i < part1.size(); i++) {
            for(int j = 0; j < part1.get(i).length(); j++) {
                barrels[i][j] = new Barrel(part1.get(i).charAt(j) - '0', false);
            }//for j
        }//for i

        ignite(barrels, 0, 0);
        solution(countDestroyed(barrels));


        barrels = new Barrel[part2.size()][part2.getFirst().length()];
        for(int i = 0; i < part2.size(); i++) {
            for(int j = 0; j < part2.get(i).length(); j++) {
                barrels[i][j] = new Barrel(part2.get(i).charAt(j) - '0', false);
            }//for j
        }//for i

        ignite(barrels, 0, 0);
        ignite(barrels, barrels.length-1, barrels[0].length-1);
        solution(countDestroyed(barrels));


        barrels = new Barrel[part3.size()][part3.getFirst().length()];
        for(int i = 0; i < part3.size(); i++) {
            for(int j = 0; j < part3.get(i).length(); j++) {
                barrels[i][j] = new Barrel(part3.get(i).charAt(j) - '0', false);
            }//for j
        }//for i

        int base = 0;

        int[] pos = chooseBest(barrels, 0);
        ignite(barrels, pos[0], pos[1]);

        base = countDestroyed(barrels);
        pos = chooseBest(barrels, base);
        ignite(barrels, pos[0], pos[1]);

        base = countDestroyed(barrels);
        pos = chooseBest(barrels, base);
        ignite(barrels, pos[0], pos[1]);

        solution(countDestroyed(barrels));

    }//solve

    int[] chooseBest(Barrel[][] barrels, int base) {

        int best_i = 0, best_j = 0, best_cnt = -1;

        for(int i = 0; i < barrels.length; i++) {
            for(int j = 0; j < barrels[i].length; j++) {

                if(base > 0 && barrels[i][j].destroy) continue;

                Barrel[][] tmp = deepCopy(barrels);
                ignite(tmp, i, j);

                int c = countDestroyed(tmp) - base;
                if(c > best_cnt) {
                    best_cnt = c;
                    best_i = i;
                    best_j = j;
                }//if
            }//for j
        }//for i

        return new int[]{best_i, best_j};

    }//chooseBest

    void ignite(Barrel[][] barrels, int i, int j) {

        barrels[i][j] = new Barrel(barrels[i][j].value, true);

        if(i > 0 && !barrels[i-1][j].destroy && barrels[i-1][j].value <= barrels[i][j].value)
            ignite(barrels, i - 1, j);

        if(i < barrels.length - 1 && !barrels[i+1][j].destroy && barrels[i+1][j].value <= barrels[i][j].value)
            ignite(barrels, i + 1, j);

        if(j > 0 && !barrels[i][j-1].destroy && barrels[i][j-1].value <= barrels[i][j].value)
            ignite(barrels, i, j - 1);

        if(j < barrels[0].length - 1 && !barrels[i][j+1].destroy && barrels[i][j+1].value <= barrels[i][j].value)
            ignite(barrels, i, j + 1);

    }//ignite

    int countDestroyed(Barrel[][] barrels) {

        int cnt = 0;
        for(Barrel[] row : barrels)
            for(Barrel barrel : row)
                cnt += barrel.destroy ? 1 : 0;

        return cnt;

    }//countDestroyed

    private Barrel[][] deepCopy(Barrel[][] original) {
        Barrel[][] copy = new Barrel[original.length][original[0].length];
        for(int i=0;i<original.length;i++) {
            for(int j=0;j<original[i].length;j++) {
                copy[i][j] = new Barrel(original[i][j].value, original[i][j].destroy);
            }//for j
        }//for i
        return copy;
    }//deepCopy

}//class
