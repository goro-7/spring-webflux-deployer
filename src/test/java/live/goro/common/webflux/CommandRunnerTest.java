package live.goro.common.webflux;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandRunnerTest {

    @Test
    void shouldRunCommand() {
        int status = CommandRunner.run("git fetch --all");
        assertEquals(status, 0);
    }

    @Test
    void shouldGitReset() {
        int status = CommandRunner.gitReset();
        assertEquals(status, 0);
    }
}

