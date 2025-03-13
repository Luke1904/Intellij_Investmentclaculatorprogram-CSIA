import java.io.File;

public class FolderManager {

    /**
     * Creates a folder named "Saves" at the specified directory path if it does not already exist.
     * The folder is created at the root of the X: drive.
     */
    public static void createSaveFolder() {
        // Define the path where the folder will be created
        String directoryPath = "X:\\Saves";

        // Create a File object representing the folder
        File folder = new File(directoryPath);

        // Check if the folder does not already exist
        if (!folder.exists()) {
            // Create the folder and any necessary parent directories
            folder.mkdirs();
        }
    }
}