package com.example.rest.API_template_demo;
import com.example.rest.API_template_demo.Configuration.MyConfig;
import com.example.rest.API_template_demo.Entity.Communication;
import com.example.rest.API_template_demo.Entity.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;


@SpringBootApplication
public class RestApiTemplateDemoApplication {

    public static void main(String[] args)  {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class, Communication.class);

        Communication communication = context.getBean("communication"
                , Communication.class);

        List<User> allUsers = communication.getAllUsers();
        System.out.println(allUsers);

        User newUser = new User(3L, "James", "Brown", (byte) 30);
        String code_1 = communication.saveUser(newUser);

        User updateUser = new User(3L, "Thomas", "Shelby", (byte) 33);
        String code_2 = communication.updateUser(updateUser);

        String code_3 = communication.deleteUser(3L);

        String finalCode = code_1 + code_2 + code_3;
        System.out.println("FinalCode: " + finalCode);

        context.close();

    }


}
