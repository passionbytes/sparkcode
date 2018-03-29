def compute(number: Int) = {
  //assume this is time consuming
  Thread.sleep(5000)
  number
}              

val start = System.nanoTime

if(Math.random() > 0.5 && compute(5) > 0) {
  System.out.println("computed");
  }else {
  System.out.println("skipped");
  }
  
val end = System.nanoTime

println(s"Time taken: ${(end - start)/1e9}")
