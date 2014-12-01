package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_usu", sequenceName = "SEQ_USU", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usu")
	@Column(name = "usu_id", nullable = false)
	private Integer id;
	@Column(name = "nome", length = 40, nullable = false)
	@NotEmpty(message = "Campo nome requerido")
	private String nome;
	@Column(name = "email", length = 50, nullable = false)
	@Email(message = "e-Mail inválido")
	private String email;
	@org.hibernate.annotations.NaturalId
	@Column(name = "login", length = 30, nullable = false)
	@NotEmpty(message = "Campo login requerido")
	private String login;
	@Column(name = "senha", length = 10, nullable = false)
	@NotEmpty(message = "Campo senha requerido")
	private String senha;

	private boolean ativo;

	private boolean administrador;
	
	private boolean recebeemail;

	public boolean isRecebeemail() {
		return recebeemail;
	}

	public void setRecebeemail(boolean recebeemail) {
		this.recebeemail = recebeemail;
	}

	@Column(name = "matricula", length = 10, nullable = false)
	@NotEmpty(message = "Campo matrícula requerido")
	private String matricula;

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}
