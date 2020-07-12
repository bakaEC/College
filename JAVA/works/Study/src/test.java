class test {
    public static void main(String[] args) {
        int y, x = 1, total = 0;
        while (x <= 10) {
            y = x * x;
            System.out.println(y);
            total += y;
            System.out.println("Total is " + total);
        }

    }
}