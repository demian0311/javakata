package devtest.q5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SpiralPrint
{

   private static final Log LOG = LogFactory.getLog(SpiralPrint.class);

   public Matrix stringListToMatrix(final List<String> input)
   {
      int[][] outArray = new int[0][0];
      int rows = input.size();
      boolean createArrayYet = false;

      int currRow = 0;
      for (String currLine : input)
      {
         LOG.debug("processing: " + currLine);
         StringTokenizer stringTokenizer = new StringTokenizer(currLine);
         if (!createArrayYet)
         {
            int columns = stringTokenizer.countTokens();
            outArray = new int[rows][columns];
            createArrayYet = true;
         }

         int currColumn = 0;
         while (stringTokenizer.hasMoreElements())
         {
            int currInt = Integer.parseInt(stringTokenizer.nextToken());
            LOG.debug("[" + currRow + "][" + currColumn + "]: " + currInt);
            outArray[currRow][currColumn] = currInt;
            currColumn++;
            LOG.debug('\n' + arrayToString(outArray));
         }
         currRow++;
      }
      return new Matrix(outArray);
   }

   /**
    * This method contains the core of the algorithm.
    * <ul>
    *    <li>read the top row</li>
    *    <li>rotate counter clockwise</li>
    *    <li>repeat until done</li>
    * </ul>
    */
   public String spiralPrintMatrix(final Matrix matrix)
   {
      StringBuffer stringBuffer = new StringBuffer();
      Matrix currMatrix = matrix;
      while (true)
      {
         stringBuffer.append(currMatrix.readTopRow());
         stringBuffer.append(' ');
         currMatrix = currMatrix.removeTopRow();
         if (currMatrix == null)
         {
            String out = stringBuffer.toString();
            return out.substring(0, out.length() - 1);// get rid of last space
         }
         currMatrix = currMatrix.rotateCounterClockwise();
      }
   }

   private String arrayToString(final int[][] arrayIn)
   {
      StringBuffer stringBuffer = new StringBuffer();
      for (int[] element : arrayIn)
      {
         stringBuffer.append('[');

         for (int element2 : element)
         {
            stringBuffer.append(element2);
            stringBuffer.append(' ');
         }
         stringBuffer.append("]\n");
      }
      return stringBuffer.toString();
   }

   public List<String> fileNameToStringList(final String fileName) throws FileNotFoundException
   {
      List<String> listOut = new ArrayList<String>();
      Scanner sc = new Scanner(new File(fileName)).useDelimiter("\n");
      while (sc.hasNext())
      {
         listOut.add(sc.next());
      }

      return listOut;
   }

   public String spiralPrint(final String fileName) throws FileNotFoundException
   {
      List<String> stringList = fileNameToStringList(fileName);
      Matrix matrix = stringListToMatrix(stringList);
      return spiralPrintMatrix(matrix);
   }

   public static void main(String[] args)
   {
      if (args.length == 0)
      {
         System.out.println("enter a filename");
         System.exit(1);
      }
      SpiralPrint sp = new SpiralPrint();
      try
      {
         String result = sp.spiralPrint(args[0]);
         System.out.println(result);
      }
      catch (FileNotFoundException e)
      {
         System.out.println(e.getMessage());
         System.exit(1);
      }
   }

}
