package devtest.q1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;

public class PointTest
{

   private static final Log LOG = LogFactory.getLog(PointTest.class);

   @Before
   public void setUp()
   {
      LOG.debug("setUp");
   }

   @Test
   public void testGetDistance()
   {
      Point a = new Point(6, 8);
      Point b = new Point(0, 0);

      double distance = a.getDistance(b);
      LOG.debug("distance: " + distance);

      assertFalse(distance == 0.0);
      assertEquals(10.0, distance, 0.0);
   }
}
