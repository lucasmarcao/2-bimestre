package Entidades;

import Entidades.Ingredientes;
import Entidades.Pedido;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T22:47:51")
@StaticMetamodel(Pizza.class)
public class Pizza_ { 

    public static volatile SingularAttribute<Pizza, Double> preco;
    public static volatile SingularAttribute<Pizza, String> tamanho;
    public static volatile SingularAttribute<Pizza, String> sabor;
    public static volatile SingularAttribute<Pizza, String> nomepizza;
    public static volatile ListAttribute<Pizza, Pedido> pedidoList;
    public static volatile SingularAttribute<Pizza, Integer> idtable1;
    public static volatile ListAttribute<Pizza, Ingredientes> ingredientesList;

}