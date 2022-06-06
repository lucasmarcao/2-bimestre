package Entidades;

import Entidades.Pedido;
import Entidades.Pessoa;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T22:47:51")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, Pessoa> pessoaidpessoa;
    public static volatile SingularAttribute<Cliente, Integer> totalDeCompras;
    public static volatile ListAttribute<Cliente, Pedido> pedidoList;
    public static volatile SingularAttribute<Cliente, Integer> idcliente;
    public static volatile SingularAttribute<Cliente, Integer> avaliacaoDoServico;

}