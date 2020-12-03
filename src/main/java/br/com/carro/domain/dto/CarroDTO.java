package br.com.carro.domain.dto;

import br.com.carro.domain.Carro;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CarroDTO {
    private Long id;
    private  String nome;
    private  String tipo;

//    public CarroDTO(Carro c) {
//        this.id = c.getId();
//        this.nome = c.getNome();
//        this.tipo = c.getTipo();
//    }

    public static CarroDTO create(Carro carro) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(carro, CarroDTO.class);
    }
}
