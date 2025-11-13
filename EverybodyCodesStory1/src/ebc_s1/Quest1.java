package ebc_s1;

import java.util.*;

public class Quest1 extends EBCUtils {

    public Quest1() { super("1"); }

    @Override
    void solve(List<String> part1, List<String> part2, List<String> part3) {

        long max = 0L;

        for(String row : part1) {

            Scanner in = new Scanner(row);
            in.useDelimiter("\\D+");
            int[] f = new int[7];

            int i = 0;
            while(in.hasNextInt())
                f[i++] = in.nextInt();

            long res = eni(f[0],f[3],f[6]) + eni(f[1],f[4],f[6]) + eni(f[2],f[5],f[6]);
            if(res > max)
                max = res;

        }//for

        solution(max);

        max = 0L;

        for(String row : part2) {

            Scanner in = new Scanner(row);
            in.useDelimiter("\\D+");
            long[] f = new long[7];

            int i = 0;
            while(in.hasNextLong())
                f[i++] = in.nextLong();

            long res = eni2(f[0],f[3],f[6]) + eni2(f[1],f[4],f[6]) + eni2(f[2],f[5],f[6]);
            if(res > max)
                max = res;

        }//for

        solution(max);

        max = 0L;

        for(String row : part3) {

            Scanner in = new Scanner(row);
            in.useDelimiter("\\D+");
            long[] f = new long[7];

            int i = 0;
            while(in.hasNextLong())
                f[i++] = in.nextLong();

            long res = eni3(f[0],f[3],f[6]) + eni3(f[1],f[4],f[6]) + eni3(f[2],f[5],f[6]);
            if(res > max)
                max = res;

        }//for

        solution(max);

    }//solve

    long eni(int n, int exp, int mod) {

        List<Integer> res = new ArrayList<>();
        int score = 1;
        for(int i = 0; i < exp; i++) {
            res.addFirst((score * n) % mod);
            score = res.getFirst();
        }//for

        return Long.parseLong(res.toString().replaceAll("[\\[\\],\\s]", ""));

    }//eni

    long eni2(long n, long exp, long mod) {

        List<Long> res = new ArrayList<>();
        List<List<Long>> resCycle = new ArrayList<>();
        long score = 1;
        int startCycle = -1;
        for(int i = 0; i < exp; i++) {
            res.addFirst((score * n) % mod);
            score = res.getFirst();
            if(res.size() == 5)
                startCycle = i;
            if(res.size() > 5)
                res.removeLast();
            if(!resCycle.contains(res))
                resCycle.add(new ArrayList<>(res));
            else
                break;
        }//for
        if(startCycle != -1 && (exp - resCycle.size() != 0)) {
            exp -= resCycle.size();
            resCycle = resCycle.subList(4, resCycle.size());
        }//if
        return Long.parseLong(resCycle.get(startCycle == -1 ? resCycle.size()-1 : (int) ((exp - 1) % resCycle.size())).toString().replaceAll("[\\[\\],\\s]", ""));

    }//eni2

    long eni3(long n, long exp, long mod) {

        long score = 1L;
        long sum = 0;
        int idxRepeat = 0;
        List<Long> scores = new ArrayList<>();
        for(int i = 0; i < exp; i++) {
            score = (score * n) % mod;
            if(scores.contains(score)) {
                idxRepeat = scores.indexOf(score);
                break;
            }//if
            scores.add(score);
            sum += score;
        }//for

        exp -= scores.size();
        scores = scores.subList(idxRepeat, scores.size());

        long sumScores = 0L;
        for(Long s : scores) sumScores += s;

        sumScores *= (exp / scores.size());
        sum += sumScores;
        exp %= scores.size();

        for(int i = 0; i < exp; i++)
            sum += scores.get(i);

        return sum;

    }//eni3

}//class
