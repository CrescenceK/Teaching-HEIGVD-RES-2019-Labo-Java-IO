package ch.heigvd.res.labio.impl;

import java.util.LinkedList;
import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {

    /* list of eventuals separators*/
     final String[] separators = new String[]{ "\r\n", "\r", "\n"};

        /* searching separator into lines */
       for(String str : separators){

         /* we have found a seperator*/
         if(lines.indexOf(str) != -1 ){

           /* fill the board ti return*/
           return (new String[] {lines.substring(0,lines.indexOf(str) + str.length()), lines.substring(lines.indexOf(str) + str.length())});
         }
       }

       /* we have not found any separators into line */
        return (new String[] {"", lines});
  }

}
