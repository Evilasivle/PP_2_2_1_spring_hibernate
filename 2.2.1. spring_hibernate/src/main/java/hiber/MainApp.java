package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      User user1 = new User("Vasya", "Popov", "vasya@mail.ru");
      User user2 = new User("Petya", "Ivanov", "petya@mail.ru");
      User user3 = new User("Sasha", "Petrova", "sasha3@mail.ru");

      Car car1 = new Car(1234, "BMW", user1);
      Car car2 = new Car(5677, "Lamborgini", user2);
      Car car3 = new Car(5678, "Honda", user3);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      userService.add(car1);
      userService.add(car2);
      userService.add(car3);

      ArrayList<Car> carList = (ArrayList<Car>) userService.listCars();
      for (Car car : carList) {
         System.out.println(car.getUser().getFirstName() + " " + car.getModel() + " " + car.getSeries());
      }

      User newUser = userService.getUserByCarParameters(user1.getCar().getModel(), user1.getCar().getSeries());
      System.out.println(newUser.toString());
      context.close();
   }
}
