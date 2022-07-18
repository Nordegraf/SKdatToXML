package io.github.nordegraf;

import java.io.File;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.AbstractFileFilter;

public class ContainsDATFileFilter extends AbstractFileFilter
{
    @Override
    public boolean accept(final File file, String name) {
        System.out.println(name);
        if (file.isDirectory()) {
            Collection<File> files = FileUtils.listFiles(file, new String[] { "dat" }, true);
            return !files.isEmpty();
        } else {
            return name.endsWith(".dat");
        }
    }

    @Override
    public boolean accept(final File file) {
        if (file.isDirectory()) {
            Collection<File> files = FileUtils.listFiles(file, new String[] { "dat" }, true);
            return !files.isEmpty();
        } else {
            return file.getName().endsWith(".dat");
        }
    }

    @Override
    public FileVisitResult accept(final Path file, final BasicFileAttributes attributes) {
        return accept(file.toFile()) ? FileVisitResult.CONTINUE : FileVisitResult.TERMINATE;
    }


}