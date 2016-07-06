package br.com.lucascruz.servico.rest.exemplo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucascruz.servico.rest.exemplo.bean.LinhaOnibus;
import br.com.lucascruz.servico.rest.exemplo.bean.PosicaoOnibus;
import br.com.lucascruz.servico.rest.exemplo.repository.LinhaOnibusRepository;

@RestController
public class LinhaOnibusController {

	@Autowired
	LinhaOnibusRepository repository;
	
	@RequestMapping(value="/api/linha/add/posicao", method=RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Boolean> salvarPosicaoOnibus(@RequestBody PosicaoOnibus posicao){
		List<PosicaoOnibus> posicoes = null;
		LinhaOnibus linha = repository.findByLinha(posicao.getLinha());		
		if(linha == null){
			linha = new LinhaOnibus();			
			linha.setLinha(posicao.getLinha());
			posicoes = linha.getPosicoes();			
			linha.setPosicao(posicao);
			repository.insert(linha);
		} else {
			posicoes = linha.getPosicoes();
			linha.setPosicao(posicao);
			repository.save(linha);
		}	
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.CREATED);
	}
	
	@RequestMapping(
			path="/api/linha/{linha}",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PosicaoOnibus> getLinha(@PathVariable String linha){
		List<PosicaoOnibus> posicoes = null;
		LinhaOnibus linhaOnibus = repository.findByLinha(linha);
		if(linhaOnibus != null)
			posicoes = linhaOnibus.getPosicoes();
		return posicoes;
	}
}
