package poker;

import static org.junit.Assert.*;

import org.junit.Test;

public class StudentTests {

	/* Use the @Test annotation for JUnit 4 
	 * [This is just an example, please erase
	 * it and write some real tests.  We are not grading your tests on this
	 * project, but writing them will help you to debug your code!]    */
	@Test
	public void testGetCardAt() {
		Deck deck = new Deck();

		// Test case 1: Get the first card in the deck
		Card firstCard = deck.getCardAt(0);
		assertTrue(firstCard.getValue() == 1);
		assertTrue(firstCard.getSuit() == 0);

		// Test case 2: Get the last card in the deck
		Card lastCard = deck.getCardAt(deck.getNumCards() - 1);
		assertTrue(lastCard.getValue() == 13);
		assertTrue(lastCard.getSuit() == 3);
	}

	@Test
	public void testGetNumCards() {
		Deck deck = new Deck();
		assertEquals(52, deck.getNumCards());

		// Deck with all cards dealt
		Deck dealtDeck = new Deck();
		int numCardsToDeal = dealtDeck.getNumCards();
		dealtDeck.deal(numCardsToDeal); // Deal all cards
		assertTrue(dealtDeck.getNumCards() == 0);
	}

	@Test
	public void testShuffle() {

		// Test case 1: Check if the top card of the top packet is a certain card
		Deck deck1 = new Deck();
		Card topCardBeforeShuffle1 = deck1.getCardAt(0);
		deck1.shuffle();
		Card topCardAfterShuffle1 = deck1.getCardAt(0);
		assertTrue(topCardAfterShuffle1.toString().equals("A of s"));
		Card secCardAfterShuffle1 = deck1.getCardAt(1);
		assertTrue(secCardAfterShuffle1.toString().equals("A of c"));
		Card lastCardAfterShuffle1 = deck1.getCardAt(51);
		assertTrue(lastCardAfterShuffle1.toString().equals("K of d"));
		Card secLastCardAfterShuffle1 = deck1.getCardAt(50);
		assertTrue(secLastCardAfterShuffle1.toString().equals("K of h"));


		// Test case 3: Check if the deck size remains the same after shuffling
		Deck deck3 = new Deck();
		int originalSize = deck3.getNumCards();
		deck3.shuffle();
		int newSize = deck3.getNumCards();
		assertTrue(originalSize == newSize);

		// Test case 4: Check if the top card of the bottom packet is a certain card
		Deck deckk4 = new Deck();
		deckk4.shuffle();
		int bottomHalfSize = deckk4.getNumCards() / 2;
		Card topCardBottomPacket = deckk4.getCardAt(bottomHalfSize);
		assertTrue(topCardBottomPacket.toString().equals("A of h"));

		// Test when the deck has an odd number of cards
		Deck deck6 = new Deck();

		// Remove the first 17 cards to have an odd number of cards
		deck6.deal(17);

		Card topCardBeforeShuffle = deck6.getCardAt(0);
		assertTrue(topCardBeforeShuffle.toString().equals("5 of h"));

		deck6.shuffle();

		Card topCardAfterShuffle = deck6.getCardAt(0);
		assertTrue(topCardAfterShuffle.toString().equals("5 of h"));
		Card secCardAfterShuffle = deck6.getCardAt(1);
		assertTrue(secCardAfterShuffle.toString().equals("10 of c"));
		Card lastCardAfterShuffle = deck6.getCardAt(34);
		assertTrue(lastCardAfterShuffle.toString().equals("9 of c"));
		Card secLastCardAfterShuffle = deck6.getCardAt(33);
		assertTrue(secLastCardAfterShuffle.toString().equals("K of d"));

		// Test when the deck has only one card
		Deck deck61 = new Deck();
		deck61.deal(deck61.getNumCards() - 1); // Remove all but one card
		Card initialCard3 = deck61.getCardAt(0);

		deck61.shuffle();
		Card shuffledCard3 = deck61.getCardAt(0);

		assertEquals(initialCard3, shuffledCard3); // The single card should remain in the same position
	}

	@Test
	public void testCut() {

		// Test cutting at position 0
		Deck deck = new Deck();
		deck.cut(0);
		Card topCard1 = deck.getCardAt(0);
		assertTrue(topCard1.toString().equals("A of s"));
		Card lastCard1 = deck.getCardAt(51);
		assertTrue(lastCard1.toString().equals("K of d"));
		// Test cutting at position 26 (middle of the deck)
		Deck deck2 = new Deck();
		deck2.cut(26);
		Card topCard2 = deck2.getCardAt(0);
		assertTrue(topCard2.toString().equals("A of c")); 
		Card midCard = deck2.getCardAt(26);
		assertTrue(midCard.toString().equals("A of s"));
		Card lastCard2 = deck2.getCardAt(51);
		assertTrue(lastCard2.toString().equals("K of h"));

		// Test cutting at position 4 (within the deck)
		Deck deck5 = new Deck();
		deck5.cut(4);
		Card topCard5 = deck5.getCardAt(0);
		assertTrue(topCard5.toString().equals("5 of s"));

		// Test cutting at position 4 (within the deck)
		Deck deck6 = new Deck();
		deck6.cut(4);
		Card topCard6 = deck6.getCardAt(51);
		assertTrue(topCard6.toString().equals("4 of s"));

		// Test cutting at position 4 (within the deck)
		Deck deck8 = new Deck();
		deck8.cut(4);
		Card midcard = deck8.getCardAt(26);
		assertTrue(midcard.toString().equals("5 of c"));

		// Test cutting at position 51 (end of the deck)
		Deck deck3 = new Deck();
		deck3.cut(51);
		Card topCard3 = deck3.getCardAt(0);
		assertTrue(topCard3.toString().equals("K of d"));

		// Test cutting at position 10 (within the deck)
		Deck deck4 = new Deck();
		deck4.cut(10);
		Card topCard4 = deck4.getCardAt(0);
		assertTrue(topCard4.toString().equals("J of s"));
	}

	@Test
	public void testDeal() {
		Deck deck = new Deck();
		// Test case 1: Deal 3 cards from a full deck
		int numCards1 = 3;
		Card[] dealtCards1 = deck.deal(numCards1);
		assertEquals(numCards1, dealtCards1.length); // Check the number of dealt cards
		assertEquals(deck.getNumCards(), 52 - numCards1); // Check the remaining deck size

		// Test case 2: Deal 10 cards from a partially depleted deck
		int numCards2 = 10;
		Card[] dealtCards2 = deck.deal(numCards2);
		assertEquals(numCards2, dealtCards2.length); // Check the number of dealt cards
		assertEquals(deck.getNumCards(), 52 - numCards1 - numCards2); // Check the remaining deck size

		int numCardsToDeal = 5;
		int originalSize = deck.getNumCards();
		Card[] dealtCards = deck.deal(numCardsToDeal);
		assertEquals(numCardsToDeal, dealtCards.length); // Number of dealt cards matches the requested number
		assertEquals(originalSize - numCardsToDeal, deck.getNumCards()); // Deck size is reduced by the dealt cards
		for (int i = 0; i < numCardsToDeal; i++) {
			boolean foundMatch = false;
			for (int j = 0; j < deck.getNumCards(); j++) {
				if (dealtCards[i].getValue() == deck.getCardAt(j).getValue()) {
					foundMatch = true;
					break;
				}
			}
			assertTrue(foundMatch); // Check if the dealt card is still present in the deck
		}

	}
	@Test
	public void testHasPair() {
		Card[] cards = {
				new Card(2, 0),
				new Card(3, 1),
				new Card(3, 2),
				new Card(5, 3),
				new Card(4, 0)
		};
		assertTrue(PokerHandEvaluator.hasPair(cards));

		Card[] cards2 = {
				new Card(2, 0),
				new Card(3, 1),
				new Card(4, 2),
				new Card(5, 3),
				new Card(6, 0)
		};
		assertFalse(PokerHandEvaluator.hasPair(cards2));
	}

	@Test
	public void testHasTwoPair() {
		Card[] cards = {
				new Card(2, 0),
				new Card(3, 1),
				new Card(1, 2),
				new Card(1, 3),
				new Card(3, 0)
		};
		assertTrue(PokerHandEvaluator.hasTwoPair(cards));
		
		
		Card[] cards2 = {
				new Card(2, 0),
				new Card(3, 1),
				new Card(4, 2),
				new Card(5, 3),
				new Card(6, 0)
		};
		assertFalse(PokerHandEvaluator.hasTwoPair(cards2));
		assertTrue(!PokerHandEvaluator.hasTwoPair(cards2));
	}

	@Test
	public void testHasThreeOfAKind() {
		Card[] cards = {
				new Card(2, 0),
				new Card(2, 1),
				new Card(4, 2),
				new Card(2, 3),
				new Card(5, 0)
		};
		assertTrue(PokerHandEvaluator.hasThreeOfAKind(cards));
		
		
		Card[] cards2 = {
				new Card(2, 0),
				new Card(3, 1),
				new Card(4, 2),
				new Card(5, 3),
				new Card(6, 0)
		};
		assertFalse(PokerHandEvaluator.hasThreeOfAKind(cards2));
		
	}

	@Test
	public void testHasStraight() {
		Card[] cards = {
				new Card(2, 0),
				new Card(3, 1),
				new Card(4, 2),
				new Card(5, 3),
				new Card(6, 0)
		};
		assertTrue(PokerHandEvaluator.hasStraight(cards));
		
		Card[] cards2 = {
				new Card(1, 0),
				new Card(3, 1),
				new Card(3, 2),
				new Card(5, 3),
				new Card(6, 0)
		};
		assertFalse(PokerHandEvaluator.hasStraight(cards2));
	}

	@Test
	public void testHasFlush() {
		Card[] cards = {
				new Card(2, 0),
				new Card(4, 0),
				new Card(6, 0),
				new Card(8, 0),
				new Card(10, 0)
		};
		assertTrue(PokerHandEvaluator.hasFlush(cards));
		
		Card[] cards2 = {
				new Card(2, 0),
				new Card(3, 1),
				new Card(4, 2),
				new Card(5, 3),
				new Card(6, 0)
		};
		assertFalse(PokerHandEvaluator.hasFlush(cards2));
	}

	@Test
	public void testHasFullHouse() {
		Card[] cards = {
				new Card(2, 0),
				new Card(2, 1),
				new Card(2, 2),
				new Card(4, 3),
				new Card(4, 0)
		};
		assertTrue(PokerHandEvaluator.hasFullHouse(cards));
		
		Card[] cards2 = {
				new Card(2, 0),
				new Card(9, 1),
				new Card(2, 2),
				new Card(4, 3),
				new Card(4, 0)
		};
		assertFalse(PokerHandEvaluator.hasFullHouse(cards2));
	}

	@Test
	public void testHasFourOfAKind() {
		Card[] cards = {
				new Card(2, 0),
				new Card(2, 1),
				new Card(2, 2),
				new Card(2, 3),
				new Card(5, 0)
		};
		assertTrue(PokerHandEvaluator.hasFourOfAKind(cards));
		
		Card[] cards2 = {
				new Card(2, 0),
				new Card(3, 1),
				new Card(4, 2),
				new Card(5, 3),
				new Card(6, 0)
		};
		assertFalse(PokerHandEvaluator.hasFourOfAKind(cards2));
	}

	@Test
	public void testHasStraightFlush() {
		Card[] cards = {
				new Card(2, 0),
				new Card(3, 0),
				new Card(4, 0),
				new Card(5, 0),
				new Card(6, 0)
		};
		assertTrue(PokerHandEvaluator.hasStraightFlush(cards));
		
		Card[] cards2 = {
				new Card(2, 0),
				new Card(3, 1),
				new Card(4, 2),
				new Card(5, 3),
				new Card(6, 0)
		};
		assertFalse(PokerHandEvaluator.hasStraightFlush(cards2));
	}

	@Test
	public void testHasStraightWithAceLow() {
		Card[] cards = {
				new Card(1, 2),
				new Card(2, 3),
				new Card(3, 1),
				new Card(4, 3),
				new Card(5, 2) 
		};
		assertTrue(PokerHandEvaluator.hasStraight(cards));
		
		Card[] cards2 = {
				new Card(2, 0),
				new Card(3, 1),
				new Card(6, 2),
				new Card(5, 3),
				new Card(6, 0)
		};
		assertFalse(PokerHandEvaluator.hasStraight(cards2));
	}

}
