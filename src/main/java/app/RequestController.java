package app;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.Greeting;
import model.Desafio;
import service.DesafioService;;

@RestController
public class RequestController {

	private static final String template = "Custon Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	DesafioService desafioService;

	@RequestMapping("/desafio/create")
	public Desafio create(@RequestParam(value = "entrada", defaultValue = "bafA") String entrada) {
		Desafio novo = new Desafio();
		novo.setEntrada(entrada);
		desafioService.create(novo);
		return novo;
	}

	@RequestMapping("/desafio")
	public List<Desafio> list() {
		List<Desafio> lista = desafioService.list();
		return lista;
	}

}
