package com.stc.petlove.controllers;

import com.stc.petlove.dtos.UserDto;
import com.stc.petlove.entities.TaiKhoan;
import com.stc.petlove.services.taikhoan.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/tai-khoan")
public class TaiKhoanController {
    private final UserServiceImpl userService;

    public TaiKhoanController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody UserDto dto) {
        TaiKhoan addUser = userService.addUser(dto);
        if (addUser == null) {
            log.error("Them " + dto.getEmail() + " is failed, email da ton tai!");
            return new ResponseEntity<String>("email da ton tai!", HttpStatus.BAD_REQUEST);
        }
        log.info("Them " + dto.getEmail() + " thanh cong!");
        return new ResponseEntity<TaiKhoan>(addUser, HttpStatus.CREATED);
    }

    @GetMapping("/readOne")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
        TaiKhoan findUser = userService.findUserByEmail(email);
        if (findUser == null) {
            log.error("Email " + email + " khong ton tai!");
            return new ResponseEntity<String>("Email " + email + " khong ton tai!!", HttpStatus.BAD_REQUEST);
        }
        log.info("Doc email " + email + " thanh cong!");
        return new ResponseEntity<TaiKhoan>(findUser, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateUserByEmail(@RequestBody UserDto dto) {
        TaiKhoan updateUser = userService.updateUserByEmail(dto);
        if (updateUser == null) {
            log.error("Email " + dto.getEmail() + " khong ton tai!!");
            return new ResponseEntity<String>("Email " + dto.getEmail() + " khong ton tai!!", HttpStatus.BAD_REQUEST);
        }
        log.info("Update email " + dto.getEmail() + " thanh cong!");
        return new ResponseEntity<TaiKhoan>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserByEmail(@RequestParam String email) {
        boolean deleteUser = userService.deleteUserByEmail(email);
        if (!deleteUser) {
            log.error("Email " + email + " khong ton tai!!");
            return new ResponseEntity<String>("email " + email + " is not exist!!!", HttpStatus.BAD_REQUEST);
        }
        log.info("Doc email " + email + " thanh cong!");
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
}
