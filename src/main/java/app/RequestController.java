package app;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/desafio/")
	public ResponseEntity<?> list() {
		List<Desafio> lista = desafioService.list();
		return new ResponseEntity<List<Desafio>>(lista, HttpStatus.OK);
	}

}
