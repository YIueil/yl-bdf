package cn.yiueil.entity;

import cn.yiueil.lang.instance.HasGuid;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "t_employee")
public class Employee implements HasGuid, Serializable {
    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "s_employee", allocationSize = 1)
    private Integer id;

    private String guid;

    private String name;

    private Double salary;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", guid='" + guid + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
