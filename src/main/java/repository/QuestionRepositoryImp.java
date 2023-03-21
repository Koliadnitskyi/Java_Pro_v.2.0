package repository;

import Exceptions.SqlUpdateException;
import model.Question;
import repository.dao.QuestionRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

public class QuestionRepositoryImp implements QuestionRepository {
    private final String getById = "SELECT * FROM Questions where id=?";

    private Question buildQuestion(ResultSet result) {
        try {
            result.next();
            return Question.builder()
                    .id(result.getInt("id"))
                    .text(result.getString("text"))
                    .topic(result.getString("topic"))
                    .build();
        } catch (SQLException e) {
            throw new SqlUpdateException(e.getMessage());
        }
    }

    @Override
    public Question get(int id) {
        try {
            PreparedStatement preparedStatement = ConnectionSingelton.getConnection().prepareStatement(getById);
            preparedStatement.setInt(1, id);
            ResultSet question = preparedStatement.executeQuery();
            return new QuestionRepositoryImp().buildQuestion(question);
        } catch (SQLException e) {
            throw new SqlUpdateException(e.getMessage());
        }
    }

    @Override
    public Question getRandom() {
        try {
            PreparedStatement preparedStatement = ConnectionSingelton.getConnection().prepareStatement(getById);
            preparedStatement.setInt(1, ThreadLocalRandom.current().nextInt(1, new QuestionRepositoryImp().getSizeTables()));
            ResultSet question = preparedStatement.executeQuery();
            return new QuestionRepositoryImp().buildQuestion(question);
        } catch (SQLException e) {
            throw new SqlUpdateException(e.getMessage());
        }
    }

    @Override
    public int getSizeTables() {
        try {
            int tmp = 0;
            PreparedStatement preparedStatement = ConnectionSingelton.getConnection().prepareStatement("SELECT * FROM Questions");
            ResultSet question = preparedStatement.executeQuery();
            while (question.next()) {
                tmp++;
            }
            return tmp;
        } catch (SQLException e) {
            throw new SqlUpdateException(e.getMessage());
        }
    }

    @Override
    public void save(Question question) {
        try {
            PreparedStatement preparedStatement = ConnectionSingelton.getConnection().prepareStatement("INSERT INTO Questions VALUE (?,?,?)");
            preparedStatement.setInt(1, question.getId());
            preparedStatement.setString(2, question.getText());
            preparedStatement.setString(3, question.getTopic());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SqlUpdateException(e.getMessage());
        }
    }
}
