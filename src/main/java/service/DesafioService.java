package service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Desafio;
import repository.DesafioRepository;

@Service
public class DesafioService {

	@Autowired
	private DesafioRepository desafioRepository;

	public Desafio create(Desafio novo) {
		novo.setSaida(this.processaResultado(novo) + "");
		desafioRepository.save(novo);
		return novo;
	}

	public List<Desafio> list() {
		List<Desafio> lista = desafioRepository.findAll();
		return lista;
	}

	private Character processaResultado(Desafio novo) {
		String name = novo.getEntrada();
		String copia = name.toLowerCase();
		Map<Character, Character> conjuntosolucao = new LinkedHashMap<Character, Character>();

		for (int i = 0; i < name.length() - 1; i++) {
			if (i > 0 && !conjuntosolucao.containsKey(copia.charAt(i)) && this.verifificaCondicaoAtendida(copia, i)) {
				System.out.println((int) copia.charAt(i));
				conjuntosolucao.put(copia.charAt(i), name.charAt(i + 1));
			} else if (this.verificaVogal(copia.charAt(i))) {
				conjuntosolucao.put(copia.charAt(i), null);
			}
		}

		Character resultado = new Character(' ');
		for (Character key : conjuntosolucao.keySet()) {
			Character value = conjuntosolucao.get(key);
			if (value != null) {
				System.out.println("-----Resultado-----");
				System.out.println(key + " = " + value);
				return value;
			}
		}
		return resultado;
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

}
