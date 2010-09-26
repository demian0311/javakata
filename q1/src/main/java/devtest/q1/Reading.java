package devtest.q1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is a reading at a point with an associated strength.
 */
public class Reading
{

   private static final Log LOG = LogFactory.getLog(Reading.class);

   public Point getPoint()
   {
      return point;
   }

   public double getStrength()
   {
      return strength;
   }

   private final Point point;
   private final double strength;

   Reading(final Point pointIn, final double strengthIn)
   {
      point = pointIn;
      strength = strengthIn;
   }

   /**
    * parsing constructor.
    */
   Reading(final String input)
   {
      LOG.info("constructing Reading out of " + input);
      String[] fields = input.split(" ");

      point = new Point(Double.parseDouble(fields[0]), Double.parseDouble(fields[1]));
      strength = Double.parseDouble(fields[2]);
   }

   /** if no points are found then we return an empty array, if 1 point is found we return an
    * array with 1 entry.  If 2 points are found we return points in the 0 and 1 index.
    */
   public Point[] getIntersections(final Reading other)
   {
      //see: http://2000clicks.com/MathHelp/GeometryConicSectionCircleIntersection.aspx
      double distance = getPoint().getDistance(other.getPoint());

      if (distance > strength + other.getStrength())
      {
         LOG.info("circles won't even touch");
         return new Point[]
         {};
      }

      if (distance < Math.abs(strength - other.getStrength()))
      {
         LOG.info("no intersection, once circle is contained in the other");
         return new Point[]
         {};
      }

      double distanceSquared = Math.pow(distance, 2);

      double thisRadiusSquared = getStrength() * getStrength();
      double otherRadiusSquared = other.getStrength() * other.getStrength();

      double a = (thisRadiusSquared - otherRadiusSquared + distanceSquared) / (2 * distance);

      double hypotenuse = Math.sqrt(thisRadiusSquared - a * a);

      // point2 is on the line connecting the original 2 points, the intersections will
      // run 90 degrees from this point
      double point2X = point.getX() + a * (other.getPoint().getX() - point.getX()) / distance;
      double point2Y = point.getY() + a * (other.getPoint().getY() - point.getY()) / distance;

      // figure the coordinates based on point2 and the differentials 
      double pointAX = point2X + hypotenuse * (other.getPoint().getY() - point.getY()) / distance;
      double pointAY = point2Y - hypotenuse * (other.getPoint().getX() - point.getX()) / distance;
      double pointBX = point2X - hypotenuse * (other.getPoint().getY() - point.getY()) / distance;
      double pointBY = point2Y + hypotenuse * (other.getPoint().getX() - point.getX()) / distance;

      Point pointA = new Point(pointAX, pointAY);
      Point pointB = new Point(pointBX, pointBY);

      if (pointA.equals(pointB))
      {
         return new Point[]
         {
            pointA
         };
      }

      return new Point[]
      {
            pointA, pointB
      };
   }

   @Override
   public String toString()
   {
      return point.toString() + ": " + strength;
   }

}
