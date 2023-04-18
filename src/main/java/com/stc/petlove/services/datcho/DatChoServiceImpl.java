package com.stc.petlove.services.datcho;

import com.stc.petlove.dtos.DatChoDto;
import com.stc.petlove.entities.DatCho;
import com.stc.petlove.repositories.DatChoRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class DatChoServiceImpl implements DatChoService{
    private final DatChoRepository datChoRepository;

    public DatChoServiceImpl(DatChoRepository datChoRepository) {
        this.datChoRepository = datChoRepository;
    }

    @Override
    public DatCho addDatCho(@Valid  DatChoDto dto) {
        if(datChoRepository.existsByEmail(dto.getEmail()))
            return null;
        DatCho datCho =
                new DatCho(null, dto.getEmail(), dto.getThongTinDatChos(), dto.getThoiGian(), dto.getCanDan(), dto.getTrangThaiDatCho(), dto.isTrangThai());
        datChoRepository.insert(datCho);
        return datCho;
    }

    @Override
    public DatCho findDatChoByEmail(String email) {
        DatCho findDatCho = datChoRepository.findByEmail(email);
        return findDatCho;
    }

    @Override
    public DatCho updateDatChoByEmail(DatChoDto dto) {
        DatCho updateDatCho = datChoRepository.findByEmail(dto.getEmail());
        if(updateDatCho == null)
            return null;
        updateDatCho.setThongTinDatChos(dto.getThongTinDatChos());
        updateDatCho.setThoiGian(dto.getThoiGian());
        updateDatCho.setCanDan(dto.getCanDan());
        updateDatCho.setTrangThaiDatCho(dto.getTrangThaiDatCho());
        updateDatCho.setTrangThai(dto.isTrangThai());
        updateDatCho = datChoRepository.save(updateDatCho);
        return updateDatCho;
    }

    @Override
    public boolean deleteDatChoByEmail(String email) {
        DatCho datCho = findDatChoByEmail(email);
        if (datCho == null)
            return false;
        datChoRepository.delete(datCho);
        return true;
    }
}
