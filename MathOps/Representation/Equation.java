package MathOps.Representation;

public class Equation {
    // Equation in the form r = n(c) + x(q)
    public int n, x, c, q, r;

    public Equation(int r, int n, int c, int x, int q){
        this.n = n;
        this.x = x;
        this.c = c;
        this.q = q;
        this.r = r;
    }

    public String getPrint(){
        return String.format("%d = %d(%d) + %d(%d)", r, n, c, x, q);
    }
}
