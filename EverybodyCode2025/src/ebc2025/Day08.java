package ebc2025;

import java.util.*;

public class Day08 extends EBCUtils {

    public Day08() { super("8"); }

    private record Thread(int x, int y){}

    @Override
    void solve(List<String> part1, List<String> part2, List<String> part3) {

        List<Integer> nails = convertToInts(List.of(part1.getFirst().split(",")));
        int numNails = 32;
        int center = 0;

        for(int i = 0; i < nails.size()-1; i++)
            if(Math.abs(nails.get(i+1) - nails.get(i)) == numNails/2)
                center++;

        solution(center);

        numNails = 256;

        nails = convertToInts(List.of(part2.getFirst().split(",")));
        List<Integer> knots = new ArrayList<>();

        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < nails.size()-1; i++)
            threads.add(new Thread(Math.min(nails.get(i), nails.get(i+1)),Math.max(nails.get(i), nails.get(i+1))));

        countKnots(knots, threads);

        solution(knots.stream().mapToInt(Integer::intValue).sum());

        Map<Thread, Integer> count = new HashMap<>();
        nails = convertToInts(List.of(part3.getFirst().split(",")));
        knots = new ArrayList<>();

        threads = new ArrayList<>();
        for(int i = 0; i < nails.size()-1; i++)
            threads.add(new Thread(Math.min(nails.get(i), nails.get(i+1)),Math.max(nails.get(i), nails.get(i+1))));

        List<Thread> allThread = new ArrayList<>();
        for(int i = 1; i <= numNails; i++)
            for(int j = 1; j <= numNails; j++)
                allThread.add(new Thread(i,j));

        countKnots(knots, threads);

        for(Thread t : allThread) {
            int nKnots = 0;
            for(Thread thread : threads) {
                Set<Integer> points = new HashSet<>(List.of(thread.x, thread.y, t.x, t.y));
                boolean inX = isInside(t, thread.x);
                boolean inY = isInside(t, thread.y);
                if(points.size() == 4 && (inX ^ inY)) nKnots++;
            }//for
            count.put(t, nKnots);
        }//for

        Thread maxT = allThread.getFirst();
        for(Thread key : count.keySet())
            if(count.get(key) > count.get(maxT))
                maxT = key;

        solution(count.get(maxT) + (threads.contains(maxT) ? 1 : 0));

    }//solve

    private void countKnots(List<Integer> knots, List<Thread> threads) {
        for(int i = 0; i < threads.size(); i++) {
            int nKnots = 0;
            for(int j = 0; j < i; j++) {
                Set<Integer> points = new HashSet<>(List.of(threads.get(j).x, threads.get(j).y, threads.get(i).x, threads.get(i).y));
                boolean inX = isInside(threads.get(i), threads.get(j).x);
                boolean inY = isInside(threads.get(i), threads.get(j).y);
                if(points.size() == 4 && (inX ^ inY)) nKnots++;
            }//for j
            knots.add(nKnots);
        }//for i
    }//countKnots

    boolean isInside(Thread t, int val) {
        for(int i = Math.min(t.x,t.y) + 1; i < Math.max(t.x,t.y); i++)
            if(i == val)
                return true;
        return false;
    }//isInside

}//class
