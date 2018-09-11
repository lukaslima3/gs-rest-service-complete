package app;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.Greeting;
import model.Desafio;

@RestController
public class RequestController {

	private static final String template = "Custon Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/teste")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "Wossrld") String name) {

		String copia = name.toLowerCase();
		Map<Character, Character> conjuntosolucao = new HashMap<Character, Character>();

		for (int i = 0; i < name.length() - 1; i++) {
			if (i > 0 && !conjuntosolucao.containsKey(copia.charAt(i)) && this.verifificaCondicaoAtendida(copia, i)) {
				System.out.println((int) copia.charAt(i));
				conjuntosolucao.put(copia.charAt(i), name.charAt(i + 1));
			} else if (!this.verificaVogal(copia.charAt(i))) {
				conjuntosolucao.put(copia.charAt(i), null);
			}
		}

		for (Character key : conjuntosolucao.keySet()) {
			Character value = conjuntosolucao.get(key);
			if (value != null) {
				System.out.println("-----Resultado-----");
				System.out.println(key + " = " + value);
				break;
			}
		}

		return this.obterResponseJson(name);
	}

	/**
	 * Verifica se o antecessor e o sucessor são vogais
	 * 
	 * @param copia
	 * @param i
	 * @return
	 */
	private boolean verifificaCondicaoAtendida(String copia, int i) {
		if (this.verificaVogal(copia.charAt(i - 1)) && this.verificaVogal(copia.charAt(i + 1))
				&& !this.verificaVogal(copia.charAt(i))) {
			return true;
		}
		return false;
	}

	/**
	 * Verifica se é vogal com base na tabela ascii letras minucculas
	 * 
	 * @param a
	 * @return
	 */
	private boolean verificaVogal(Character a) {
		int intVAlue = (int) a;
		if (intVAlue == 97 || intVAlue == 101 || intVAlue == 105 || intVAlue == 111 || intVAlue == 117) {
			return true;
		}
		return false;
	}

	private Greeting obterResponseJson(String name) {

		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

}
