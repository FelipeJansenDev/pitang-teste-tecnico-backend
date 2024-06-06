package com.pitang.testeTecnico.service;

import com.pitang.testeTecnico.exceptions.LicensePlateExistenteException;
import com.pitang.testeTecnico.mapper.CarroMapper;
import com.pitang.testeTecnico.model.Carro;
import com.pitang.testeTecnico.model.dto.CarroDTO;
import com.pitang.testeTecnico.model.dto.MeDTO;
import com.pitang.testeTecnico.repository.CarroRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarroServiceTest {

    @InjectMocks
    public CarroService carroService;

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

    @Mock
    Carro carroUpdated;

    Set<CarroDTO> carroDTOset = new HashSet<>();

    @Before
    public void setUp() {
        when(authService.me()).thenReturn(meDTO);
        when(meDTO.getId()).thenReturn(1L);
        when(carroMapper.toEntity(carroDTO)).thenReturn(carro);
        when(carroMapper.toDto(carro)).thenReturn(carroDTO);
        when(carroRepository.save(carro)).thenReturn(carro);
    }

    @Test
    @DisplayName("Retornar todos os usuários")
    public void getAll() {
        // ARRANGE
        carroDTOset.add(carroDTO);
        when(meDTO.getCars()).thenReturn(carroDTOset);

        // ACT
        Set<CarroDTO> carroDTOS = carroService.getAll();

        // ASSERT
        assertEquals(1L, carroDTOS.size());
    }

    @Test
    @DisplayName("Criar um novo carro")
    public void create_new() {
        // ACT
        carroService.create(carroDTO);
    }

    @Test(expected = LicensePlateExistenteException.class)
    @DisplayName("Criar um novo usuário cuja placa já está cadastrada")
    public void create_when_a_licensePlate_already_exists() {
        // ARRANGE
        when(carroDTO.getLicensePlate()).thenReturn("PDU-0306");
        when(carroRepository.existsByLicensePlate("PDU-0306")).thenReturn(Boolean.TRUE);

        // ACT
        carroService.create(carroDTO);
    }

    @Test
    @DisplayName("Criar um novo usuário cuja placa já está cadastrada")
    public void create_a_new_car() {
        // ARRANGE
        when(carroDTO.getLicensePlate()).thenReturn("PDU-0306");
        when(carroRepository.existsByLicensePlate("PDU-0306")).thenReturn(Boolean.FALSE);

        // ACT
        carroService.create(carroDTO);
    }

    @Test
    @DisplayName("Atualizar carro")
    public void test_update_car() {
        // ARRANGE
        when(carroRepository.findByIdAndUsuarioId(1L, authService.me().getId())).thenReturn(Optional.ofNullable(carro));
        when(carroMapper.toEntity(carroDTO)).thenReturn(carroUpdated);
        when(carroUpdated.getLicensePlate()).thenReturn("PDU-0306");
        when(carro.getLicensePlate()).thenReturn("PDU-0306");

        // ACT
        carroService.updateCarro(1L, carroDTO);
    }

    @Test(expected = LicensePlateExistenteException.class)
    @DisplayName("Atualizar carro, placa alterada por uma que jpa está cadastrada")
    public void test_update_car_fail_when_licenceplate_already_exists() {
        // ARRANGE
        when(carroRepository.findByIdAndUsuarioId(1L, authService.me().getId())).thenReturn(Optional.ofNullable(carro));
        when(carroMapper.toEntity(carroDTO)).thenReturn(carroUpdated);
        when(carroUpdated.getLicensePlate()).thenReturn("PDU-0306");
        when(carro.getLicensePlate()).thenReturn("OKR-9024");
        when(carroRepository.existsByLicensePlate(carroDTO.getLicensePlate())).thenReturn(Boolean.TRUE);

        // ACT
        carroService.updateCarro(1L, carroDTO);
    }

    @Test
    @DisplayName("Atualizar carro, placa alterada para uma que não existe no sistema")
    public void test_update_car_change_licenseplate_for_a_non_existing() {
        // ARRANGE
        when(carroRepository.findByIdAndUsuarioId(1L, authService.me().getId())).thenReturn(Optional.ofNullable(carro));
        when(carroMapper.toEntity(carroDTO)).thenReturn(carroUpdated);
        when(carroUpdated.getLicensePlate()).thenReturn("PDU-0306");
        when(carro.getLicensePlate()).thenReturn("OKR-9024");
        when(carroRepository.existsByLicensePlate(carroDTO.getLicensePlate())).thenReturn(Boolean.FALSE);

        // ACT
        carroService.updateCarro(1L, carroDTO);
    }

}