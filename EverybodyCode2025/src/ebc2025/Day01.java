package ebc2025;

import java.util.ArrayList;
import java.util.List;

public class Day01 extends EBCUtils {

    public Day01() { super("1"); }

    @Override
    void solve(List<String> part1, List<String> part2, List<String> part3) {

        DragonDuck dd = new DragonDuck();
        solution(dd.myName(part1));
        solution(dd.firstParentName(part2));
        solution(dd.secondParentName(part3));

    }//solve

    private static class DragonDuck {

        String myName(List<String> input) {

            List<String> names = List.of(input.getFirst().split(","));
            List<String> commands = List.of(input.getLast().split(","));

            int idx = 0;
            for(String command : commands) {
                int steps = command.charAt(1) - '0';
                if(command.charAt(0) == 'L')
                    idx = Math.max(0, idx - steps);
                if(command.charAt(0) == 'R')
                    idx = Math.min(names.size()-1, idx + steps);
            }//for

            return names.get(idx);

        }//myName

        String firstParentName(List<String> input) {

            List<String> names = List.of(input.getFirst().split(","));
            List<String> commands = List.of(input.getLast().split(","));

            int idx = 0;
            for(String command : commands) {
                int steps = Integer.parseInt(command.substring(1));
                if(command.charAt(0) == 'L')
                    idx -= steps;
                if(command.charAt(0) == 'R')
                    idx += steps;
                idx = idx < 0 ? (names.size() + idx) % names.size() : idx % names.size();
            }//for

            return names.get(idx);

        }//firstParentName

        String secondParentName(List<String> input) {

            List<String> names = new ArrayList<>(List.of(input.getFirst().split(",")));
            List<String> commands = List.of(input.getLast().split(","));

            for(String command : commands) {
                int idx = 0;
                int steps = Integer.parseInt(command.substring(1));
                if(command.charAt(0) == 'L')
                    idx -= steps;
                if(command.charAt(0) == 'R')
                    idx += steps;

                if(idx < 0)
                    while(idx < 0)
                        idx = (names.size() + idx);

                idx %= names.size();
                String first = names.getFirst();
                names.set(0, names.get(idx));
                names.set(idx, first);

            }//for

            return names.getFirst();

        }//secondParentName

    }//class
    
}//class
