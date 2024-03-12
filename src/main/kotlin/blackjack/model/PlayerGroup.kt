package blackjack.model

class PlayerGroup {
    private var _players: List<Player> = emptyList()
    val players: List<Player>
        get() = _players

    fun addPlayer(playerNames: List<String>) {
        require(_players.size + playerNames.size in PLAYERS_COUNT_RANGE) { INVALID_PLAYERS_COUNT_ERROR_MESSAGE }
        _players += playerNames.map { Player(nickname = Nickname(it)) }
    }

    fun drawPlayerCard(
        hitOrStay: (nickname: Nickname) -> Boolean,
        showPlayerCards: (player: Player) -> Unit,
    ) {
        players.forEach { player ->
            player.drawCardsForPlayer(hitOrStay, showPlayerCards)
        }
    }

    companion object {
        val PLAYERS_COUNT_RANGE = 1..8

        const val INVALID_PLAYERS_COUNT_ERROR_MESSAGE = "플레이어의 수는 1 ~ 8명 사이여야 합니다"
    }
}
