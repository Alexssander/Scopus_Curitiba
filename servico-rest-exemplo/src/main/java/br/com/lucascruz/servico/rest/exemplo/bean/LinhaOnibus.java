package br.com.lucascruz.servico.rest.exemplo.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

/**
 * Este Bean armazena as informações referentes a uma linha de onibus
 * 
 * @author Lucas A Cruz
 *
 */
public class LinhaOnibus {

	/*
	 * Identificação no MongoDB
	 */
	@Id
	private String _id;
	/*
	 * Representa a linha do onibus
	 */
	private String linha;
	/*
	 * Lista com as posições do Onibus ao longo do caminho
	 */
	private List<PosicaoOnibus> posicoes;
	
	/*
	 * Construtor da Classe 
	 */
	public LinhaOnibus() {}


	/*
	 * Métodos Sets e Gets 
	 */
	
	
	public String get_id() {
		return _id;
	}


	public void set_id(String _id) {
		this._id = _id;
	}


	public String getLinha() {
		return linha;
	}


	public void setLinha(String linha) {
		this.linha = linha;
	}


	public List<PosicaoOnibus> getPosicoes() {
		if(posicoes == null)
			posicoes = new ArrayList<>();
		return posicoes;
	}


	public void setPosicao(PosicaoOnibus posicao) {
		this.posicoes.add(posicao);
	}


	/*
	 * Método To String
	 * @see java.lang.Object#toString()
	 */	
	@Override
	public String toString() {
		return "LinhaOnibus [_id=" + _id + ", linha=" + linha + ", posicoes=" + posicoes + "]";
	}
	
	
	
}
