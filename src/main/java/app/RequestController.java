package app;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/desafio/createteste")
	public Desafio createteste(@RequestParam(value = "entrada", defaultValue = "bafA") String entrada) {
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

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/desafio/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Desafio create(@RequestBody Desafio novo) {
		desafioService.create(novo);
		return novo;
	}

}
