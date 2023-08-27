package br.com.udemy.cursouml.domain;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class ItemPedido implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK itemPedidoPK = new ItemPedidoPK();
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	public ItemPedido() {}

	public ItemPedido(Produto produto, Pedido pedido, Double desconto, Integer quantidade, Double preco) {
		super();
		this.itemPedidoPK.setPedido(pedido);
		this.itemPedidoPK.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	@JsonIgnore
	public Pedido getPedido() {
		return this.itemPedidoPK.getPedido();
	}
	
	public Produto getProduto() {
		return this.itemPedidoPK.getProduto();
	}

	public ItemPedidoPK getItemPedidoPK() {
		return itemPedidoPK;
	}

	public void setItemPedidoPK(ItemPedidoPK itemPedidoPK) {
		this.itemPedidoPK = itemPedidoPK;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemPedidoPK);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(itemPedidoPK, other.itemPedidoPK);
	}

}
