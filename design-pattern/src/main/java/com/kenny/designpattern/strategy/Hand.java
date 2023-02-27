package com.kenny.designpattern.strategy;

public enum Hand {

    ROCK("바위", 0),
    SCISSORS("가위", 1),
    PAPER("보", 2)
    ;

    private final String name;
    private final int handValue;

    // 생성자
    Hand(final String name, final int handValue) {
        this.name = name;
        this.handValue = handValue;
    }

    // 손의 값을 상수로 받기 위한 배열
    private static Hand[] hands = {
        ROCK, SCISSORS, PAPER
    };

    // 손의 값을 ENUM으로 가져온다
    public static Hand getHand(final int handValue) {
        return hands[handValue];
    }

    // 이겼는지 확인
    public boolean isStrongerThan( final Hand h ) {
        return fight(h) == 1;
    }

    // 졌는지 확인
    public boolean isWeakerThan(final Hand h) {
        return fight(h) == -1;
    }

    // 대결 : 무승부는 0, this가 이기면 1, 지면 -1
    private int fight( final Hand h ) {
        if (this == h) {
            return 0;
        } else if ((this.handValue + 1) % 3 == h.handValue) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Hand{" +
                "name='" + name + '\'' +
                '}';
    }
}
