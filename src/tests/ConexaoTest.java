package tests;

import rn.UsuarioRN;
import entity.Usuario;

public class ConexaoTest {

	public static void main(String[] args) {
		Usuario usuario = new Usuario();
		UsuarioRN usuarioRN = new UsuarioRN();
		usuario.setMatricula("30131995");
		usuario.setNome("William Cassio");
		usuario.setLogin("william");
		usuario.setAtivo(true);
		usuario.setEmail("william.gomes@uninorte.com.br");
		usuario.setAdministrador(true);
		usuario.setSenha("sql");
		usuarioRN.salvar(usuario);	
	}

}
