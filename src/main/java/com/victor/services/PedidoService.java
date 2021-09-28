 package com.victor.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.domain.ItemPedido;
import com.victor.domain.PagamentoComBoleto;
import com.victor.domain.Pedido;
import com.victor.domain.enums.EstadoPagamento;
import com.victor.repositories.ItemPedidoRepository;
import com.victor.repositories.PagamentoRepository;
import com.victor.repositories.PedidoRepository;
import com.victor.repositories.ProdutoRepository;
import com.victor.services.exceptions.ObjectNotFoundException;
/**
 * Classe responsavel por implementar serviços para a classe "Pedido"
 * @author Victor 
 *
 */
@Service 
public class PedidoService {

	@Autowired 
	private PedidoRepository repo;
	
	@Autowired 
	private PagamentoRepository pagamentoRepository;
	
	@Autowired 
	private ProdutoService produtoService;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	
	/**
	 * Busca objetos do tipo PedidoRepository por id, caso não seja encontrado ele retorna uma excessão de "ObjectNotFoundException".
	 * 
	 * @param id
	 * @return
	 */
	public Pedido find(Integer id)
	{
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())); 	
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstance(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pag = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preenchePagamentoComBoleto(pag, obj.getInstance());
		}
		
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido x : obj.getItens()) {
			x.setDesconto(0.0);
			x.setPreco(produtoService.find(x.getProduto().getId()).getPreco());
			x.setPedido(obj);
			x.setProduto(produtoService.find(x.getProduto().getId()));
		}
		itemPedidoRepository.saveAll(obj.getItens());
		System.out.println(obj);
		return obj;
		
	}

}