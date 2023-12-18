package poker;

import java.util.Random;
/*
 * This class represents a deck of cards.
 * It starts off with 52 cards, but as cards are dealt from the deck, 
 * the number of cards becomes smaller. 
 * The class has one private instance variable that stores the Cards 
 * that are currently in the Deck:
 */

public class Deck {

	private Card[] cards;

	/*
	 * This constructor initializes the Deck with 52 card objects, 
	 * representing the 52 cards that are in a standard deck. 
	 * The first card in the array is Ace of Spades, and the 
	 * last card is King of Diamonds
	 */
	public Deck() {
		cards = new Card[52];
		int index = 0;
		for(int suit = 0; suit < 4; suit++) {
			for(int value = 1; value <= 13; value++) {
				cards[index] = new Card(value, suit);
				index++;
			}
		}
	}

	/*
	 * Standard copy constructor, will construct a new deck as a copy. 
	 * the parameter other is the deck to copy.
	 */
	public Deck(Deck other) {
		cards = new Card[other.cards.length];
		for (int i = 0; i < other.cards.length; i++) {
			cards[i] = other.cards[i];
		}
	}

	/*
	 * This method will get the card at the specified position stated in
	 * the parameter. It will return that card. If the position is out of
	 * bounds it will throw an exception.
	 */
	public Card getCardAt(int position) {
		if (position < 0 || position >= cards.length) {
			throw new IllegalArgumentException("Invalid position: " + position);
		}
		return cards[position];
	}

	/*
	 * Returns the size of the array of Cards. This method will get the 
	 * number of cards in the deck. 
	 */
	public int getNumCards() {
		return cards.length;
	}

	/*
	 * This method will re-arrange the cards that are in the deck. 
	 *  The idea is that the deck will be divided into two "packets" -- 
	 *  the top half and the bottom half.  The new array of cards will 
	 *  consist of:  the first card from the top packet, 
	 *  followed by the first card from the bottom packet,
	 *   followed by the second card from the top packet, 
	 *   followed by the second card from the bottom packet, etc.
	 */
	public void shuffle() {

		if (cards.length <= 1) {
			return; // No need to shuffle if there's only one card or no cards
		}

		int topHalfSize = (cards.length + 1 ) / 2;
		int bottomHalfSize = cards.length - topHalfSize;

		Card[] shuffledCards = new Card[cards.length];
		int currentIndex = 0;

		for (int i = 0; i < topHalfSize; i++) {
			shuffledCards[currentIndex] = cards[i];
			currentIndex += 2;
		}

		currentIndex = 1;

		for (int i = topHalfSize; i < cards.length; i++) {
			shuffledCards[currentIndex] = cards[i];
			currentIndex += 2;
		}

		cards = shuffledCards;
	}

	/*
	 * This method divides the deck into two subpackets: 
	 *  The part above the specified position, and the part that is 
	 *  at the specified position or below.  The two subpackets are reversed 
	 *  (the top packet is placed on the bottom and the bottom packet is 
	 *  placed on the top.)  The position value uses 0-based indexing as 
	 *  usual, so the card at the top of the deck is at position 0. 
	 *  It will throw an illegalArgumentException id position is out of bounds
	 */
	public void cut(int position) {
		if (position < 0 || position >= cards.length) {
			throw new IllegalArgumentException("Invalid position: " + position);
		}

		Card[] newCards = new Card[cards.length];
		int topPacketSize = position + 1;

		// Copy the bottom packet to the temporary array
		for (int i = 0; i <= cards.length - topPacketSize; i++) {
			newCards[i] = cards[i + topPacketSize - 1 ];
		}

		// Copy the top packet to the temporary array
		for (int i = cards.length - topPacketSize + 1, j = 0; i < cards.length;
				i++, j++) {
			newCards[i] = cards[j];
		}

		cards = newCards;

	}

	/*
	 * This method will remove the specified number of cards from the top of 
	 * the deck and return them as an array.
	 * The parameter numCards is the number of cards to deal. 
	 * It will return and array containing the cards dealt
	 * throwing an exception if number of cards is out of bounds
	 */
	public Card[] deal(int numCards) {
		if (numCards <= 0 || numCards > cards.length) {
			throw new IllegalArgumentException("Invalid number of cards to "
					+ "deal: " + numCards);
		}

		Card[] dealtCards = new Card[numCards];
		for (int i = 0; i < numCards; i++) {
			dealtCards[i] = cards[i];
		}

		Card[] remainingCards = new Card[cards.length - numCards];
		for (int i = numCards; i < cards.length; i++) {
			remainingCards[i - numCards] = cards[i];
		}

		cards = remainingCards;

		return dealtCards;
	}



}
