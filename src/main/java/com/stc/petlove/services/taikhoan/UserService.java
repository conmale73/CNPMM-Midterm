package com.stc.petlove.services.taikhoan;

import com.stc.petlove.dtos.UserDto;
import com.stc.petlove.entities.TaiKhoan;

public interface UserService {
    TaiKhoan addUser(UserDto dto);

    TaiKhoan findUserByEmail(String email);

    TaiKhoan updateUserByEmail(UserDto dto);

    boolean deleteUserByEmail(String email);
}
