package com.garcom.Garcom.servicos;

import com.garcom.Garcom.entidades.Pedido;
import com.garcom.Garcom.entidades.Produto;
import com.garcom.Garcom.repositorio.GarcomRepositorio;
import com.garcom.Garcom.repositorio.PedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServico {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private GarcomRepositorio garcomRepositorio;

    public Pedido criarPedido(Pedido pedido) {
        return pedidoRepositorio.save(pedido);
    }

    public Pedido buscarPedidoPorId(String id) {
        return pedidoRepositorio.findById(id).get();
    }

    public List<Pedido> buscarPedidos() {
        return pedidoRepositorio.findAll();
    }

    public Pedido atualizarPedido(Pedido pedido) {
        if (pedido.getId() != null) {
            var ped = pedidoRepositorio.findById(pedido.getId()).get();
            var total = 0.00;
            for (Produto prod : pedido.getProdutoList()) {
                total += prod.getPreco();
            }
            ped.setStatus(pedido.getStatus());
            ped.setViagem(pedido.getViagem());
            ped.setProdutoList(pedido.getProdutoList());
            ped.setValorPedido(total);
            ped.setGarcom(pedido.getGarcom());
            return pedidoRepositorio.save(pedido);
        }
        return null;
    }

    public void deletarPedido(String id) {
        if (id != null) {
            var ped = pedidoRepositorio.findById(id).get();
            pedidoRepositorio.delete(ped);
        }
    }

    public void caixaAtualizaPedido(List<Pedido> pedidosFechados) {
        // pego todos os pedidos
        List<Pedido> pedidos = pedidoRepositorio.findAll();

        List<String> garcomList = new ArrayList<>();

        // pego os garcons que tiveram pedidos alterados
        for (int i = 0; i < pedidosFechados.size(); i++) {
            garcomList.add(pedidosFechados.get(i).getGarcom().getId());
        }

        // removo duplicatas
        var gar = removeDuplicatas(garcomList);

        // pego todos os pedidos que pertencem ao garcom e adiciono em uma lista

        List<Pedido> novosPedidos = new ArrayList<>();
        for (int j = 0; j < garcomList.size(); j++) {
            for (int i = 0; i < pedidos.size(); i++) {
                if (pedidos.get(i).getGarcom().getId().equals(garcomList.get(j))) {
                    novosPedidos.add(pedidos.get(i));
                }
            }
            var garcom = garcomRepositorio.findById(garcomList.get(j)).get();

            garcom.setPedidos(removeDuplicatas(novosPedidos));
            garcomRepositorio.save(garcom);
        }
    }

    public <T> ArrayList<T> removeDuplicatas(List<T> list) {
        ArrayList<T> newList = new ArrayList<T>();
        for (T element : list) {
            if (!newList.contains(element)) {
                newList.add((T) element);
            }
        }
        return newList;
    }
}
