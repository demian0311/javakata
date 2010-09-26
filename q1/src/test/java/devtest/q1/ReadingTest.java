package devtest.q1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;

public class ReadingTest
{

   private static final Log LOG = LogFactory.getLog(ReadingTest.class);

   @Before
   public void setUp()
   {
      LOG.debug("setUp");
   }

   @Test
   public void testParsingConstructor()
   {
      String input = "6.0 8.0 5.0";
      Reading reading = new Reading(input);
      LOG.debug("parsing constructor created Reading: " + reading);
      assertNotNull("reading should not be null", reading);
      assertEquals(5.0, reading.getStrength(), 0);
      Point expectedPoint = new Point(6.0, 8.0);

      assertEquals(expectedPoint, reading.getPoint());
   }

   @Test
   public void testGetIntersectionsOnePoint()
   {
      Reading a = new Reading(new Point(6.0, 8.0), 5.0);
      Reading b = new Reading(new Point(0.0, 0.0), 5.0);

      Point[] intersections = b.getIntersections(a);
      assertTrue("if points are the same just return one", intersections.length == 1);
      LOG.debug(intersections[0]);

      Point expectedPoint = new Point(3.0, 4.0);
      assertEquals(expectedPoint, intersections[0]);
   }

   private static final double PRECISION = .000001;

   @Test
   public void testGetIntersections()
   {
      Reading a = new Reading(new Point(2.0, 2.0), 2.0);
      Reading b = new Reading(new Point(5.0, 5.0), Math.sqrt(9 + 25));

      Point[] intersections = b.getIntersections(a);
      LOG.debug(intersections[0]);
      LOG.debug(intersections[1]);

      // pretty close
      assertEquals(0.0, intersections[0].getX(), PRECISION);
      assertEquals(2.0, intersections[0].getY(), PRECISION);
      assertEquals(2.0, intersections[1].getX(), PRECISION);
      assertEquals(0.0, intersections[1].getY(), PRECISION);
   }

   @Test
   public void testGetIntersectionWontTouch()
   {
      Reading a = new Reading(new Point(0.0, 0.0), 2.0);
      Reading b = new Reading(new Point(5.0, 5.0), 2.0);

      Point[] intersections = b.getIntersections(a);
      assertNotNull(intersections);
      assertTrue(intersections.length == 0);
   }

   @Test
   public void testGetIntersectionOneInsideTheOther()
   {
      Reading a = new Reading(new Point(5.0, 5.0), 2.0);
      Reading b = new Reading(new Point(5.0, 5.0), 5.0);

      Point[] intersections = b.getIntersections(a);
      assertNotNull(intersections);
      assertTrue(intersections.length == 0);
   }
}
