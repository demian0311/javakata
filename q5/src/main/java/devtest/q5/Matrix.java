package devtest.q5;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Provide data structure and a few methods for the q5 spiral printing use case.
 */
public class Matrix
{

   private static final Log LOG = LogFactory.getLog(Matrix.class);

   private final int[][] array;
   private final int columns;
   private final int rows;

   public Matrix(int[][] arrayIn)
   {
      array = arrayIn;

      rows = array.length;
      columns = array[0].length;

      LOG.debug("done creating matrix");
   }

   /** returns a new Matrix that is rotated counter clockwise. */
   public Matrix rotateCounterClockwise()
   {
      int[][] newarray = new int[columns][rows];
      for (int ii = rows - 1; ii >= 0; ii--)
      {
         for (int jj = 0; jj < columns; jj++)
         {
            newarray[jj][ii] = array[ii][jj];
         }
      }

      // TODO: I should be able to do these at the same time
      // now flip it vertically
      int[][] newFlippedArray = new int[columns][rows];
      for (int ii = columns - 1; ii >= 0; ii--)
      {
         newFlippedArray[ii] = newarray[columns - (ii + 1)];
      }

      return new Matrix(newFlippedArray);
   }

   public String readTopRow()
   {
      StringBuffer sb = new StringBuffer();
      for (int ii = 0; ii < columns; ii++)
      {
         sb.append(array[0][ii]);
         if (ii != columns - 1)
         {
            sb.append(' ');
         }
      }
      return sb.toString();
   }

   /** returns a new Matrix has the top row removed.  
    * If there is only 1 row currently then we return null
    **/
   public Matrix removeTopRow()
   {
      if (rows == 1)
      {
         return null;
      }

      int[][] newarray = new int[rows - 1][columns];

      for (int ii = 1; ii < rows; ii++)
      {
         for (int jj = 0; jj < columns; jj++)
         {
            newarray[ii - 1][jj] = array[ii][jj];
         }
      }
      return new Matrix(newarray);
   }

   @Override
   public String toString()
   {
      StringBuffer sb = new StringBuffer();

      for (int ii = 0; ii < rows; ii++)
      {
         sb.append('|');
         for (int jj = 0; jj < columns; jj++)
         {
            sb.append(array[ii][jj]);
            sb.append(' ');
         }
         sb.append("|\n");
      }

      return sb.toString();
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;

      for (int ii = 0; ii < rows; ii++)
      {
         for (int jj = 0; jj < columns; jj++)
         {
            result = prime * result + array[ii][jj];
         }
      }

      result = prime * result + columns;
      result = prime * result + rows;
      return result;
   }

   /* no use case to expose this yet */
   private int get(int row, int column)
   {
      return array[row][column];
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         LOG.debug("this object was null"); //uhhh
         return true;
      }
      if (obj == null)
      {
         LOG.debug("other object was null");
         return false;
      }
      if (getClass() != obj.getClass())
      {
         LOG.debug("not same class");
         return false;
      }
      Matrix other = (Matrix) obj;

      // Arrays.equals didn't seem to work for this purpose, 
      // should probably be skeptical of why I needed to implement on my own?
      for (int ii = 0; ii < rows; ii++)
      {
         for (int jj = 0; jj < columns; jj++)
         {
            if (get(ii, jj) != other.get(ii, jj))
            {
               return false;
            }
         }
      }

      if (columns != other.columns)
      {
         LOG.debug("columns not equals");
         return false;
      }
      if (rows != other.rows)
      {
         LOG.debug("rows not equals");
         return false;
      }
      return true;
   }
}
