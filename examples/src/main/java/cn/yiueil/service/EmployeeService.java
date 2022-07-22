package cn.yiueil.service;

import cn.yiueil.data.impl.JpaBaseDao;
import cn.yiueil.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    @Autowired
    JpaBaseDao jpaBaseDao;
}
