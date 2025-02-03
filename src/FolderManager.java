import java.io.File;

public class FolderManager {

    public static void createSaveFolder() {
        String directoryPath = "X:\\Saves";
        File folder = new File(directoryPath);
        if (!folder.exists()) folder.mkdirs();
    }
}





