/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.capicom.wrapper.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rusakovich
 */
public final class NativeUtils {

    private static final Logger logger = Logger.getLogger(NativeUtils.class.getName());

    public static String TEMP_PATH = System.getProperty("java.io.tmpdir");

    private NativeUtils() {
    }

    //Changing the system property later doesn’t 
    //have any effect, since the property is evaluated very early and cached.
    //
    //At first the system property is updated with the new value. 
    //This might be a relative path – or maybe you want to create that path dynamically.
    //The Classloader has a static field (sys_paths) that contains the paths. 
    //If that field is set to null, it is initialized automatically. 
    //Therefore forcing that field to null will result into the reevaluation of the 
    //library path as soon as loadLibrary() is called…
    public static void setLibraryPath(String folder) {
        try {
            System.setProperty("java.library.path", folder);

            Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
            fieldSysPath.setAccessible(true);
            fieldSysPath.set(null, null);
        } catch (Exception ex) {
            String msg = "Error while library path setting";

            logger.log(Level.SEVERE, msg, ex);
            throw new RuntimeException(msg, ex);
        }
    }

    /**
     * Loads library from current JAR archive
     *
     * The file from JAR is copied into system temporary directory and then
     * loaded. The temporary file is deleted after exiting. Method uses String
     * as filename because the pathname is "abstract", not system-dependent.
     *
     * @param path - path to resource inside jar file
     * @throws IllegalArgumentException If source file (param path) does not
     * exist
     * @throws IllegalArgumentException If the path is not absolute or if the
     * filename is shorter than three characters (restriction of {
     * @see File#createTempFile(java.lang.String, java.lang.String)}).
     */
    public static void loadLibraryFromJar(String path) {
        try {
            if (!path.startsWith("/")) {
                throw new IllegalArgumentException("The path to be absolute (start with '/').");
            }

            String fileName = FileUtils.getFileName(path);

            // Prepare temporary file
            File temp = new File(TEMP_PATH + fileName);
            temp.createNewFile();
            temp.deleteOnExit();

            if (!temp.exists()) {
                throw new FileNotFoundException("File " + temp.getAbsolutePath() + " does not exist.");
            }

            FileUtils.copyResourceToFile(path, temp);

            setLibraryPath(temp.getParent());

            // Finally, load the library
            System.load(temp.getAbsolutePath());
        } catch (Exception ex) {
            String msg = "Cannot load library from jar";

            logger.log(Level.SEVERE, msg, ex);
            throw new RuntimeException(msg, ex);
        }
    }
}
