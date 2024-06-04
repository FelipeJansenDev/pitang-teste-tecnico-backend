package com.pitang.testeTecnico.service;

import com.pitang.testeTecnico.exceptions.LicensePlateExistenteException;
import com.pitang.testeTecnico.mapper.CarroMapper;
import com.pitang.testeTecnico.model.Carro;
import com.pitang.testeTecnico.model.dto.CarroDTO;
import com.pitang.testeTecnico.model.dto.MeDTO;
import com.pitang.testeTecnico.repository.CarroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarroServiceTest {

    @InjectMocks
    private CarroService carroService;

    @Mock
    CarroRepository carroRepository;

    @Mock
    CarroMapper carroMapper;

    @Mock
    AuthService authService;

    @Mock
    MeDTO meDTO;

    @Mock
    CarroDTO carroDTO;

    @Mock
    Carro carro;

    Set<CarroDTO> carroDTOset = new HashSet<>();

    @Test
    void getAll() {
        // ARRANGE
        carroDTOset.add(carroDTO);
        when(authService.me()).thenReturn(meDTO);
        when(meDTO.getCars()).thenReturn(carroDTOset);

        // ACT
        Set<CarroDTO> carroDTOS = carroService.getAll();

        // ASSERT
        assertEquals(1L, carroDTOS.size());
    }

    @Test
    void create_new() {
        // ARRANGE
        when(authService.me()).thenReturn(meDTO);
        when(meDTO.getId()).thenReturn(1L);
        when(carroMapper.toEntity(carroDTO)).thenReturn(carro);
        when(carroMapper.toDto(carro)).thenReturn(carroDTO);
        when(carroRepository.save(carro)).thenReturn(carro);

        // ACT
        CarroDTO carro1 = carroService.create(carroDTO);

        // ASSERT
        assertEquals(carro1.getId(), 1L);
    }

    @Test
    void create_when_a_licensePlate_already_exists() {
        // ARRANGE
        Exception exception = assertThrows(LicensePlateExistenteException.class, () -> {
            when(carroDTO.getLicensePlate()).thenReturn("PDU-0306");
            when(carroRepository.existsByLicensePlate("PDU-0306")).thenReturn(Boolean.TRUE);

            // ACT
            carroService.create(carroDTO);
        });

        // ASSERT
        assertEquals("com.pitang.testeTecnico.exceptions.LicensePlateExistenteException", exception.getClass().getName());
    }

    @Test
    void getCarro() {
    }

    @Test
    void deleteCarro() {
    }

    @Test
    void updateCarro() {
    }
}