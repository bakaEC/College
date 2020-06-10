package study;

public class stringCounter {
    public int count(String string, String input) {
        String[] split = new String[(string.length())];
        for (int i = 0; i < string.length(); i++) {
            split[i] = string.substring(i, i + 1);
        }
        int countNum = 0;
        for (String i : split) {
            if (input.equals(i)) {
                countNum++;
            }
        }
        return countNum;

    }
}