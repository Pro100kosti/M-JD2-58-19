package by.pvt.service;

public enum MessageType {

    INVITATION_MESSAGE("Dear %s, You are invited to course", "Invitation"),
    CANCEL_MESSAGE("Dear %s, Course is canceled", "Cancel")
    ;

    private String body;
    private String subject;

    MessageType(String body, String subject) {
        this.body = body;
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public String getSubject() {
        return subject;
    }
}
