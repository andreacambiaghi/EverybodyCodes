package ebc2025;

import java.util.ArrayList;
import java.util.List;

public class Day02 extends EBCUtils {

    public Day02() { super("2"); }

    @Override
    void solve(List<String> part1, List<String> part2, List<String> part3) {

        int x = Integer.parseInt(part1.getFirst().split(",")[0].substring(3));
        int y = Integer.parseInt(part1.getFirst().split(",")[1].substring(0, part1.getFirst().split(",")[1].length()-1));

        Complex A = new Complex(x,y);

        solution(threeCycle(A).toString());

        x = Integer.parseInt(part2.getFirst().split(",")[0].substring(3));
        y = Integer.parseInt(part2.getFirst().split(",")[1].substring(0, part2.getFirst().split(",")[1].length()-1));

        A = new Complex(x,y);
        solution(A.calculate(A.getCoordinates101()));

        x = Integer.parseInt(part3.getFirst().split(",")[0].substring(3));
        y = Integer.parseInt(part3.getFirst().split(",")[1].substring(0, part3.getFirst().split(",")[1].length()-1));

        A = new Complex(x,y);
        solution(A.calculate(A.getCoordinates1001()));

    }//solve

    Complex threeCycle(Complex c) {

        Complex R = new Complex(0, 0);
        Complex ten = new Complex(10,10);

        for(int i = 0; i < 3; i++) {
            R = R.mul(R);
            R = R.div(ten);
            R = R.sum(c);
        }//for

        return R;

    }//threeCycle

    private record Complex(long x, long y) {

        @Override
        public String toString(){
            return "["+x+","+y+"]";
        }//toString

        Complex sum(Complex c) {
            return new Complex(x + c.x, y + c.y);
        }//sum

        Complex mul(Complex c) {
            return new Complex(x * c.x - y * c.y, x * c.y + y * c.x);
        }//mul

        Complex div(Complex c) {
            return new Complex(x / c.x, y / c.y);
        }//div

        int calculate(List<Complex> coordinates) {

            int valid = 0;

            for(Complex P : coordinates) {
                boolean isValid = true;
                Complex R = new Complex(0,0);
                for(int i = 0; i < 100; i++) {
                    R = R.mul(R);
                    R = R.div(new Complex(100000,100000));
                    R = R.sum(P);
                    if(R.x > 1000000 || R.x < -1000000 || R.y > 1000000 || R.y < -1000000) {
                        isValid = false;
                        break;
                    }//if

                }//for i
                if(isValid)
                    valid++;
            }//for c

            return valid;

        }//calculate

        List<Complex> getCoordinates101() {

            List<Complex> coordinates = new ArrayList<>();

            for(long i = x; i <= x+1000; i+=10)
                for(long j = y; j <= y+1000; j+=10)
                    coordinates.add(new Complex(i,j));

            return coordinates;

        }//getCoordinates101

        List<Complex> getCoordinates1001() {

            List<Complex> coordinates = new ArrayList<>();

            for(long i = x; i <= x+1000; i++)
                for(long j = y; j <= y+1000; j++)
                    coordinates.add(new Complex(i,j));

            return coordinates;

        }//getCoordinates1001

    }//record

}//class
