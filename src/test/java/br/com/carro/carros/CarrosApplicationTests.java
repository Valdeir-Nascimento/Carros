package br.com.carro.carros;

import br.com.carro.domain.Carro;
import br.com.carro.domain.dto.CarroDTO;
import br.com.carro.service.CarroService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.AssertionErrors;

import java.util.Optional;


@SpringBootTest
class CarrosApplicationTests {

	@Autowired
	private CarroService service;

	@Test
	void test1() {
		Carro carro = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("Luxo");

		CarroDTO carroDTO =  service.criar(carro);
		Assertions.assertNotNull(carroDTO);

		Long id = carroDTO.getId();
		Optional<CarroDTO> op = service.getCarroById(id);
		AssertionErrors.assertTrue("True", op.isPresent());

	}

}
