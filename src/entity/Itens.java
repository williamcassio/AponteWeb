package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@SuppressWarnings("deprecation")
@Entity
@Table(name="itens_apontamento")
@SequenceGenerator(name="seq_ite", sequenceName="SEQ_ITE", allocationSize=1)
public class Itens implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_ite")
	private Integer id;
	
	@Column(name="descricao", length=255, nullable=false)
	private String descricao;
	
	@Column(precision=1, scale=2)
	private Float duracao;
	
	@Column(name="fase", length=100, nullable=false)
	private String fase;

	@ManyToOne
	@JoinColumn(name="tip_id", nullable=true)
	@ForeignKey(name="fk_itens_tipo")
	private Tipo tipo;
	
	@ManyToOne
	@JoinColumn(name="projeto_id", nullable=true)
	@ForeignKey(name="fk_itens_projeto")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Projeto projeto;
	
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}


	@ManyToOne
	@JoinColumn(name="apo_id", nullable=false)
	@ForeignKey(name="fk_itens_apontamento")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Apontamento apontamento;
	
	@Column(name="id_chamado", nullable=true, length=15)
	private String idChamado;


	public String getIdChamado() {
		return idChamado;
	}

	public void setIdChamado(String idChamado) {
		this.idChamado = idChamado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Itens other = (Itens) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Float getDuracao() {
		return duracao;
	}


	public void setDuracao(Float duracao) {
		this.duracao = duracao;
	}


	public Tipo getTipo() {
		return tipo;
	}


	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}


	public Apontamento getApontamento() {
		return apontamento;
	}


	public void setApontamento(Apontamento apontamento) {
		this.apontamento = apontamento;
	}


	public String getFase() {
		return fase;
	}


	public void setFase(String fase) {
		this.fase = fase;
	}
	
	

}
