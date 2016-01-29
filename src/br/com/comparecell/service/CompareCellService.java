package br.com.comparecell.service;

import java.util.List;

import br.com.comparecell.entidades.Aparelho;
import br.com.comparecell.entidades.Rank;



public interface CompareCellService {

	public List<Aparelho> listarAparelhos();
	
	public Rank recuperaCell(Integer cod);
	
	public List<Rank> listarRank();
	
	public void gravarRank(Rank k) throws Exception;
}
