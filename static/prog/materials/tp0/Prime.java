/**************************************
 * Sieve of Eratosthenes
 *
 * The Sieve of Eratosthenes is a procedure  
 * that allows to find the prime number
 * up to a given limit
 *
 **************************************/

public class Prime
{
  public static void main(String [] argList)
  {
    final int N = 19;
    // initialize sieve
    boolean [] prime = initSieve(N);
    // prime[n] is true if n is prime

// let's check odd numbers from 3 to N
    for (int n = 3; n <= N; n += 2)
    {
      checkPrime(n, prime);
    }
    // display prime numbers
    displayPrime(prime, N);
  }

  /**
     initialize sieve to find prime numbers <= N
     @param N : greatest number to check
     @return array of boolean (capacity = N);
	     all elements initialized to false, except prime[2] = true
  */
  static boolean [] initSieve(int N)
  {
    boolean [] prime = new boolean[N];
    // all initial values default to false

    // 2 is the first prime number
    prime[2] = true; 
    return prime;
  }

  /**
     check if candidate is prime
     @param candidate : number to test (2 <= candidate <= N)
     @param prime     : array of boolean (capacity = N);
     ensure : prime[candidate] is true if candidate is prime
   */
  static void checkPrime(int candidate, boolean [] prime)
  {
    int i = 2;
    while (prime[i])
    {
      if (candidate % i == 0)
      {
	// i divides candidate so candidate is not prime
	prime[candidate] = false;
	return;
      }
      i++;
    }
    // candidate is prime
    prime[candidate] = true;
  }

  // display prime numbers <= N
  static void displayPrime(boolean [] prime, int N)
  {
    int numberOfPrime = 0;
    for (int n = 0; n <= N; ++n)
    {
      if (prime[n])
      {
	++numberOfPrime;
	System.out.print(n + " ");
	if (numberOfPrime % 10 == 0) { System.out.println(); }
      }
    }
    System.out.println("\nTotal number of prime numbers <= " + N +
		       " is " + numberOfPrime);
  }
}
