package blackjack.model

class Hand(
    cards: List<Card> = emptyList(),
    state: UserState = UserState.RUNNING,
) {
    private var _cards: List<Card> = cards
    val cards: List<Card>
        get() = _cards

    private var _state: UserState = state
    val state: UserState
        get() = _state

    operator fun plus(other: Card): Hand {
        _cards += other
        updateState()
        return Hand(cards, state)
    }

    private fun updateState() {
        _state =
            when (calculate()) {
                in RUNNING_RANGE -> UserState.RUNNING
                BLACKJACK_NUMBER -> if (cards.size == MIN_CARD_COUNTS) UserState.BLACKJACK else UserState.STAY
                else -> UserState.BUST
            }
    }

    fun changeState(userState: UserState) {
        _state = userState
    }

    fun calculate(): Int {
        var total = cards.sumOf { it.number.value }
        val aceCount = cards.count { it.number == CardNumber.ACE }

        repeat(aceCount) {
            if (total <= BLACKJACK_NUMBER) return@repeat
            total -= DIFF_ACE_TO_ONE
        }
        return total
    }

    companion object {
        const val BLACKJACK_NUMBER = 21
        const val DIFF_ACE_TO_ONE = 10
        const val MIN_CARD_COUNTS = 2
        val RUNNING_RANGE = (0..20)
    }
}