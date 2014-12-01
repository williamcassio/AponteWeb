package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import rn.ProjetoRN;
import utils.MensagensUtil;
import entity.Projeto;

@ManagedBean(name="projetoBean")
@ViewScoped
public class ProjetoBean {

	private Projeto selecionado;
	
	private List<Projeto> lista;
	
	boolean exibeCampos;

	public Projeto getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Projeto selecionado) {
		this.selecionado = selecionado;
	}

	public boolean isExibeCampos() {
		return exibeCampos;
	}

	public void setExibeCampos(boolean exibeCampos) {
		this.exibeCampos = exibeCampos;
	}

	public List<Projeto> getLista() {
		if (lista == null){
			ProjetoRN projetoRN = new ProjetoRN();
			lista = projetoRN.listar();
		}
		return lista;
	}
	
	public ProjetoBean(){
		exibeCampos = false;
		lista = null;
	}
	
	public void salvar(){
		ProjetoRN projetoRN = new ProjetoRN();
		if (projetoRN.salvar(selecionado) == 0){
			MensagensUtil.mensagemErro("Erro ao gravar registro de Projeto!");
		} else {
			MensagensUtil.mensagemInfo("Projeto gravado com sucesso!");
		}
		lista = null;
		exibeCampos = false;		

	}
	
	public void novo(){
		selecionado = new Projeto();
		exibeCampos = true;
	}
	
	public void editar(){
		exibeCampos = true;
	}
	
	public void cancelar(){
		exibeCampos = false;
	}
	
	public void excluir(){
		ProjetoRN projetoRN = new ProjetoRN();
		projetoRN.excluir(selecionado);
		lista = null;
		MensagensUtil.mensagemInfo("Projeo excluído com sucesso!");
	}
	
	public String principal(){
		return "/privado/principal";
	}	
	
}
