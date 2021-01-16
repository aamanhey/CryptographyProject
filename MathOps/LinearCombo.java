package MathOps;

import MathOps.Representation.Equation;
import MathOps.Representation.Remainder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LinearCombo {
    // Gives the linear combination where gcd(n, k) = 1

    public static Equation findLinearCombo(int x, int n){
        // Completes the back substitution of x and n to find the linear combination
        ArrayList<Remainder> remainders = find_remainders(x, n);
        Equation[] equations = new Equation[remainders.size()];
        for (int i = 0; i < remainders.size(); i++) {
            Remainder curr = remainders.get(i);
            if(i > 1){
                Equation prev_1 = equations[i-1];
                Equation prev_2 = equations[i-2];
                int new_c = prev_1.c * -1 * curr.q;
                int new_q = prev_1.q * -1 * curr.q;
                Equation equation = new Equation(curr.r, prev_2.x, new_c + prev_2.q, prev_1.x,new_q + prev_2.c);
                equations[i] = equation;
            }else if(i == 1){
                Remainder prev = remainders.get(i-1);
                Equation equation = new Equation(curr.r, curr.n, prev.q * curr.q + 1, prev.n,-1 * curr.q);
                equations[i] = equation;
            }else{
                Equation equation = new Equation(curr.r, curr.n, 1, curr.x,-1 * curr.q);
                equations[i] = equation;
            }
        }
        return equations[remainders.size()-1];
    }

    public static Map<Integer, Remainder> solve_remainder(int x, int n){
        HashMap<Integer, Remainder> map = new HashMap();
        ArrayList<Remainder> remainders = find_remainders(x, n);
        for (Remainder remainder:remainders) {
            map.put(remainder.r, remainder);
        }
        return map;
    }

    public static ArrayList<Remainder> find_remainders(int x, int n){
        ArrayList<Remainder> remainder_list = new ArrayList<Remainder>();
        if(simple_remainder(x,n,remainder_list)){
            return remainder_list;
        }
        System.out.println("a needs to have no common denomenator with 26 other than 1");
        return null;
    }

    public static boolean simple_remainder(int x, int n, ArrayList<Remainder> remainder_list){
        // n should be greater than x
        // n = x * q + r with 0 <= r <= x-1
        int r = n % x;
        int q = (n - r) / x;
        //System.out.println(String.format("%d = %d(%d) + %d", n, x, q, r));
        if(remainder_list != null) {
            Remainder new_remainder = new Remainder(n, x, q, r);
            remainder_list.add((new_remainder));
        }
        if(r == 0){
            return false;
        }else if(r != 1){
            simple_remainder(r, x, remainder_list);
        }
        return true;
    }
}
