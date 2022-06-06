package Entidades;

import Entidades.Acompanhamentos;
import Entidades.Cliente;
import Entidades.Pizza;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T22:47:51")
@StaticMetamodel(Pedido.class)
public class Pedido_ { 

    public static volatile SingularAttribute<Pedido, Cliente> clienteIdcliente;
    public static volatile SingularAttribute<Pedido, Acompanhamentos> acompanhamentosidacompanhamentos;
    public static volatile SingularAttribute<Pedido, Short> entregue;
    public static volatile SingularAttribute<Pedido, Integer> idpedido;
    public static volatile ListAttribute<Pedido, Pizza> pizzaList;
    public static volatile SingularAttribute<Pedido, Date> datapedido;

}