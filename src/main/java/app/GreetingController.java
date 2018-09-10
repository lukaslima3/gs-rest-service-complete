package app;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Desafio;
import repository.DesafioRepository;

@RestController
public class GreetingController {

	private static final String template = "custon Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	private DesafioRepository desafioRepository;

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "Wossrld") String name) {

		Desafio novo = new Desafio();
		novo.setEntrada(name);

		String copia = name.toLowerCase();
		int ascii = (int) copia.charAt(0);
		int[] mapping = new int[123];

		for (int i = 0; i <= 122; i++) {// inicializar array
			mapping[i] = -1;
		}

		for (int i = 0; i < name.length(); i++) {
			if (mapping[(int) copia.charAt(i)] == -1) {// se for a primeira vez grava a posicao do caracter
				System.out.println((int) copia.charAt(i));
				mapping[(int) copia.charAt(i)] = i;
			} else {// caso contrario seta zero
				mapping[(int) copia.charAt(i)] = 0;
			}
		}
//		System.out.print("---->");
		boolean[] possiveisposicoes = new boolean[name.length()];
		for (int i = 102; i <= 122; i++) {
//			System.out.print("<--posicao -->");
//			System.out.println(mapping[i] - 1);
//			System.out.println(mapping[i] + 1);
			if (mapping[i] - 1 >= 0 && mapping[i] < name.length()) {// desprezar algumas posições inválidas
				possiveisposicoes[mapping[i]] = true;// se tando a posição do carater como válida
			}
		}
		System.out.print("<--resultado final-->");
		for (int i = 0; i < name.length(); i++) {
			if (possiveisposicoes[i]) {
				System.out.print("<--posicao valida-->");
				System.out.println(i);
				if (this.verifificaCondicaoAtendida(copia, i)) {
					System.out.print("<--Resultado-->");
					System.out.println(name.charAt(i + 1));
					novo.setSaida(name.charAt(i + 1) + "");
					i = name.length();
				}
			}
		}
		desafioRepository.save(novo);
		List<Desafio> lista = desafioRepository.findAll();
		return this.obterResponseJson(name);
	}

	private boolean verifificaCondicaoAtendida(String copia, int i) {
		System.out.print(copia.charAt(i - 1));
		System.out.print("<--comparando-->");
		System.out.print(copia.charAt(i + 1));
		System.out.println("-----");
		if ((int) copia.charAt(i - 1) < 102 && (int) copia.charAt(i + 1) < 102) {
			return true;
		}
		return false;
	}

	private Greeting obterResponseJson(String name) {

		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
