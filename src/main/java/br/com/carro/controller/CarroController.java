package br.com.carro.controller;

import br.com.carro.domain.Carro;
import br.com.carro.domain.dto.CarroDTO;
import br.com.carro.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        Optional<CarroDTO> carro = carroService.getCarroById(id);
//        if(carro.isPresent()) {
//            //Carro c = carro.get();
//            return ResponseEntity.ok(carro.get());
//        }else {
//            return ResponseEntity.notFound().build();
//        }

//        return carro.isPresent() ? ResponseEntity.ok(carro.get()) : ResponseEntity.notFound().build();

        return carro
                .map(ResponseEntity::ok) //c -> ResponseEntity.ok(c)
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<CarroDTO>> getCarroByTipo(@PathVariable("tipo") String tipo) {
        List<CarroDTO> carros = carroService.getCarroByTipo(tipo);
        return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);
    }

    //@RequestBody converte os dados json em um objeto
    @PostMapping
    public ResponseEntity carroPost(@RequestBody Carro carro) {
        try {
            CarroDTO c = carroService.criar(carro);
            URI location = getUri(carro.getId());
            return ResponseEntity.created(location).build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity carroPut(@PathVariable("id") Long id, @RequestBody Carro carro) {
        carro.setId(id);
        CarroDTO carroDTO = carroService.update(carro, id);
        return carroDTO != null ? ResponseEntity.ok(carroDTO) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        boolean statusOk = carroService.delete(id);
        return statusOk ? ResponseEntity.ok(statusOk) : ResponseEntity.notFound().build();
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
