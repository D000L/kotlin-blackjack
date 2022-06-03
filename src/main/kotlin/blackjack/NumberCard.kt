package blackjack

data class NumberCard(override val symbol: Symbol, val number: Int) : Card {
    init {
        require(number in 2..10) { "숫자 카드의 범위는 2 ~ 10 입니다." }
    }

    override fun score(): Int = number
}