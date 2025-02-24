package de.unijena.bioinf.FragmentationTreeConstruction.computation.tree.ilp;

import java.util.List;

public class CLPLibs {
    public static final String LINUX_JNI_WRAPPER_NAME = "libCLPModelWrapper_JNI.so";
    public static final String LINUX_ARM64_PREFIX = "/linux-arm64/";
    public static final List<String> LINUX_ARM64_JAR = List.of(
            LINUX_ARM64_PREFIX + "libstdc++.so.6",

            LINUX_ARM64_PREFIX + "libCoinUtils.so.3",
            LINUX_ARM64_PREFIX + "libOsi.so.1",
            LINUX_ARM64_PREFIX + "libClp.so.1",
            LINUX_ARM64_PREFIX + "libOsiClp.so.1",
            LINUX_ARM64_PREFIX + "libCgl.so.1",
            LINUX_ARM64_PREFIX + "libCbc.so.3"
    );
    ;
    public static final List<String> LINUX_ARM64_PATH = List.of(
            "stdc++",

            "CoinUtils",
            "Osi",
            "Clp",
            "OsiClp",
            "Cgl",
            "Cbc"
    );

    public static final String LINUX_X86_64_PREFIX = "/linux-x86-64/";
    public static final List<String> LINUX_X86_64_JAR = List.of(
            LINUX_X86_64_PREFIX + "libquadmath.so.0",
            LINUX_X86_64_PREFIX + "libgfortran.so.5",
            LINUX_X86_64_PREFIX + "liblapack.so.3",
            LINUX_X86_64_PREFIX + "libblas.so.3",

            LINUX_X86_64_PREFIX + "libstdc++.so.6",

            LINUX_X86_64_PREFIX + "libCoinUtils.so.3",
            LINUX_X86_64_PREFIX + "libOsi.so.1",
            LINUX_X86_64_PREFIX + "libClp.so.1",
            LINUX_X86_64_PREFIX + "libOsiClp.so.1",
            LINUX_X86_64_PREFIX + "libCgl.so.1",
            LINUX_X86_64_PREFIX + "libCbc.so.3"
    );
    ;
    public static final List<String> LINUX_X86_64_PATH = List.of(
            "quadmath",
            "gfortran",
            "lapack",
            "blas",

            "stdc++",

            "CoinUtils",
            "Osi",
            "Clp",
            "OsiClp",
            "Cgl",
            "Cbc"
    );

    public static final String MAC_JNI_WRAPPER_NAME = "libCLPModelWrapper_JNI.dylib";
    public static final String MAC_ARM64_PREFIX = "/mac-arm64/";
    public static final List<String> MAC_ARM64_JAR = List.of(
            // MAC_ARM64_PREFIX + "libc++.1.dylib",
            MAC_ARM64_PREFIX + "libCbc.3.dylib",
            MAC_ARM64_PREFIX + "libCgl.0.dylib",
            MAC_ARM64_PREFIX + "libClp.0.dylib",
            MAC_ARM64_PREFIX + "libClpSolver.0.dylib",
            MAC_ARM64_PREFIX + "libCoinUtils.3.dylib",
            // MAC_ARM64_PREFIX + "libgcc_s.1.1.dylib",
            // MAC_ARM64_PREFIX + "libgfortran.5.dylib",
            MAC_ARM64_PREFIX + "libOsi.0.dylib",
            MAC_ARM64_PREFIX + "libOsiClp.0.dylib"
            // MAC_ARM64_PREFIX + "libquadmath.0.dylib",
            // MAC_ARM64_PREFIX + "liblapack.3.dylib",
            // MAC_ARM64_PREFIX + "libomp.dylib",
            // MAC_ARM64_PREFIX + "libreadline.8.dylib",
            // MAC_ARM64_PREFIX + "libncurses.6.dylib"
    );
    public static final List<String> MAC_ARM64_PATH = List.of(
            // "c++",
            "Cbc",
            "Cgl",
            "Clp",
            "ClpSolver",
            "CoinUtils",
            // "gcc_s",
            // "gfortran",
            "Osi",
            "OsiClp"
            // "quadmath",
            // "lapack",
            // "omp",
            // "libreadline",
            // "ncurses"
    );
    ;

    public static final String MAC_X86_64_PREFIX = "/mac-x86-64/";
    ;
    public static final List<String> MAC_X86_64_JAR = List.of(
            MAC_X86_64_PREFIX + "libCbc.3.dylib",
            MAC_X86_64_PREFIX + "libCgl.1.dylib",
            MAC_X86_64_PREFIX + "libClp.1.dylib",
            MAC_X86_64_PREFIX + "libClpSolver.1.dylib",
            MAC_X86_64_PREFIX + "libCoinUtils.3.dylib",
            MAC_X86_64_PREFIX + "libOsi.1.dylib",
            MAC_X86_64_PREFIX + "libOsiClp.1.dylib"
    );
    ;
    public static final List<String> MAC_X86_64_PATH = List.of(
            "Cbc",
            "Cgl",
            "Clp",
            "ClpSolver",
            "CoinUtils",
            "Osi",
            "OsiClp"
    );


    public static final String WIN_JNI_WRAPPER_NAME = "CLPModelWrapper_JNI.dll";
    public static final String WIN_X86_64_PREFIX = "/win-x86-64/";
    public static final List<String> WIN_X86_64_JAR = List.of(
            WIN_X86_64_PREFIX + "libwinpthread-1.dll",
            WIN_X86_64_PREFIX + "libgcc_s_seh-1.dll",
            WIN_X86_64_PREFIX + "libstdc++-6.dll",
            WIN_X86_64_PREFIX + "libgmp-10.dll",
            WIN_X86_64_PREFIX + "zlib1.dll",
            WIN_X86_64_PREFIX + "libbz2-1.dll",
            WIN_X86_64_PREFIX + "libquadmath-0.dll",
            WIN_X86_64_PREFIX + "libgfortran-5.dll"
    );
    public static final List<String> WIN_X86_64_PATH = List.of(
            "winpthread",
            "gcc_s_seh",
            "stdc++",
            "gmp",
            "zlib1",
            "bz2",
            "quadmath",
            "fortran"
    );


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


    public static CLPLibs of(String os, String arch) {
        if (isMac(os)) {
            if (isARM64(arch)) {
                return new CLPLibs(MAC_ARM64_PREFIX, MAC_ARM64_PREFIX + MAC_JNI_WRAPPER_NAME, MAC_ARM64_JAR, MAC_ARM64_PATH);
            } else {
                return new CLPLibs(MAC_X86_64_PREFIX, MAC_X86_64_PREFIX + MAC_JNI_WRAPPER_NAME, MAC_X86_64_JAR, MAC_X86_64_PATH);
            }
        } else if (isWindows(os)) {
            // NOTE: has to be in correct order for windows
            return new CLPLibs(WIN_X86_64_PREFIX, WIN_X86_64_PREFIX + WIN_JNI_WRAPPER_NAME, WIN_X86_64_JAR, WIN_X86_64_PATH);
        } else {
            if (isARM64(arch)) {
                return new CLPLibs(LINUX_ARM64_PREFIX, LINUX_ARM64_PREFIX + LINUX_JNI_WRAPPER_NAME, LINUX_ARM64_JAR, LINUX_ARM64_PATH);
            } else {
                return new CLPLibs(LINUX_X86_64_PREFIX, LINUX_X86_64_PREFIX + LINUX_JNI_WRAPPER_NAME, LINUX_X86_64_JAR, LINUX_X86_64_PATH);
            }
        }
    }


    private static boolean isWindows(String os) {
        if (os == null)
            return false;
        os = os.toLowerCase();
        return os.contains("win");
    }

    private static boolean isMac(String os) {
        if (os == null)
            return false;
        os = os.toLowerCase();
        return os.contains("mac") || os.contains("darwin");
    }

    private static boolean isARM64(String arch) {
        if (arch == null)
            return false;
        arch = arch.toLowerCase();
        return arch.contains("arm64") || arch.contains("aarch64") || arch.contains("arm");
    }
}
