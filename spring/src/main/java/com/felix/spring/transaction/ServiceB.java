package com.felix.spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2022/1/15 17:46
 * @desc:
 */
@Service
public class ServiceB {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.MANDATORY)
    public void exec(int val){
        jdbcTemplate.update("UPDATE test SET  COUNT =? WHERE id =?;",val,2);
//        try {
            double d = 1/0;
//        }catch (Exception e){
//            e.printStackTrace();
//        }

    }

}
