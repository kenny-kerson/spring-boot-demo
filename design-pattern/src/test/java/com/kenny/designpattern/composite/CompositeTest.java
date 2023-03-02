package com.kenny.designpattern.composite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompositeTest {
    @Test
    void Composite패턴_실행() {
        System.out.println("Making root entries...");

        final Directory rootDir = new Directory("root");
        final Directory binDir = new Directory("bin");
        final Directory tmpDir = new Directory("tmp");
        final Directory usrDir = new Directory("usr");

        rootDir.add(binDir);
        rootDir.add(tmpDir);
        rootDir.add(usrDir);

        binDir.add(new File("vi", 10000));
        binDir.add(new File("latext", 20000));

        rootDir.printList();
        System.out.println();


        System.out.println("Making user entries...");
        final Directory youngjin = new Directory("youngjin");
        final Directory gildong = new Directory("gildong");
        final Directory dojun = new Directory("dojun");

        usrDir.add(youngjin);
        usrDir.add(gildong);
        usrDir.add(dojun);

        youngjin.add(new File("diary.html", 100));
        youngjin.add(new File("Composite.java", 200));
        gildong.add(new File("memo.tex", 300));
        dojun.add(new File("game.doc", 400));
        dojun.add(new File("junk.mail", 500));

        rootDir.printList();
    }
}