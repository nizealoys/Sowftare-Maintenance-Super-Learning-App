package core.log;

public class LogElement {
    private final String log;
    private final long timestamp;

    public LogElement(String log) {
        this.log = log;
        this.timestamp = System.currentTimeMillis();
    }

    public String getLog() {
        return log;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
