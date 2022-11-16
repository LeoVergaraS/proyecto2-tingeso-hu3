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

    @GetMapping("/verificar/{mes}/{anio}/{rut}")
    public ResponseEntity<Double> verificarSiTieneHorasExtras(@PathVariable("mes") int mes, @PathVariable("anio") int anio, @PathVariable("rut") String rut){
        double horasExtras = horaExtraService.verificarSiTieneHorasExtras(mes, anio, rut);
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

    @PostMapping("/file")
    public ResponseEntity<String> createFromFile() {
        boolean creado = horaExtraService.guardarDelArchivo();
        if(creado){
            return ResponseEntity.ok("Se crearon las horas extras");
        }
        return ResponseEntity.badRequest().body("No se pudieron crear las horas extras");
    }
}
