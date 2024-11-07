package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User mihail = new User("Misha", "Petrov", "misha@mail.ru");
        User vitaliy = new User("Vitaliy", "Ivanov", "vitaliy@mail.ru");
        User anton = new User("anton", "Kuznetsov", "anton@mail.ru");
        User irina = new User("irina", "Baranova", "irina@mail.ru");

        Car bmw = new Car(243, "BMW");
        Car lada = new Car(55, "LADA");
        Car hammer = new Car(242, "HAMMER");
        Car mercedes = new Car(241, "MERCEDES");

        userService.add(mihail.setCar(bmw).setUser(mihail));
        userService.add(vitaliy.setCar(lada).setUser(vitaliy));
        userService.add(anton.setCar(hammer).setUser(anton));
        userService.add(irina.setCar(mercedes).setUser(irina));

        System.out.println(irina.getCar().getModel());

        for (User user : userService.listUsers()) {
            System.out.println(user + " " + user.getCar());
        }

        System.out.println(userService.getUserWithModelAndSeries("LADA", 55));

        context.close();
    }
}
