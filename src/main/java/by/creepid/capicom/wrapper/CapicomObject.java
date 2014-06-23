/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.capicom.wrapper;


import by.creepid.capicom.wrapper.utils.NativeUtils;
import by.creepid.capicom.wrapper.utils.Platform;
import com.jacob.com.Dispatch;

/**
 *
 * @author rusakovich
 */
public abstract class CapicomObject {

    private static String X86_PATH = "/x86/jacob-1.18-M2-x86.dll";
    private static String X64_PATH = "/x86/jacob-1.18-M2-x64.dll";

    private static void loadLibraries() {
        String path = (Platform.is64Bit()) ? X64_PATH : X86_PATH;
        NativeUtils.loadLibraryFromJar(path);
    }

    static {
        loadLibraries();
    }

    abstract Dispatch getObject();

}
