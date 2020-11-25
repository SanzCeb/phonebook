package phonebook.stopwatch;

import java.time.Duration;
import java.time.Instant;

public class StopWatch {
    private Instant start = null;
    private Instant end = null;
    private Duration elapsedTime = Duration.ZERO;

    public void start() throws  StopWatchAlreadyRunningException {
        if (start != null ){
            throw new StopWatchAlreadyRunningException();
        }
        start = Instant.now();
    }

    public void stop() throws  StopWatchNotStartedException {
        if (start == null ){
            throw new StopWatchNotStartedException();
        }
        end = Instant.now();
        elapsedTime = Duration.between(start, end);
    }

    public void reset() {
        start = null;
        end = null;
        elapsedTime = Duration.ZERO;
    }

    public long getElapsedMinutes() {
        return elapsedTime.toMinutesPart();
    }

    public long getElapsedSeconds() {
        return elapsedTime.toSecondsPart();
    }

    public long getElapsedMilliSeconds() {
        return elapsedTime.toMillisPart();
    }

}
