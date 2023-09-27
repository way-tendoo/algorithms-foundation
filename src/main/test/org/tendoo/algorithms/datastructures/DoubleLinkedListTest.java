package org.tendoo.algorithms.datastructures;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class DoubleLinkedListTest {

    @Test
    public void pushFrontTest() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        Assert.assertTrue(list.isEmpty());
        list.pushFront(5);
        Assert.assertEquals(list.getTail().getValue().intValue(), 5);
        Assert.assertEquals(list.getHead().getValue().intValue(), 5);
        Assert.assertNull(list.getHead().getNext());
        Assert.assertNull(list.getHead().getPrev());
        Assert.assertNull(list.getTail().getNext());
        Assert.assertNull(list.getTail().getPrev());

        list.pushFront(4);
        Assert.assertEquals(list.getTail().getValue().intValue(), 5);
        Assert.assertEquals(list.getHead().getValue().intValue(), 4);
        Assert.assertNotNull(list.getHead().getNext());
        Assert.assertNull(list.getHead().getPrev());
        Assert.assertNull(list.getTail().getNext());
        Assert.assertNotNull(list.getTail().getPrev());
    }

    @Test
    public void popFrontTest() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        Assert.assertFalse(list.popFront().isPresent());
        list.pushFront(0);
        Assert.assertEquals(list.popFront().get().intValue(), 0);
        for (int i = 0; i < 5; i++) {
            list.pushFront(i);
        }
        for (int i = 4; i >= 0; i--) {
            Assert.assertEquals(list.popFront().get().intValue(), i);
        }
        Assert.assertTrue(list.isEmpty());
        Assert.assertFalse(list.popFront().isPresent());
    }

    @Test
    public void pushBackTest() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        Assert.assertTrue(list.isEmpty());
        list.pushBack(5);
        Assert.assertEquals(list.getTail().getValue().intValue(), 5);
        Assert.assertEquals(list.getHead().getValue().intValue(), 5);
        Assert.assertNull(list.getHead().getNext());
        Assert.assertNull(list.getHead().getPrev());
        Assert.assertNull(list.getTail().getNext());
        Assert.assertNull(list.getTail().getPrev());

        list.pushBack(4);
        Assert.assertEquals(list.getTail().getValue().intValue(), 4);
        Assert.assertEquals(list.getHead().getValue().intValue(), 5);
        Assert.assertNotNull(list.getHead().getNext());
        Assert.assertNull(list.getHead().getPrev());
        Assert.assertNull(list.getTail().getNext());
        Assert.assertNotNull(list.getTail().getPrev());
    }

    @Test
    public void popBackTest() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        Assert.assertFalse(list.popBack().isPresent());
        list.pushBack(0);
        Assert.assertEquals(list.popBack().get().intValue(), 0);
        for (int i = 0; i < 5; i++) {
            list.pushFront(i);
        }
        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(list.popBack().get().intValue(), i);
        }
        Assert.assertTrue(list.isEmpty());
        Assert.assertFalse(list.popBack().isPresent());
    }

    @Test
    public void insertAfterTest() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        Assert.assertTrue(list.isEmpty());
        list.pushBack(1);
        DoubleLinkedList.Node<Integer> node = list.get(0).get();
        DoubleLinkedList.Node<Integer> newNode = list.insertAfter(node, 2);
        Assert.assertEquals(newNode, list.getTail());
        for (int i = 3; i < 5; i++) {
            list.pushBack(i);
        }
        node = list.get(3).get();
        DoubleLinkedList.Node<Integer> next = node.getNext();
        newNode = list.insertAfter(node, 11);
        Assert.assertEquals(newNode.getPrev(), node);
        Assert.assertEquals(newNode.getNext(), next);
    }

    @Test
    public void insertBeforeTest() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        Assert.assertTrue(list.isEmpty());
        list.pushBack(1);
        DoubleLinkedList.Node<Integer> node = list.get(0).get();
        DoubleLinkedList.Node<Integer> newNode = list.insertBefore(node, 2);
        Assert.assertEquals(newNode, list.getHead());
        for (int i = 3; i < 5; i++) {
            list.pushBack(i);
        }
        node = list.get(3).get();
        DoubleLinkedList.Node<Integer> next = node.getPrev();
        newNode = list.insertBefore(node, 11);
        Assert.assertEquals(newNode.getNext(), node);
        Assert.assertEquals(newNode.getPrev(), next);
    }

    @Test
    public void eraseTest() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.pushBack(i);
        }
        Optional<DoubleLinkedList.Node<Integer>> nodeToErase = list.get(2);
        Assert.assertTrue(nodeToErase.isPresent());
        Integer erasedNodeValue = list.erase(nodeToErase.get());
        Assert.assertEquals(erasedNodeValue.intValue(), 2);
    }

    @Test
    public void forwardTest() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.pushBack(i);
        }
        DoubleLinkedList.Node<Integer> forwardedNode = list.forward(list.getHead(), 0, 2).get();
        Assert.assertEquals(forwardedNode.getValue().intValue(), 2);
        forwardedNode = list.forward(forwardedNode, 2, 4).get();
        Assert.assertEquals(forwardedNode.getValue().intValue(), 4);
        Assert.assertFalse(list.forward(forwardedNode, 4, 100).isPresent());
    }

    @Test
    public void backTest() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.pushFront(i);
        }
        DoubleLinkedList.Node<Integer> forwardedNode = list.back(list.getTail(), 4, 2).get();
        Assert.assertEquals(forwardedNode.getValue().intValue(), 2);
        forwardedNode = list.back(forwardedNode, 2, 0).get();
        Assert.assertEquals(forwardedNode.getValue().intValue(), 4);
        Assert.assertFalse(list.back(forwardedNode, 0, 100).isPresent());
    }
}
