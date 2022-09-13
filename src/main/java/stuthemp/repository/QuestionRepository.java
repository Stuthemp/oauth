package stuthemp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stuthemp.model.Question;
import stuthemp.model.QuestionType;

import java.util.List;
import java.util.Optional;

/**
 * todo Document type QuestionRepository
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> getQuestionsByQuestionType(QuestionType type);

    Optional<Question> getQuestionById(Long id);


}
