package Cryptography;

import DataStructureTools.RSATuple;
import DataStructureTools.RandomSet;
import MathOps.Representation.Equation;
import MathOps.LinearCombo;
import MathOps.Modulo;
import MathOps.Prime;

import java.util.Random;

public class RSA_Encryption {

    public static void main(String[] args) {
        RSATuple tuple = RSA(11, 17, 37);
        int m = 131;
        int cipher_txt = encode(tuple.getEncode(), m);
        int plain_txt = decode(tuple.getDecode(), cipher_txt);
        System.out.println("Cipher Text: "+cipher_txt);
        System.out.println("Plain Text: "+plain_txt);
    }

    public static RSATuple RSA(){
        Random rand = new Random();
        int p = rand.nextInt(100);
        while(!Prime.isPrime(p)){
            p = rand.nextInt(100);
        }
        int q = rand.nextInt(100);
        while(!Prime.isPrime(q)){
            q = rand.nextInt(100);
        }
        int e = rand.nextInt(100);
        while(!Prime.isPrime(q)){
            e = rand.nextInt(100);
        }

        return get_RSA(p, q, e);
    }

    public static RSATuple RSA(int p, int q, int e){
        return get_RSA(p, q, e);
    }

    private static RSATuple get_RSA(int p, int q, int e) {
        int n = p * q;
        int k = (p - 1) * (q - 1);
        // check that e is relatively prime
        Equation linear_comb = LinearCombo.findLinearCombo(e, k);
        if(linear_comb.r != 1){
            e = Prime.get_next(k);
        }
        linear_comb = LinearCombo.findLinearCombo(e, k);
        int d;
        if(linear_comb.n == e){
            d = linear_comb.c;
        }else{
            //linear_comb.x == e
            d = linear_comb.q;
        }
        RSATuple tuple = new RSATuple(e, d, n);
        return tuple;
    }

    public static int encode(Integer[] list, int m){
        int e = list[0];
        int n = list[1];
        // check to see if m is relatively prime to n
        Equation linear_comb = LinearCombo.findLinearCombo(m, n);
        if (linear_comb.r != 1) {
            throw new AssertionError("The message was not relatively prime to n.");
        }
        return Modulo.successive_squaring(m, e, n);
    }

    public static int decode(Integer[] list, int cipher_txt){
        // ct^(d)(mod n)
        return Modulo.successive_squaring(cipher_txt, list[0], list[1]);
    }
}
