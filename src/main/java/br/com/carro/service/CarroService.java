package br.com.carro.service;

import br.com.carro.domain.Carro;
import br.com.carro.domain.dto.CarroDTO;
import br.com.carro.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<CarroDTO> getCarros() {
//        List<Carro> carros = carroRepository.findAll();
//        List<CarroDTO> carroList = new ArrayList<>();
//        carros.forEach(carro -> {
//            carroList.add(new CarroDTO(carro));
//        });

        return carroRepository
                .findAll()
                .stream()
                .map(c -> CarroDTO.create(c))
                .collect(Collectors.toList());
    }

    public Optional<CarroDTO> getCarroById(Long id) {
        return carroRepository.findById(id).map(CarroDTO::create);
//        Optional<Carro> carro = carroRepository.findById(id);
//        if (carro.isPresent()) {
//            //Convertendo para um Optional
//            return Optional.of(new CarroDTO(carro.get()));
//        } else {
//            return null;
//        }
    }

    public List<CarroDTO> getCarroByTipo(String tipo) {
        return carroRepository
                .findByTipo(tipo)
                .stream()
                .map(CarroDTO::create)
                .collect(Collectors.toList());
    }

    public CarroDTO criar(Carro carro) {
        Assert.isNull(carro.getId(), "Não foi possível criar o registro!");
        return CarroDTO.create(carroRepository.save(carro));
    }

    public CarroDTO update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro");

        //Busca o carro na base de dados
        Optional<Carro> carroOptional = carroRepository.findById(id);
        if (carroOptional.isPresent()) {
            Carro db = carroOptional.get();

            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro ID: " + db.getId());
            //Atualiza o carro no banco
            carroRepository.save(db);
            return CarroDTO.create(db);
        } else {
            return null;
        }
    }

    public boolean delete(Long id) {
        if (getCarroById(id).isPresent()) {
            carroRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
