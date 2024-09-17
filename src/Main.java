//by julian and devin
import java.util.*;

/*
what this program does:
    -takes the user input for the base they want to convert from and to.
    -takes the number the user wants to convert in their desired base
    -parses each "digit" the user inputs, adds them to a list in that order,
    then calls a method to convert to the target base

    convert method:
        -converts the number to base 10 first by going through each digit
        starting with the most significant digit to the least significant digit,
        adding each digit's value multiplied by the initial base raised to the
        current position

        -after the value is found in base 10, we convert to the user's desired base
        by using the cake method to find all the remainders and keep dividing the
        value by the final base until it is 0.
        -we keep track of each remainder of the division by adding each remainder
        to a stack since we want the last remainder we get to be the first digit

        -once we have all the remainders, we append each one to a stringbuilder
        and return a final string containing the number in the desired base
 */
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

        Stack<Integer> stack = new Stack<>();

        //cake method to convert from base 10 to any other base
        //we're using stack because it's last in first out
        while (valBase10 > 0) {
            int remainder = valBase10 % toBase;
            valBase10 /= toBase;
            stack.add(remainder);
        }





        //now append all the numbers to a final string
        StringBuilder sb = new StringBuilder();
        sb.append("the number is [");
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
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
