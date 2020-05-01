package com.projectx.repository;

import com.projectx.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {


    // @Query(value="SELECT p.* FROM predefined_response p WHERE p.question_id=:id", nativeQuery = true)
    // User findAllBycpf(@Param("cpf") String cpf);

    @Query("{ 'document' : ?0 }")
    Optional<User> findBycpf(String cpf);

}
