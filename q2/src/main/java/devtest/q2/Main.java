package devtest.q2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main
{

   public static void main(String[] args)
   {
      if (args.length == 0)
      {
         System.out.println("enter a filename");
         System.exit(1);
      }
      Justify justify = new Justify();
      try
      {
         Scanner sc = new Scanner(new File(args[0])).useDelimiter("\n");
         int width = Integer.parseInt(sc.next());
         String text = sc.next();

         System.out.println(justify.justify(width, text));
      }
      catch (FileNotFoundException e)
      {
         System.out.println(e.getMessage());
         System.exit(1);
      }
   }
}
