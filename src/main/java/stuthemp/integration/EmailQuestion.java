package stuthemp.integration;

import stuthemp.model.Question;
import stuthemp.model.QuestionType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

/**
 * todo Document type EmailQuestion
 */
public class EmailQuestion {

    private String questionLocation;

    private String rightAnswer;

    private String wrongAnswer1;

    private String wrongAnswer2;

    private String wrongAnswer3;

    private String docRef;

    private String questionType;

    private String sentBy;

    private String sentAt;

    public EmailQuestion(String sentBy, String sentAt) {
        this.sentBy = sentBy;
        this.sentAt = sentAt;
    }

    public String getQuestionLocation() {
        return questionLocation;
    }

    public void setQuestionLocation(String questionLocation) {
        this.questionLocation = questionLocation;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public void setWrongAnswer1(String wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    public void setWrongAnswer3(String wrongAnswer3) {
        this.wrongAnswer3 = wrongAnswer3;
    }

    public String getDocRef() {
        return docRef;
    }

    public void setDocRef(String docRef) {
        this.docRef = docRef;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public String getSentAt() {
        return sentAt;
    }

    public void setSentAt(String sentAt) {
        this.sentAt = sentAt;
    }

    @Override
    public String toString() {
        return "EmailQuestion{" +
            "questionLocation='" + questionLocation + '\'' +
            ", rightAnswer='" + rightAnswer + '\'' +
            ", wrongAnswer1='" + wrongAnswer1 + '\'' +
            ", wrongAnswer2='" + wrongAnswer2 + '\'' +
            ", wrongAnswer3='" + wrongAnswer3 + '\'' +
            ", docRef='" + docRef + '\'' +
            ", questionType='" + questionType + '\'' +
            ", sentBy='" + sentBy + '\'' +
            ", sentAt='" + sentAt + '\'' +
            '}';
    }
}
