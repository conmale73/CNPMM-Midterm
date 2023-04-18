package com.stc.petlove.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoaiThuCungDto {
    private String id;

    // mã không được trùng
    private String maLoaiThuCung;

    private String tenLoaiThuCung;

    private boolean trangThai = false;
}