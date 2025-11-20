package ebc2025;

import java.util.ArrayList;
import java.util.List;

public class Day13 extends EBCUtils {

    public Day13() { super("13"); }

    private record Barrel(int value, boolean destroy){}

    @Override
    void solve(List<String> part1, List<String> part2, List<String> part3) {

        List<Integer> numbers = convertToInts(part1);
        List<Integer> wheel = new ArrayList<>(List.of(1));

        for(int i = 0; i < numbers.size(); i+=2)
            wheel.add(numbers.get(i));

        for(int i = 1, j = 0; i < numbers.size(); i+=2)
            wheel.add(wheel.size() - j++, numbers.get(i));

        solution(wheel.get(2025 % wheel.size()));

        List<String> intervals = new ArrayList<>(part2);
        wheel = new ArrayList<>(List.of(1));

        for(int i = 0; i < intervals.size(); i+=2) {
            int s = Integer.parseInt(intervals.get(i).split("-")[0]);
            int e = Integer.parseInt(intervals.get(i).split("-")[1]);
            for(int j = s; j <= e; j++)
                wheel.add(j);
        }//for


        for(int i = 1, j = 0; i < intervals.size(); i+=2) {
            int s = Integer.parseInt(intervals.get(i).split("-")[0]);
            int e = Integer.parseInt(intervals.get(i).split("-")[1]);
            for(int k = s; k <= e; k++)
                wheel.add(wheel.size() - j++, k);
        }//for

        solution(wheel.get(20252025 % wheel.size()));

        intervals = new ArrayList<>(part3);
        long dial = 202520252025L;
        long size = 1L;

        for(String interval : intervals) {
            long s = Long.parseLong(interval.split("-")[0]);
            long e = Long.parseLong(interval.split("-")[1]);
            size += (e - s + 1);
        }//for

        dial = (dial % size) - 1;

        for(int i = 0; i < intervals.size(); i+=2) {
            long s = Long.parseLong(intervals.get(i).split("-")[0]);
            long e = Long.parseLong(intervals.get(i).split("-")[1]);
            long len = (e - s + 1);

            if(dial >= len)
                dial -= len;
            else {
                solution(s + dial);
                return;
            }//else
        }//for

        int startIdx = intervals.size() - 1;
        if(startIdx % 2 == 0)
            startIdx--;

        for(int i = startIdx; i >= 1; i-=2) {
            long s = Long.parseLong(intervals.get(i).split("-")[0]);
            long e = Long.parseLong(intervals.get(i).split("-")[1]);
            long len = (e - s + 1);

            if(dial >= len)
                dial -= len;
            else {
                solution(e - dial);
                break;
            }//else
        }//for

    }//solve

}//class
