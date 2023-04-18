package com.stc.petlove.controllers;

import com.stc.petlove.dtos.DatChoDto;
import com.stc.petlove.entities.DatCho;
import com.stc.petlove.services.datcho.DatChoServiceImpl;
import com.stc.petlove.utils.EnumTrangThaiDatCho;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/dat-cho")
public class DatChoController {
    private final DatChoServiceImpl datChoService;

    public DatChoController(DatChoServiceImpl datChoService) {
        this.datChoService = datChoService;
    }

    @PostMapping
    public ResponseEntity<?> addDatCho(@RequestBody DatChoDto dto) {
        if(!isInEnumTrangThaiDatCho(dto.getTrangThaiDatCho().trim()))
            return new ResponseEntity<String>("trangThaiDatCho khong ton tai!", HttpStatus.BAD_REQUEST);

        DatCho addDatCho = datChoService.addDatCho(dto);
        if (addDatCho == null) {
            log.error("Them " + dto.getEmail() + " khong thanh cong, maLoai da ton tai!");
            return new ResponseEntity<String>("email DatCho da ton tai!", HttpStatus.BAD_REQUEST);
        }
        log.info("Them " + dto.getEmail() + " thanh cong!");
        return new ResponseEntity<DatCho>(addDatCho, HttpStatus.CREATED);
    }

    public boolean isInEnumTrangThaiDatCho(String value) {
        try {
            EnumTrangThaiDatCho enumTrangThaiDatCho = EnumTrangThaiDatCho.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @GetMapping("/readOne")
    public ResponseEntity<?> getDatChoByEmail(@RequestParam String email) {
        DatCho findDatCho = datChoService.findDatChoByEmail(email);
        if (findDatCho == null) {
            log.error("Email " + email + " khong ton tai!");
            return new ResponseEntity<String>("email " + email + " khong ton tai!", HttpStatus.BAD_REQUEST);
        }
        log.info("Doc email " + email + " thanh cong!");
        return new ResponseEntity<DatCho>(findDatCho, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateDatChoByEmail(@RequestBody DatChoDto dto) {
        DatCho updateDatCho = datChoService.updateDatChoByEmail(dto);
        if (updateDatCho == null) {
            log.error("Email " + dto.getEmail() + " khong ton tai!");
            return new ResponseEntity<String>("email " + dto.getEmail() + " khong ton tai!", HttpStatus.BAD_REQUEST);
        }
        log.info("Update email " + dto.getEmail() + " thanh cong!");
        return new ResponseEntity<DatCho>(updateDatCho, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteDatChoByEmail(@RequestParam String email) {
        boolean deleteDatCho = datChoService.deleteDatChoByEmail(email);
        if (!deleteDatCho) {
            log.error("Email " + email + " khong ton tai!");
            return new ResponseEntity<String>("email " + email + " khong ton tai!", HttpStatus.BAD_REQUEST);
        }
        log.info("Doc email " + email + " thanh cong!");
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
}
