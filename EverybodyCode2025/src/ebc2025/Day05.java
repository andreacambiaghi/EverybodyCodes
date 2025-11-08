package ebc2025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Day05 extends EBCUtils {

    public Day05() { super("5"); }

    @Override
    void solve(List<String> part1, List<String> part2, List<String> part3) {

        List<Integer> swords = convertToInts(List.of(part1.getFirst().split(":")[1].split(",")));
        solution(getQuality(swords));

        long max = 0L;
        long min = Long.MAX_VALUE;
        for(String row : part2) {

            swords = convertToInts(List.of(row.split(":")[1].split(",")));
            long central = getQuality(swords);
            if(central > max)
                max = central;
            else if(central < min)
                min = central;

        }//for

        solution(max - min);

        List<Spines> swordsSpines = new ArrayList<>();
        for(String row : part3) {

            int id = Integer.parseInt(row.split(":")[0]);
            swords = convertToInts(List.of(row.split(":")[1].split(",")));

            swordsSpines.add(new Spines(getSpines(swords), id, getQuality(swords)));

        }//for

        swordsSpines.sort(Comparator.reverseOrder());

        int checksum  = 0;
        int i = 1;
        for(Spines swordSpines : swordsSpines)
            checksum  += (i++ * swordSpines.id);

        solution(checksum);

    }//solve

    private static class Spine {

        public int left = 0;
        public int mid;
        public int right = 0;

        public Spine(int mid) {
            this.mid = mid;
        }//Spine

        int value() {
            return Integer.parseInt((left + "" + mid + "" + right).replace("0",""));
        }//value

        @Override
        public String toString() {
            return (left + "" + mid + "" + right).replace("0","");
        }//toString

    }//class

    private static class Spines implements Comparable<Spines> {

        List<Spine> spines;
        int id;
        long quality;

        Spines(List<Spine> spines, int id, Long quality) {
            this.spines = new ArrayList<>(spines);
            this.id = id;
            this.quality = quality;
        }//Spines

        @Override
        public int compareTo(Spines o) {

            if(quality > o.quality)
                return 1;
            else if(quality < o.quality)
                return -1;

            for(int i = 0; i < spines.size(); i++) {
                if(spines.get(i).value() > o.spines.get(i).value())
                    return 1;
                else if(spines.get(i).value() < o.spines.get(i).value())
                    return -1;
            }//for

            return id - o.id;

        }//compareTo

        @Override
        public String toString() {
            return spines.toString();
        }//toString

    }//class

    Long getQuality(List<Integer> swords) {

        List<Spine> spines = getSpines(swords);

        StringBuilder central = new StringBuilder();

        for(Spine spine : spines)
            central.append(spine.mid);

        return Long.parseLong(central.toString());

    }//getQuality

    private static List<Spine> getSpines(List<Integer> swords) {

        List<Spine> spines = new ArrayList<>();
        spines.add(new Spine(swords.getFirst()));

        for(Integer sword : swords.subList(1, swords.size())) {

            boolean insert = false;
            for(Spine spine : spines) {

                if(spine.left == 0 && spine.mid > sword) {
                    spine.left = sword;
                    insert = true;
                    break;
                }//if

                if(spine.right == 0 && spine.mid < sword) {
                    spine.right = sword;
                    insert = true;
                    break;
                }//if

            }//for spine

            if(!insert)
                spines.add(new Spine(sword));

        }//for sword
        return spines;
    }//getSpines

}//class
