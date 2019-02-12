public class Prime {
    private /*@ spec_public @*/ int p;
    /*@ public invariant
      @ is_prime(p);
      @*/

    public Prime() {
        p = 3;
    }

    //@ requires (is_prime(x));
    //@ ensures (is_prime(x));
    public Prime(int x) {
        p = x;
    }

    //@ requires (is_prime(x));
    //@ ensures (is_prime(x));
    public void set_p(int x) {
        p = x;
    }

    //@ ensures ( is_prime(p) );
    public /*@ pure @*/ int get_p() {
        return p;
    }

    /*@ ensures \result == true <==>
      @   (n > 1 ) && (\forall int d; 2<= d && d<= n-1; n % d != 0);
      @*/
    public static /*@ pure helper @*/ boolean is_prime(int n) {
        boolean isPrime = true;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                isPrime = false;
            }
        }
        return isPrime;
    }
}
