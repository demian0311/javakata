package devtest.q1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FindSource
{

   private static final Log LOG = LogFactory.getLog(FindSource.class);

   public String findSourceFromStringArray(final String[] lines)
   {
      final Reading reading1 = new Reading(lines[0]);
      LOG.debug("reading1: " + reading1);
      final Reading reading2 = new Reading(lines[1]);
      LOG.debug("reading2: " + reading2);

      final StringBuffer sb = new StringBuffer();
      final Point[] points = reading1.getIntersections(reading2);
      for (Point point : points)
      {
         sb.append(point.getX() + " " + point.getY() + "\n");
      }

      return sb.toString();
   }

   private String[] fileNameToStringArray(String fileName) throws FileNotFoundException
   {
      String[] arrayOut = new String[2];
      Scanner scanner = new Scanner(new File(fileName)).useDelimiter("\n");

      // the format of a valid file is always 2 lines
      arrayOut[0] = scanner.next();
      arrayOut[1] = scanner.next();

      return arrayOut;
   }

   public String findSource(String fileName) throws FileNotFoundException
   {
      String[] stringArray = fileNameToStringArray(fileName);
      return findSourceFromStringArray(stringArray);
   }

   public static void main(String[] args)
   {
      if (args.length == 0)
      {
         System.out.println("enter a filename");
         System.exit(1);
      }
      FindSource fs = new FindSource();
      try
      {
         String result = fs.findSource(args[0]);
         System.out.println(result);
      }
      catch (FileNotFoundException e)
      {
         System.out.println(e.getMessage());
         System.exit(1);
      }
   }
}
