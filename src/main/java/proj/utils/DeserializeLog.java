package proj.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import proj.model.QuestionLog;

import java.io.IOException;

/**
 * todo Document type DesirializeLog
 */
public class DeserializeLog extends StdDeserializer<QuestionLog> {

    public DeserializeLog() {
        this(null);
    }

    public DeserializeLog(Class<?> vc) {
        super(vc);
    }

    @Override
    public QuestionLog deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);

        String id = node.get("id").asText();
        String question = node.get("questionLocation").asText();
        String rightAnswer = node.get("rightAnswer").asText();
        String wrongAnswer1 = node.get("wrongAnswer1").asText();
        String wrongAnswer2= node.get("wrongAnswer2").asText();
        String wrongAnswer3= node.get("wrongAnswer3").asText();
        String questionType = node.get("questionType").asText();
        String docRef = node.get("docRef").asText();
        String createdAt = node.get("createdAt").asText();
        String createdBy= node.get("createdBy").asText();

        QuestionLog questionLog = new QuestionLog(id,question,rightAnswer,wrongAnswer1,wrongAnswer2,wrongAnswer3,
            questionType, docRef, createdAt, createdBy);

        return questionLog;
    }
}
