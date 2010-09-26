package devtest.q5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;

public class SpiralPrintTest
{

   private static final Log LOG = LogFactory.getLog(SpiralPrintTest.class);
   private static SpiralPrint spiralPrint;
   private Matrix matrix;

   @Before
   public void setUp()
   {
      spiralPrint = new SpiralPrint();

      int[][] twoDimensionalArray = new int[][]
      {
            {
                  1, 2, 3
            },
            {
                  4, 5, 6
            },
            {
                  7, 8, 9
            }
      };
      matrix = new Matrix(twoDimensionalArray);
   }

   @Test
   public void testParseInputToMatrix()
   {
      LOG.debug(spiralPrint);

      // simulate reading strings from a file
      List<String> input = new ArrayList<String>();
      input.add("1 2 3");
      input.add("4 5 6");
      input.add("7 8 9");

      Matrix result = spiralPrint.stringListToMatrix(input);
      assertNotNull(result);
      LOG.debug("\n" + result);
   }

   @Test
   public void testFileNameToStringList() throws FileNotFoundException
   {
      List<String> result = spiralPrint.fileNameToStringList("src/test/resources/input_a.dat");
      assertNotNull(result);
      LOG.debug("result: " + result);

      assertEquals("1 2 3", result.get(0));
      assertEquals("4 5 6", result.get(1));
      assertEquals("7 8 9", result.get(2));
   }

   @Test
   public void testSpiralPrintMatrix()
   {
      String result = spiralPrint.spiralPrintMatrix(matrix);
      assertEquals("1 2 3 6 9 8 7 4 5", result);
   }

   @Test
   public void testSpiralPrint() throws FileNotFoundException
   {
      String result = spiralPrint.spiralPrint("src/test/resources/input_a.dat");
      assertEquals("1 2 3 6 9 8 7 4 5", result);
   }

}
