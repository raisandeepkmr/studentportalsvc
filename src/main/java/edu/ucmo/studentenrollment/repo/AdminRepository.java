package edu.ucmo.studentenrollment.repo;

import edu.ucmo.studentenrollment.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AdminRepository extends MongoRepository<Admin, String> {
    @Query("{name: '?0'}")
    Admin findAdminByName(String name);
    List<Admin> findAll();
}
