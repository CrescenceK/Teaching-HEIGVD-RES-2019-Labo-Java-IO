package ch.heigvd.res.labio.impl.explorers;

import ch.heigvd.res.labio.interfaces.IFileExplorer;
import ch.heigvd.res.labio.interfaces.IFileVisitor;
import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {

    /* visit current directory */
    vistor.visit(rootDirectory);

    File[] directories = rootDirectory.listFiles(new FileFilter() {
      @Override
      public boolean accept(File pathname) {
        return pathname.isDirectory();
      }
    });

    File[] files = rootDirectory.listFiles(new FileFilter() {
      @Override
      public boolean accept(File pathname) {
        return !pathname.isDirectory();
      }
    });

    /* visit sub-directories */
    if(directories != null) {
      Arrays.sort(directories);
      for (File f : directories) {
        if (f != null) {
          explore(f, vistor);
        }
      }
    }

    /* visit files */
    if(files != null) {
      Arrays.sort(files);
      for (File f : files) {
        if (f != null) {
          vistor.visit(f);
        }
      }
    }
  }
}
