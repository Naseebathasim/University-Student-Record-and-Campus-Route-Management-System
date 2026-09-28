import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActionRecord {
    private String actionType;   // "ADD", "UPDATE", or "DELETE"
    private String studentId;
    private String details;
    private String timestamp;

    public ActionRecord(String actionType, String studentId, String details) {
        this.actionType = actionType;
        this.studentId = studentId;
        this.details = details;
        this.timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getActionType() {
        return actionType;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getDetails() {
        return details;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s -> Student ID: %s | %s",
                timestamp, actionType, studentId, details);
    }
}