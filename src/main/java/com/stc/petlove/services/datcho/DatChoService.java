package com.stc.petlove.services.datcho;

import com.stc.petlove.dtos.DatChoDto;
import com.stc.petlove.entities.DatCho;

public interface DatChoService {
    DatCho addDatCho(DatChoDto dto);

    DatCho findDatChoByEmail(String email);

    DatCho updateDatChoByEmail(DatChoDto dto);

    boolean deleteDatChoByEmail(String email);
}
