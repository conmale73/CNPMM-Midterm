package com.stc.petlove.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String id;

    private String name;

    // email không được trùng nhau
    private String email;

    private String password;

    private String dienThoai;

    private List<String> roles = new ArrayList<>();

    private boolean trangThai = true;
}