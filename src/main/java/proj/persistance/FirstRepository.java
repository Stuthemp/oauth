package proj.persistance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import proj.model.QuestionLog;

import java.util.List;

/**
 * todo Document type FirstRepository
 */
@Repository
public interface FirstRepository extends MongoRepository<QuestionLog, String> {

}
