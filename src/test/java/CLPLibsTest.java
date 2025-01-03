import de.unijena.bioinf.FragmentationTreeConstruction.computation.tree.ilp.CLPLibs;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;


public class CLPLibsTest {

    @ParameterizedTest
    @MethodSource("osArchProvider")
    public void parseSystemArchitectureTest(String os, String arch, String expectedPrefix, List<String> expectedJar, List<String> expectedPath) {
        CLPLibs clpLibs = CLPLibs.of(os, arch);
        assertEquals(expectedPrefix, clpLibs.getPrefix());
        assertLinesMatch(expectedJar, clpLibs.getJarDependencies());
        assertLinesMatch(expectedPath, clpLibs.getPathDependencies());
    }


    private static Stream<Arguments> osArchProvider() {
        return Stream.of(
                Arguments.of("windows", "x86_64", CLPLibs.WIN_X86_64_PREFIX, CLPLibs.WIN_X86_64_JAR, CLPLibs.WIN_X86_64_PATH),
                Arguments.of("windows", "x86-64", CLPLibs.WIN_X86_64_PREFIX, CLPLibs.WIN_X86_64_JAR, CLPLibs.WIN_X86_64_PATH),
                Arguments.of("windows", "amd64", CLPLibs.WIN_X86_64_PREFIX, CLPLibs.WIN_X86_64_JAR, CLPLibs.WIN_X86_64_PATH), // windows amd64 maps to win-x86_64

                Arguments.of("mac", "arm64", CLPLibs.MAC_ARM64_PREFIX, CLPLibs.MAC_ARM64_JAR, CLPLibs.MAC_ARM64_PATH),
                Arguments.of("mac", "aarch64", CLPLibs.MAC_ARM64_PREFIX, CLPLibs.MAC_ARM64_JAR, CLPLibs.MAC_ARM64_PATH), //mac aarch64 maps to mac-arm64
                Arguments.of("darwin", "x86_64", CLPLibs.MAC_X86_64_PREFIX, CLPLibs.MAC_X86_64_JAR, CLPLibs.MAC_X86_64_PATH),
                Arguments.of("darwin", "x86-64", CLPLibs.MAC_X86_64_PREFIX, CLPLibs.MAC_X86_64_JAR, CLPLibs.MAC_X86_64_PATH),
                Arguments.of("darwin", "amd64",  CLPLibs.MAC_X86_64_PREFIX, CLPLibs.MAC_X86_64_JAR, CLPLibs.MAC_X86_64_PATH), //darwin amd64 maps to mac-x86_64

                Arguments.of("linux", "arm64", CLPLibs.LINUX_ARM64_PREFIX, CLPLibs.LINUX_ARM64_JAR, CLPLibs.LINUX_ARM64_PATH),
                Arguments.of("linux", "aarch64", CLPLibs.LINUX_ARM64_PREFIX, CLPLibs.LINUX_ARM64_JAR, CLPLibs.LINUX_ARM64_PATH), // Tests fallback behavior for aarch64 on Linux
                Arguments.of("linux", "amd64", CLPLibs.LINUX_X86_64_PREFIX, CLPLibs.LINUX_X86_64_JAR, CLPLibs.LINUX_X86_64_PATH),
                Arguments.of("LiNuX", "x86_64", CLPLibs.LINUX_X86_64_PREFIX, CLPLibs.LINUX_X86_64_JAR, CLPLibs.LINUX_X86_64_PATH),  // Test case insensitivity
                Arguments.of("LiNuX", "x86-64", CLPLibs.LINUX_X86_64_PREFIX, CLPLibs.LINUX_X86_64_JAR, CLPLibs.LINUX_X86_64_PATH),  // Test case insensitivity
                Arguments.of("some_unknown_os", "x86_64", CLPLibs.LINUX_X86_64_PREFIX, CLPLibs.LINUX_X86_64_JAR, CLPLibs.LINUX_X86_64_PATH) // Test unknown OS
        );
    }

}
