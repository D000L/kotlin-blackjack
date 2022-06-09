package blackjack.domain

object WinningDiscriminator {

    fun discrimination(dealer: Dealer, players: Players): List<WinningResult> {
        return listOf(getDealerResults(dealer, players)) + getPlayerResults(dealer, players)
    }

    private fun getDealerResults(dealer: Dealer, players: Players): WinningResult {
        return WinningResult(dealer).apply {
            players.forEach { player ->
                val isDealerWin = isDealerWin(dealer, player)
                updateResult(isDealerWin)
            }
        }
    }

    private fun getPlayerResults(dealer: Dealer, players: Players): List<WinningResult> {
        return players.map { player ->
            WinningResult(player).apply {
                val isPlayerWin = isDealerWin(dealer, player).not()
                updateResult(isPlayerWin)
            }
        }
    }

    private fun isDealerWin(dealer: Dealer, player: Player): ResultStatus {
        return when {
            dealer.isBust() -> ResultStatus.Lose
            player.isBust() -> ResultStatus.Win
            dealer.score > player.score -> ResultStatus.Win
            dealer.score == player.score -> ResultStatus.Draw
            dealer.score < player.score -> ResultStatus.Lose
            else -> ResultStatus.Draw
        }
    }
}
