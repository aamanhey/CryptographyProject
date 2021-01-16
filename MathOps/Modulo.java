package MathOps;

public class Modulo {

    public static int successive_squaring(int base, int exponent, int mod){
        Integer[] binary = Binary.get_binary(exponent);
        Integer[] values = new Integer[binary.length];
        values[0] = base%mod;
        for (int i = 1; i < binary.length; i++) {
            int value = values[i - 1] * values[i - 1]; // square
            value = value%mod; // reduce
            values[i] = value;
        }
        int cipher_txt = 1;
        for (int i = 0; i < binary.length; i++) {
            if(binary[i] == 1){
                cipher_txt = cipher_txt * values[binary.length - i - 1];
                cipher_txt = cipher_txt % mod;
            }
        }
        return cipher_txt;
    }
}
