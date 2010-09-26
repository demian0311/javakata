package devtest.q2;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Justify
{

   private static final Log LOG = LogFactory.getLog(Justify.class);

   public String justify(final int width, final String input)
   {
      final StringBuffer out = new StringBuffer();
      for (String line : split(width, input))
      {
         out.append(fixWidth(width, line));
         out.append("\n");
      }
      String outString = out.toString();

      return outString.substring(0, outString.length() - 1); // remove last newline
   }

   protected String fixWidth(final int width, final String input)
   {
      LOG.debug(width + "|" + input);
      int spacesToAdd = width - input.length();
      if (spacesToAdd < 0)
      {
         throw new IllegalArgumentException("input string cannot be longer than width");
      }
      if (spacesToAdd == 0)
      {
         return input;
      }

      String[] splitString = input.split(" ");
      int numGaps = splitString.length - 1;

      if (spacesToAdd > numGaps)
      {
         return input;
      }

      LOG.debug("spacesToAdd: " + spacesToAdd);
      LOG.debug("numGaps: " + numGaps);
      int centerGap = (numGaps / 2) + 1;
      LOG.debug("centerGap: " + centerGap);
      int gapToBegin = centerGap - spacesToAdd / 2;
      LOG.debug("gapToBegin: " + gapToBegin);
      int gapToEnd = gapToBegin + spacesToAdd;

      StringBuffer output = new StringBuffer();
      int currWordCount = 0;
      for (String currString : splitString)
      {
         output.append(currString);
         output.append(' ');
         currWordCount++;
         if ((currWordCount >= gapToBegin) && (currWordCount < gapToEnd))
         {
            output.append(' ');
         }
      }
      String outString = output.toString();

      return outString.substring(0, outString.length() - 1);
   }

   protected List<String> split(final int width, final String input)
   {
      final List<String> output = new ArrayList<String>();
      StringBuffer currLine = new StringBuffer();
      final StringTokenizer sringTokenizer = new StringTokenizer(input);
      String currWord = null;
      List<String> wordList = new ArrayList<String>();
      int currWidth = 0;
      while (sringTokenizer.hasMoreTokens())
      {
         currWord = sringTokenizer.nextToken();
         currWidth += currWord.length() + 1;
         LOG.debug("currWidth: " + currWidth);

         if (currWidth + 1 < width)
         {
            LOG.debug("found another word: " + currWord);
            wordList.add(currWord);
         }
         else
         {
            LOG.debug("past width");
            for (String word : wordList)
            {
               word += " ";
               currLine.append(word);
            }
            currLine.append(currWord);

            output.add(currLine.toString());
            currLine = new StringBuffer();
            currWidth = 0;
            wordList = new ArrayList<String>();
         }
      }

      // just append the rest
      for (String word : wordList)
      {
         word += " ";
         currLine.append(word);
      }
      output.add(currLine.substring(0, currLine.length() - 1));

      return output;
   }
}
