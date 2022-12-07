package edu.ucmo.studentenrollment.repo;

import edu.ucmo.studentenrollment.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AdminRepository extends MongoRepository<Admin, String> {
    @Query("{email: '?0'}")
    Admin findAdminByEmail(String email);
    List<Admin> findAll();
}
