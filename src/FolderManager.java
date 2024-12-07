import java.io.File;

public class FolderManager {
    public static void createSaveFolder() {
        String directoryPath = "X:\\Applications\\IntelliJ IDEA Community Edition 2023.3.5\\bin\\Saves";
        File folder = new File(directoryPath);
        if (!folder.exists()) folder.mkdirs();
    }
}





