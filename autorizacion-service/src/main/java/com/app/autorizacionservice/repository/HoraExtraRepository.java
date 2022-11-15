package com.app.autorizacionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.autorizacionservice.entity.HoraExtra;

@Repository
public interface HoraExtraRepository extends JpaRepository<HoraExtra, Long> {
    @Query(value = "select * from horas_extras h where h.mes = :mes and h.anio = :anio and h.rut_empleado = :rut", 
            nativeQuery = true)
    public HoraExtra findHoraExtraByRutFecha(@Param("mes") int mes, @Param("anio") int anio, @Param("rut") String rut);
}
