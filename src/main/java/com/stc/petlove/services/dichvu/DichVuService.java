package com.stc.petlove.services.dichvu;

import com.stc.petlove.dtos.DichVuDto;
import com.stc.petlove.entities.DichVu;

public interface DichVuService {
    DichVu addDichVu(DichVuDto dto);

    DichVu findDichVuByMaDV(String maDV);

    DichVu updateDichVuByMaDV(DichVuDto dto);

    boolean deleteDichVuByMaDV(String maDV);
}
