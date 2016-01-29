package br.com.comparecell.dao;

import java.util.List;

import br.com.comparecell.entidades.Rank;



public interface CompareCellDAO {

	public List listarAparelhos();
	
	public Rank recuperaCell(Integer cod);
	
	public List<Rank> listarRank();
	
	public void gravarRank(Rank k) throws Exception;
}
