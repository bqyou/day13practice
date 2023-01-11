package DietDiary.CaloREE.Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class IOutil {

    public static void createDir(String path) {
        File dir = new File(path);
        boolean isCreated = dir.mkdir();

        if (isCreated) {
            String osname = System.getProperty("os.name");
            if (!osname.contains("Windows")) { // for non windows users to set permission to write files
                try {
                    String perm = "rwxrwx---";
                    Set<PosixFilePermission> permissions = PosixFilePermissions.fromString(perm);
                    Files.setPosixFilePermissions(dir.toPath(), permissions);
                } catch (IOException e) {
                    System.out.println("IO EX");
                }
            }
        }
    }

}
