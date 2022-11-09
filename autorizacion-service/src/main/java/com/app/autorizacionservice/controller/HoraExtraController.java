package com.app.autorizacionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.autorizacionservice.entity.HoraExtra;
import com.app.autorizacionservice.service.HoraExtraService;

@RestController
@RequestMapping("/horaExtra")
public class HoraExtraController {
    
    @Autowired
    HoraExtraService horaExtraService;

    @GetMapping
    public ResponseEntity<List<HoraExtra>> getAll() {
        List<HoraExtra> horasExtras = horaExtraService.obtenerHorasExtras();
        if(horasExtras.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(horasExtras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HoraExtra> getById(@PathVariable("id") Long id) {
        HoraExtra horaExtra = horaExtraService.obtenerHoraExtra(id);
        if(horaExtra == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(horaExtra);
    }

    @PostMapping
    public ResponseEntity<HoraExtra> create(@RequestBody HoraExtra horaExtra) {
        HoraExtra nuevaHoraExtra = horaExtraService.guardarHoraExtra(horaExtra);
        return ResponseEntity.ok(nuevaHoraExtra);
    }
}
