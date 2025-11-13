package ebc_s1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class EBCUtils {
    public EBCUtils(String day) {
        if (day == null || day.isEmpty()) {
            return;
        }//if

        List<String> part1 = readInputFile("in/quest" + day + ".1.txt");
        List<String> part2 = readInputFile("in/quest" + day + ".2.txt");
        List<String> part3 = readInputFile("in/quest" + day + ".3.txt");

        solve(part1, part2, part3);
    }//AOCPuzzle

    abstract void solve(List<String> part1, List<String> part2, List<String> part3);

    private List<String> readInputFile(String path) {
        File file = new File(path);
        List<String> lines = new ArrayList<>();

        if (!file.exists()) {
            return lines;
        }//if

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = reader.readLine()) != null)
                lines.add(line);
        } catch(IOException e) {
            e.printStackTrace();
        }//catch

        return lines;
    }//readInputFile

    public List<Integer> convertToInts(List<String> input) {
        List<Integer> ints = new ArrayList<>();
        for(String s : input)
            ints.add(Integer.parseInt(s));
        return ints;
    }//convertToInts

    public List<Long> convertToLongs(List<String> input) {
        List<Long> ints = new ArrayList<>();
        for(String s : input)
            ints.add(Long.parseLong(s));
        return ints;
    }//convertToLongs

    // TIME

    private int part = 1;
    private long timerStart = System.nanoTime();

    public void solution(int answer) {
        solution(String.valueOf(answer));
    }//solution

    public void solution(long answer) {
        solution(String.valueOf(answer));
    }//solution

    public void solution(String answer) {
        long timeSpent = (System.nanoTime() - timerStart) / 1000;
        System.out.println("Part " + part + ": " + answer + ", Duration: " + timeToString(timeSpent));
        timerStart = System.nanoTime();
        part++;
    }//solution

    public String timeToString(long timeSpent) {
        if(timeSpent < 1000)
            return timeSpent + "µs";
        if(timeSpent < 1000000)
            return (timeSpent / 1000.0) + "ms";
        return (timeSpent / 1000000.0) + "s";
    }//timeToString

}//class
