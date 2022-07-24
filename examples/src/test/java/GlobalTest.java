import cn.yiueil.data.impl.JpaBaseDao;
import cn.yiueil.entity.Employee;
import cn.yiueil.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebAppConfiguration
@SpringJUnitConfig(locations = {"classpath:spring.xml"})
public class GlobalTest {
    @Autowired
    JpaBaseDao jpaBaseDao;

    @Autowired
    EmployeeRepository employeeRepository;

    /**
     * 测试：增加一个张三 一个李四
     */
    @Test
    @Transactional
    @Commit
    public void test1(){
        Employee employee = new Employee("张三");
        jpaBaseDao.save(employee);
        employee = new Employee("李四");
        jpaBaseDao.save(employee);
        employee = new Employee("王五");
        jpaBaseDao.save(employee);
        System.out.println();
    }

    /**
     * 测试：删除
     */
    @Test
    @Commit
    @Transactional
    public void test2(){
        jpaBaseDao.deleteById(Employee.class, 1);
    }

    /**
     * 测试：查询
     */
    @Test
    public void test3(){
        jpaBaseDao.findById(Employee.class, 2).ifPresent(System.out::println);
    }

    /**
     * 测试：更新
     */
    @Test
    @Commit
    @Transactional
    public void test4(){
        Employee employee = new Employee();
        employee.setId(7);
        employee.setName("小乌龟2");
        jpaBaseDao.save(employee);
    }

    /**
     * 测试：
     */
    @Test
    @Transactional
    public void test5(){
        HashMap<String, Object> filter = new HashMap<>();
        List<String> nameList = new ArrayList<>();
        nameList.add("张三");
        nameList.add("李四");
        filter.put("nameList", nameList);
        filter.put("ccc", 1);
        List<Map<String, Object>> list = jpaBaseDao.sqlAsMap("select * from t_employee where name in (:nameList)", filter);
        System.out.println(list);
    }

    /**
     * 测试：
     */
    @Test
    @Transactional
    public void test6(){
        List<Map<String, Object>> list = jpaBaseDao.sqlAsMap("select * from t_employee", null, 2, 3);
        System.out.println(list);
    }

    /**
     * 测试：测试更新删除
     */
    @Test
    @Transactional
    @Commit
    public void test7(){
        HashMap<String, Object> filter = new HashMap<>();
        filter.put("id", 6);
        System.out.println(jpaBaseDao.executeUpdate("delete from t_employee where id = ?1", filter));
    }
}
