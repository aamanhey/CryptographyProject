package MathOps;

public class Prime {

    static int i = 2;

    public static int get_next(int number){
        // give the next relative prime of the input
        int next_prime = number + 1;
        while (!relatively_prime(next_prime, number)){
            next_prime++;
        }
        return next_prime;
    }

    public static boolean relatively_prime(int num1, int num2){
        return (LinearCombo.findLinearCombo(num1, num2).r == 1);
    }

    // Function check whether a number
    // is prime or not
    public static boolean isPrime(int n)
    {
        // Corner cases
        if (n == 0 || n == 1)
        {
            return false;
        }

        // Checking Prime
        if (n == i)
            return true;

        // Base cases
        if (n % i == 0)
        {
            return false;
        }
        i++;
        return isPrime(n);
    }

}
