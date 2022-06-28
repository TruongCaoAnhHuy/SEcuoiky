package Control;

import com.google.gson.JsonObject;

import Entity.BookingCard;

public class DepositController {
    private BookingCard bookingCard;

    public DepositController(BookingCard bookingCard) {
        this.bookingCard = bookingCard;
    }

    public void addMoneyCard(Integer money) {
        if(bookingCard.checkTypeCard() == 1){
            System.out.println("Your balance: "+ bookingCard.getBalance());
            bookingCard.setBalance(bookingCard.getBalance()+money);
            int index = BookingCard.getBookingCards().search("username",bookingCard.getUsername());
            JsonObject jsonObject = BookingCard.convertToJsonObject(bookingCard);

            BookingCard.getBookingCards().set(index, jsonObject);
            BookingCard.getBookingCards().write();
            System.out.println("[DEPOSIT SUCCESS] Money has been transferred to your card" );
            System.out.println("Your new balance: "+ bookingCard.getBalance());
        }else{
            System.out.println("[Deposit failed] You can't top up your postpaid card !!!");
        }
    }
}
