package app;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.Greeting;

@RestController
public class RequestController {

	private static final String template = "Custon Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/teste")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "Wossrld") String name) {
		Greeting teste = new Greeting(1, "Conteudo");
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

}
