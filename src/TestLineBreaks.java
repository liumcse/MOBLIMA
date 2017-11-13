import java.util.Scanner;
import java.util.StringTokenizer;

public class TestLineBreaks {
    public static String addLinebreaks(String input, int maxLineLength) {
        StringTokenizer tok = new StringTokenizer(input, " ");
        StringBuilder output = new StringBuilder(input.length());
        int lineLen = 0;
        while (tok.hasMoreTokens()) {
            String word = tok.nextToken();

            if (lineLen + word.length() > maxLineLength) {
                output.append("\n").append("             ");
                lineLen = 0;
            }
            output.append(word).append(" ");
            lineLen += word.length();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a sentence: ");
        System.out.println(addLinebreaks(sc.nextLine(), 50));
    }
}
