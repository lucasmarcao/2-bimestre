package Entidades;

import Entidades.Funcionario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T22:47:51")
@StaticMetamodel(Cargos.class)
public class Cargos_ { 

    public static volatile SingularAttribute<Cargos, String> descricaoCargo;
    public static volatile ListAttribute<Cargos, Funcionario> funcionarioList;
    public static volatile SingularAttribute<Cargos, Integer> idcargos;
    public static volatile SingularAttribute<Cargos, String> nomecargo;

}