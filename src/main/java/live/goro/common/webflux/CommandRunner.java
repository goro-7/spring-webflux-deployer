package live.goro.common.webflux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public interface CommandRunner {
    Logger log = LoggerFactory.getLogger(CommandRunner.class);
    Runtime runtime = Runtime.getRuntime();

    String GIT_FETCH = "git fetch --all";
    String GIT_RESET = "git reset --hard origin/master";
    String MVN_COMPILE = "mvn compile -DskipTests";

    static int run(String command) {
        log.info("Executing command : " + command);

        Process exec = null;
        try {
            exec = runtime.exec(command);
            List<String> errors = printProcessStreams(exec.getErrorStream());
            List<String> logs = printProcessStreams(exec.getInputStream());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        while (exec.isAlive()) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int exitValue = exec.exitValue();
        log.info("Exec result = " + exitValue);
        return exitValue;
    }

    static List<String> printProcessStreams(InputStream errorStream) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(errorStream))) {
            List<String> lines = br.lines().collect(Collectors.toList());
            lines.forEach(line -> {
                System.out.println(line);
                log.info(line);
            });
            return lines;
        }
    }


    static int recompile() {
        int fetch = run(GIT_FETCH);
        int reset = run(GIT_RESET);
        int compile = run(MVN_COMPILE);
        int result = fetch + reset + compile;
        log.info("Hot Reload result : {}", result);
        return result;
    }
}
