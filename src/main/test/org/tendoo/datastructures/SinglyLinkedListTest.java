package org.tendoo.datastructures;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public final class SinglyLinkedListTest {

    @Test
    public void pushFrontTest() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.pushFront(1);
        Assert.assertFalse(list.isEmpty());
        Assert.assertEquals(list.getSize(), 1);
        Assert.assertEquals(list.getHead().get().getValue().intValue(), 1);
        for (int i = 2; i <= 5; i++) {
            list.pushFront(i);
        }
        Assert.assertEquals(list.getSize(), 5);
        Assert.assertEquals(list.getHead().get().getValue().intValue(), 5);
        Assert.assertEquals(list.getHead().get().getNext().getValue().intValue(), 4);
    }

    @Test
    public void popFrontTest() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.pushFront(i);
        }
        Assert.assertEquals(list.getSize(), 5);
        for (int i = 4; i >= 0; i--) {
            Assert.assertEquals(list.popFront().get().intValue(), i);
            Assert.assertEquals(list.getSize(), i);
        }
        Assert.assertEquals(list.getSize(), 0);
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void insertAfterTest() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.pushFront(i);
        }
        SinglyLinkedList.Node<Integer> node = list.getHead().get().getNext().getNext();
        list.insertAfter(node, 9);
        Assert.assertEquals(node.getNext().getValue().intValue(), 9);
    }

    @Test
    public void eraseAfterTest() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.pushFront(i);
        }
        SinglyLinkedList.Node<Integer> node = list.getHead().get().getNext().getNext();
        Assert.assertEquals(node.getNext().getValue().intValue(), 1);
        list.eraseAfter(node);
        Assert.assertEquals(node.getNext().getValue().intValue(), 0);
    }

    @Test
    public void findTest() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        Assert.assertFalse(list.find(3).isPresent());
        for (int i = 0; i < 5; i++) {
            list.pushFront(i);
        }
        Optional<SinglyLinkedList.Node<Integer>> value = list.find(3);
        Assert.assertTrue(value.isPresent());
        Assert.assertEquals(value.get().getValue().intValue(), 3);
    }

    @Test
    public void getHeadTest() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        Assert.assertFalse(list.getHead().isPresent());
        list.pushFront(1);
        Assert.assertTrue(list.getHead().isPresent());
        Assert.assertEquals(list.getHead().get().getValue().intValue(), 1);
    }

    @Test
    public void isEmptyTest() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        Assert.assertTrue(list.isEmpty());
        list.pushFront(1);
        Assert.assertFalse(list.isEmpty());
    }
}
