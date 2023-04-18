package com.stc.petlove.repositories;

import com.stc.petlove.entities.DatCho;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DatChoRepository extends MongoRepository<DatCho, String> {
    DatCho findByEmail(String email);
    boolean existsByEmail(String email);
}
