package com.vrizki.BelajarSpringboot.controller;

import com.vrizki.BelajarSpringboot.model.Buku;
import com.vrizki.BelajarSpringboot.repository.BukuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/buku")
public class BukuController {

    @Autowired
    BukuRepository bukuRepository;


    @GetMapping("/")
    public List<Buku> getAll(){
        return bukuRepository.findAll();
    }

    @PostMapping("/")
    public Buku tambahbuku(@Valid @RequestBody Buku buku) {
        return bukuRepository.save(buku);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Buku> updateBuku(@PathVariable(value="id")Long id,
                                           @Valid @RequestBody Buku detailbuku){
        Buku buku = bukuRepository.getOne(id);
        if(buku == null)
            return ResponseEntity.notFound().build();
        buku.setTitleBook(detailbuku.getTitleBook());
        buku.setNamaDepanPengarang(detailbuku.getNamaDepanPengarang());
        buku.setNamaBelakangPengarang(detailbuku.getNamaBelakangPengarang());
        buku.setNamaPeminjam(detailbuku.getNamaPeminjam());
        buku.setStatusPeminjaman(detailbuku.getStatusPeminjaman());
        Buku updatedBuku = bukuRepository.save(buku);
        return ResponseEntity.ok(updatedBuku);
    }

//    @DeleteMapping("/{Id}")
//    public String deleteBuku(@PathVariable (value="Id") Long Id){
//        Buku buku = bukuRepository.getOne(Id);
//        String result = "";
//        if(buku == null) {
//            result = "id "+Id+" tidak ditemukan";
//            return result;
//        }
//        result = "id "+Id+" berhasil di hapus";
//        bukuRepository.Delete(Id);
//        return result;
//    }

//    @GetMapping("/{Id}")
//    public ResponseEntity<Buku> getBukuById(@PathVariable(value="Id") Long Id){
//        Buku buku = bukuRepository.getOne(Id);
//        if(buku == null) return ResponseEntity.notFound().build();
//        return ResponseEntity.ok().body(buku);
//    }

//@GetMapping("/sortid")
//public List<Buku> sortid(@RequestParam(value="id")Long Id){
//    return bukuRepository.findByIdBukus(Id);
//}
    //http://localhost:8080/buku/sortbuku?title=Buku
    @GetMapping("/sortbuku")
    public List<Buku> sortbuku(@RequestParam(value="title")String titleBook){
        return bukuRepository.findByTitleBook(titleBook);
    }

    //http://localhost:8080/buku/sortstatus/1
    @GetMapping("/sortstatus/{stspinjam}")
    public List<Buku> sortstatus(@PathVariable(value="stspinjam") int sts){
        return bukuRepository.findByStatusPeminjaman(sts);
    }

    @GetMapping("/sortpengarang/{nadep}")
    public List<Buku> sortnamadepanpengarang(@PathVariable(value="nadep") String nadep){
        return bukuRepository.findBynamaDepanPengarang(nadep);
    }

    @GetMapping("/hitungbuku/{judul}")
    public Integer hitungBuku(@PathVariable(value="judul") String judul){
        return bukuRepository.countByTitleBook(judul);
    }

    @GetMapping("/cari/{id}")
    public Optional<Buku> hitungBuku(@PathVariable(value="id") Long id){
        return bukuRepository.findById(id);
    }

}