package MathOps.Representation;

import java.util.Random;

public class CryptoVal {

    private static final double MIN = 1E300;
    private static final double MAX = 999E298;

    public static double get_val(){
        Random rand = new Random();
        return rand.nextDouble() * 1E302;
    }
}
