package bean;

import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import rn.ApontamentoRN;
import rn.ItensRN;
import rn.ProjetoRN;
import rn.TipoRN;
import utils.ContextoUtil;
import utils.EnviarEmail;
import utils.GeraPlanilha;
import utils.MensagensUtil;
import conversores.TipoConverter;
import entity.Apontamento;
import entity.Itens;
import entity.Projeto;
import entity.Tipo;

@SuppressWarnings("serial")
@ManagedBean(name = "aponteBean")
@ViewScoped
public class AponteBean implements Serializable {

	private Apontamento apontamento = new Apontamento();
	private List<Apontamento> listaapontamentos = new ArrayList<Apontamento>();
	private List<Itens> listaitens = new ArrayList<Itens>();
	private List<Itens> listaCarregadaBanco = new ArrayList<Itens>();
	private List<Projeto> listaprojetos = new ArrayList<Projeto>();
	
	private Integer idProjetoSelecionado;
	
	public Integer getIdProjetoSelecionado() {
		return idProjetoSelecionado;
	}

	public void setIdProjetoSelecionado(Integer idProjetoSelecionado) {
		this.idProjetoSelecionado = idProjetoSelecionado;
	}

	public List<Projeto> getListaprojetos() {
		return listaprojetos;
	}

	private Itens item = new Itens();

	private boolean mostrarckTodos;

	public boolean isMostrarckTodos() {
		return mostrarckTodos;
	}

	private Date dtIni;
	private Date dtFim;

	private String stotal;

	private boolean todos;

	private boolean pnEdicao = false;

	public Date getDtIni() {
		return dtIni;
	}

	public void setDtIni(Date dtIni) {
		this.dtIni = dtIni;
	}

	public Date getDtFim() {
		return dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	// Métodos
	public Itens getItem() {
		return item;
	}

	public void setItem(Itens item) {
		this.item = item;
	}

	private List<Tipo> tipos;

	private TipoConverter tipoConverter;

	public TipoConverter getTipoConverter() {
		return tipoConverter;
	}

	public void setTipoConverter(TipoConverter tipoConverter) {
		this.tipoConverter = tipoConverter;
	}

	public Apontamento getApontamento() {
		return apontamento;
	}

	public void setApontamento(Apontamento apontamento) {
		this.apontamento = apontamento;
	}

	public List<Itens> getListaitens() {
		return listaitens;
	}

	public void setListaitens(List<Itens> itens) {
		this.listaitens = itens;
	}

	public List<Tipo> getTipos() {
		return tipos;
	}

	public void setTipos(List<Tipo> tipos) {
		this.tipos = tipos;
	}

	public AponteBean() {
		tipoConverter = new TipoConverter();
		TipoRN tipoRN = new TipoRN();
		tipos = tipoRN.listar();
		ProjetoRN projetoRN = new ProjetoRN();
		listaprojetos = projetoRN.listar();
		criarLista();
		listaapontamentos = null;
		pnEdicao = false;
		todos = false;
		mostrarckTodos = false;
		if (ContextoUtil.getContextoBean() != null) {
			mostrarckTodos = ContextoUtil.getContextoBean().getUsuarioLogado()
					.isAdministrador();
		}
	}
	
	public boolean isProjeto(){
		return (item.getTipo() != null && item.getTipo().getId() == 1l);
	}

	public void iniciarAponte() {
		apontamento = new Apontamento();
		apontamento.setUsuario(ContextoUtil.getContextoBean()
				.getUsuarioLogado());
		apontamento.setData(new Date());
		item = new Itens();
		criarLista();
		idProjetoSelecionado = 0;
	}

	public String dataToString(Date dt) {
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		return formatador.format(dt);
	}

	public Date stringToData(String st) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date rData = null;
		try {
			rData = formatter.parse(st);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rData;
	}

	public void criarLista() {
		listaitens = new ArrayList<Itens>();
		listaCarregadaBanco = new ArrayList<Itens>();
	}

	public void inserirNaLista() {
		if (idProjetoSelecionado == 0){
			item.setProjeto(null);
		} else{
			ProjetoRN projetoRN = new ProjetoRN();
			item.setProjeto(projetoRN.carregar(idProjetoSelecionado));
		}
		listaitens.add(item);
		item = new Itens();
		idProjetoSelecionado = 0;
		somaHoras();
	}

	public void removerDaLista() {
		listaitens.remove(item);
		somaHoras();
	}

	public void pesquisar() {
		listaapontamentos = null;
	}

	public List<Apontamento> getListaapontamentos() {
		if (listaapontamentos == null) {
			ApontamentoRN apontamentoRN = new ApontamentoRN();
			if (!todos) {
				if (ContextoUtil.getContextoBean() != null) {
					listaapontamentos = apontamentoRN.listarPorData(
							ContextoUtil.getContextoBean().getUsuarioLogado(),
							dtIni, dtFim);
				}
			} else {
				listaapontamentos = apontamentoRN.listarGeral();
			}
		}
		return listaapontamentos;
	}

	public void setListaapontamentos(List<Apontamento> listaapontamentos) {
		this.listaapontamentos = listaapontamentos;
	}

	public String novoAponte() {
		iniciarAponte();
		pnEdicao = true;
		return null;
	}

	public String editarAponte() {
		if (!ContextoUtil.getContextoBean().getUsuarioLogado()
				.equals(apontamento.getUsuario())) {
			MensagensUtil
					.mensagemErro("Você não pode alterar um apontamento que não seja seu!");
			return null;
		} else {
			ItensRN itensRN = new ItensRN();
			item = new Itens();
			listaCarregadaBanco = new ArrayList<Itens>();
			listaitens = new ArrayList<Itens>();
			listaCarregadaBanco = itensRN.listar(apontamento);
			listaitens = itensRN.listar(apontamento);
			somaHoras();
			pnEdicao = true;
			return null;
		}
	}

	public void excluirAponte() {
		if (!ContextoUtil.getContextoBean().getUsuarioLogado()
				.equals(apontamento.getUsuario())) {
			MensagensUtil
					.mensagemErro("Você não pode excluir um apontamento que não seja seu!");
		} else {
			ApontamentoRN apontamentoRN = new ApontamentoRN();
			ItensRN itensRN = new ItensRN();
			listaCarregadaBanco = itensRN.listar(apontamento);
			if (listaCarregadaBanco != null && listaCarregadaBanco.size() > 0) {
				itensRN.excluirTodosApontamento(listaCarregadaBanco);
			}
			apontamentoRN.excluir(apontamento);
		}
		listaapontamentos = null;
	}

	public String salvarApontamento() {
		if (listaitens != null && listaitens.size() > 0) {
			if (existeAponteNaData()) {
				MensagensUtil
						.mensagemErro("Já existe apontamento para esta data!");
				return null;
			} else {
				ApontamentoRN apontamentoRN = new ApontamentoRN();
				apontamento = apontamentoRN.salvar(apontamento);
				salvarListaApontamento();
				MensagensUtil.mensagemInfo("Apontamento Salvo com sucesso!");
				listaapontamentos = null;
				pnEdicao = false;
				return null;
			}
		} else {
			MensagensUtil.mensagemAviso("Não existe apontamento a ser salvo!");
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public void salvarListaApontamento() {
		ItensRN itensRN = new ItensRN();
		if (listaCarregadaBanco != null && listaCarregadaBanco.size() > 0) {
			itensRN.excluirTodosApontamento(listaCarregadaBanco);
		}
		for (Iterator iterator = listaitens.iterator(); iterator.hasNext();) {
			Itens item = (Itens) iterator.next();
			item.setApontamento(apontamento);
			item.setId(null);
			itensRN.salvar(item);
		}

	}

	public boolean existeAponteNaData() {
		ApontamentoRN apontamentoRN = new ApontamentoRN();
		List<Apontamento> listaData;
		listaData = apontamentoRN.listarPorData(ContextoUtil.getContextoBean()
				.getUsuarioLogado(), apontamento.getData(), apontamento
				.getData());
		return (listaData != null && listaData.size() > 0 && apontamento
				.getId() == null);
	}

	@SuppressWarnings({ "rawtypes" })
	public void somaHoras() {
		DecimalFormat df = new DecimalFormat("##0.00");
		Float total = 0f;
		for (Iterator iterator = listaitens.iterator(); iterator.hasNext();) {
			Itens item = (Itens) iterator.next();
			total = total + item.getDuracao();
		}
		stotal = df.format(total);
	}

	public String getStotal() {
		return stotal;
	}

	public void setStotal(String stotal) {
		this.stotal = stotal;
	}

	public boolean isTodos() {
		return todos;
	}

	public void setTodos(boolean todos) {
		this.todos = todos;
	}

	public String retornarPrincipal() {
		return "/privado/principal?faces-redirect=true";
	}

	public String carregarApontes() {
		this.listaCarregadaBanco = null;
		this.listaapontamentos = null;
		return null;
	}

	public boolean isPnEdicao() {
		return pnEdicao;
	}

	public void setPnEdicao(boolean pnEdicao) {
		this.pnEdicao = pnEdicao;
	}

	public String cancelar() {
		this.listaCarregadaBanco = null;
		this.listaapontamentos = null;
		pnEdicao = false;
		return null;
	}

	public void gravarPlanilha() {
		if (this.apontamento != null) {
			SimpleDateFormat sf = new SimpleDateFormat("ddMMyyyy");
			String dataFormatada = sf.format(apontamento.getData());
			String nomeArq = "C:/planilhas/aponte_"+dataFormatada+"_"+apontamento.getUsuario().getLogin()+".xlsx";
			GeraPlanilha gp = new GeraPlanilha();
			gp.criarPlanilha(nomeArq, apontamento);
			EnviarEmail ev = new EnviarEmail();
			if (ev.enviarEmail(nomeArq, "aponte_"+dataFormatada) == true){			
				MensagensUtil.mensagemInfo("Aponte enviado com sucesso!");
			}else{
				MensagensUtil.mensagemErro("Erro ao enviar aponte por email!");
			}
			File file = new File(nomeArq);
			file.delete();
		}
	}
}
