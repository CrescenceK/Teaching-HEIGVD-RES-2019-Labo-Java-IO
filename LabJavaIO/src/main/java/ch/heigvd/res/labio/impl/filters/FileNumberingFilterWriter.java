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
  private int numberOfLine = 1;


  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

    //recovering our strings
    String[] strings = Utils.getNextLine(str.substring(off, off + len));

    // print the first line number.
    if(numberOfLine == 1){
        out.write((numberOfLine++) + "\t");
    }

    //write the first the current line and the next line number.
    while (strings[0].length() != 0) {
      out.write(strings[0] + (numberOfLine++) + "\t");
      strings = Utils.getNextLine(strings[1]);
    }

      // restart the process for other lines.
      out.write(strings[1]);
    }


  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
       this.write(new String(cbuf), off, len);
  }

  @Override
  public void write(int c) throws IOException {

    int charBefore = 0;

    // print the first line number.
    if(numberOfLine == 1){
      out.write((numberOfLine++) + "\t");
    }

    //
    if(c == '\n') {
      out.write(c);
      out.write((numberOfLine++) + "\t");
    } else {
      out.write(c);
    }
   /* } else if (charBefore  == '\r'){
     out.write((numberOfLine) + "\t");
      out.write(c);
    } else {
      out.write(c);
    }
    charBefore = c; */
  }

}
