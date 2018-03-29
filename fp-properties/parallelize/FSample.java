import java.util.*;
import java.util.stream.IntStream;

public class FSample {
  private static double sumOfSqrtsOfPrimes = 0.0;

  public static boolean isPrime(int number) {
    return number > 1 && 
      IntStream.range(2, number)
               .noneMatch(i -> number % i == 0);
  }
  
  public static void main(String[] args) {
    int number = 500000;
    long start = System.nanoTime();
                         
    IntStream.rangeClosed(1, number)
             .boxed()
             .filter(Sample::isPrime)
             .map(Math::sqrt)
             .forEach(value -> sumOfSqrtsOfPrimes += value); //This is bad                       
    
    long end = System.nanoTime();
    
    System.out.printf("Time Taken: %g sec\n", (end - start)/1e9);
    System.out.println(sumOfSqrtsOfPrimes);
  }
}
