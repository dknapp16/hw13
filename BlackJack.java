import java.util.*;

public class BlackJack {


    public static void main(String[] args) {
        Map<String, List<Integer>> deck = new HashMap<>();
        deck.put("Clubs", createCards());
        deck.put("Diamonds", createCards());
        deck.put("Spades", createCards());
        deck.put("Hearts", createCards());





        Scanner scanner = new Scanner(System.in);
        ArrayList<Card> myHand = new ArrayList<Card>();
        ArrayList<Card> dealerHand = new ArrayList<Card>();

        Card dealerFirst = dealCard(deck);
        Card dealerSecond = dealCard(deck);

        Card firstCard = dealCard(deck);
        Card secondCard = dealCard(deck);
        boolean isPlaying = true;
        myHand.add(firstCard);
        dealerHand.add(dealerFirst);
        myHand.add(secondCard);
        dealerHand.add(dealerSecond);




        int dealerSum = dealerFirst.value + dealerSecond.value; ////creates an int to hold values of dealerHand




        if (dealerSum == 21) {
            printDealerHand(dealerHand);
            System.out.println("Dealer hit 21! Dealer wins!");
            isPlaying = false;
        }
        printDealerFirst(dealerHand);



        System.out.print("Player's hand is: ");
        printCurrentHand(myHand);

        int sum = firstCard.value + secondCard.value;
        if (firstCard.value > 10) {
            int newFirstCard=10;
            sum = newFirstCard + secondCard.value;
        }
        if (secondCard.value > 10) {
            int newSecondCard=10;
            sum = firstCard.value + newSecondCard;
        }
        if (firstCard.value > 10 && secondCard.value > 10) {
            sum =20;
        }


            while (isPlaying) {
                System.out.println("\nPress 1 to hit or 2 to stand.");

                String hitOrStand = scanner.next();
                if (hitOrStand.equals("1")) {

                    Card hit = dealCard(deck);
                    myHand.add(hit);
                    printCurrentHand(myHand);

                    sum = sum + hit.value;

                    System.out.println(sum);


                    if (sum > 21) {
                        System.out.println("Your total is " + sum + ". That's a bust! You lose!");
                        isPlaying = false;
                    }


                } else if (hitOrStand.equals("2")) {

                    while (dealerSum < 17) {
                        Card dealerHit = dealCard(deck);
                        dealerHand.add(dealerHit);
                        if (dealerHit.value == 11 || dealerHit.value == 12 || dealerHit.value == 13) {
                            dealerHit.value = 10;
                        }

                        dealerSum = dealerSum + dealerHit.value;
                    }
                    if (dealerSum == 21) {
                        printDealerHand(dealerHand);
                        System.out.println("Dealer hit 21! Dealer wins!");
                    } else if (dealerSum > 16 && dealerSum > sum && dealerSum < 21) {
                        printDealerHand(dealerHand);
                        System.out.println("Dealer has " + dealerSum + ". Player has " + sum + ". Dealer wins!");
                    } else if (dealerSum == sum) {
                        printDealerHand(dealerHand);
                        System.out.println("It's a tie!");
                    } else {
                        printDealerHand(dealerHand);
                        System.out.println("Player has " + sum + " and dealer has " + dealerSum + ". Player wins!");
                    }
                    isPlaying = false;
                } else {
                    System.out.println("\nInvalid Input.");

                }

            }
        }



    public static List<Integer> createCards() {
        List<Integer> cards = new ArrayList<>();
        for (int i = 1; i < 14; i++) {
            cards.add(i);
        }


        return cards;
    }

    public static Card dealCard(Map<String, List<Integer>> deck) {
        Card nextCard = DeckRandomizer.chooseRandomCards(deck, 1).get(0);
        deck.get(nextCard.suit).remove(nextCard.value);
        return nextCard;
    }



    public static void printCurrentHand(List<Card> myHand) {

        for (int i = 0; i < myHand.size(); i++) {
            Card card = myHand.get(i);

            System.out.print(card.suit + " - " + card.value);

            if (i + 1 < myHand.size()) {
                System.out.print(", ");
            }
        }
    }

    public static void printDealerFirst(List<Card> dealerFirst) {
        System.out.println();
        Card card = dealerFirst.get(0);
        System.out.println("Dealer's first card is" + " " + card.suit + " - " + card.value);
        System.out.println();
    }




    public static void printDealerHand(List<Card> dealerHand) {

        for (int i = 0; i < dealerHand.size(); i++) {
            Card card = dealerHand.get(i);

            System.out.print(card.suit + " - " + card.value);

            if (i + 1 < dealerHand.size()) {
                System.out.print(", ");
            }
            else {
                System.out.println(" ");
            }
        }
    }








}