package poker;
public class PokerHandEvaluator {
	/*
	 * The parameter for each of these methods will be an array of exactly
	 *  5 cards.  Each method will return true or false, based on whether or 
	 *  not the given set of cards satisfies the poker hand being 
	 *  evaluated in the method.  
	 */

	/*
	 * A pair is a hand where two of your cards have the same value. 
	 * The parameter is the array of cards to evaluate, it will return true
	 * if there is a pair of cards with the same value.
	 */
	public static boolean hasPair(Card[] cards) {
		for (int i = 0; i < cards.length - 1; i++) {
			for (int j = i + 1; j < cards.length; j++) {
				if (cards[i].getValue() == cards[j].getValue()) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * A two pair is a hand where two sets of your cards have the same value. 
	 * The parameter is the array of cards to evaluate, it will return true
	 * if there is a 2 pairs of cards with the same value.
	 */
	public static boolean hasTwoPair(Card[] cards) {
		int[] cardCounts = new int[14];

		for (int i = 0; i < cards.length; i++) {
			int cardValue = cards[i].getValue();
			cardCounts[cardValue]++;
		}

		int pairsCount = 0;

		for (int i = 1; i <= 13; i++) {
			if (cardCounts[i] >= 2) {
				pairsCount++;
			}
		}

		return pairsCount >= 2;
	}

	/*
	 * This method will determine whether the set of cards given through the 
	 * parameter contains a three of a kind. 
	 * Three of a kind is when you have three of the same values.
	 * This method will return true if this set has three of a kind
	 */
	public static boolean hasThreeOfAKind(Card[] cards) {
		for (int i = 0; i < cards.length; i++) {
			int count = 0;
			for (int j = 0; j < cards.length; j++) {
				if (i != j && cards[i].getValue() == cards[j].getValue()) {
					count++;
				}
			}
			if (count == 2) {
				return true;
			}
		}
		return false;}
	/*
	 * Five cards (not four) with values that are consecutive is 
	 * called a "Straight". This method will check the given cards that
	 * are passed in through the parameter to see if it contains a 
	 * straight. It will return true if it does, otherwise false.
	 * It will throw an exception if number of cards is not 5.
	 */

	public static boolean hasStraight(Card [] cards) {
		if (cards.length != 5) {
			throw new IllegalArgumentException("Invalid number of cards."
					+ " A straight requires exactly 5 cards.");
		}

		// Sort the cards by value in ascending order
		for (int i = 0; i < cards.length - 1; i++) {
			for (int j = i + 1; j < cards.length; j++) {
				if (cards[i].getValue() > cards[j].getValue()) {
					Card temp = cards[i];
					cards[i] = cards[j];
					cards[j] = temp;
				}
			}
		}
		// Check for a regular straight (no Ace-low straight)
		boolean regularStraight = true;
		for (int i = 0; i < cards.length - 1; i++) {
			if (cards[i].getValue() + 1 != cards[i + 1].getValue()) {
				regularStraight = false;
				break;
			}
		}

		// Check for an Ace-low straight
		boolean aceLowStraight = cards[0].getValue() == 1
				&& cards[1].getValue() == 10
				&& cards[2].getValue() == 11
				&& cards[3].getValue() == 12
				&& cards[4].getValue() == 13;

		return regularStraight || aceLowStraight;

	}
	/*
	 * Five cards of the same suit is called a "flush". 
	 * This method will check the given cards that
	 * are passed in through the parameter to see if it contains a 
	 * flush. It will return true if it does, otherwise false.
	 */

	public static boolean hasFlush(Card[] cards) {
		int suit = cards[0].getSuit();

		for (int i = 1; i < cards.length; i++) {
			if (cards[i].getSuit() != suit) {
				return false;
			}
		}
		return true;
	}

	/*
	 * A "full house" is a hand where a pair of cards share the same value, 
	 * and the other three cards share a second value. 
	 * This method will check the given cards that
	 * are passed in through the parameter to see if it contains a 
	 * full house. It will return true if it does, otherwise false.
	 * It will throw an exception if number of cards is not 5.
	 */
	public static boolean hasFullHouse(Card[] cards) {
		if (cards.length != 5) {
			throw new IllegalArgumentException("Invalid number of cards. "
					+ "A full house requires exactly 5 cards.");
		}

		int[] valueCount = new int[14]; 
		// Count the occurrences of each card value
		for (int i = 0; i < cards.length; i++) {
			Card card = cards[i];
			valueCount[card.getValue()]++;
		}

		boolean hasThreeOfAKind = false;
		boolean hasPair = false;

		// Check for three of a kind and pair
		for (int i = 0; i < valueCount.length; i++) {
			int count = valueCount[i];
			if (count == 3) {
				hasThreeOfAKind = true;
			} else if (count == 2) {
				hasPair = true;
			}
		}

		return hasThreeOfAKind && hasPair;
	}

	/*
	 * "four of a kind" is a hand where you hold four cards 
	 * with the same value. This method will check the given cards that
	 * are passed in through the parameter to see if it contains a 
	 * four of a kind. It will return true if it does, otherwise false.
	 * It will throw an exception if number of cards is not 5.
	 */
	public static boolean hasFourOfAKind(Card[] cards) {

		for (int i = 0; i < cards.length - 3; i++) {
			int count = 1;
			for (int j = i + 1; j < cards.length; j++) {
				if (cards[i].getValue() == cards[j].getValue()) {
					count++;
				}
			}
			if (count == 4) {
				return true;
			}
		}
		return false;
	}

	/*
	 * The "straight flush" when you hold 5 cards that qualify both as 
	 *  a straight and as a flush.  In other words you must hold 5 cards with 
	 *  consecutive values and identical suits. 
	 *  This method will check the given cards that
	 * are passed in through the parameter to see if it contains a 
	 * straight flush. It will return true if it does, otherwise false.
	 */
	public static boolean hasStraightFlush(Card[] cards) {
		return hasStraight(cards) && hasFlush(cards);
	}
}

