package br.fatec.infra.control;


import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import br.fatec.infra.model.Pedido;
import java.util.List;

@ManagedBean
@RequestScoped
public class PedidoBean implements Serializable{
    private static final long serialVersionUID = 1L;
    private Pedido pedido;
    private List<Pedido> pedidos;
    
    public PedidoBean() {}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
