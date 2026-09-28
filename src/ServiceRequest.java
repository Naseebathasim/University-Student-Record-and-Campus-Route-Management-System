import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServiceRequest {
    private static int counter = 1; // auto-incrementing request ID

    private int requestId;
    private String studentId;
    private String description;
    private String timestamp;

    public ServiceRequest(String studentId, String description) {
        this.requestId = counter++;
        this.studentId = studentId;
        this.description = description;
        this.timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public int getRequestId() {
        return requestId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getDescription() {
        return description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("Request #%d | Student ID: %s | %s | Submitted: %s",
                requestId, studentId, description, timestamp);
    }
}