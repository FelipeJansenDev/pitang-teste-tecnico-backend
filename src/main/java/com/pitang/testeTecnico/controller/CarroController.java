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

    /**
     * Retornar todos os carros cadastrados
     * @return uma lista com os carros cadastrados no sistema
     */
    @GetMapping
    public ResponseEntity<Set<CarroDTO>> getCarros()  {
        return new ResponseEntity<>(carroService.getAll(), HttpStatus.OK);
    }

    /**
     * Criar um novo carro
     * @param carroDTO Objeto com as informações do carro
     * @return Objeto com o novo carro cadastrado
     */
    @PostMapping
    public ResponseEntity<CarroDTO> createCarro(@Valid @RequestBody CarroDTO carroDTO)  {
        return new ResponseEntity<>(carroService.create(carroDTO), HttpStatus.CREATED);
    }

    /**
     * Retornar carro através do ID
     * @param id id do carro
     * @return Objeto com o carro selecionado
     */
    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> getCarro(@PathVariable Long id)  {
        CarroDTO carroDTO = carroService.getCarro(id);
        return new ResponseEntity<>(carroDTO, carroDTO.getId() == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    /**
     * Deletar carro
     * @param id id do carro
     * @return caso o carro exista, irá retornar o status 200, caso contrário, o status 404
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<CarroDTO> deleteCarro(@PathVariable Long id)  {
        CarroDTO carroDTO = carroService.deleteCarro(id);
        return new ResponseEntity<>(carroDTO, carroDTO == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    /**
     * Atualizar carro
     * @param id id do carro
     * @param carroDTO objeto com as novad informações do carro
     * @return O carro com as novas informações
     */
    @PutMapping("/{id}")
    public ResponseEntity<CarroDTO> updateCarro(@PathVariable Long id, @Valid @RequestBody CarroDTO carroDTO)  {
        CarroDTO carroDTO1 = carroService.updateCarro(id, carroDTO);
        return new ResponseEntity<>(carroDTO1, HttpStatus.OK);
    }

}
