package devtest.q5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;

public class MatrixTest
{

   private static final Log LOG = LogFactory.getLog(MatrixTest.class);

   private static Matrix matrix;
   private static Matrix oneRowMatrix;

   @Before
   public void setUp()
   {
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

      int[][] oneRowTwoDimensionalArray = new int[][]
      {
         {
               1, 2, 3
         }
      };
      oneRowMatrix = new Matrix(oneRowTwoDimensionalArray);
   }

   @Test
   public void testRemoveTopRow()
   {
      Matrix resultMatrix = matrix.removeTopRow();
      assertNotNull(resultMatrix);

      int[][] topRowRemovedTwoDimensionalArray = new int[][]
      {
            {
                  4, 5, 6
            },
            {
                  7, 8, 9
            }
      };
      Matrix expectedMatrix = new Matrix(topRowRemovedTwoDimensionalArray);

      assertEquals(expectedMatrix, resultMatrix);
      assertEquals(expectedMatrix.hashCode(), resultMatrix.hashCode());
   }

   @Test
   public void testRemoveTopRowJustHasOneRow()
   {
      // this test is important, returning a null is part of the contract, if we don't do it
      // consumers might end up in an infinite loop.
      Matrix resultMatrix = oneRowMatrix.removeTopRow();
      assertNull(resultMatrix);
   }

   @Test
   public void testReadTopRow()
   {
      String result = matrix.readTopRow();
      assertNotNull(result);
      assertEquals("1 2 3", result);
   }

   @Test
   public void testRotateCounterClockwise()
   {
      LOG.debug("\n" + matrix);
      Matrix resultMatrix = matrix.rotateCounterClockwise();
      LOG.debug("\n" + matrix);

      int[][] rotatedTwoDimensionalArray = new int[][]
      {
            {
                  3, 6, 9
            },
            {
                  2, 5, 8
            },
            {
                  1, 4, 7
            }
      };
      Matrix expectedMatrix = new Matrix(rotatedTwoDimensionalArray);
      assertEquals(expectedMatrix, resultMatrix);
      assertEquals(expectedMatrix.hashCode(), resultMatrix.hashCode());
   }
}
