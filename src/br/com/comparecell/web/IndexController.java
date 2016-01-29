package br.com.comparecell.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Messagebox;

import br.com.comparecell.Util.GridCellComparado;
import br.com.comparecell.Util.GridCellPrincipal;
import br.com.comparecell.Util.GridTitulo;
import br.com.comparecell.entidades.Aparelho;
import br.com.comparecell.entidades.Rank;
import br.com.comparecell.service.CompareCellService;
import br.com.comparecell.service.CompareCellServiceImpl;


public class IndexController extends SelectorComposer<Component>{
	
	@Wire
	private Combobox _cellA;
	
	@Wire
	private Combobox _cellB;
	
	@Wire
	private Button btnCompara;
	
	@Wire
	private Button btnRank;
	
	@Wire
	private Listbox _lsCellPrincipal;
	
	@Wire
	private Listbox _lsCellComparado;
	
	@Wire
	private Listbox _lsGridTitulo;
	
	@Wire
	private Button btnFavCellA;
	
	@Wire
	private Button btnFavCellB;
	
	
	private Comboitem _itensA;
	
	private Comboitem _itensB;
	
	private List<Aparelho> aparelhos;
	
	private Map compara = new HashMap();
	
	private CompareCellService service = new CompareCellServiceImpl();
	
	
	
	


//Carrega a tela
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		aparelhos = (List<Aparelho>) service.listarAparelhos();
		
		popularCombos(aparelhos);
	
		
		btnFavCellA.setDisabled(true);
		btnFavCellB.setDisabled(true);
		btnCompara.setDisabled(true);
		_cellB.setDisabled(true);
	}
	
	
	
	@SuppressWarnings("unused")
	private void popularCombos(List<Aparelho> lista){
		
		Iterator<Aparelho> it = lista.iterator();
		while (it.hasNext()) {
			 Aparelho a = it.next();
			 _itensA = new Comboitem();
			 _itensA.setLabel(a.getModelo());
			 _itensA.setValue(a.getId());
			 _cellA.appendChild(_itensA);
			
			 _itensB = new Comboitem();
			 _itensB.setLabel(a.getModelo());
			 _itensB.setValue(a.getId());
			 _cellB.appendChild(_itensB);
			 
		}
	}
	
	//Metodo para pegar o intem selecionado no primeiro combobox
	@Listen("onChange = #_cellA")
	public void selecionaA(){
		int i = _cellA.getSelectedItem().getValue();

		Iterator<Aparelho> it = aparelhos.iterator();
		while (it.hasNext()) {
			 Aparelho a = it.next();
			if (i == a.getId()){
				compara.put("A", a);
				break;
			}
		}
		
		_cellB.setDisabled(false);
	}
	
	
	
	//Metodo para pegar o intem selecionado no segundo combobox
	@Listen("onChange = #_cellB")
	public void selecionaB(){
		int i = _cellB.getSelectedItem().getValue();
		
		Iterator<Aparelho> it = aparelhos.iterator();
		while (it.hasNext()) {
			 Aparelho b = it.next();
			if (i == b.getId()){
				compara.put("B", b);
				break;
			}
		}
		btnCompara.setDisabled(false);
	}
	
	
	
	@Listen("onClick = #btnFavCellA")
	public void favotiarPrincipal(){
		int i = _cellA.getSelectedItem().getValue();
		
		this.gravarVoto(i);
		btnFavCellB.setDisabled(false);
		
		
	}
	
	@Listen("onClick = #btnFavCellB")
	public void favoritarComparado(){
		int i = _cellB.getSelectedItem().getValue();
		
		this.gravarVoto(i);
		btnFavCellA.setDisabled(false);
		
		
		
	}
	
	
	private void gravarVoto(int cod) {
		try{
		Rank rank ;
		rank = service.recuperaCell(cod);
		
		if(rank == null){
			rank = new Rank();
			Aparelho ap = new Aparelho();
			ap.setId(cod);
			rank.setAparelho(ap);
			rank.setRankField(1);
			service.gravarRank(rank);
		
		}else{
			int val = rank.getRankField() + 1;
			rank.setRankField(val);
			service.gravarRank(rank);
		}
			Messagebox.show("Voto Registrado", "Informação", Messagebox.OK, Messagebox.INFORMATION);
			
		}catch(Exception d){
			Messagebox.show("Erro ao registrar o voto.", "Erro", Messagebox.OK, Messagebox.ERROR);
		
		}
		
		//Refresh da pagina
		Executions.sendRedirect("rank.zul");
	}
	
	@Listen("onClick = #btnRank") 
	public void voltar(){
		Executions.sendRedirect("rank.zul");
	}
	
	
	
	
	@Listen("onClick = #btnCompara")
	public void compara(){
		
		//Habilita botoes para favoritar
		btnFavCellA.setDisabled(false);
		btnFavCellB.setDisabled(false);
		
		ListModelList lsCellPrin = new ListModelList();
		ListModelList lsCellComp = new ListModelList();
		
		Aparelho _apA = (Aparelho) compara.get("A");
		Aparelho _apB = (Aparelho) compara.get("B");
		
		Listcell listPrincipal;
		Listcell listaComparado;
		
		//Comparando os celulares
		
		
		//Modelo
		listPrincipal = new Listcell( _apA.getModelo() );
		listPrincipal.setStyle("font-weight:bold; font-size:14px;");
		
		lsCellPrin.add(listPrincipal);
		
		listaComparado = new Listcell( _apB.getModelo() );
		listaComparado.setStyle("font-weight:bold; font-size:14px;");
		
		lsCellComp.add(listaComparado);
		
		
		//Ano Lançamento
		listPrincipal = new Listcell( _apA.getLancamento());
		listaComparado = new Listcell( _apB.getLancamento());
		
		
		int lancA = new Integer(_apA.getLancamento().trim());
		int lancB = new Integer(_apB.getLancamento().trim());
		
		if(lancA > lancB){
			listaComparado.setStyle("font-weight:bold; font-size:14px; color: #FF0000");
			listPrincipal.setStyle("font-weight:bold; font-size:14px;  color: #00FF00");
		
		}else if(lancA < lancB){
			listaComparado.setStyle("font-weight:bold; font-size:14px; color: #00FF00");
			listPrincipal.setStyle("font-weight:bold; font-size:14px; color: #FF0000");
		
		}else{
			listaComparado.setStyle("font-weight:bold; font-size:14px; ");
			listPrincipal.setStyle("font-weight:bold; font-size:14px;  ");
		}
		
		lsCellPrin.add(listPrincipal);
		lsCellComp.add(listaComparado);
		
		
		
		//Status
		
		if( _apA.getStatus()){ 
			listPrincipal = new Listcell( "Ativo");
			listPrincipal.setStyle("font-weight:bold; font-size:14px;  color: #00FF00"); 
		}else{
			listPrincipal = new Listcell( "Descontinuado");
			listPrincipal.setStyle("font-weight:bold; font-size:14px;  color: #FF0000");
		}
		
		if( _apB.getStatus()){ 
			listaComparado = new Listcell("Ativo");
			listaComparado.setStyle("font-weight:bold; font-size:14px;  color: #00FF00"); 
		}else{
			listaComparado = new Listcell("Descontinuado");
			listaComparado.setStyle("font-weight:bold; font-size:14px;  color: #FF0000"); 	
		}
		
		
		lsCellPrin.add(listPrincipal);
		lsCellComp.add(listaComparado);
		
		
		//CPU


		
		
	    listPrincipal = new Listcell(new StringBuilder().append(_apA.getCpuCore()).append(" Cores - ").append(_apA.getCpuFrequenca()).append(_apA.getCpuDescricao()).toString());
		listaComparado = new Listcell(new StringBuilder().append(_apB.getCpuCore()).append(" Cores - ").append(_apB.getCpuFrequenca()).append(_apB.getCpuDescricao()).toString());
		
		if( _apA.getCpuCore() > _apB.getCpuCore()){
			
			listaComparado.setStyle("font-weight:bold; font-size:14px; color: #FF0000");
			listPrincipal.setStyle("font-weight:bold; font-size:14px;  color: #00FF00");
			
		}else if (_apA.getCpuCore() < _apB.getCpuCore()){
			
			listaComparado.setStyle("font-weight:bold; font-size:14px; color: #00FF00");
			listPrincipal.setStyle("font-weight:bold; font-size:14px; color: #FF0000");
			
		}else if( _apA.getCpuFrequenca().floatValue() >  _apB.getCpuFrequenca().floatValue() ){
			
			listaComparado.setStyle("font-weight:bold; font-size:14px; color: #FF0000");
			listPrincipal.setStyle("font-weight:bold; font-size:14px;  color: #00FF00");
				
			}else if( _apA.getCpuFrequenca().floatValue() <  _apB.getCpuFrequenca().floatValue()){
				listaComparado.setStyle("font-weight:bold; font-size:14px; color: #00FF00");
				listPrincipal.setStyle("font-weight:bold; font-size:14px; color: #FF0000");
			}else{
				listaComparado.setStyle("font-weight:bold; font-size:14px; " );
				listPrincipal.setStyle("font-weight:bold; font-size:14px; ");
			}
			
				
	
		
		lsCellPrin.add(listPrincipal);
		lsCellComp.add(listaComparado);
		
		
		//Cartao-SD
		listPrincipal = new Listcell( _apA.getArmazenamentoCartao().toString().concat("MB"));
		listaComparado = new Listcell( _apB.getArmazenamentoCartao().toString().concat("MB"));
		
		if( _apA.getArmazenamentoCartao() >  _apB.getArmazenamentoCartao()){
			listaComparado.setStyle("font-weight:bold; font-size:14px; color: #FF0000");
			listPrincipal.setStyle("font-weight:bold; font-size:14px;  color: #00FF00");
		
		}else if ( _apA.getArmazenamentoCartao() <  _apB.getArmazenamentoCartao()){
			listaComparado.setStyle("font-weight:bold; font-size:14px; color: #00FF00");
			listPrincipal.setStyle("font-weight:bold; font-size:14px; color: #FF0000");
		
		}else{
			listaComparado.setStyle("font-weight:bold; font-size:14px; ");
			listPrincipal.setStyle("font-weight:bold; font-size:14px;  ");
		}
		
		
		lsCellPrin.add(listPrincipal);
		lsCellComp.add(listaComparado);
		
		
		//Aramazenamento Interno
		listPrincipal = new Listcell( _apA.getArmazenamentoInterno().toString().concat("MB"));
		listaComparado = new Listcell( _apB.getArmazenamentoInterno().toString().concat("MB"));
		
		if( _apA.getArmazenamentoInterno() >  _apB.getArmazenamentoInterno()){
			listaComparado.setStyle("font-weight:bold; font-size:14px; color: #FF0000");
			listPrincipal.setStyle("font-weight:bold; font-size:14px;  color: #00FF00");
		
		}else if ( _apA.getArmazenamentoInterno() <  _apB.getArmazenamentoInterno()){
			listaComparado.setStyle("font-weight:bold; font-size:14px; color: #00FF00");
			listPrincipal.setStyle("font-weight:bold; font-size:14px; color: #FF0000");
		
		}else{
			listaComparado.setStyle("font-weight:bold; font-size:14px; ");
			listPrincipal.setStyle("font-weight:bold; font-size:14px;  ");
		}
		
		
		lsCellPrin.add(listPrincipal);
		lsCellComp.add(listaComparado);
		
		
		
		
		//Memoria
		
		listPrincipal = new Listcell( new StringBuilder().append(_apA.getMemoria()).append(_apA.getMemoriaDesc()).toString());
		listaComparado = new Listcell( new StringBuilder().append(_apB.getMemoria()).append(_apB.getMemoriaDesc()).toString());
		
		if( _apA.getMemoria() >  _apB.getMemoria()){
			listaComparado.setStyle("font-weight:bold; font-size:14px; color: #FF0000");
			listPrincipal.setStyle("font-weight:bold; font-size:14px;  color: #00FF00");
		
		}else if ( _apA.getMemoria() <  _apB.getMemoria()){
			listaComparado.setStyle("font-weight:bold; font-size:14px; color: #00FF00");
			listPrincipal.setStyle("font-weight:bold; font-size:14px; color: #FF0000");
		
		}else{
			listaComparado.setStyle("font-weight:bold; font-size:14px; ");
			listPrincipal.setStyle("font-weight:bold; font-size:14px;  ");
		}
		
		
		lsCellPrin.add(listPrincipal);
		lsCellComp.add(listaComparado);
		
		
		
		
		//Display Tamanho
		listPrincipal = new Listcell( _apA.getDisplayTamanho().toString());
		listaComparado = new Listcell( _apB.getDisplayTamanho().toString());
		
		if( _apA.getDisplayTamanho() >  _apB.getDisplayTamanho()){
			listaComparado.setStyle("font-weight:bold; font-size:14px; color: #FF0000");
			listPrincipal.setStyle("font-weight:bold; font-size:14px;  color: #00FF00");
		
		}else if ( _apA.getDisplayTamanho() <  _apB.getDisplayTamanho()){
			listaComparado.setStyle("font-weight:bold; font-size:14px; color: #00FF00");
			listPrincipal.setStyle("font-weight:bold; font-size:14px; color: #FF0000");
		
		}else{
			listaComparado.setStyle("font-weight:bold; font-size:14px; ");
			listPrincipal.setStyle("font-weight:bold; font-size:14px;  ");
		}
		
		
		lsCellPrin.add(listPrincipal);
		lsCellComp.add(listaComparado);
		
		
		
		//Display Resolução
				listPrincipal = new Listcell( _apA.getDisplayResolucao());
				listaComparado = new Listcell( _apB.getDisplayResolucao());
				
				int  apA = new Integer(_apA.getDisplayResolucao().substring(0,4).trim());
				int  apB = new Integer( _apB.getDisplayResolucao().substring(0,4).trim());
				
				
				if( apA  >  apB){
					listaComparado.setStyle("font-weight:bold; font-size:14px; color: #FF0000");
					listPrincipal.setStyle("font-weight:bold; font-size:14px;  color: #00FF00");
				
				}else if ( apA <  apB){
					listaComparado.setStyle("font-weight:bold; font-size:14px; color: #00FF00");
					listPrincipal.setStyle("font-weight:bold; font-size:14px; color: #FF0000");
				
				}else{
					listaComparado.setStyle("font-weight:bold; font-size:14px; ");
					listPrincipal.setStyle("font-weight:bold; font-size:14px;  ");
				}
				
				
				lsCellPrin.add(listPrincipal);
				lsCellComp.add(listaComparado);
				
		
		//Camera Primaria
				listPrincipal = new Listcell( _apA.getCameraPrimaria().toString().concat("MP"));
				listaComparado = new Listcell( _apB.getCameraPrimaria().toString().concat("MP"));
				
				if( _apA.getCameraPrimaria() >  _apB.getCameraPrimaria()){
					listaComparado.setStyle("font-weight:bold; font-size:14px; color: #FF0000");
					listPrincipal.setStyle("font-weight:bold; font-size:14px;  color: #00FF00");
				
				}else if ( _apA.getCameraPrimaria() <  _apB.getCameraPrimaria()){
					listaComparado.setStyle("font-weight:bold; font-size:14px; color: #00FF00");
					listPrincipal.setStyle("font-weight:bold; font-size:14px; color: #FF0000");
				
				}else{
					listaComparado.setStyle("font-weight:bold; font-size:14px; ");
					listPrincipal.setStyle("font-weight:bold; font-size:14px;  ");
				}
				
				
				lsCellPrin.add(listPrincipal);
				lsCellComp.add(listaComparado);
				
				
				
				//Camera Secundaria
				listPrincipal = new Listcell( _apA.getCameraSecundaria().toString().concat("MP"));
				listaComparado = new Listcell( _apB.getCameraSecundaria().toString().concat("MP"));
				
				if( _apA.getCameraSecundaria() >  _apB.getCameraSecundaria()){
					listaComparado.setStyle("font-weight:bold; font-size:14px; color: #FF0000");
					listPrincipal.setStyle("font-weight:bold; font-size:14px;  color: #00FF00");
				
				}else if ( _apA.getCameraSecundaria() <  _apB.getCameraSecundaria()){
					listaComparado.setStyle("font-weight:bold; font-size:14px; color: #00FF00");
					listPrincipal.setStyle("font-weight:bold; font-size:14px; color: #FF0000");
				
				}else{
					listaComparado.setStyle("font-weight:bold; font-size:14px; ");
					listPrincipal.setStyle("font-weight:bold; font-size:14px;  ");
				}
				
				
				lsCellPrin.add(listPrincipal);
				lsCellComp.add(listaComparado);
		
		
		
		_lsCellPrincipal.setModel(lsCellPrin);
		_lsCellPrincipal.setItemRenderer(new GridCellPrincipal());
		
		
		_lsCellComparado.setModel(lsCellComp);
		_lsCellComparado.setItemRenderer(new GridCellComparado());
		
		
		this.montaTitulo();
	}
	
	
	private void montaTitulo(){
		ListModelList ls = new ListModelList();
		
		Listcell lista;
		
		lista = new Listcell( "Modelo"  );
		lista.setStyle("font-weight:bold; font-size:14px;");
		ls.add(lista);
		
		lista = new Listcell( "Ano de Lançamento "  );
		lista.setStyle("font-weight:bold; font-size:14px;");
		ls.add(lista);
		
		lista = new Listcell( "Status"  );
		lista.setStyle("font-weight:bold; font-size:14px;");
		ls.add(lista);
		
		lista = new Listcell( "CPU"  );
		lista.setStyle("font-weight:bold; font-size:14px;");
		ls.add(lista);
		
		lista = new Listcell( "Cartao-SD"  );
		lista.setStyle("font-weight:bold; font-size:14px;");
		ls.add(lista);
		
		lista = new Listcell( "Armaz. Interno"  );
		lista.setStyle("font-weight:bold; font-size:14px;");
		ls.add(lista);
		
		lista = new Listcell( "Memoria"  );
		lista.setStyle("font-weight:bold; font-size:14px;");
		ls.add(lista);
		
		lista = new Listcell( "Display Tamanho "  );
		lista.setStyle("font-weight:bold; font-size:14px;");
		ls.add(lista);
		
		lista = new Listcell( " Display Resoluçao"  );
		lista.setStyle("font-weight:bold; font-size:14px;");
		ls.add(lista);
		
		lista = new Listcell( "Camera Primaria"  );
		lista.setStyle("font-weight:bold; font-size:14px;");
		ls.add(lista);
		
		lista = new Listcell( "Camera Secundaria"  );
		lista.setStyle("font-weight:bold; font-size:14px;");
		ls.add(lista);
		
		
		_lsGridTitulo.setModel(ls);
		_lsGridTitulo.setItemRenderer(new GridTitulo());
		
		
		
	}
	
	
}
