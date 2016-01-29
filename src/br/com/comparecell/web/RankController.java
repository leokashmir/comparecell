package br.com.comparecell.web;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

import br.com.comparecell.Util.GridRank;
import br.com.comparecell.service.CompareCellService;
import br.com.comparecell.service.CompareCellServiceImpl;


public class RankController extends SelectorComposer<Component> {

	
	
	private List lstRank;
	
	@Wire
	private Listbox _lsGridRank;
	
	@Wire
	private Button btnVoltar;
	
	
	private CompareCellService service = new CompareCellServiceImpl();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		lstRank =  new ArrayList();
		lstRank = service.listarRank();
		exibeRank(lstRank);
	}
	

	
	private void exibeRank(List lista){
		
		ListModelList ls = new ListModelList();
		ls.addAll(lista);
		
		_lsGridRank.setModel(ls);
		_lsGridRank.setItemRenderer(new GridRank());
	}
	
	@Listen("onClick = #btnVoltar") 
	public void voltar(){
		Executions.sendRedirect("index.zul");
	}
}
