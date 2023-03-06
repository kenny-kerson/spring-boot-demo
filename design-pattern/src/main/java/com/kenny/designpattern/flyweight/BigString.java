package com.kenny.designpattern.flyweight;

// Client
public class BigString {

    private BigChar[] bigChars;

    // BigString을 만들때, 1글자씩 파싱해서, factory를 통해 BigChar를 생성하거나 이미 만들어진 인스턴스를 가져온다!!
    public BigString(String string) {
        final BigCharFactory factory = BigCharFactory.getInstance();
        bigChars = new BigChar[string.length()];
        for (int i = 0; i < bigChars.length; i++) {
            bigChars[i] = factory.getBigChar(string.charAt(i));
        }
    }

    public void print() {
        for (BigChar bc: bigChars) {
            bc.print();
        }
    }
}
