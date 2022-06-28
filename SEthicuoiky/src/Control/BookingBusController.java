package Control;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Entity.Bus;
import Entity.BookingCard;

public class BookingBusController {
    private BookingCard bookingCard = new BookingCard();
    private Bus bus;

    public BookingBusController(BookingCard bookingCard){
        this.bookingCard = bookingCard;
    }

    public void BookingBus(int i){
        if(bookingCard.getBus().checkStatus().equals("Same route")){
            System.out.println("[BOOK FAILED] Not on the same route");
            return;
        }
        else if(bookingCard.checkTypeCard() == 1 && bookingCard.checkBalance() != true){
            System.out.println("[BOOK FAILED] You need to top up to book the bus");
            return;
        }
        else{
            JsonArray tempMemory = Bus.getBuss().getAll();
            JsonObject jsonObject = new JsonObject();
            jsonObject = tempMemory.get(i).getAsJsonObject();
            bus = Bus.convertToObject(jsonObject);
            if(bus.checkStatus().equals("Same route")){
                System.out.println("[RENT FAILED] The bus not on the same route!!!, choice another one");
                return;
            }
            bus.setStatus("Same route");
            bookingCard.setNumberPlate(bus.getNumberPlate());
            bookingCard.setBus(bus);
            int index = BookingCard.getBookingCards().search("username", bookingCard.getUsername());
            BookingCard.getBookingCards().set(index, BookingCard.convertToJsonObject(bookingCard));
            BookingCard.getBookingCards().write();
            Bus.getBuss().set(i, Bus.convertToJsonObject(bus));
            Bus.getBuss().write();
            System.out.println("[RENT SUCCESS] You can drive a bike to anywhere");
            
        }
    }

    public void showBus(int i, JsonObject jsonObject, JsonArray memory){
        jsonObject = memory.get(i).getAsJsonObject();
        Bus bus = Bus.convertToObject(jsonObject);
        bus.showInfor();
    }
}
