package br.com.udemy.cursouml;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.udemy.cursouml.domain.Categoria;
import br.com.udemy.cursouml.domain.Cidade;
import br.com.udemy.cursouml.domain.Cliente;
import br.com.udemy.cursouml.domain.Endereco;
import br.com.udemy.cursouml.domain.Estado;
import br.com.udemy.cursouml.domain.ItemPedido;
import br.com.udemy.cursouml.domain.Pagamento;
import br.com.udemy.cursouml.domain.PagamentoComBoleto;
import br.com.udemy.cursouml.domain.PagamentoComCartao;
import br.com.udemy.cursouml.domain.Pedido;
import br.com.udemy.cursouml.domain.Produto;
import br.com.udemy.cursouml.domain.enums.EstadoPagamento;
import br.com.udemy.cursouml.domain.enums.TipoCliente;
import br.com.udemy.cursouml.repositories.CategoriaRepository;
import br.com.udemy.cursouml.repositories.CidadeRepository;
import br.com.udemy.cursouml.repositories.ClienteRepository;
import br.com.udemy.cursouml.repositories.EnderecoRepository;
import br.com.udemy.cursouml.repositories.EstadoRepository;
import br.com.udemy.cursouml.repositories.ItemPedidoRepository;
import br.com.udemy.cursouml.repositories.PagamentoRepository;
import br.com.udemy.cursouml.repositories.PedidoRepository;
import br.com.udemy.cursouml.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoumlApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;	
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;	
	@Autowired
	private CidadeRepository cidadeRepository;	
	@Autowired
	private ClienteRepository clienteRepository;	
	@Autowired
	private EnderecoRepository enderecoRepository;	
	@Autowired
	private PedidoRepository pedidoRepository;	
	@Autowired
	private PagamentoRepository pagamentoRepository;	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoumlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria categoria1 = new Categoria(null, "Informática");
		Categoria categoria2 = new Categoria(null, "Escritório");
		
		Produto produto1 = new Produto(null, "Computador", 2000.0);
		Produto produto2 = new Produto(null, "Impressora", 800.0);
		Produto produto3 = new Produto(null, "Mouse", 80.0);
		
		categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto2));
		
		produto1.getCategorias().addAll(Arrays.asList(categoria1));
		produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria1));

		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
		
		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");
		
		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);
		
		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));
		
		
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		
		Cliente cliente1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "222133", TipoCliente.PESSOA_FISICA);
		cliente1.getTelefones().addAll(Arrays.asList("(11)3602-5551", "(11)98307-2750"));
		
		Endereco endereco1 = new Endereco(null, "Rua das Flores", "300", "Apt 203", "Jardins", "123321", cliente1, cidade1);
		Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "543345", cliente1, cidade2);
		
		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
		
		clienteRepository.saveAll(Arrays.asList(cliente1));
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
		
		SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy hh:mm");
		
		Pedido pedido1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cliente1, endereco1);
		Pedido pedido2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cliente1, endereco2);
		
		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		pedido1.setPagamento(pagamento1);
		
		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2,sdf.parse("20/10/2017 00:00"), null);
		pedido2.setPagamento(pagamento2);
		
		cliente1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));
		
		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));
		
		ItemPedido itemPedido1 = new ItemPedido(produto1, pedido1, 0.0, 1, 2000.0);
		ItemPedido itemPedido2 = new ItemPedido(produto3, pedido1, 0.0, 2, 80.0);
		ItemPedido itemPedido3 = new ItemPedido(produto2, pedido2, 100.0, 1, 800.0);
		
		pedido1.getItemPedidos().addAll(Arrays.asList(itemPedido1, itemPedido3));
		pedido2.getItemPedidos().addAll(Arrays.asList(itemPedido2));
		
		produto1.getItensPedidos().addAll(Arrays.asList(itemPedido1));
		produto2.getItensPedidos().addAll(Arrays.asList(itemPedido3));
		produto3.getItensPedidos().addAll(Arrays.asList(itemPedido2));
		
		itemPedidoRepository.saveAll(Arrays.asList(itemPedido1, itemPedido2, itemPedido3));

	}

}
