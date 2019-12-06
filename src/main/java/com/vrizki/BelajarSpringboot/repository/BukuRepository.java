package com.vrizki.BelajarSpringboot.repository;

import com.vrizki.BelajarSpringboot.model.Buku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface BukuRepository extends JpaRepository<Buku, Long> {
    List<Buku> findByStatusPeminjaman (int statusPeminjaman);
    List<Buku> findByTitleBook(String titleBook);
    List<Buku> findBynamaDepanPengarang(String namaDepanPengarang);
    Integer countByTitleBook(String titleBook);
    Buku findById (long Id);
}

