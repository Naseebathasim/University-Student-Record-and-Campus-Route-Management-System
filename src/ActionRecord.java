public class ActionRecord {
    private String actionType;
    private String studentId;
    private String details;

    public ActionRecord(String actionType, String studentId, String details) {
        this.actionType = actionType;
        this.studentId = studentId;
        this.details = details;
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

    @Override
    public String toString() {
        return actionType + " | Student ID: " + studentId + " | " + details;
    }
}