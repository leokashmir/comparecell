package br.com.comparecell.service;

import java.util.List;

import br.com.comparecell.dao.CompareCellDAO;
import br.com.comparecell.dao.CompareCellDAOImpl;
import br.com.comparecell.entidades.Rank;


public class CompareCellServiceImpl implements CompareCellService{

	
	
	private CompareCellDAO indexDao = new CompareCellDAOImpl();
	
	
	@Override
	public List listarAparelhos() {
		return (List) indexDao.listarAparelhos();
	}


	@Override
	public Rank recuperaCell(Integer cod) {
		return indexDao.recuperaCell(cod);
		
	}


	@Override
	public List<Rank> listarRank() {
		return indexDao.listarRank();
		
	}


	@Override
	public void gravarRank(Rank k) throws Exception {
		indexDao.gravarRank(k);
		
	}

}
