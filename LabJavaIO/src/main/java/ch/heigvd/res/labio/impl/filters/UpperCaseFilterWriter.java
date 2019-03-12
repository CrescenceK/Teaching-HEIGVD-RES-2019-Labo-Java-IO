package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

    String st = str.toUpperCase();
    super.write(st, off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

    char[] tab = new char[cbuf.length];
    int i = 0;
        for(char carac : cbuf){
          tab[i++] = Character.toUpperCase(carac);
         }
      super.write(tab, off, len);
  }

  @Override
  public void write(int c) throws IOException {
    int num = Character.toUpperCase(c);
    super.write(num);
  }
}
