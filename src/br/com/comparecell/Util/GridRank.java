package br.com.comparecell.Util;

import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import br.com.comparecell.entidades.Rank;

public class GridRank implements ListitemRenderer {

	@Override
	public void render(Listitem item, Object data, int index) throws Exception {
		Rank rank = (Rank) data;
		Listcell lc;
		
	   lc = new Listcell(rank.getAparelho().getModelo());
	   lc.setStyle("font-weight:bold; font-size:14px; width:10%");
	   lc.setParent(item);
	   
	   lc = new Listcell(rank.getRankField().toString());
	   lc.setStyle("font-weight:bold; font-size:14px; width:5%");
	   lc.setParent(item);
		
	   lc = new Listcell("Votos");
	   lc.setStyle("font-weight:bold; font-size:14px; width:5%");
	   lc.setParent(item);
	}

}
