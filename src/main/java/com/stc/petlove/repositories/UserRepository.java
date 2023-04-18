package com.stc.petlove.repositories;

import com.stc.petlove.entities.TaiKhoan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<TaiKhoan, String> {
    TaiKhoan findByEmail(String email);
    boolean existsByEmail(String email);
}
