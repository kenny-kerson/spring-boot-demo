package com.kenny.designpattern.proxy;

// RealSubject
public class Printer implements Printable {

    private String name;

    // 인스턴스 생성시 리소스가 많이 발생하는 클래스다!!
    public Printer() {
        heavyJob("Printer 인스턴스 생성중!!");
    }

    public Printer(final String name) {
        this.name = name;
        heavyJob("Printer 인스턴스(" + name + ") 생성 중");
    }

    private void heavyJob(final String msg) {
        System.out.print(msg);
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
            System.out.print(".");
        }
        System.out.println("완료");
    }

    @Override
    public void setPrinterName(final String name) {
        this.name = name;
    }

    @Override
    public String getPrinterName() {
        return this.name;
    }

    @Override
    public void print(final String string) {
        System.out.println("===" + name + "===");
        System.out.println(string);
    }
}
