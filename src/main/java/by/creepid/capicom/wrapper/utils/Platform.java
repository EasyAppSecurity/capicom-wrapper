/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.capicom.wrapper.utils;

/**
 *
 * @author rusakovich
 */
public final class Platform {
    
    private Platform(){
    }
    
    //While it works if a user is running a 64bit JVM on a 64bit system. 
    //It doesn't work if the user is running a 32bit JVM on a 64 bit system.

    //The following code works for properly detecting Windows 64-bit operating systems. 
    //On a Windows 64 bit system the environment variable "Programfiles(x86)" will be set. 
    //It will NOT be set on a 32-bit system and java will read it as null.
    public static boolean is64Bit() {
        boolean is64bit = false;

        if (System.getProperty("os.name").contains("Windows")) {
            is64bit = (System.getenv("ProgramFiles(x86)") != null);
        } else {
            is64bit = (System.getProperty("os.arch").indexOf("64") != -1);
        }

        return is64bit;
    }

}
