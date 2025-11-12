package ebc2025;

import java.util.*;

public class Day07 extends EBCUtils {

    private Map<String, List<String>> rules;
    private List<String> names;

    public Day07() { super("7"); }

    @Override
    void solve(List<String> part1, List<String> part2, List<String> part3) {

        readNamesRules(part1);
        for(String name : names) {
            if(isValidName(name)) {
                solution(name);
                break;
            }//if
        }//for

        readNamesRules(part2);
        int countValid = 0;
        for(int i = 0; i < names.size(); i++)
            if(isValidName(names.get(i)))
                countValid += i+1;
        solution(countValid);

        readNamesRules(part3);
        Set<String> validNames = new HashSet<>();
        for(String name : names)
            if(isValidName(name))
                compose(validNames, name);
        solution(validNames.size());

    }//solve

    private void readNamesRules(List<String> input) {

        names = List.of(input.getFirst().split(","));
        rules = new HashMap<>();
        for(int i = 2; i < input.size(); i++) {
            String[] splitted = input.get(i).split(" > ");
            rules.put(splitted[0], List.of(splitted[1].split(",")));
        }//for

    }//readNamesRules

    private boolean isValidName(String name) {

        boolean valid = true;
        for(int i = 0; i < name.length()-1; i++) {
            if(!rules.get(name.charAt(i) + "").contains(name.charAt(i + 1) + "")) {
                valid = false;
                break;
            }//if
        }//for
        return valid;

    }//isValidName

    void compose(Set<String> validNames, String name) {

        if(name.length() > 11)
            return;

        if(name.length() >= 7)
            validNames.add(name);

        String last = name.charAt(name.length()-1) + "";
        List<String> valids = rules.getOrDefault(last, new ArrayList<>());
        for(String valid : valids)
            compose(validNames, name + valid);

    }//compose

}//class
