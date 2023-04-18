package com.stc.petlove.controllers;

import com.stc.petlove.dtos.DichVuDto;
import com.stc.petlove.entities.DichVu;
import com.stc.petlove.services.dichvu.DichVuServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/dich-vu")
public class DichVuController {
    private final DichVuServiceImpl dichVuService;

    public DichVuController(DichVuServiceImpl dichVuService) {
        this.dichVuService = dichVuService;
    }

    @PostMapping
    public ResponseEntity<?> addDichVu(@RequestBody DichVuDto dto) {
        DichVu addDichVu = dichVuService.addDichVu(dto);
        if (addDichVu == null) {
            log.error("Them " + dto.getMaDichVu() + " khong thanh cong, maLoai da ton tai!");
            return new ResponseEntity<String>("maDichVu da ton tai!", HttpStatus.BAD_REQUEST);
        }
        log.info("Them " + dto.getMaDichVu() + " thanh cong!");
        return new ResponseEntity<DichVu>(addDichVu, HttpStatus.CREATED);
    }

    @GetMapping("/readOne")
    public ResponseEntity<?> getDichVuByMaLoai(@RequestParam String maDV) {
        DichVu findDichVu = dichVuService.findDichVuByMaDV(maDV);
        if (findDichVu == null) {
            log.error("maDV " + maDV + " khong ton tai!");
            return new ResponseEntity<String>("maDV " + maDV + " khong ton tai!", HttpStatus.BAD_REQUEST);
        }
        log.info("Doc maDV " + maDV + " thanh cong!");
        return new ResponseEntity<DichVu>(findDichVu, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateDichVuByMaLoai(@RequestBody DichVuDto dto) {
        DichVu updateDichVu = dichVuService.updateDichVuByMaDV(dto);
        if (updateDichVu == null) {
            log.error("maDV " + dto.getMaDichVu() + " khong ton tai!");
            return new ResponseEntity<String>("maDV " + dto.getMaDichVu() + " khong ton tai!", HttpStatus.BAD_REQUEST);
        }
        log.info("Update maDV " + dto.getMaDichVu() + " thanh cong!");
        return new ResponseEntity<DichVu>(updateDichVu, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteDichVuByMaLoai(@RequestParam String maDV) {
        boolean deleteDichVu = dichVuService.deleteDichVuByMaDV(maDV);
        if (!deleteDichVu) {
            log.error("maDV " + maDV + " khong ton tai!");
            return new ResponseEntity<String>("maDV " + maDV + " khong ton tai!", HttpStatus.BAD_REQUEST);
        }
        log.info("Doc maDV " + maDV + " thanh cong!");
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
}
