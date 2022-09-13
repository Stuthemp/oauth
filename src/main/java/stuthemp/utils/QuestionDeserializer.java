package stuthemp.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import stuthemp.model.Question;
import stuthemp.model.QuestionType;

import java.io.IOException;

/**
 * Deserializes json array to Question object
 */
public class QuestionDeserializer extends StdDeserializer<Question> {

    public QuestionDeserializer() {
        this(null);
    }

    public QuestionDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Question deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        Question question = new Question();
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);

        String id= node.get("id").asText();
        String questionLocation = node.get("questionLocation").asText();
        String wrongAnswer1 = node.get("wrongAnswer1").asText();
        String wrongAnswer2 = node.get("wrongAnswer2").asText();
        String wrongAnswer3 = node.get("wrongAnswer3").asText();
        String rightAnswer = node.get("rightAnswer").asText();
        String docRef = node.get("docRef").asText();
        JsonNode questionType = node.get("questionType");
        QuestionType qt = QuestionType.get(questionType.asText());

        question.setId(Long.valueOf(id));
        question.setRightAnswer(rightAnswer);
        question.setWrongAnswer1(wrongAnswer1);
        question.setWrongAnswer2(wrongAnswer2);
        question.setWrongAnswer3(wrongAnswer3);
        question.setQuestionLocation(questionLocation);
        question.setDocRef(docRef);
        question.setQuestionType(qt);

        return question;
    }
}
