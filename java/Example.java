public class Example {
    public static final String EXAMPLE_TEST = "1#FL#200";

    public static void main(String[] args) {
        String[] splitString = (EXAMPLE_TEST.split("#"));
        System.out.println(splitString.length);
        for (String string : splitString) {
            System.out.println(string);
        }
    }
}
