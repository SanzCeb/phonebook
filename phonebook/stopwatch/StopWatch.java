package phonebook.stopwatch;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

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
    public long getElapsedMinutesPart() {
        return getElapsedTime().toMinutesPart();
    }

    public long getElapsedSecondsPart() {
        return getElapsedTime().toSecondsPart();
    }

    public long getElapsedMilliSecondsPart() {

        return getElapsedTime().toMillisPart();
    }

    public long getElapsedMilliSeconds() {
        return getElapsedTime().toMillis();}

    public String getElapsedFormattedTime() {
        return String.format("%d min. %d sec. %d ms.", getElapsedMinutesPart(), getElapsedSecondsPart(), getElapsedMilliSecondsPart());
    }

    private Duration getElapsedTime() {
        return elapsedTime == Duration.ZERO && Objects.nonNull(start) ?
                Duration.between(start, Instant.now()) :
                elapsedTime;
    }
}
