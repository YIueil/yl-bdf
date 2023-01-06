package cc.yiueil.service;

import cc.yiueil.data.impl.JpaBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    JpaBaseDao jpaBaseDao;
}
