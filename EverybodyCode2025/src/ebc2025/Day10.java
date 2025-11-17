package ebc2025;

import java.util.*;

public class Day10 extends EBCUtils {

    public Day10() { super("10"); }

    private record Pos(int x, int y){}

    int eating = 0;

    @Override
    void solve(List<String> part1, List<String> part2, List<String> part3) {

        Character[][] board = new Character[part1.size()][part1.getFirst().length()];
        for(int i = 0; i < part1.size(); i++)
            for(int j = 0; j < part1.get(i).length(); j++)
                board[i][j] = part1.get(i).charAt(j);
        
        int sheepsBefore = countSheeps(board);

        for(int i = 0; i < 4; i++)
            moveDragon(board);

        solution((sheepsBefore - countSheeps(board)));

        board = new Character[part2.size()][part2.getFirst().length()];
        for(int i = 0; i < part2.size(); i++)
            for(int j = 0; j < part2.get(i).length(); j++)
                board[i][j] = part2.get(i).charAt(j);

        List<Pos> sheeps = findSheeps(board);
        List<Pos> hideouts = findHideouts(board);
        Set<Pos> dragons = findDragons(board);

        for(int i = 0; i < part2.size(); i++)
            for(int j = 0; j < part2.get(i).length(); j++)
                board[i][j] = board[i][j] == 'D' ? 'D' : '.';

        for(int turn = 0; turn < 20; turn++) {
            moveDragonNew(board, sheeps, hideouts, dragons);
            moveSheep(sheeps, board.length, dragons, hideouts);
        }//for turn

        solution(eating);

        

    }//solve

    void printGrid(Character[][] board) {
        for(Character[] cellBoards : board) {
            for(Character cellBoard : cellBoards)
                System.out.print(cellBoard);
            System.out.println();
        }//for i
    }//printGrid

    void printSheepSituation(Character[][] board, List<Pos> sheeps) {

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++)
                if(sheeps.contains(new Pos(i, j)))
                    System.out.print("S");
                else
                    System.out.print(".");
            System.out.println();
        }//for i

    }//printSheepSituation

    void moveDragon(Character[][] board) {

        int[][] offsets = {
                {-2,-1}, {-2,1}, {2,-1}, {2,1},
                {1,2}, {1,-2}, {-1,2}, {-1,-2}
        };

        Set<Pos> dragons = findDragons(board);
        for(Pos dragon : dragons) {

            board[dragon.x][dragon.y] = 'X';

            for(int[] o : offsets)
                if(isIn(board, dragon, o))
                    board[dragon.x + o[0]][dragon.y + o[1]] = 'D';

        }//for

    }//moveDragon

    private void moveDragonNew(Character[][] board, List<Pos> sheeps, List<Pos> hideouts, Set<Pos> dragons) {

        int sheepSize = sheeps.size();

        int[][] offsets = {
                {-2,-1}, {-2,1}, {2,-1}, {2,1},
                {1,2}, {1,-2}, {-1,2}, {-1,-2}
        };
        for(Pos dragon : new ArrayList<>(dragons)) {

            board[dragon.x][dragon.y] = '.';
            dragons.remove(dragon);

            for(int[] o : offsets)
                if(isIn(board, dragon, o)) {
                    board[dragon.x + o[0]][dragon.y + o[1]] = 'D';
                    dragons.add(new Pos(dragon.x + o[0], dragon.y + o[1]));

                    if(!hideouts.contains(new Pos(dragon.x + o[0], dragon.y + o[1])))
                        sheeps.remove(new Pos(dragon.x + o[0], dragon.y + o[1]));

                }//if

        }//for

        eating += (sheepSize - sheeps.size());

    }//moveDragonNew

    void moveSheep(List<Pos> sheeps, int size, Set<Pos> dragons, List<Pos> hideouts) {

        int sheepSize = sheeps.size();

        sheeps.replaceAll(pos -> new Pos(pos.x + 1, pos.y));

        for(Pos sheep : new ArrayList<>(sheeps))
            if(!hideouts.contains(sheep))
                if(dragons.contains(sheep))
                    sheeps.remove(sheep);

        eating += (sheepSize - sheeps.size());

        sheeps.removeIf(sheep -> sheep.x >= size);

    }//moveSheep

    private static boolean isIn(Character[][] board, Pos dragon, int[] o) {
        return !(dragon.x + o[0] >= board.length || dragon.x + o[0] < 0 || dragon.y + o[1] >= board[dragon.x].length || dragon.y + o[1] < 0);
    }//isIn

    Set<Pos> findDragons(Character[][] board) {

        Set<Pos> dragons = new HashSet<>();

        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[i].length; j++)
                if(board[i][j] == 'D')
                    dragons.add(new Pos(i, j));

        return dragons;

    }//findDragons

    List<Pos> findSheeps(Character[][] board) {

        List<Pos> sheeps = new ArrayList<>();

        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[i].length; j++)
                if(board[i][j] == 'S')
                    sheeps.add(new Pos(i, j));

        return sheeps;

    }//findDragons

    List<Pos> findHideouts(Character[][] board) {

        List<Pos> hideouts = new ArrayList<>();

        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[i].length; j++)
                if(board[i][j] == '#')
                    hideouts .add(new Pos(i, j));

        return hideouts;

    }//findDragons

    int countSheeps(Character[][] board) {

        int sheeps = 0;

        for(Character[] row : board)
            for(Character c : row)
                if (c == 'S')
                    sheeps++;

        return sheeps;

    }//countSheep

}//class
