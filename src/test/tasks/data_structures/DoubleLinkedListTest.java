package tasks.data_structures;

import org.junit.jupiter.api.*;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DoubleLinkedListTest {

    @Test
    @DisplayName("Getting list size")
    void size() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        int expectedSize = 0;
        assertEquals(expectedSize, list.size());
        list = testList();
        expectedSize = 10;
        assertEquals(expectedSize, list.size());
        for (int i = 0; i < 3; i++) {
            list.add(null);
        }
        expectedSize = 13;
        assertEquals(expectedSize, list.size());
        list.clear();
        expectedSize = 0;
        assertEquals(expectedSize, list.size());
    }

    @Test
    @DisplayName("Clearing list")
    void clear() {
        DoubleLinkedList<Integer> list = testList();
        list.clear();
        int expectedSize = 0;
        String expectedList = "[]";
        assertEquals(expectedSize, list.size());
        assertEquals(expectedList, list.toString());
    }

    @Test
    @DisplayName("Converting list to String")
    void listToString() {
        DoubleLinkedList<Integer> list = DoubleLinkedList.of(212, null, 42, 1, 6, null, 592391, 475, 91, null, 12479, 9173, 1, 5, 723, 47913);
        String expectedList = "[212, null, 42, 1, 6, null, 592391, 475, 91, null, 12479, 9173, 1, 5, 723, 47913]";
        assertEquals(expectedList, list.toString());
        list.clear();
        expectedList = "[]";
        assertEquals(expectedList, list.toString());
        list.add(null);
        expectedList = "[null]";
        assertEquals(expectedList, list.toString());
    }

    @Nested
    @DisplayName("Adding Elements")
    @TestMethodOrder(MethodOrderer.DisplayName.class)
    class AddingElements {
        @Test
        @DisplayName("1. Add 10 elements")
        void fillList() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            for (int i = 1; i < 11; i++) {
                list.add(i);
            }
            int expectedSize = 10;
            Integer expectedFirstElement = 1;
            Integer expectedLastElement = 10;
            String expectedList = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]";
            assertEquals(expectedSize, list.size());
            assertEquals(expectedFirstElement, list.get(0));
            assertEquals(expectedLastElement, list.get(list.size() - 1));
            assertEquals(expectedList, list.toString());
            list.add(null);
            expectedSize = 11;
            expectedList = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, null]";
            assertEquals(expectedSize, list.size());
            assertNull(list.get(list.size() - 1));
            assertEquals(expectedList, list.toString());
        }

        @Test
        @DisplayName("2. Initialize with the static method of()")
        void staticCreation() {
            DoubleLinkedList<Integer> list = DoubleLinkedList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, null);
            int expectedSize = 11;
            Integer expectedFirstElement = 1;
            String expectedList = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, null]";
            assertEquals(expectedSize, list.size());
            assertEquals(expectedFirstElement, list.get(0));
            assertNull(list.get(list.size() - 1));
            assertEquals(expectedList, list.toString());
        }

        @Test
        @DisplayName("3. Add element at the start")
        void addToStart() {
            DoubleLinkedList<Integer> list = testList();
            list.addFirst(256);
            int expectedSize = 11;
            Integer expectedFirstElement = 256;
            String expectedList = "[256, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]";
            assertEquals(expectedSize, list.size());
            assertEquals(expectedFirstElement, list.get(0));
            assertEquals(expectedList, list.toString());
            list.addFirst(null);
            expectedSize = 12;
            expectedList = "[null, 256, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]";
            assertEquals(expectedSize, list.size());
            assertNull(list.get(0));
            assertEquals(expectedList, list.toString());
        }

        @Test
        @DisplayName("4. Add element at the end")
        void addToEnd() {
            DoubleLinkedList<Integer> list = testList();
            list.addLast(256);
            int expectedSize = 11;
            Integer expectedLastElement = 256;
            String expectedList = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 256]";
            assertEquals(expectedSize, list.size());
            assertEquals(expectedLastElement, list.get(list.size() - 1));
            assertEquals(expectedList, list.toString());
            list.addLast(null);
            expectedSize = 12;
            expectedList = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 256, null]";
            assertEquals(expectedSize, list.size());
            assertNull(list.get(list.size() - 1));
            assertEquals(expectedList, list.toString());
        }

        @Test
        @DisplayName("5. Add element at an index")
        void addAtIndex() {
            DoubleLinkedList<Integer> list = testList();
            list.add(4, 111);
            int expectedSize = 11;
            Integer expectedFirstElement = 1;
            Integer expectedLastElement = 10;
            Integer expectedElementAtIndex = 111;
            String expectedList = "[1, 2, 3, 4, 111, 5, 6, 7, 8, 9, 10]";
            assertEquals(expectedSize, list.size());
            assertEquals(expectedFirstElement, list.get(0));
            assertEquals(expectedLastElement, list.get(list.size() - 1));
            assertEquals(expectedElementAtIndex, list.get(4));
            assertEquals(expectedList, list.toString());
            list.add(7, null);
            expectedSize = 12;
            expectedList = "[1, 2, 3, 4, 111, 5, 6, null, 7, 8, 9, 10]";
            assertEquals(expectedSize, list.size());
            assertNull(list.get(7));
            assertEquals(expectedList, list.toString());
            Class<IndexOutOfBoundsException> expectedExceptionClass = IndexOutOfBoundsException.class;
            assertThrows(expectedExceptionClass, () -> list.add(-10, 100));
            assertThrows(expectedExceptionClass, () -> list.add(13, 100));
        }
    }

    @Nested
    @DisplayName("Removing Elements")
    @TestMethodOrder(MethodOrderer.DisplayName.class)
    class RemovingElements{
        @Test
        @DisplayName("1. Remove element until empty")
        void removeUntilEmpty() {
            DoubleLinkedList<Integer> list = testList();
            DoubleLinkedList<Integer> removedElements = new DoubleLinkedList<>();
            while (!list.isEmpty()) {
                removedElements.add(list.remove());
            }
            int expectedSize = 0;
            int expectedRemovedElementsCount = 10;
            String expectedRemovedElements = "[10, 9, 8, 7, 6, 5, 4, 3, 2, 1]";
            assertEquals(expectedSize, list.size());
            assertEquals(expectedRemovedElementsCount, removedElements.size());
            assertEquals(expectedRemovedElements, removedElements.toString());
            assertThrows(NoSuchElementException.class, list::remove);
        }

        @Test
        @DisplayName("2. Remove element from the start")
        void removeFromStart() {
            DoubleLinkedList<Integer> list = testList();
            Integer removedElement = list.removeFirst();
            Integer expectedRemovedElement = 1;
            int expectedSize = 9;
            String expectedList = "[2, 3, 4, 5, 6, 7, 8, 9, 10]";
            assertEquals(expectedRemovedElement, removedElement);
            assertEquals(expectedSize, list.size());
            assertEquals(expectedList, list.toString());
            list = new DoubleLinkedList<>();
            assertThrows(NoSuchElementException.class, list::removeFirst);
        }

        @Test
        @DisplayName("3. Remove element from the end")
        void removeFromEnd() {
            DoubleLinkedList<Integer> list = testList();
            Integer removedElement = list.removeLast();
            Integer expectedRemovedElement = 10;
            int expectedSize = 9;
            String expectedList = "[1, 2, 3, 4, 5, 6, 7, 8, 9]";
            assertEquals(expectedRemovedElement, removedElement);
            assertEquals(expectedSize, list.size());
            assertEquals(expectedList, list.toString());
            list = new DoubleLinkedList<>();
            assertThrows(NoSuchElementException.class, list::removeLast);
        }

        @Test
        @DisplayName("4. Remove element at index")
        void removeAtIndex() {
            DoubleLinkedList<Integer> list = testList();
            Integer removedElement = list.remove(3);
            Integer expectedRemovedElement = 4;
            int expectedSize = 9;
            String expectedList = "[1, 2, 3, 5, 6, 7, 8, 9, 10]";
            assertEquals(expectedRemovedElement, removedElement);
            assertEquals(expectedSize, list.size());
            assertEquals(expectedList, list.toString());
            assertThrows(IndexOutOfBoundsException.class, () -> list.remove(10));
            assertThrows(IndexOutOfBoundsException.class, () -> list.remove(100));
            assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        }

        @Test
        @DisplayName("5. Remove specific element")
        void removeSpecificElement() {
            DoubleLinkedList<Integer> list = testList();
            list.add(8, null);

            int removedElementIndex = list.remove(Integer.valueOf(3));
            Integer expectedRemovedElementIndex = 2;
            int expectedSize = 10;
            String expectedList = "[1, 2, 4, 5, 6, 7, 8, null, 9, 10]";
            assertEquals(expectedRemovedElementIndex, removedElementIndex);
            assertEquals(expectedSize, list.size());
            assertEquals(expectedList, list.toString());

            removedElementIndex = list.remove(null);
            expectedRemovedElementIndex = 7;
            expectedSize = 9;
            expectedList = "[1, 2, 4, 5, 6, 7, 8, 9, 10]";
            assertEquals(expectedRemovedElementIndex, removedElementIndex);
            assertEquals(expectedSize, list.size());
            assertEquals(expectedList, list.toString());

            expectedRemovedElementIndex = -1;
            assertEquals(expectedRemovedElementIndex, list.remove(Integer.valueOf(100)));
            assertEquals(expectedRemovedElementIndex, list.remove(Integer.valueOf(-1)));
            assertEquals(expectedRemovedElementIndex, list.remove(null));
        }
    }

    @Nested
    @DisplayName("Searching elements")
    class SearchingElements {
        @Test
        @DisplayName("1. Find element position")
        void findElement() {
            DoubleLinkedList<Integer> list = testList();
            Integer[] elements = {4, 10, null, 11111, 1};
            int[] expectedElementIndexes = {3, 9, -1, -1, 0};
            for (int i = 0; i < elements.length; i++) {
                assertEquals(expectedElementIndexes[i], list.findFirst(elements[i]));
            }
        }

        @Test
        @DisplayName("1. Get element at index")
        void getElement() {
            DoubleLinkedList<Integer> list = testList();
            list.addFirst(null);
            int[] elementIndexes = {3, 9, 0, 1, 4};
            Integer[] expectedElements = {3, 9, null, 1, 4};
            for (int i = 0; i < elementIndexes.length; i++) {
                assertEquals(expectedElements[i], list.get(elementIndexes[i]));
            }
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(11));
        }
    }
    private DoubleLinkedList<Integer> testList() {
        return DoubleLinkedList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }
}
