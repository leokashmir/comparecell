package br.com.comparecell.Util;

import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

public class GridCellPrincipal implements ListitemRenderer {

	@Override
	public void render(Listitem item, Object data, int index) throws Exception {

	
		Listcell lc = (Listcell) data;
		item.appendChild(lc);
   
	 
	}
}
