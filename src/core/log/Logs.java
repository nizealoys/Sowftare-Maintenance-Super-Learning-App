package src.core.log;

import java.util.ArrayList;

// Le Log est chelou ?!?
public class Logs {

    private final static Logs instance = new Logs();
    private final ArrayList<LogElement> logs;

    private Logs() {
        logs = new ArrayList<>();
    }

    public static Logs getInstance() {
        return instance;
    }

    public void addLog(String log) {
        logs.add(new LogElement(log));
    }

    public String[] getLogs() {
        String[] logArray = new String[logs.size()];
        for (int i = 0; i < logs.size(); i++) {
            logArray[i] = logs.get(i).getLog() + " (Timestamp: " + logs.get(i).getTimestamp() + ")";
        }
        return logArray;
    }
}
