package com.kenny.designpattern.proxy;

/*
 * Proxy 패턴
 *     - 필요할때 생성한다.
 *     - 대리인을 세우고, 요청에 따라 대리인이 처리할 수 있는 것들은 대리인이 처리하고, 본인이 처리해야 할때만 인스턴스를 생성한다.
 *     - 본인이 인스턴스를 생성하는데 리소스가 많이 드는 경우 사용한다.
 */
// Subject
public interface Printable {

    void setPrinterName(String name);
    String getPrinterName();
    void print(String string);
}
