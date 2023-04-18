package com.stc.petlove.services.dichvu;

import com.stc.petlove.dtos.DichVuDto;
import com.stc.petlove.entities.DichVu;
import com.stc.petlove.repositories.DichVuRepository;
import org.springframework.stereotype.Service;

@Service
public class DichVuServiceImpl implements DichVuService{

    private final DichVuRepository dichVuRepository;

    public DichVuServiceImpl(DichVuRepository dichVuRepository) {
        this.dichVuRepository = dichVuRepository;
    }

    @Override
    public DichVu addDichVu(DichVuDto dto) {
        if(dichVuRepository.existsByMaDichVu(dto.getMaDichVu()))
            return null;
        DichVu dichVu =
                new DichVu(null, dto.getMaDichVu(), dto.getTenDichVu(), dto.getNoiDung(), dto.getGiaDichVus(), dto.isTrangThai());
        dichVuRepository.insert(dichVu);
        return dichVu;
    }

    @Override
    public DichVu findDichVuByMaDV(String maDV) {
        DichVu findDichVu = dichVuRepository.findByMaDichVu(maDV);
        return findDichVu;
    }

    @Override
    public DichVu updateDichVuByMaDV(DichVuDto dto) {
        DichVu updateDichVu = dichVuRepository.findByMaDichVu(dto.getMaDichVu());
        if(updateDichVu == null)
            return null;
        updateDichVu.setTenDichVu(dto.getTenDichVu());
        updateDichVu.setNoiDung(dto.getNoiDung());
        updateDichVu.setGiaDichVus(dto.getGiaDichVus());
        updateDichVu.setTrangThai(dto.isTrangThai());
        updateDichVu = dichVuRepository.save(updateDichVu);
        return updateDichVu;
    }

    @Override
    public boolean deleteDichVuByMaDV(String maDV) {
        DichVu dichVu = findDichVuByMaDV(maDV);
        if (dichVu == null)
            return false;
        dichVuRepository.delete(dichVu);
        return true;
    }
}
