package stuthemp.model;

import javax.persistence.*;

/**
 * Main domain class. Represents the question.
 */
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
        generator = "seq-generator")
    @SequenceGenerator(name = "seq-generator", sequenceName = "questions_id_seq", allocationSize = 1)
    private Long id;

    private String questionLocation;
    private String wrongAnswer1;
    private String wrongAnswer2;

    private String wrongAnswer3;
    private String rightAnswer;
    private String docRef;
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    public boolean semanticEquality(Question question) {
        return this.toString().equals(question.toString());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
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

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getDocRef() {
        return docRef;
    }

    public void setDocRef(String docRef) {
        this.docRef = docRef;
    }

    public String getQuestionLocation() {
        return questionLocation;
    }

    public void setQuestionLocation(String questionLocation) {
        this.questionLocation = questionLocation;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    @Override
    public String toString() {
        return "{" +
            "id=" + id +
            ", questionLocation='" + questionLocation + '\'' +
            ", wrongAnswer1='" + wrongAnswer1 + '\'' +
            ", wrongAnswer2='" + wrongAnswer2 + '\'' +
            ", wrongAnswer3='" + wrongAnswer3 + '\'' +
            ", rightAnswer='" + rightAnswer + '\'' +
            ", docRef='" + docRef + '\'' +
            ", questionType=" + questionType +
            '}';
    }
}
