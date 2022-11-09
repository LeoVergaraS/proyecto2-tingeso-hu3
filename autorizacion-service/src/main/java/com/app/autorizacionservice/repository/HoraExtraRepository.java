package com.app.autorizacionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.autorizacionservice.entity.HoraExtra;

@Repository
public interface HoraExtraRepository extends JpaRepository<HoraExtra, Long> {
}
