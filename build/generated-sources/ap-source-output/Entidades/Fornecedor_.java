package Entidades;

import Entidades.Acompanhamentos;
import Entidades.Ingredientes;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T22:47:51")
@StaticMetamodel(Fornecedor.class)
public class Fornecedor_ { 

    public static volatile SingularAttribute<Fornecedor, Integer> idfornecedor;
    public static volatile SingularAttribute<Fornecedor, Date> inicioDaParceria;
    public static volatile SingularAttribute<Fornecedor, String> porteDaEmpresa;
    public static volatile SingularAttribute<Fornecedor, String> nomefornecedor;
    public static volatile ListAttribute<Fornecedor, Acompanhamentos> acompanhamentosList;
    public static volatile ListAttribute<Fornecedor, Ingredientes> ingredientesList;

}