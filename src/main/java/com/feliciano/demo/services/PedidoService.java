package com.feliciano.demo.services;

import com.feliciano.demo.repositories.ItemPedidoRepository;
import com.feliciano.demo.repositories.PagamentoRepository;
import com.feliciano.demo.repositories.PedidoRepository;
import com.feliciano.demo.resources.domain.Cliente;
import com.feliciano.demo.resources.domain.ItemPedido;
import com.feliciano.demo.resources.domain.PagamentoComBoleto;
import com.feliciano.demo.resources.domain.Pedido;
import com.feliciano.demo.resources.domain.enums.EstadoPagamento;
import com.feliciano.demo.security.SpringSecurityUser;
import com.feliciano.demo.services.exceptions.AuthorizationException;
import com.feliciano.demo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;
    @Autowired
    private BoletoService boletoService;
    @Autowired
    private ProdutoService prodService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private PagamentoRepository pagamento;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private EmailService emailService;

    public Pedido find(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    @Transactional
    public Pedido insert(Pedido obj) {
        obj.setId(null); // it doesn't allow the passing of id to database
        obj.setInstante(new Date());
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }
        obj = repo.save(obj);
        pagamento.save(obj.getPagamento());

        for (ItemPedido p : obj.getItens()) {
            p.setDesconto(0.0);
            p.setProduto(prodService.find(p.getProduto().getId()));
            p.setPreco(p.getProduto().getPreco());
            p.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        emailService.sendOrderConfirmationHtmlEmail(obj);
        return obj;
    }

    public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        SpringSecurityUser user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso negado");
        }
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Cliente cliente = clienteService.find(user.getId());
        return repo.findByCliente(cliente, pageRequest);

    }
}
