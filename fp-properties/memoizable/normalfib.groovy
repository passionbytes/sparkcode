// https://www.jdoodle.com/execute-groovy-online
def fib;
  
fib = { n ->
   if (n < 2)
     1
   else
     fib(n - 1) + fib(n - 2)
}

def start = System.nanoTime()
println(fib(20))
def end = System.nanoTime()

println ((end - start)/1.0e9)

