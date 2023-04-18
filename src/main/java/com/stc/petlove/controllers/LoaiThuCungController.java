package com.stc.petlove.controllers;

import com.stc.petlove.dtos.LoaiThuCungDto;
import com.stc.petlove.entities.LoaiThuCung;
import com.stc.petlove.services.loaithucung.LoaiThuCungServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/loai-thu-cung")
public class LoaiThuCungController {
    private final LoaiThuCungServiceImpl loaiThuCungService;

    public LoaiThuCungController(LoaiThuCungServiceImpl loaiThuCungService) {
        this.loaiThuCungService = loaiThuCungService;
    }

    @PostMapping
    public ResponseEntity<?> addLoaiThuCung(@RequestBody LoaiThuCungDto dto) {
        LoaiThuCung addLoaiThuCung = loaiThuCungService.addLoaiThuCung(dto);
        if (addLoaiThuCung == null) {
            log.error("Them " + dto.getMaLoaiThuCung() + " that bai, maLoai da ton tai!");
            return new ResponseEntity<String>("maLoaiThuCung is exist!!!", HttpStatus.BAD_REQUEST);
        }
        log.info("Them " + dto.getMaLoaiThuCung() + " thanh cong!!");
        return new ResponseEntity<LoaiThuCung>(addLoaiThuCung, HttpStatus.CREATED);
    }

    @GetMapping("/readOne")
    public ResponseEntity<?> getLoaiThuCungByMaLoai(@RequestParam String maLoai) {
        LoaiThuCung findLoaiThuCung = loaiThuCungService.findLoaiThuCungByMaLoai(maLoai);
        if (findLoaiThuCung == null) {
            log.error("maLoai " + maLoai + " khong ton tai!");
            return new ResponseEntity<String>("maLoai " + maLoai + " khong ton tai!", HttpStatus.BAD_REQUEST);
        }
        log.info("Doc maLoai " + maLoai + " thanh cong!");
        return new ResponseEntity<LoaiThuCung>(findLoaiThuCung, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateLoaiThuCungByMaLoai(@RequestBody LoaiThuCungDto dto) {
        LoaiThuCung updateLoaiThuCung = loaiThuCungService.updateLoaiThuCungByMaLoai(dto);
        if (updateLoaiThuCung == null) {
            log.error("maLoai " + dto.getMaLoaiThuCung() + " khong ton tai!!");
            return new ResponseEntity<String>("maLoai " + dto.getMaLoaiThuCung() + " khong ton tai!!", HttpStatus.BAD_REQUEST);
        }
        log.info("Update maLoai " + dto.getMaLoaiThuCung() + " thanh cong!");
        return new ResponseEntity<LoaiThuCung>(updateLoaiThuCung, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteLoaiThuCungByMaLoai(@RequestParam String maLoai) {
        boolean deleteLoaiThuCung = loaiThuCungService.deleteLoaiThuCungByMaLoai(maLoai);
        if (!deleteLoaiThuCung) {
            log.error("maLoai " + maLoai + " khong ton tai!!");
            return new ResponseEntity<String>("maLoai " + maLoai + " khong ton tai!!", HttpStatus.BAD_REQUEST);
        }
        log.info("Doc maLoai " + maLoai + " thanh cong!");
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
}
