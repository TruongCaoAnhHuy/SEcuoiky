package Boundary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Control.LoginController;
import Entity.BookingCard;

public class LoginBookingCardUI {
    private LoginController loginController;
    private static Scanner sc = new Scanner(System.in);

    //Menu menu ;
    
    public LoginBookingCardUI() {
    }
    public void handleLogin(){
        loginController = new LoginController(new BookingCard());
        System.out.println("Enter a username, a password");
        List<Object> list =  loginInputs();
        loginController.logIn(list.get(0).toString(), (String)list.get(1));
        System.out.println("Logged in as # "+ loginController.getBookingCards().getUsername());
    }

    private List<Object> loginInputs() {
        List<Object> list = new ArrayList<>();
        System.out.print("USERNAME: ");
        String username = sc.nextLine();
        if(username.equals("")){
            username = sc.nextLine();
        }
        System.out.print("PASSWORD: ");
        Integer password = sc.nextInt();
        list.add(username);
        list.add(password);
        return list;
    }
    public LoginController getLoginController() {
        return loginController;
    }
}
