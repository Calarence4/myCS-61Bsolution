public class Dessert {
    int flavour,price;
    public static int numDessert=0;
    public Dessert(int flavour,int price){
        this.flavour=flavour;
        this.price=price;
        numDessert++;
    }
    public void printDessert(){
        System.out.println(this.flavour+" "+this.price+" "+numDessert);
    }
    public static void main(String[] args){
        System.out.println("I love dessert!");
    }
}
