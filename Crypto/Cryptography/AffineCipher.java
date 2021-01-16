package Cryptography;

import MathOps.Representation.Equation;
import MathOps.LinearCombo;

public class AffineCipher {
    // s1 * n + s2, where s1 and s2 are integers
    /* The function e: int/26 -> int/26int given
    by e([n]26) = [a*n + b]26 is bijective if and
    only if gcd(a, n) = 1.
     */
    public static Alphabet alphabet = new Alphabet();

    public static String encode(int a, int b, String plain_txt){
        // check gcd(a, n) = 1
        plain_txt = plain_txt.toLowerCase();
        char[] cipher_txt = new char[plain_txt.length()];
        for (int i = 0; i < plain_txt.length(); i++) {
            // need to skip over spaces
            if(plain_txt.charAt(i) != ' ') {
                int letter_num = alphabet.getLetter(plain_txt.charAt(i));
                letter_num = a * letter_num + b;
                letter_num = letter_num % 26;
                cipher_txt[i] = alphabet.letters[letter_num];
            }else{
                cipher_txt[i] = plain_txt.charAt(i);
            }
        }
        return String.valueOf(cipher_txt).toUpperCase();
    }

    public static String decode(int a, int b, String cipher_txt){
        /* gcd can be written as linear combo of the letters
        There exists s, t in the Ints w/ 1 = as + nt

        d([n](mod 26)) = [inverse(a)(n-b)](mod 26)
         */
        Equation equation = LinearCombo.findLinearCombo(a, 26);
        cipher_txt = cipher_txt.toLowerCase();
        char[] plain_txt = new char[cipher_txt.length()];
        for (int i = 0; i < cipher_txt.length(); i++) {
            if(cipher_txt.charAt(i) != ' ') {
                int letter_num = alphabet.getLetter(cipher_txt.charAt(i));
                int inv_a = equation.c;
                while (inv_a < 0) {
                    inv_a += 26;
                }
                letter_num = (inv_a * (letter_num - b)) % 26;
                while (letter_num < 0) {
                    letter_num += 26;
                }
                plain_txt[i] = alphabet.letters[letter_num];
            }else{
                plain_txt[i] = cipher_txt.charAt(i);
            }
        }
        return String.valueOf(plain_txt).toUpperCase();
    }

}
