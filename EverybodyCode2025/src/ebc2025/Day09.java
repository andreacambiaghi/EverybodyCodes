package ebc2025;

import java.util.*;

public class Day09 extends EBCUtils {

    public Day09() { super("9"); }

    @Override
    void solve(List<String> part1, List<String> part2, List<String> part3) {

       String parent1 = part1.getFirst().split(":")[1];
       String parent2 = part1.get(1).split(":")[1];
       String child = part1.get(2).split(":")[1];
       solution(getDegree(parent1, parent2, child));

       int totDegree = 0;
       for(int i = 0; i < part2.size(); i++)
           for(int j = i+1; j < part2.size(); j++)
               for(int k = j+1; k < part2.size(); k++) {
                   totDegree = getTotDegree(part2, totDegree, i, j, k);
               }//for k

        solution(totDegree);

       List<Set<Integer>> families = new ArrayList<>();
        totDegree = 0;
        for(int i = 0; i < part3.size(); i++)
            for(int j = i+1; j < part3.size(); j++)
                for(int k = j+1; k < part3.size(); k++) {

                    int prevDegree = totDegree;
                    totDegree = getTotDegree(part3, totDegree, i, j, k);

                    if(prevDegree != totDegree) {
                        int f = Integer.parseInt(part3.get(i).split(":")[0]);
                        int s = Integer.parseInt(part3.get(j).split(":")[0]);
                        int t = Integer.parseInt(part3.get(k).split(":")[0]);
                        families.add(new HashSet<>(Set.of(f,s,t)));
                    }//if

                }//for k

        boolean changed;
        do {
            changed = false;
            for (int i = 0; i < families.size(); i++) {
                for (int j = i + 1; j < families.size(); j++) {
                    if(!Collections.disjoint(families.get(i), families.get(j))) {
                        families.get(i).addAll(families.get(j));
                        families.remove(j);
                        changed = true;
                        break;
                    }//if
                }//for j
                if(changed) break;
            }//for i
        }while(changed);

        int max = 0;
        for(Set<Integer> family : families) {
            int sum = family.stream().mapToInt(Integer::intValue).sum();
            if(sum > max)
                max = sum;
        }//for

        solution(max);

    }//solve

    private int getTotDegree(List<String> part3, int totDegree, int i, int j, int k) {
        String first = part3.get(i).split(":")[1];
        String second = part3.get(j).split(":")[1];
        String third = part3.get(k).split(":")[1];
        if(isPossibile(first, second, third))
            totDegree += getDegree(first, second, third);
        else if(isPossibile(first, third, second))
            totDegree += getDegree(first, third, second);
        else if(isPossibile(second, third, first))
            totDegree += getDegree(second, third, first);
        return totDegree;
    }//getTotDegree

    boolean isPossibile(String parent1, String partent2, String child) {

        for(int i = 0; i < parent1.length(); i++)
            if(!List.of(parent1.charAt(i), partent2.charAt(i)).contains(child.charAt(i)))
                return false;

        return true;

    }//isPossibile

    int getDegree(String parent1, String parent2, String child) {

        int withFirstParent = 0;
        int withSecodnParent = 0;
        for(int i = 0; i < parent1.length(); i++) {
            withFirstParent += parent1.charAt(i) == child.charAt(i) ? 1 : 0;
            withSecodnParent += parent2.charAt(i) == child.charAt(i) ? 1 : 0;
        }//for

        return withFirstParent * withSecodnParent;

    }//getDegree

}//class
