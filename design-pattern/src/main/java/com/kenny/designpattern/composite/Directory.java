package com.kenny.designpattern.composite;

import java.util.ArrayList;
import java.util.List;

// Composite
public class Directory extends Entry {

    private final String name;
    // Entry를 목록으로 가지고 있음 : 멤버로 파일을 가질수도 있고, 디렉터리를 가질 수도 있음!!
    // 디렉터리가 디렉터리를 가질 수 있는 구조로, 재귀적인 호출 구조 형태가 됨!!
    private final List<Entry> directory = new ArrayList<>();

    public Directory(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        int size = 0;
        for (Entry entry : directory) {
            // 파일과 디렉터리 모두 하위클래스에서 getSize()를 구현하게 강제되어 있으므로,
            // Entry 목록의 아이템들은 모두 getSize()를 호출할 수 있다!!
            size += entry.getSize();
        }
        return size;
    }

    @Override
    protected void printList(final String prefix) {
        // String과 결합된 객체는 객체.toString()을 호출하게된다.
        // 아래의 this는 toString()과 동일하다.
        System.out.println(prefix + "/" + this);
        for (Entry entry : directory) {
            entry.printList(prefix + "/" + name);
        }
    }

    // 엔트리를 디렉터리에 추가한다!!
    public Entry add(final Entry entry) {
        directory.add(entry);
        return this;
    }
}
