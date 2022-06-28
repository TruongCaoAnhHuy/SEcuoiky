package Control;

import Entity.BookingCard;

public class RefundCardController {
    private BookingCard bookingCard = new BookingCard();

    public RefundCardController(BookingCard bookingCard) {
        this.bookingCard = bookingCard;
    }

    public int refundCard() {
        if(!bookingCard.getNumberPlate().equals("0000")){
            System.out.println("[REFUND FAILED] You need to return the bike before refund card!!!");
            return 0;
        }else{
            if(bookingCard.checkTypeCard() == 1){
                System.out.println("You will get the remaining  " + bookingCard.getBalance() +" d in the card");
            }
            System.out.println("[REFUND SUCCESS] Thank you for using our service");
            BookingCard.getBookingCards().remove(BookingCard.getBookingCards().search("username", bookingCard.getUsername()));
            BookingCard.getBookingCards().write();
            return 1;
        }
    }
}
