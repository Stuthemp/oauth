package stuthemp.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum for types of questions
 */
public enum QuestionType {

    EMBED_TYPES("embed_types"), EXCEPTIONS("exceptions"), INHERITANCE("inheritance");

    private final String name;

    private static final Map<String, QuestionType> lookup = new HashMap<String, QuestionType>();

    static {
        for (QuestionType d : QuestionType.values()) {
            lookup.put(d.getName(), d);
        }
    }

    private QuestionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static QuestionType get(String name) {
        return lookup.get(name);
    }



}
