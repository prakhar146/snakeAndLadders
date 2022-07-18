package util;

import java.util.Random;

public class Dice {

    static int[] faces = new int[]{1, 2, 3, 4, 5, 6};
    static Random rd = new Random();

    public static int roll() {
        int fLength = faces.length;
        int idx = rd.nextInt(fLength - 1);
        return faces[idx];
    }
}
