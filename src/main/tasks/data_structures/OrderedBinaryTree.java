package tasks.data_structures;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class OrderedBinaryTree<E extends Comparable<E>> implements Collection<E>, Iterable<E> {

    int size;
    TreeNode<E> root;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        try {
            E element = (E) o;
            return findNode(element) != null;
        } catch (ClassCastException e) {
            return false;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        for (E e : this) {
            array[index++] = e;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        @SuppressWarnings("unchecked") T[] toReturn = array.length >= size ? array : (T[]) Array.newInstance(array.getClass().getComponentType(), size);
        int index = 0;
        try {
            for (E e : this) {
                toReturn[index] = (T) e;
            }
        } catch (ClassCastException e) {
            throw new ArrayStoreException();
        }
        return toReturn;
    }

    private TreeNode<E> addRecursively(TreeNode<E> newNode, TreeNode<E> currentNode, TreeNode<E> parentNode) {
        if (currentNode == null) {
            size++;
            newNode.parent = parentNode;
            return newNode;
        } else if (currentNode.compareTo(newNode) > 0) {
            currentNode.left = addRecursively(newNode, currentNode.left, currentNode);
        } else if (currentNode.compareTo(newNode) < 0) {
            currentNode.right = addRecursively(newNode, currentNode.right, currentNode);
        }
        return currentNode;
    }

    @Override
    public boolean add(E element) {
        root = addRecursively(new TreeNode<>(element, null), root, null);
        return true;
    }

    private TreeNode<E> minNode(TreeNode<E> root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    private TreeNode<E> removeRecursively(E element, TreeNode<E> currentNode) {
        if (currentNode == null) {
            return null;
        }
        if (element.compareTo(currentNode.element) < 0) {
            currentNode.left = removeRecursively(element, currentNode.left);
        } else if (element.compareTo(currentNode.element) > 0) {
            currentNode.right = removeRecursively(element, currentNode.right);
        } else if (currentNode.isLeaf()) {
            return null;
        } else if (currentNode.right == null) {
            return currentNode.left;
        } else if (currentNode.left == null) {
            return currentNode.right;
        } else {
            currentNode.element = minNode(currentNode.right).element;
            currentNode.right = removeRecursively(currentNode.element, currentNode.right);
        }
        return currentNode;
    }

    @Override
    public boolean remove(Object o) {
        E element;
        try {
            element = (E) o;
        } catch (ClassCastException e) {
            return false;
        }
        return removeRecursively(element, root) != null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (contains(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        c.forEach(this::add);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (containsAll(c)) {
            c.forEach(this::remove);
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        for (E e : this) {
            if (!c.contains(e)) {
                remove(e);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    public String toString() {
        return toString(TraverseStrategy.DFSLPR);
    }

    public String toString(TraverseStrategy traverseStrategy) {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        Iterator<E> iterator = iterator(traverseStrategy);
        while (iterator.hasNext()) {
            stringJoiner.add(iterator.next().toString());
        }
        return stringJoiner.toString();
    }

    private class OrderedTreeNodeIterator implements Iterator<TreeNode<E>> {

        TraverseStrategy traverseStrategy;

        DataStructure<TreeNode<E>> toTraverse;

        public OrderedTreeNodeIterator(TraverseStrategy traverseStrategy, TreeNode<E> startingNode) {
            this.traverseStrategy = traverseStrategy;
            toTraverse = traverseStrategy == TraverseStrategy.BFS ? new Queue<>() : new Stack<>();
            if (traverseStrategy == TraverseStrategy.DFSLPR) {
                while (startingNode != null) {
                    toTraverse.add(startingNode);
                    startingNode = startingNode.left;
                }
            } else if (traverseStrategy == TraverseStrategy.DFSRPL) {
                while (startingNode != null) {
                    toTraverse.add(startingNode);
                    startingNode = startingNode.right;
                }
            } else {
                toTraverse.add(startingNode);
            }
        }

        @Override
        public boolean hasNext() {
            return !toTraverse.isEmpty();
        }

        @Override
        public TreeNode<E> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            TreeNode<E> currentNode = toTraverse.remove();
            TreeNode<E> nodeToReturn = currentNode;
            if (traverseStrategy == TraverseStrategy.DFSLPR) {
                currentNode = currentNode.right;
                while (currentNode != null) {
                    toTraverse.add(currentNode);
                    currentNode = currentNode.left;
                }
            } else if (traverseStrategy == TraverseStrategy.DFSRPL) {
                currentNode = currentNode.left;
                while (currentNode != null) {
                    toTraverse.add(currentNode);
                    currentNode = currentNode.right;
                }
            } else {
                if (currentNode.left != null) {
                    toTraverse.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    toTraverse.add(currentNode.right);
                }
            }
            return nodeToReturn;
        }


    }

    private class OrderedTreeIterator implements Iterator<E> {

        private OrderedTreeNodeIterator nodeIterator;

        public OrderedTreeIterator(TraverseStrategy traverseStrategy) {
            this.nodeIterator = new OrderedTreeNodeIterator(traverseStrategy, root);
        }

        @Override
        public boolean hasNext() {
            return nodeIterator.hasNext();
        }

        @Override
        public E next() {
            return nodeIterator.next().element;
        }


    }

    @Override
    public Iterator<E> iterator() {
        return iterator(TraverseStrategy.DFSLPR);
    }

    public Iterator<E> iterator(TraverseStrategy strategy) {
        return new OrderedTreeIterator(strategy);
    }

    private TreeNode<E> findNode(E element) {
        Iterator<TreeNode<E>> iterator = new OrderedTreeNodeIterator(TraverseStrategy.DFSLPR, root);
        while (iterator.hasNext()) {
            TreeNode<E> currentNode = iterator.next();
            if (currentNode.element.equals(element)) {
                return currentNode;
            }
        }
        return null;
    }
}