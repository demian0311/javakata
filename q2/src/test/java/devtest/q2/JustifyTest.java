package devtest.q2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;

public class JustifyTest
{

   private static final Log LOG = LogFactory.getLog(JustifyTest.class);
   private static Justify app;

   @Before
   public void setUp()
   {
      app = new Justify();
   }

   @Test
   public void testSplit()
   {
      //                                   12345678901234567890123456789012345678901234
      final List<String> actual = app.split(20, "The quick brown fox jumps over the lazy dog.");
      assertNotNull("result should not be null", actual);
      assertEquals("The quick brown fox", actual.get(0));
      assertEquals("jumps over the lazy", actual.get(1));
      assertEquals("dog.", actual.get(2));
   }

   @Test
   public void testFixWidthBigInput()
   {
      try
      {
         app.fixWidth(10, "The quick brown fox");
         fail("should have thrown an exception");
      }
      catch (IllegalArgumentException iae)
      {
         // its okay to be here
      }
   }

   @Test
   public void testFixWidthAlreadySameWidth()
   {
      String input = "The quick";
      String result = app.fixWidth(input.length(), input);
      assertEquals("strings should be exactly the same", input, result);
   }

   @Test
   public void testFixWidth()
   {
      int width = 20;
      String input = "The quick brown fox";
      final String result = app.fixWidth(width, input);
      LOG.debug("input: " + input);
      LOG.debug("output: " + result);
      assertNotNull(result);
      assertEquals("width doesn't match specified", width, result.length());
      assertEquals("The quick  brown fox", result);
   }

   @Test
   public void test()
   {
      //                               12345678901234567890123456789012345678901234
      String actual = app.justify(20, "The quick brown fox jumps over the lazy dog.");
      LOG.debug("output: \n" + actual);
      assertNotNull(actual);
      assertEquals("The quick  brown fox\njumps over  the lazy\ndog.", actual);
   }
}
