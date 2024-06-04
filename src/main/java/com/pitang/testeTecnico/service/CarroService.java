package com.pitang.testeTecnico.service;

import com.pitang.testeTecnico.exceptions.LicensePlateExistenteException;
import com.pitang.testeTecnico.mapper.CarroMapper;
import com.pitang.testeTecnico.model.Carro;
import com.pitang.testeTecnico.model.dto.CarroDTO;
import com.pitang.testeTecnico.repository.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class CarroService {

    private final CarroRepository carroRepository;

    private final CarroMapper carroMapper;

    private final AuthService authService;

    public CarroService(CarroRepository carroRepository, CarroMapper carroMapper, AuthService authService) {
        this.carroRepository = carroRepository;
        this.carroMapper = carroMapper;
        this.authService = authService;
    }

    public Set<CarroDTO> getAll() {
        return authService.me().getCars();
    }

    public CarroDTO create(CarroDTO carroDTO) {
        if (carroRepository.existsByLicensePlate(carroDTO.getLicensePlate())) {
            throw new LicensePlateExistenteException();
        }
        carroDTO.setUserId(authService.me().getId());
        Carro carro = carroRepository.save(carroMapper.toEntity(carroDTO));
        return carroMapper.toDto(carro);
    }

    public CarroDTO getCarro(Long id) {
        return carroMapper.toDto(carroRepository.findByIdAndUsuarioId(id, authService.me().getId()).orElse(null));
    }

    public CarroDTO deleteCarro(Long id) {
        Carro carro = carroRepository.findByIdAndUsuarioId(id, authService.me().getId()).orElse(null);
        if (carro != null) {
            carroRepository.deleteById(carro.getId());
        }
        return carroMapper.toDto(carro);
    }

    public CarroDTO updateCarro(Long id, CarroDTO carroDTO) {

        Carro carro = carroRepository.findByIdAndUsuarioId(id, authService.me().getId()).orElse(null);

        if (carro != null) {
            Carro carroUpdated = carroMapper.toEntity(carroDTO);

            if (!Objects.equals(carroUpdated.getLicensePlate(), carro.getLicensePlate())
                    && carroRepository.existsByLicensePlate(carroDTO.getLicensePlate())) {
                throw new LicensePlateExistenteException();
            }
            carro = carroRepository.save(carroUpdated);
        }
        return carroMapper.toDto(carro);
    }
}
