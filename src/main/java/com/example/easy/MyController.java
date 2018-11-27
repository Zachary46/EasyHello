package com.example.easy;

import com.google.gson.Gson;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by zachary on 2018/11/23.
 */
@RestController
public class MyController {
    @GetMapping("/getStudent")
    public String getStudent(){
        // 根据 mybatis-config.xml 配置的信息得到 sqlSessionFactory
        String resource = "conf.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 然后根据 sqlSessionFactory 得到 session
        SqlSession session = sqlSessionFactory.openSession();
        // 最后通过 session 的 selectList() 方法调用 sql 语句 listStudent
        List<Student> listStudent = session.selectList("userMapper.getStudent");
        Student s=new Student();
        s.setName("周杰伦");
        s.setId(110);
        listStudent.add(s);
        Gson gson = new Gson();
        String data = gson.toJson(listStudent);
        for (Student student : listStudent) {
            System.out.println("ID:" + student.getId() + ",NAME:" + student.getName());
        }

        return data;
    }
}
