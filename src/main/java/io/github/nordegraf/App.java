package io.github.nordegraf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import com.threerings.export.b; // Binary Importer
import com.threerings.export.aA; // XML Exporter

class App
{

    /**
     * Collects all .dat files in the Spiral Knights game directory and converts them to
     * XML using the Binary Importer and XML Exporter that ships with the game.
     * Enter the full path to the Spiral Knights game folder in the sk_dir variable.
     * The XML files are written to the output_dir directory.
     * @param args
     */
    public static void main( String[] args )
    {
        try {
            String sk_dir = "/path/to/Spiral Knights/";
            String output_dir = "./Spiral Knights/";
            File parent_dir = new File(sk_dir);

            // create directories
            Collection<File> dirs = FileUtils.listFilesAndDirs(parent_dir, new NotFileFilter(TrueFileFilter.INSTANCE), new ContainsDATFileFilter());
            for (File dir : dirs) {
                Files.createDirectories(Paths.get(dir.toString().replace(sk_dir, output_dir)));
                // System.out.println(dir.toString().replace(sk_dir, output_dir));
            }

            System.out.println("created output directories");

            List<File> files = (List<File>) FileUtils.listFiles(parent_dir, new String[] { "dat" }, true);

            for (File file : files) {
                System.out.println("currently deserializing: " + file.getCanonicalPath());
                deserialize(file.toString(), file.toString().replace(sk_dir, output_dir).replace(".dat", ".xml"));
		    }

            System.out.println("converted every .dat file to .xml");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Converts a .dat file to a .xml file using ThreeRings functions
     * shipped with the game
     * @param source path to a .dat file
     * @param dest path to the .xml file that gets generated
     */
    public static void deserialize(String source, String dest)
    {
        FileInputStream source_stream;
        FileOutputStream dest_stream;
        try {
            source_stream = new FileInputStream(new File(source));
            dest_stream = new FileOutputStream(new File(dest));

            b in = new b(source_stream);
            aA out = new aA(dest_stream);

            Object dat = in.readObject();

            out.writeObject(dat);

            in.close();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}