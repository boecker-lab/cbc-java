package de.unijena.bioinf.FragmentationTreeConstruction.computation.tree.ilp;

import java.util.List;

public class CLPLibs {
    private final String prefix;
    private final String jniWrapper;
    private final List<String> jarDependencies;
    private final List<String> pathDependencies;

    private CLPLibs(String prefix, String jniWrapper, List<String> jarDependencies, List<String> pathDependencies) {
        this.prefix = prefix;
        this.jniWrapper = jniWrapper;
        this.jarDependencies = jarDependencies;
        this.pathDependencies = pathDependencies;
    }

    public List<String> getJarDependencies() {
        return jarDependencies;
    }

    public String getJniWrapper() {
        return jniWrapper;
    }

    public String getPrefix() {
        return prefix;
    }

    public List<String> getPathDependencies() {
        return pathDependencies;
    }


    public static CLPLibs of(String os, String arch){
        String prefix;
        String jniWrapper;
        List<String> jarDependencies;
        List<String> pathDependencies;

//		System.out.println("os: " + os + ", arch: " + arch);
        if (os.contains("win")) {
            // NOTE: has to be in correct order for windows
            prefix = "/win-x86-64/";
            jniWrapper = prefix + "CLPModelWrapper_JNI.dll";
            jarDependencies = List.of(
                    prefix + "libwinpthread-1.dll",
                    prefix + "libgcc_s_seh-1.dll",
                    prefix + "libstdc++-6.dll",
                    prefix + "libgmp-10.dll",
                    prefix + "zlib1.dll",
                    prefix + "libbz2-1.dll",
                    prefix + "libquadmath-0.dll",
                    prefix + "libgfortran-5.dll"
            );
            pathDependencies = List.of(
                    "winpthread",
                    "gcc_s_seh",
                    "stdc++",
                    "gmp",
                    "zlib1",
                    "bz2",
                    "quadmath",
                    "fortran"
            );
        } else if (os.contains("mac") || os.contains("darwin")) {
            if (arch.contains("arm64") || os.contains("aarch64")) {
                prefix = "/mac-arm64/";
                jniWrapper = prefix + "libCLPModelWrapper_JNI.dylib";
                // fake pre-loading to circumvent lazy unpacking of resources
                jarDependencies = List.of(
                        prefix + "libc++.1.dylib",
                        prefix + "libCbc.3.dylib",
                        prefix + "libClp.1.dylib",
                        prefix + "libCoinUtils.3.dylib",
                        prefix + "libgcc_s.1.1.dylib",
                        prefix + "libgfortran.5.dylib",
                        prefix + "libOsiClp.1.dylib",
                        prefix + "libquadmath.0.dylib"
                );
                pathDependencies = List.of(
                        "c++",
                        "Cbc",
                        "Clp",
                        "CoinUtils",
                        "gcc_s",
                        "gfortran",
                        "OsiClp",
                        "quadmath"
                );
            } else {
                prefix = "/mac-x86-64/";
                jniWrapper = prefix + "libCLPModelWrapper_JNI.dylib";
                // fake pre-loading to circumvent lazy unpacking of resources
                jarDependencies = List.of(
                        prefix + "libCbc.3.dylib",
                        prefix + "libCgl.1.dylib",
                        prefix + "libClp.1.dylib",
                        prefix + "libClpSolver.1.dylib",
                        prefix + "libCoinUtils.3.dylib",
                        prefix + "libOsi.1.dylib",
                        prefix + "libOsiClp.1.dylib"
                );
                pathDependencies = List.of(
                        "Cbc",
                        "Cgl",
                        "Clp",
                        "ClpSolver",
                        "CoinUtils",
                        "Osi",
                        "OsiClp"
                );
            }
        } else {
            prefix = "/linux-x86-64/";
            jniWrapper = prefix + "libCLPModelWrapper_JNI.so";

            // to load from bundled jar
            jarDependencies = List.of(
                    prefix + "libCbc.so.3",
                    prefix + "libCgl.so.1",
                    prefix + "libClp.so.1",
                    prefix + "libCoinUtils.so.3",
//                    prefix + "libgcc_s.so.1",
                    prefix + "libgfortran.so.5",
                    prefix + "libOsi.so.1",
                    prefix + "libOsiClp.so.1",
                    prefix + "libquadmath.so.0",
                    prefix + "libstdc++.so.6"
            );
            pathDependencies = List.of(
                    "Cbc",
                    "Cgl",
                    "Clp",
                    "CoinUtils",
//                    "gcc_s", causes problems if not gcc ist used on the system (conda)?
                    "gfortran",
                    "Osi",
                    "OsiClp",
                    "quadmath",
                    "stdc++"
            );
        }
        return new CLPLibs(prefix, jniWrapper, jarDependencies, pathDependencies);
    }

}
