package com.app.autorizacionservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.autorizacionservice.entity.HoraExtra;
import com.app.autorizacionservice.models.IngresoSalida;
import com.app.autorizacionservice.repository.HoraExtraRepository;

@Service
public class HoraExtraService {
    @Autowired
    HoraExtraRepository horaExtraRepository;

    RestTemplate restTemplate = new RestTemplate();

    public List<HoraExtra> obtenerHorasExtras() {
        return horaExtraRepository.findAll();
    }

    public HoraExtra obtenerHoraExtra(Long id) {
        return horaExtraRepository.findById(id).orElse(null);
    }

    public HoraExtra crearHoraExtra(HoraExtra horaExtra) {
        HoraExtra horaExtraCreada = horaExtraRepository.save(horaExtra);
        return horaExtraCreada;
    }

    public HoraExtra obtenerPorEmpleadoFecha(int mes, int anio, String rut){
        return horaExtraRepository.findHoraExtraByRutFecha(mes, anio, rut);
    }

    private double calculoHorasExtras(IngresoSalida ingresoSalida){
        String[] tiempoSeparado = ingresoSalida.getHora().toString().split(":");
        int horas = Integer.valueOf(tiempoSeparado[0]);
        int min = Integer.valueOf(tiempoSeparado[1]);
        int total = horas * 60 + min;
        double calculo = (total - 1080)/60;
        return calculo;
    }

    public double verificarSiTieneHorasExtras(int mes, int anio, String rut){
        HoraExtra horaExtra = horaExtraRepository.findHoraExtraByRutFecha(mes, anio, rut);
        if(horaExtra == null){
            return 0;
        }
        if(horaExtra.getAutorizado() == 1){
            return horaExtra.getCantidadHorasExtras();
        }
        return 0;
    }

    public boolean guardarDelArchivo() {
        IngresoSalida[] horasExtras = restTemplate.getForObject("http://localhost:8001/ingresosSalidas/salidas", IngresoSalida[].class); 
        if(horasExtras != null) {
            String[] fechaSeparada = horasExtras[0].getFecha().toString().split("-");
            int anio = Integer.valueOf(fechaSeparada[0]);
            int mes = Integer.valueOf(fechaSeparada[1]);
            for(IngresoSalida he:horasExtras){
                String rut = he.getRutEmpleado();
                HoraExtra horaExtra = horaExtraRepository.findHoraExtraByRutFecha(mes, anio, rut);
                if(horaExtra != null) {
                    double cantHorasExtras = horaExtra.getCantidadHorasExtras() + Math.floor(calculoHorasExtras(he));
                    horaExtra.setCantidadHorasExtras(cantHorasExtras);
                    horaExtraRepository.save(horaExtra);
                }else{
                    double cantHorasExtras = Math.floor(calculoHorasExtras(he));
                    horaExtraRepository.save(new HoraExtra(null, mes, anio, cantHorasExtras, 0, rut));
                }
            }
            return true;
        }
        return false;
    }
}
