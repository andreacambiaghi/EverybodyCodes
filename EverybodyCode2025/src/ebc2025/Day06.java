package ebc2025;

import java.util.List;

public class Day06 extends EBCUtils {

    public Day06() { super("6"); }

    @Override
    void solve(List<String> part1, List<String> part2, List<String> part3) {

        solution(mentor(part1.getFirst(), 'a'));

        int mentors = 0;
        for(Character c : List.of('a','b','c'))
            mentors += mentor(part2.getFirst(), c);
        solution(mentors);

        mentors = 0;
        String repeated = part3.getFirst().repeat(1000);
        for(Character c : List.of('a','b','c'))
            mentors += mentor1000(repeated, c);

        solution(mentors);

    }//solve

    int mentor(String couples, char type) {

        int mentors = 0;

        for(int i = 0; i < couples.length(); i++)
            if(couples.charAt(i) == type)
                for(int j = 0; j < i; j++)
                    if(couples.charAt(j) == Character.toUpperCase(type))
                        mentors++;

        return mentors;

    }//mentor

    int mentor1000(String couples, char type) {

        int mentors = 0;

        for(int i = 0; i < couples.length(); i++)
            if(couples.charAt(i) == type)
                for(int j = Math.max(0, i-1000); j <= Math.min(couples.length()-1, i+1000); j++)
                    if(couples.charAt(j) == Character.toUpperCase(type))
                        mentors++;

        return mentors;

    }//mentor1000

}//class
