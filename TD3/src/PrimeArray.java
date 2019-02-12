public class PrimeArray {

    private /*@ spec_public @*/ Prime[] l;
    private /*@ spec_public @*/ int size = 0;
 
    
    /*@ public invariant // Premier invariant
      @ (* Les size premiers elements sont ranges en ordre croissant *);
      @ \forall int i; i>=0 && i<size && l[i].is_prime() && l[i+1].is_prime() && l[i].is_prime() < l[i+1].is_prime();
      @*/

    /*@ public invariant // Deuxieme invariant
      @ (* Les entiers compris entre deux elements consecutifs,
      @ parmi les size premiers elements, ne sont pas premiers *);
//      @ (\forall int i,j; i>=0 && i<l[size] && i > l[j] && i < l[j+1]  && l[j] <= l[j+1] && Prime.is_prime(i)== false)
//      @ (\forall int i; i >= 0 && i < size; (\forall int j; j > l[i] && j < l[i+1]; Prime.is_prime(j) == false))
      @*/

    /*@
      @ ensures size == 0;
      @*/
    public PrimeArray() {
        l = new Prime[100];
        size = 0;
    }

    /*@
      @ ensures \old(size)+1 == size;
      @*/
    public void grow() {
//        int nextPosition = size + 1;
//        int nextPrime = 5; // à changer
//        l[nextPosition] = nextPrime;
//        size = size + 1;
    }
}
