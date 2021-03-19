package com.cursomc.configuracao;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.cursomc.domain.Categoria;
import com.cursomc.domain.Cidade;
import com.cursomc.domain.Cliente;
import com.cursomc.domain.Endereco;
import com.cursomc.domain.Estado;
import com.cursomc.domain.Produto;
import com.cursomc.domain.enuns.TipoCliente;
import com.cursomc.repositories.CategoriaRepository;
import com.cursomc.repositories.CidadeRepository;
import com.cursomc.repositories.ClienteRepository;
import com.cursomc.repositories.EnderecoRepository;
import com.cursomc.repositories.EstadoRepository;
import com.cursomc.repositories.ProdutoRepository;

@Configuration
public class TesteConfig implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Override
	public void run(String... args) throws Exception {
		
	Categoria cat1 = new Categoria(null, "Informatica");	
	Categoria cat2 = new Categoria(null, "Escritório");
	
	
	Produto p1 = new Produto(null,"Computador", 20000.00);
	Produto p2 = new Produto(null,"Impressora", 800.00);
	Produto p3 = new Produto(null,"Mouse", 80.00);
	
	categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
	produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
	
	cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
	cat2.getProdutos().addAll(Arrays.asList(p2));
	
	p1.getCategorias().addAll(Arrays.asList(cat1));
	p2.getCategorias().addAll(Arrays.asList(cat2,cat1));
	p3.getCategorias().addAll(Arrays.asList(cat1));
	
	categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
	produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
	Estado est1 = new Estado(null,"Minas Gerais");
	Estado est2 = new Estado(null,"São Paulo");
	
	estadoRepository.saveAll(Arrays.asList(est1,est2));
	
	Cidade c1 = new Cidade(null,"Uberlândia", est1);
	Cidade c2 = new Cidade(null,"São Paulo", est2);
	Cidade c3 = new Cidade(null,"Campinas", est2);
	
	cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
	
	est1.getCidades().addAll(Arrays.asList(c1));
	est2.getCidades().addAll(Arrays.asList(c2,c3));
	
	estadoRepository.saveAll(Arrays.asList(est1,est2));
	
	Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com","36378912377", TipoCliente.PESSOAFISICA);
	cli1.getTelefones().addAll(Arrays.asList("213633213","93636393"));
	
	Endereco e1 = new Endereco(null,"Rua Flores", "300","Apto 203", "Jardim", "38220834",c1,cli1);
	Endereco e2 = new Endereco(null,"Av Matos", "105","Sala 800", "Centro", "38777012",c2,cli1);
	
	cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
	
	clienteRepository.saveAll(Arrays.asList(cli1));
	enderecoRepository.saveAll(Arrays.asList(e1,e2));
	}

}
