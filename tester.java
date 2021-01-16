import Cryptography.AffineCipher;
import Cryptography.CaesarCipher;
import Cryptography.PerfectCode;
import Cryptography.RSA_Encryption;
import DataStructureTools.Graph.Graph;
import DataStructureTools.Graph.GraphInfo;
import DataStructureTools.RSATuple;
import DataStructureTools.Tree.Tree;
import MathOps.LinearCombo;
import MathOps.Representation.Equation;
import MathOps.SumFactors;

import java.util.Arrays;


public class tester {

    /**
     * Check if two booleans are the same.
     * @param actual The boolean to check.
     * @param expected The expected (correct) boolean.
     */
    public static void assertEquals(boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError("Expected " + expected + " but got " + actual);
        }
    }

    /**
     * Check if two Integers are equal
     * @param actual The computed integer to check.
     * @param expected The expected (correct) integer.
     */
    public static void assertEquals(Integer actual, Integer expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError("Expected " + expected + " but got " + actual);
        }
    }

    /**
     * Check if two Strings are equal.
     * @param actual The computed String to check.
     * @param expected The expected (correct) String.
     */
    public static void assertEquals(String actual, String expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError("Expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        /* NEXT
          Perfect Codes (requires a graph)
          AES Encryption
         */
        CaesarCipherTest();
        CaesarCipherTest2();
        CaesarCipherPhraseTest();
        AffineCipherTest();
        AffineCipherPhraseTest();
        SimpleRemainderTest(); // Linear Combo
        FindLinearComboTest();
        GraphTest();
        PerfectCodeTest();
        SumFactorsTest();
        //System.out.println("ALL TESTS PASSED");
        System.out.println("-----");
    }

    public static void CaesarCipherTest(){
        String plain_txt = "CAT";
        String cipher_txt = CaesarCipher.encode(2, plain_txt);
        String decoded_txt = CaesarCipher.decode(2, cipher_txt);
        assertEquals(plain_txt, decoded_txt);
    }

    public static void CaesarCipherTest2(){
        String plain_txt = "LAZY";
        String cipher_txt = CaesarCipher.encode(2, plain_txt);
        String decoded_txt = CaesarCipher.decode(2, cipher_txt);
        assertEquals(decoded_txt, plain_txt);
    }

    public static void CaesarCipherPhraseTest(){
        String plain_txt = "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG";
        String cipher_txt = CaesarCipher.encode(2, plain_txt);
        String decoded_txt = CaesarCipher.decode(2, cipher_txt);
        assertEquals(decoded_txt, plain_txt);
    }

    public static void AffineCipherTest(){
        String plain_txt = "CALI";
        int a = 7;
        int b = 4;
        String cipher_txt = AffineCipher.encode(a, b, plain_txt);
        assertEquals(cipher_txt, "SEDI");
        String decoded_txt = AffineCipher.decode(a, b, cipher_txt);
        assertEquals(plain_txt, decoded_txt);
    }

    public static void AffineCipherPhraseTest(){
        String plain_txt = "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG";
        int a = 7;
        int b = 4;
        String cipher_txt = AffineCipher.encode(a, b, plain_txt);
        String decoded_txt = AffineCipher.decode(a, b, cipher_txt);
        assertEquals(plain_txt, decoded_txt);
    }

    public static void SimpleRemainderTest(){
        boolean result = LinearCombo.simple_remainder(7,26,null);
        assertEquals(result, true);
        result = LinearCombo.simple_remainder(2,8, null);
        assertEquals(result, false);
    }

    public static void FindLinearComboTest(){
        Equation equation = LinearCombo.findLinearCombo(7, 26);
        assertEquals(equation.getPrint(), "1 = 7(-11) + 26(3)");
        /*
        This means that 1(mod 26) = 7(mod 26) * -11(mod 26)
        So, inverse(7(mod 26)) = -11(mod 26) = 15(mod 26)
         */
    }

    public static void GraphTest(){
        Graph graph = new Graph("Test Graph");
        int max = 5;
        for (int i = 0; i < max; i++) {
            graph.put(i,i+1);
        }
        graph.put(max,0);
        String result = graph.print_graph();
        String expected = "--Graph--\n" +
                "0 : 1, 5\n" +
                "1 : 0, 2\n" +
                "2 : 1, 3\n" +
                "3 : 2, 4\n" +
                "4 : 3, 5\n" +
                "5 : 4, 0\n";
        assertEquals(result, expected);
        //System.out.println(graph.get_graphviz());
        graph.remove(1,2);
        result = graph.print_graph();
        String after_removal = "--Graph--\n" +
                "0 : 1, 5\n" +
                "1 : 2\n" +
                "2 : 3\n" +
                "3 : 2, 4\n" +
                "4 : 3, 5\n" +
                "5 : 4, 0\n";
        assertEquals(result, after_removal);
    }

    public static void PerfectCodeTest(){
        GraphInfo griffin = PerfectCode.generate_rand_graph();
        //System.out.println(griffin.getVpcAsString());
        //System.out.println(griffin.getRaw_graph());
        //System.out.println(griffin.getGraph().get_graphviz());
        //PerfectCode.clump(griffin.getGraph(), 17);
        System.out.println("Perfect Code Test Failed");
        // NOT FINISHED
    }

    public static void SumFactorsTest(){
        SumFactors.factorize(1, 9);
    }
    
    public static void TreeTest(){
        Tree tree = new Tree();
        assertEquals(tree.put(8, 64), true);
        assertEquals(tree.put(7, 49), true);
        assertEquals(tree.put(10, 100), true);
        assertEquals(tree.put(6, 36), true);
        assertEquals(tree.put(11, 121), true);
        assertEquals(tree.size(),5); //checking the size

        int[] keysArray = tree.toKeysArray();
        assertEquals(
                Arrays.equals(keysArray, new int[]{6,7,8,10,11}),
                true
        );
        for (int i = 0; i < keysArray.length; i++) {
            int key = keysArray[i];
                assertEquals(tree.get(key), key * key);
        }
    }

    public static void RSATest(){
        RSATuple tuple = RSA_Encryption.RSA(11, 17, 37);
        int m = 131;
        int cipher_txt = RSA_Encryption.encode(tuple.getEncode(), m);
        assertEquals(54, cipher_txt);
        int plain_txt = RSA_Encryption.decode(tuple.getDecode(), cipher_txt);
        assertEquals(m, plain_txt);
    }
}
