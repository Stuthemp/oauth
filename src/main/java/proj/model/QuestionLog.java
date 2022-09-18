package proj.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * todo Document type QuestionLog
 */
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionLog {
    @Id
    @JsonProperty("id")
    private String id;
    @JsonProperty("questionLocation")
    private String question;
    @JsonProperty("rightAnswer")
    private String rightAnswer;
    @JsonProperty("wrongAnswer1")
    private String wrongAnswer1;
    @JsonProperty("wrongAnswer2")
    private String wrongAnswer2;
    @JsonProperty("wrongAnswer3")
    private String wrongAnswer3;
    @JsonProperty("questionType")
    private String type;
    @JsonProperty("docRef")
    private String dpcRef;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("createdBy")
    private String createdBy;

    public QuestionLog(String id, String question, String rightAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3, String type,
        String dpcRef, String createdAt, String createdBy) {
        this.id = id;
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
        this.type = type;
        this.dpcRef = dpcRef;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public QuestionLog() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public void setWrongAnswer1(String wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }

    public void setWrongAnswer3(String wrongAnswer3) {
        this.wrongAnswer3 = wrongAnswer3;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDpcRef(String dpcRef) {
        this.dpcRef = dpcRef;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    public String getType() {
        return type;
    }

    public String getDpcRef() {
        return dpcRef;
    }
}
