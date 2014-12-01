package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import rn.TipoRN;
import utils.MensagensUtil;
import entity.Tipo;
@SuppressWarnings("serial")
@ManagedBean(name="tipoBean")
@ViewScoped
public class TipoBean implements Serializable{
	
	private Tipo selecionado = new Tipo();
	private List<Tipo> lista;
	private boolean exibeCampos;
	
	public Tipo getSelecionado() {
		return selecionado;
	}
	public void setSelecionado(Tipo selecionado) {
		this.selecionado = selecionado;
	}
	public List<Tipo> getLista() {
		if (this.lista == null){
			TipoRN tipoRN = new TipoRN();
			this.lista = tipoRN.listar();
		}		
		return lista;
	}
	public void setLista(List<Tipo> lista) {
		this.lista = lista;
	}
	public boolean isExibeCampos() {
		return exibeCampos;
	}
	public void setExibeCampos(boolean exibeCampos) {
		this.exibeCampos = exibeCampos;
	}
	
	public TipoBean(){
		exibeCampos = false;
		lista = null;		
	}
	
	public void salvar(){
		TipoRN tipoRN = new TipoRN();
		if (tipoRN.salvar(selecionado) == 0){
			MensagensUtil.mensagemErro("Erro ao gravar registro de Tipo!");
		} else {
			MensagensUtil.mensagemInfo("Tipo gravado com sucesso!");
		}
		lista = null;
		exibeCampos = false;		
	}
	
	public void novo(){
		selecionado = new Tipo();
		exibeCampos = true;
	}
	
	public void editar(){
		exibeCampos = true;
	}
	
	public void cancelar(){
		exibeCampos = false;
	}
	
	public void excluir(){
		TipoRN tipoRN = new TipoRN();
		tipoRN.excluir(selecionado);
		lista = null;
		MensagensUtil.mensagemInfo("Tipo excluído com sucesso!");
	}
		
	public String principal(){
		return "/privado/principal";
	}
	
}
