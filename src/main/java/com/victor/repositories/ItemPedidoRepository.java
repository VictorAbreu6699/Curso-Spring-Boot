package com.victor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victor.domain.ItemPedido;

@Repository 
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
