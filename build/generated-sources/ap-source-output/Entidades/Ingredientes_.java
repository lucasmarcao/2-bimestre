package Entidades;

import Entidades.Fornecedor;
import Entidades.Pizza;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T22:47:51")
@StaticMetamodel(Ingredientes.class)
public class Ingredientes_ { 

    public static volatile SingularAttribute<Ingredientes, Fornecedor> fornecedoridfornecedor;
    public static volatile ListAttribute<Ingredientes, Pizza> pizzaList;
    public static volatile SingularAttribute<Ingredientes, Integer> idingredientes;
    public static volatile SingularAttribute<Ingredientes, String> nome;

}