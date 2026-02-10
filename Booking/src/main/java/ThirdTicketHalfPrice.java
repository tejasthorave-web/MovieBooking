public class ThirdTicketHalfPrice implements OfferStrategy {
    public double apply(double price, int count) {
        if(count >= 3) return price * 0.5;
        return price;
    }
}