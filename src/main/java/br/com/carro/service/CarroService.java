package br.com.carro.service;

import br.com.carro.domain.Carro;
import br.com.carro.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public Iterable<Carro> getCarros() {
        return  carroRepository.findAll();
    }

    public List<Carro> getCarrosFaKe() {
        List<Carro> carros = new ArrayList<>();
        carros.add(new Carro(1L, "Fusca"));
        carros.add(new Carro(2L, "Corsa"));
        carros.add(new Carro(3L, "Prisma"));
        return  carros;

    }

 }
