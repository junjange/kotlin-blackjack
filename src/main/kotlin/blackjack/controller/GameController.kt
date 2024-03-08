package blackjack.controller

import blackjack.model.BlackJackGame
import blackjack.model.Dealer
import blackjack.model.Participants
import blackjack.model.PlayerGroup
import blackjack.view.InputView
import blackjack.view.InputView.askHitOrStay
import blackjack.view.OutputView
import blackjack.view.OutputView.printDealerDrawCard
import blackjack.view.OutputView.printEveryCards
import blackjack.view.OutputView.printGameSetting
import blackjack.view.OutputView.showPlayerCards

class GameController {
    fun run() {
        val playerGroup = createPlayerGroup()
        val dealer = Dealer()
        val participants = Participants(dealer = dealer, playerGroup = playerGroup)

        playGame(participants = participants)

        participants.matchResult()
        OutputView.printMatchResult(dealer = participants.dealer, playerGroup = participants.playerGroup)
    }

    private fun playGame(participants: Participants) {
        BlackJackGame(participants = participants).apply {
            start(printGameSetting = ::printGameSetting)
            runPlayersTurn(hitOrStay = ::askHitOrStay, showPlayerCards = ::showPlayerCards)
            runDealerTurn(printDealerDrawCard = ::printDealerDrawCard)
            finish(printEveryCards = ::printEveryCards)
        }
    }

    private fun createPlayerGroup(): PlayerGroup {
        val playersNames = InputView.inputPlayersNames()
        val participants = PlayerGroup()
        participants.addPlayer(playerNames = playersNames)
        return participants
    }
}
