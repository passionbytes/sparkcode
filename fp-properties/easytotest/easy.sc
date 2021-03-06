def greet(name : String, hourOfDay: Int): String =  {
  
  if(hourOfDay > 4 && hourOfDay < 13)
    return "Good morning, " + name;
  
  if(hourOfDay > 12 && hourOfDay < 17)
    return "Good afternoon, " + name;

   if(hourOfDay > 16 && hourOfDay < 20)
      return "Good evening, " + name;

   return "Good night, " + name;
}
