package ch.heigvd.res.labio.impl.filters;

import ch.heigvd.res.labio.impl.Utils;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

    /* first sentence */
    String current = Utils.getNextLine(str.substring(off, off + len))[0];
    /* next sentences */
    String next    =  Utils.getNextLine(str.substring(off, off + len))[1];
    int i = 1;
    //String string = "";


    String string = i == 1 ? i + "\t" : "" ;
    i++;

    while (current.length() != 0) {
      string += current + i + "\t";
      current = Utils.getNextLine(next)[0];
      i++;
    }

    string += next;
    super.write(string, 0, string.length());
    }


  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

  }

  @Override
  public void write(int c) throws IOException {
    throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
