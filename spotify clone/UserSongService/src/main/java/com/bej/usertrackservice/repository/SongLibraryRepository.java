package com.bej.usertrackservice.repository;

import com.bej.usertrackservice.domain.SongsDatabaseLibrary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongLibraryRepository extends MongoRepository<SongsDatabaseLibrary,String> {

}
