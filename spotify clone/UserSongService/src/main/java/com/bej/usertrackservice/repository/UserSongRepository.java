package com.bej.usertrackservice.repository;

import com.bej.usertrackservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserSongRepository extends MongoRepository<User,String> {
    User findByEmail(String email);
}
