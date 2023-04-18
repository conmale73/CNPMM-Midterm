package com.stc.petlove.services.taikhoan;

import com.stc.petlove.dtos.UserDto;
import com.stc.petlove.entities.TaiKhoan;
import com.stc.petlove.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public TaiKhoan addUser(UserDto dto) {
        if(userRepository.existsByEmail(dto.getEmail()))
            return null;
        TaiKhoan taiKhoan =
                new TaiKhoan(null, dto.getName(), dto.getEmail(), dto.getPassword(), dto.getDienThoai(), dto.getRoles(), dto.isTrangThai());
        userRepository.insert(taiKhoan);
        return taiKhoan;
    }

    @Override
    public TaiKhoan findUserByEmail(String email) {
        TaiKhoan findTaiKhoan = userRepository.findByEmail(email);
        return findTaiKhoan;
    }

    @Override
    public TaiKhoan updateUserByEmail(UserDto dto) {
        TaiKhoan updateTaiKhoan = userRepository.findByEmail(dto.getEmail());
        if(updateTaiKhoan == null)
            return null;
        updateTaiKhoan.setName(dto.getName());
        updateTaiKhoan.setPassword(dto.getPassword());
        updateTaiKhoan.setDienThoai(dto.getDienThoai());
        updateTaiKhoan.setRoles(dto.getRoles());
        updateTaiKhoan.setTrangThai(dto.isTrangThai());
        updateTaiKhoan = userRepository.save(updateTaiKhoan);
        return updateTaiKhoan;
    }

    @Override
    public boolean deleteUserByEmail(String email) {
        TaiKhoan updateTaiKhoan = userRepository.findByEmail(email);
        if(updateTaiKhoan == null)
            return false;
        updateTaiKhoan.setTrangThai(false);
        updateTaiKhoan = userRepository.save(updateTaiKhoan);
        return true;
    }
}
