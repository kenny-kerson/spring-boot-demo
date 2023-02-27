package com.kenny.designpattern.strategy;

// Context
public class Player {

    private final String name;
    // Context에서 전략을 가지고 있다!!
    private final Strategy strategy;
    private int winCount;
    private int loseCount;
    private int gameCount;

    // 이름과 전략을 받아서 Context를 만든다
    public Player(final String name, final Strategy strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    // 전략에 따라서 다음 손을 결정한다!!
    public Hand nextHand() {
        return strategy.nextHand();
    }

    // 승리
    public void win() {
        strategy.study(true);
        winCount++;
        gameCount++;
    }

    // 패배
    public void lose() {
        strategy.study(false);
        loseCount++;
        gameCount++;
    }

    // 무승부
    public void even() {
        gameCount++;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", strategy=" + strategy +
                ", winCount=" + winCount +
                ", loseCount=" + loseCount +
                ", gameCount=" + gameCount +
                '}';
    }
}
