package Control;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Entity.BankCard;
import Entity.BookingCard;

public class NewBookingCardController {
    private BookingCard bookingCard;

    public BookingCard getAccount() {
        return bookingCard;
    }

    public NewBookingCardController(BookingCard bookingCard) {
        this.bookingCard = bookingCard;
    }


    public void createPrepaidCards(String username, Integer password, String email,String name,String phoneNumber,Integer balance) {
        
        List<Object> listCheck;

        if(balance <1000000){
            System.out.println("[NOT ENOUGH MONEY] You must deposit at least 1000000 to register a rental card");
            return;
        }
        
        listCheck = accountValid(username, email);
        if (!(boolean) listCheck.get(0)) {
            System.out.println(listCheck.get(1));
        } else {
            // them account moi vao CSDL
            BankCard bankCard = new BankCard();
            BookingCard bookingCard = new BookingCard("123", name, phoneNumber, balance, username, password, email, bankCard);
            BookingCard.getBookingCards().add(BookingCard.convertToJsonObject(bookingCard));// memory
            BookingCard.getBookingCards().write();
            System.out.println(listCheck.get(1));
        }

    }
    public void createPostpaidCard(String username, Integer password, String email,String iD,Integer pin) {
        // so do tuan tu - sequence
        // check valid username, email =>??? class method : accountValid
        List<Object> listCheck;
        
        listCheck = accountValid(username, email);
        if (!(boolean) listCheck.get(0)) {
            System.out.println(listCheck.get(1));
        } else {
            int index = BankCard.getBankCards().search("iD", iD);
            JsonArray tempMemory = BankCard.getBankCards().getAll();
            if(index == -1){
                System.out.println("[WRONG ID] The ID you entered is incorrect!!! ");
                return;
            }else{
                JsonObject jsonObject = tempMemory.get(index).getAsJsonObject();
                int passwordAcc = jsonObject.get("pin").getAsInt();
                if(passwordAcc == pin){
                    String name = jsonObject.get("name").getAsString();
                    String phoneNumber = jsonObject.get("phoneNumber").getAsString();
                    BankCard bankCard = BankCard.convertToObject(jsonObject);
                    BookingCard bookingCard = new BookingCard(iD, name, phoneNumber, 0, username, password, email, bankCard);
                    BookingCard.getBookingCards().add(BookingCard.convertToJsonObject(bookingCard));// memory
                    BookingCard.getBookingCards().write();
                    System.out.println(listCheck.get(1));
                }else{
                    System.out.println("[WRONG PIN] The PIN  you entered is incorrect!!!");
                }
            }



        }

    }
    public  List<Object> accountValid(String username, String email) {
        List<Object> list = new ArrayList<>();
        int index = 0;

        index = BookingCard.getBookingCards().search("username", username);
        if (index != -1) {
            // valid = false;
            list.add(false);
            list.add("[USERNAME EXISTS] An user with that username already exists.");// 1
            return list;
        }
        index = BookingCard.getBookingCards().search("email", email);
        if (index != -1) {
            // valid = false;
            list.add(false);
            list.add("[EMAIL EXISTS] A mail with that email already exists.");
            return list;
        }
        if (index == -1) {
            // valid
            list.add(true);
            list.add("[ACCOUNT_CREATED] An account has been created.");
        }
        return list;
    }
}
