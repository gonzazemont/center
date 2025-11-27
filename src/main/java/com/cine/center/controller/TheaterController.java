package com.cine.center.controller;

import com.cine.center.dto.theater.TheaterRequestDTO;
import com.cine.center.dto.theater.TheaterResponseDTO;
import com.cine.center.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/theaters")
public class TheaterController {

    private final TheaterService service;

    //create theater
    @PostMapping
    public ResponseEntity<TheaterResponseDTO> create(TheaterRequestDTO dto){
        TheaterResponseDTO createdTheater = service.create(dto);
        return ResponseEntity.ok(createdTheater);
    }

    //get all theaters
    @GetMapping
    public ResponseEntity<List<TheaterResponseDTO>> getAll(){
        List<TheaterResponseDTO> theaters = service.getAll();
        return ResponseEntity.ok(theaters);
    }

    //get theater by id
    @GetMapping("/{id}")
    public ResponseEntity<TheaterResponseDTO> getById(Long id) {
        TheaterResponseDTO theater = service.getById(id);
        return ResponseEntity.ok(theater);
    }

    //update theater
    @PutMapping("/{id}")
    public ResponseEntity<TheaterResponseDTO> update(@PathVariable Long id, @RequestBody TheaterRequestDTO dto) {
        TheaterResponseDTO updatedTheater = service.update(id, dto);
        return ResponseEntity.ok(updatedTheater);
    }

    //delete theater
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
