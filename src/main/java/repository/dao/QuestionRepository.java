package repository.dao;

import model.Question;

public interface QuestionRepository {
    Question get(int id);

    Question getRandom();

    int getSizeTables();

    void save(Question question);
}
