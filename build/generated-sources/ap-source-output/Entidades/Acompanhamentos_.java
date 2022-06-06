package Entidades;

import Entidades.Fornecedor;
import Entidades.Pedido;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T22:47:51")
@StaticMetamodel(Acompanhamentos.class)
public class Acompanhamentos_ { 

    public static volatile SingularAttribute<Acompanhamentos, Fornecedor> fornecedoridfornecedor;
    public static volatile SingularAttribute<Acompanhamentos, String> nomeaconpanhamento;
    public static volatile SingularAttribute<Acompanhamentos, Integer> idacompanhamentos;
    public static volatile ListAttribute<Acompanhamentos, Pedido> pedidoList;

}