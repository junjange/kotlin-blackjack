package blackjack.model

import blackjack.exception.Exceptions.NoCardErrorException
import blackjack.model.CardNumber.ACE
import blackjack.model.Pattern.HEART
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GameDeckTest {
    @Test
    fun `카드 덱의 크기는 52개이다`() {
        val gameDeck = GameDeck(ShuffleGeneratorImpl.shuffleGameDeck())
        Assertions.assertThat(gameDeck.cards.size).isEqualTo(GameDeck.DECK_SIZE)
    }

    @Test
    fun `카드 덱에 처음 나오는 문양과 숫자는 하트와 ACE이다`() {
        val card = Card(pattern = HEART, number = ACE)
        val gameDeck = GameDeck(ShuffleGeneratorImpl.shuffleGameDeck(listOf(card)))
        Assertions.assertThat(gameDeck.cards.first()).isEqualTo(card)
    }

    @Test
    fun `카드 덱에 카드가 없다면 NoCardErrorException이 발생한다`() {
        val gameDeck = GameDeck(ShuffleGeneratorImpl.shuffleGameDeck(listOf()))
        assertThrows<NoCardErrorException> { gameDeck.drawCard() }
    }
}
