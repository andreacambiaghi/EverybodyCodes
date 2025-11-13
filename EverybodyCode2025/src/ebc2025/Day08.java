package ebc2025;

import java.util.*;

public class Day08 extends EBCUtils {

    public Day08() { super("8"); }

    private record Thread(int x, int y) {
        int min() { return Math.min(x, y); }
        int max() { return Math.max(x, y); }

        boolean isInside(int val) {
            return val > min() && val < max();
        }//isInside
    }//record Thread

    @Override
    void solve(List<String> part1, List<String> part2, List<String> part3) {

        List<Integer> nails = convertToInts(Arrays.asList(part1.getFirst().split(",")));
        int numNails = 32;
        int center = 0;

        for (int i = 0; i < nails.size() - 1; i++)
            if (Math.abs(nails.get(i + 1) - nails.get(i)) == numNails / 2)
                center++;

        solution(center);

        numNails = 256;
        nails = convertToInts(Arrays.asList(part2.getFirst().split(",")));
        List<Integer> knots = new ArrayList<>();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < nails.size() - 1; i++)
            threads.add(new Thread(nails.get(i), nails.get(i + 1)));

        countKnots(knots, threads);
        solution(knots.stream().mapToInt(Integer::intValue).sum());

        Map<Thread, Integer> count = new HashMap<>();
        nails = convertToInts(Arrays.asList(part3.getFirst().split(",")));
        knots = new ArrayList<>();
        threads = new ArrayList<>();
        for (int i = 0; i < nails.size() - 1; i++)
            threads.add(new Thread(nails.get(i), nails.get(i + 1)));

        List<Thread> allThreads = new ArrayList<>();
        for (int i = 1; i <= numNails; i++)
            for (int j = 1; j <= numNails; j++)
                allThreads.add(new Thread(i, j));

        countKnots(knots, threads);

        for (Thread t : allThreads) {
            int nKnots = 0;
            for (Thread thread : threads) {
                Set<Integer> points = new HashSet<>(Arrays.asList(thread.x, thread.y, t.x, t.y));
                boolean inX = t.isInside(thread.x);
                boolean inY = t.isInside(thread.y);
                if (points.size() == 4 && (inX ^ inY)) nKnots++;
            }//for thread
            count.put(t, nKnots);
        }//for t

        Thread maxT = allThreads.getFirst();
        for (Thread key : count.keySet())
            if (count.get(key) > count.get(maxT))
                maxT = key;

        solution(count.get(maxT) + (threads.contains(maxT) ? 1 : 0));

    }//solve

    private void countKnots(List<Integer> knots, List<Thread> threads) {
        for (int i = 0; i < threads.size(); i++) {
            int nKnots = 0;
            for (int j = 0; j < i; j++) {
                Set<Integer> points = new HashSet<>(Arrays.asList(
                        threads.get(j).x, threads.get(j).y,
                        threads.get(i).x, threads.get(i).y));
                boolean inX = threads.get(i).isInside(threads.get(j).x);
                boolean inY = threads.get(i).isInside(threads.get(j).y);
                if (points.size() == 4 && (inX ^ inY)) nKnots++;
            }//for j
            knots.add(nKnots);
        }//for i
    }//countKnots

}//class
