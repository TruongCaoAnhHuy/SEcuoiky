package Control;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Entity.BookingCard;

public class LoginController {
    private BookingCard bookingCards;

    public LoginController(BookingCard bookingCards){
        this.bookingCards = bookingCards;
    }

    public void logIn(String user, String pass){
        JsonArray tempMemory = BookingCard.getBookingCards().getAll();

        int index = -1;
        index = BookingCard.getBookingCards().search("username", user);

        if (user.equals(this.bookingCards.getUsername())) {
            System.out.println("[ALREADY LOGGED IN] You have already logged in.");

            // 2. dang nhap nhieu tai khoan=> username nhap vao != objcBookingCards.username
        } 
        else if (this.bookingCards.getUsername() != null && !this.bookingCards.getUsername().equals(user)) {
            System.out.println("[INVALID MULTIPLE LOGINS] You need  to logout first to try another login.");
            // 4. dang nhap binh thuong|
        } 
        else if (index != -1) {// bookingCardsObject => un =null, ps = null, email = null; loggedIn = false
            JsonObject jsonObject = tempMemory.get(index).getAsJsonObject();
            String passwordAcc = jsonObject.get("password").getAsString();
            if (passwordAcc.equals(pass)) {
                
                bookingCards = BookingCard.convertToObject(jsonObject);
                bookingCards.setLoggedIn(true);
                System.out.println("[LOGGED IN] You are logged in.");

            } else {
                System.out.println("[WRONG PASSWORD] The password  you enterd is incorrect!!!");
            }

        } 
        else {
            System.out.println("[NOT REGISTERD] You have to  register first.!!! ");
        }
    }

    public void setBookingCards(BookingCard bookingCards){
        this.bookingCards = bookingCards;
    }

    public BookingCard getBookingCards(){
        return bookingCards;
    }

    public void logOut(){
        this.bookingCards.logout();
    }
}
