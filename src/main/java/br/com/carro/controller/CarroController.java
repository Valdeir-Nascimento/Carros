package br.com.carro.controller;

import br.com.carro.domain.Carro;
import br.com.carro.domain.dto.CarroDTO;
import br.com.carro.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    public ResponseEntity<List<CarroDTO>> get() {
        return ResponseEntity.ok(carroService.getCarros());
        //return new ResponseEntity<>(carroService.getCarros(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> getId(@PathVariable("id") Long id) {
//        if(carro.isPresent()) {
//            //Carro c = carro.get();
//            return ResponseEntity.ok(carro.get());
//        }else {
//            return ResponseEntity.notFound().build();
//        }

//        return carro.isPresent() ? ResponseEntity.ok(carro.get()) : ResponseEntity.notFound().build();
        CarroDTO carro = carroService.getCarroById(id);
        return ResponseEntity.ok(carro);


    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<CarroDTO>> getCarroByTipo(@PathVariable("tipo") String tipo) {
        List<CarroDTO> carros = carroService.getCarroByTipo(tipo);
        return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);
    }

    //@RequestBody converte os dados json em um objeto
    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity criar(@RequestBody Carro carro) {
        CarroDTO c = carroService.criar(carro);
        URI location = getUri(carro.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody Carro carro) {
        carro.setId(id);
        CarroDTO carroDTO = carroService.update(carro, id);
        return carroDTO != null ? ResponseEntity.ok(carroDTO) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        carroService.delete(id);
        return ResponseEntity.ok().build();
    }

    //Montado a URL
    private URI getUri(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }


}
