package Entidades;

import Entidades.Cargos;
import Entidades.Pessoa;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-05T22:47:51")
@StaticMetamodel(Funcionario.class)
public class Funcionario_ { 

    public static volatile SingularAttribute<Funcionario, Pessoa> pessoaidpessoa;
    public static volatile SingularAttribute<Funcionario, Integer> idfuncionario;
    public static volatile SingularAttribute<Funcionario, Short> trabalhando;
    public static volatile SingularAttribute<Funcionario, Double> salario;
    public static volatile SingularAttribute<Funcionario, Cargos> cargosidcargos;

}