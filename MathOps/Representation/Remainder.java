package MathOps.Representation;

public class Remainder {
    // Gives the remainder in the form
    // n = x * q + r with 0 <= r <= x-1
    public int n, x, q, r;

    public Remainder(int n, int x, int q, int r){
        this.n = n;
        this.x = x;
        this.q = q;
        this.r = r;
    }

    public String getPrint(){
        return String.format("%d = %d(%d) + %d", n, x, q, r);
    }
}
