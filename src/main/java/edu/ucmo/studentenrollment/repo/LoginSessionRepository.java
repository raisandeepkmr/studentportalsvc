package edu.ucmo.studentenrollment.repo;

import edu.ucmo.studentenrollment.model.LoginSession;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface LoginSessionRepository extends MongoRepository<LoginSession, String> {
    @Query("{email: '?0'}")
    LoginSession findLoginSessionByEmail(String email);
    @Query("{token: '?0'}")
    LoginSession findLoginSessionByToken(String token);
    @DeleteQuery("{email: '?0'}")
    LoginSession deleteLoginSessionByEmail(String email);
    List<LoginSession> findAll();
    LoginSession save(LoginSession loginSession);
}
