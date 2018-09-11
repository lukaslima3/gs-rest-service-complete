package app;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Desafio;


@RestController
public class GreetingController {

	private static final String template = "custon Hello, %s!";
	private final AtomicLong counter = new AtomicLong();



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
		for (int i = 98; i <= 122; i++) {
//			System.out.print("<--posicao -->");
//			System.out.println(mapping[i] - 1);
//			System.out.println(mapping[i] + 1);
			if (i != 97 && i != 101 && i != 105 && i != 111 && i != 117 && mapping[i] - 1 >= 0
					&& mapping[i] < name.length()) {// desprezar algumas posições inválidas
				possiveisposicoes[mapping[i]] = true;// se tando a posição do carater como válida
			}
		}
		System.out.print("<--resultado finanovo testel-->");
		System.out.print("<--resultado finanovo testel-->");
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
//		desafioRepository.save(novo);
//		List<Desafio> lista = desafioRepository.findAll();
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
		if (this.verificaVogal(copia.charAt(i - 1)) && this.verificaVogal(copia.charAt(i + 1))) {
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
		if (intVAlue != 97 || intVAlue != 101 || intVAlue != 105 || intVAlue != 111 || intVAlue != 117) {
			return true;
		}
		return false;
	}

	private Greeting obterResponseJson(String name) {

		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
