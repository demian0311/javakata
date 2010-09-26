package devtest.q1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;

public class FindSourceTest
{

   private static final Log LOG = LogFactory.getLog(FindSourceTest.class);
   private FindSource findSource = null;

   @Before
   public void setUp()
   {
      LOG.debug("setUp");
   }

   @Test
   public void testFindSource()
   {
      findSource = new FindSource();

      final String[] inputLines = new String[]
      {
            "6.0 8.0 5.0", "0.0 0.0 5.0"
      };

      final String result = findSource.findSourceFromStringArray(inputLines);
      LOG.debug("result: " + result);
      assertNotNull("result should not be null", result);
      assertEquals("3.0 4.0\n", result);

   }
}
