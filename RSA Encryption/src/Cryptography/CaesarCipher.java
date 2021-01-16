package Cryptography;

public class CaesarCipher {
    /** FIXME
     * There is a probelm with the caesar cipher where the "z" does not encode properly.
     */
    public static String encode(int shift, String plain_txt){
        plain_txt = plain_txt.toLowerCase();
        // a is 97 and z is 122
        char[] cipher_txt = new char[plain_txt.length()];
        for (int i = 0; i < plain_txt.length(); i++) {
            int letter_num = plain_txt.charAt(i);
            if(plain_txt.charAt(i) != ' ') {
                //int letter_num = plain_txt.charAt(i);
                letter_num += shift;
                if (letter_num > 122) {
                    letter_num = 96 + (letter_num % 122);
                    // we need it to be able to get to a
                }
            }
            cipher_txt[i] = (char) letter_num;
        }
        return String.valueOf(cipher_txt).toUpperCase();
    }

    public static String decode(int shift, String cipher_txt){
        cipher_txt = cipher_txt.toLowerCase();
        // a is 97 and z is 122
        char[] plain_txt = new char[cipher_txt.length()];
        for (int i = 0; i < cipher_txt.length(); i++) {
            int letter_num = cipher_txt.charAt(i);
            if(cipher_txt.charAt(i) != ' ') {
                letter_num -= shift;
                if (letter_num < 97) {
                    letter_num = 123 - (97 - letter_num);
                    // we need it to be able to loop to the other end
                }
            }
            plain_txt[i] = (char) letter_num;
        }
        return String.valueOf(plain_txt).toUpperCase();
    }
}
