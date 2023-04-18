package com.stc.petlove.services.loaithucung;

import com.stc.petlove.dtos.LoaiThuCungDto;
import com.stc.petlove.entities.LoaiThuCung;
import com.stc.petlove.repositories.LoaiThuCungRepository;
import org.springframework.stereotype.Service;

@Service
public class LoaiThuCungServiceImpl implements LoaiThuCungService {
    private final LoaiThuCungRepository loaiThuCungRepository;

    public LoaiThuCungServiceImpl(LoaiThuCungRepository loaiThuCungRepository) {
        this.loaiThuCungRepository = loaiThuCungRepository;
    }

    @Override
    public LoaiThuCung addLoaiThuCung(LoaiThuCungDto dto) {
        if(loaiThuCungRepository.existsByMaLoaiThuCung(dto.getMaLoaiThuCung()))
            return null;
        LoaiThuCung loaiThuCung =
                new LoaiThuCung(null, dto.getMaLoaiThuCung(), dto.getTenLoaiThuCung(), dto.isTrangThai());
        loaiThuCungRepository.insert(loaiThuCung);
        return loaiThuCung;
    }

    @Override
    public LoaiThuCung findLoaiThuCungByMaLoai(String maLoai) {
        LoaiThuCung findLoaiThuCung = loaiThuCungRepository.findByMaLoaiThuCung(maLoai);
        return findLoaiThuCung;
    }

    @Override
    public LoaiThuCung updateLoaiThuCungByMaLoai(LoaiThuCungDto dto) {
        LoaiThuCung updateLoaiThuCung = loaiThuCungRepository.findByMaLoaiThuCung(dto.getMaLoaiThuCung());
        if(updateLoaiThuCung == null)
            return null;
        updateLoaiThuCung.setTenLoaiThuCung(dto.getTenLoaiThuCung());
        updateLoaiThuCung.setTrangThai(dto.isTrangThai());
        updateLoaiThuCung = loaiThuCungRepository.save(updateLoaiThuCung);
        return updateLoaiThuCung;
    }

    @Override
    public boolean deleteLoaiThuCungByMaLoai(String maLoai) {
        LoaiThuCung loaiThuCung = findLoaiThuCungByMaLoai(maLoai);
        if (loaiThuCung == null)
            return false;
        loaiThuCungRepository.delete(loaiThuCung);
        return true;
    }

}
