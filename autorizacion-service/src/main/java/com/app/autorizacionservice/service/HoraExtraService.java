package com.app.autorizacionservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.autorizacionservice.entity.HoraExtra;
import com.app.autorizacionservice.repository.HoraExtraRepository;

@Service
public class HoraExtraService {
    @Autowired
    HoraExtraRepository horaExtraRepository;

    public List<HoraExtra> obtenerHorasExtras() {
        return horaExtraRepository.findAll();
    }

    public HoraExtra obtenerHoraExtra(Long id) {
        return horaExtraRepository.findById(id).orElse(null);
    }

    public HoraExtra guardarHoraExtra(HoraExtra horaExtra) {
        HoraExtra nuevaHoraExtra = horaExtraRepository.save(horaExtra);
        return nuevaHoraExtra;
    }
}
