package com.stc.petlove.services.loaithucung;

import com.stc.petlove.dtos.LoaiThuCungDto;
import com.stc.petlove.entities.LoaiThuCung;

public interface LoaiThuCungService {
    LoaiThuCung addLoaiThuCung(LoaiThuCungDto dto);
    LoaiThuCung findLoaiThuCungByMaLoai(String maLoai);

    LoaiThuCung updateLoaiThuCungByMaLoai(LoaiThuCungDto dto);

    boolean deleteLoaiThuCungByMaLoai(String maLoai);
}
