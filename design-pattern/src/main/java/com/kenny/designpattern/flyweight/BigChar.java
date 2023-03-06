package com.kenny.designpattern.flyweight;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// Flyweight
public class BigChar {
    // 문자이름
    private char charname;
    // 큰 문자를 표현하는 문자열
    private String fontData;

    // 생성할때 파일을 접근해서, 인스턴스 생성에 많은 리소스가 들어가는 클래스!!
    public BigChar(final char charname) {
        this.charname = charname;
        try {
            String filename = "big" + charname + ".txt";
            StringBuffer sb = new StringBuffer();
            for (String line: Files.readAllLines(Path.of(filename))) {
                sb.append(line);
                sb.append("\n");
            }
            this.fontData = sb.toString();

        } catch (IOException e) {
            this.fontData = charname + "?";
        }
    }

    // 큰 문자를 표시한다.
    public void print() {
        System.out.println(fontData);
    }
}
