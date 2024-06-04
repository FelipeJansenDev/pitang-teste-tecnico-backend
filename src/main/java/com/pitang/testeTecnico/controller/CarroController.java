package com.pitang.testeTecnico.controller;

import com.pitang.testeTecnico.model.dto.CarroDTO;
import com.pitang.testeTecnico.service.CarroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/cars")
public class CarroController {

    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @GetMapping
    public ResponseEntity<Set<CarroDTO>> getCarros()  {
        return new ResponseEntity<>(carroService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarroDTO> createCarro(@Valid @RequestBody CarroDTO carroDTO)  {
        return new ResponseEntity<>(carroService.create(carroDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> getCarro(@PathVariable Long id)  {
        CarroDTO carroDTO = carroService.getCarro(id);
        return new ResponseEntity<>(carroDTO, carroDTO.getId() == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarroDTO> deleteCarro(@PathVariable Long id)  {
        CarroDTO carroDTO = carroService.deleteCarro(id);
        return new ResponseEntity<>(carroDTO, carroDTO == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarroDTO> updateCarro(@PathVariable Long id, @Valid @RequestBody CarroDTO carroDTO)  {
        CarroDTO carroDTO1 = carroService.updateCarro(id, carroDTO);
        return new ResponseEntity<>(carroDTO1, HttpStatus.OK);
    }

}
