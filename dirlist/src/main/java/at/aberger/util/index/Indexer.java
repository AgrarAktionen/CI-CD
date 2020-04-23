/**
 * A directory Indexer
 * (c) http://www.aberger.at (2020)
 */
package at.aberger.util.index;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Indexer {
    public static void main(String args[]) throws IOException {
        new Indexer().createIndex(Path.of(args.length > 0 ? args[0] : System.getProperty("user.dir")));
    }
    private void createIndex(Path folder) throws IOException {
        var parent = folder.toString() + "/";
        Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (!Files.isDirectory(file)) {
                    var path = file.toString().replace(parent, "");
                    System.out.println(path);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
