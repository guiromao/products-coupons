package co.guiromao.findmultiple;

public class FindMultiple {

    public static void main(String[] args) {
        System.out.println(findMultiple(98.51));
    }

    private static int findMultiple(double number) {
        boolean found = false;

        for(int i = 1; true; i++) {
            if ((number * i) % 1 == 0) {
                return (int) Math.floor(number * i);
            }
        }
    }

}
