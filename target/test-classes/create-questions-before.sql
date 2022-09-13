delete from questions;
insert into questions(id, right_answer, wrong_answer1, wrong_answer2, wrong_answer3, doc_ref, question_location, question_type) values
(1, 'A', 'B', 'C', 'D', 'http', 'C://', 'EMBED_TYPES'),
(2, '1', '2', '3', '4', 'https', 'D://', 'EXCEPTIONS');

alter sequence questions_id_seq restart with 10;
