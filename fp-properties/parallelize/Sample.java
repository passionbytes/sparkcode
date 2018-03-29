import java.util.*;
import java.util.stream.IntStream;

public class Sample {
  private static double sumOfSqrtsOfPrimes = 0.0;

  public static boolean isPrime(int number) {
    return number > 1 && 
      IntStream.range(2, number)
               .noneMatch(i -> number % i == 0);
  }
  
  public static void main(String[] args) {
    int number = 500000;
    long start = System.nanoTime();
                         
    for(int i = 1; i <= number; i++) {
      if(isPrime(i))
       sumOfSqrtsOfPrimes += Math.sqrt(i);
    }
                           
    long end = System.nanoTime();
    
    System.out.printf("Time Taken: %g sec\n", (end - start)/1e9);
    System.out.println(sumOfSqrtsOfPrimes);
  }
}
