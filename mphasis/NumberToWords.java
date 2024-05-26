package mphasis;

public class NumberToWords {

    private static final String[] LOW_NAMES = {
        "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
        "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
        "seventeen", "eighteen", "nineteen"};

    private static final String[] TENS_NAMES = {
        "", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

    private static final String[] THOUSANDS_NAMES = {
        "", "thousand", "million", "billion"};

    public static void main(String[] args) {
        int no = 123_456_789;
        System.out.println(no + ":" + convert(no));
    }

    public static String convert(int number) {
        if (number == 0) {
            return LOW_NAMES[0];
        }

        String prefix = "";

        if (number < 0) {
            number = -number;
            prefix = "negative";
        }

        String current = "";
        int place = 0;

        do {
            int n = number % 1000;
            if (n != 0) {
                String s = convertLessThanOneThousand(n);
                current = s + " " + THOUSANDS_NAMES[place] + " " + current;
            }
            place++;
            number /= 1000;
        } while (number > 0);

        return (prefix + " " + current).trim();
    }

    private static String convertLessThanOneThousand(int number) {
        String current;

        if (number % 100 < 20) {
            current = LOW_NAMES[number % 100];
            number /= 100;
        } else {
            current = LOW_NAMES[number % 10];
            number /= 10;

            current = TENS_NAMES[number % 10] + " " + current;
            number /= 10;
        }

        if (number == 0) {
            return current;
        }
        return LOW_NAMES[number] + " hundred " + current;
    }
}
