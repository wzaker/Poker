# Poker Hand Evaluator

## Overview

The Poker Hand Evaluator is a Java-based application that evaluates different poker hands based on the standard rules of poker. It can identify various hand types, such as pairs, two pairs, three of a kind, straights, flushes, full houses, four of a kind, and straight flushes.

## Features

- **Hand Evaluation:** Determine if a hand contains a specific combination of cards.
- **Card Representation:** Each card is represented by its value and suit.
- **Deck Management:** Create, shuffle, and deal cards from a standard deck of 52 playing cards.

## Classes

### `Card`

Represents a playing card from a standard deck, with properties for value and suit.

- **Value:** Ranges from 1 (Ace) to 13 (King).
- **Suit:** Represented as integers (0: Spades, 1: Hearts, 2: Clubs, 3: Diamonds).

### `Deck`

Manages a collection of `Card` objects. It provides methods to:

- Initialize a standard deck of 52 cards.
- Shuffle the deck.
- Cut the deck at a specified position.
- Deal a specified number of cards.

### `PokerHandEvaluator`

Contains static methods for evaluating poker hands based on an array of `Card` objects. The evaluation methods include:

- `hasPair(Card[] cards)`
- `hasTwoPair(Card[] cards)`
- `hasThreeOfAKind(Card[] cards)`
- `hasStraight(Card[] cards)`
- `hasFlush(Card[] cards)`
- `hasFullHouse(Card[] cards)`
- `hasFourOfAKind(Card[] cards)`
- `hasStraightFlush(Card[] cards)`

## Usage

1. **Create a Deck:**
   ```java
   Deck deck = new Deck();
   ```
2. **Shuffle the Deck:**
   ```java
   deck.shuffle();
   ```
4. **Deal Cards:**
   ```java
   Card[] hand = deck.deal(5); // Deal 5 cards
   ```
6. **Evaluate the Hand**
   ```java
   boolean isPair = PokerHandEvaluator.hasPair(hand);
   boolean isFlush = PokerHandEvaluator.hasFlush(hand);
   ```

## Example
```java
public class PokerExample {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();
        Card[] hand = deck.deal(5);

        System.out.println("Your hand: ");
        for (Card card : hand) {
            System.out.println(card);
        }

        if (PokerHandEvaluator.hasPair(hand)) {
            System.out.println("You have a pair!");
        } else {
            System.out.println("No pair found.");
        }
    }
}
```

