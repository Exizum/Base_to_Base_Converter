//by julian and devin
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("enter the base you want to convert from: ");
        int fromBase = s.nextInt();

        System.out.println("enter the number you want to convert from" +
                " with each place value in base 10 separated by spaces. ");
        System.out.println("for example 2 19 (base 20) = 2 * 20^1 + 19" +
                " * 20^0 = 59 (base 10)");
        s = new Scanner(System.in);
        String fromNum = s.nextLine();


        System.out.println("enter the base you want to convert to: ");
        s = new Scanner(System.in);
        int toBase = s.nextInt();

        StringTokenizer st = new StringTokenizer(fromNum); //this parses each "digit" in the
                                                            //input stream
        ArrayList<Integer> fromList = new ArrayList<>();


        while (st.hasMoreTokens()) {
            fromList.add(Integer.parseInt(st.nextToken()));
        }

        String toNum = convert(fromList, fromBase, toBase);
        System.out.println(toNum);


        //3 5 10 12
    }

    public static String convert(List<Integer> fromList, int fromBase, int toBase) {

        //first convert the value to base 10 as a middleman
        int valBase10 = 0;
        int placeVal = fromList.size() - 1;
        for (int num : fromList) {
            valBase10 += num * pow(fromBase, placeVal); //adding the digit * base ^ placeval
            placeVal--;
        }

        ArrayList<Integer> res = new ArrayList<>();
        int temp = 1;
        placeVal = 0;

        //then we find the highest place value that the number can reach in base 10
        while (valBase10 >= temp) {
            temp *= toBase;
            placeVal++;
        }
        placeVal--;

        int cur = pow(toBase, placeVal);
//        System.out.println(valBase10 + " " + placeVal);

        while (placeVal >= 0) {
            int multiplier = valBase10 / cur; //how many times the cur can fit in the
                                                    //number in base 10
            res.add(multiplier);
            valBase10 -= multiplier * cur; //subtract from the total

            placeVal--; //move place value to the right
            cur /= toBase;
        }

        //now append all the numbers to a final string
        StringBuilder sb = new StringBuilder();
        sb.append("the number is [");
        for (int num : res) {
            sb.append(String.valueOf(num));
            sb.append(" ");
        }
        sb.delete(sb.length()-1, sb.length());
        sb.append("] (base " + toBase + ")");
        return sb.toString();

    }

    public static int pow(int x, int y) { //this return x ^ y
        int res = 1;

        while (y > 0) {
            res = res * x;
            y--;
        }

        return res;

    }
}
