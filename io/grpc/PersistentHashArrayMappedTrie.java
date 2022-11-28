package io.grpc;

import java.util.Arrays;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class PersistentHashArrayMappedTrie<K, V> {
    private final Node<K, V> root;

    /* loaded from: classes3.dex */
    static final class CollisionLeaf<K, V> implements Node<K, V> {
        private final K[] keys;
        private final V[] values;

        CollisionLeaf(Object obj, Object obj2, Object obj3, Object obj4) {
            this(new Object[]{obj, obj3}, new Object[]{obj2, obj4});
        }

        private CollisionLeaf(K[] kArr, V[] vArr) {
            this.keys = kArr;
            this.values = vArr;
        }

        private int indexOfKey(K k2) {
            int i2 = 0;
            while (true) {
                K[] kArr = this.keys;
                if (i2 >= kArr.length) {
                    return -1;
                }
                if (kArr[i2] == k2) {
                    return i2;
                }
                i2++;
            }
        }

        @Override // io.grpc.PersistentHashArrayMappedTrie.Node
        public V get(K k2, int i2, int i3) {
            int i4 = 0;
            while (true) {
                K[] kArr = this.keys;
                if (i4 >= kArr.length) {
                    return null;
                }
                if (kArr[i4] == k2) {
                    return this.values[i4];
                }
                i4++;
            }
        }

        @Override // io.grpc.PersistentHashArrayMappedTrie.Node
        public Node<K, V> put(K k2, V v, int i2, int i3) {
            int hashCode = this.keys[0].hashCode();
            if (hashCode != i2) {
                return CompressedIndex.a(new Leaf(k2, v), i2, this, hashCode, i3);
            }
            int indexOfKey = indexOfKey(k2);
            if (indexOfKey != -1) {
                K[] kArr = this.keys;
                Object[] copyOf = Arrays.copyOf(kArr, kArr.length);
                Object[] copyOf2 = Arrays.copyOf(this.values, this.keys.length);
                copyOf[indexOfKey] = k2;
                copyOf2[indexOfKey] = v;
                return new CollisionLeaf(copyOf, copyOf2);
            }
            K[] kArr2 = this.keys;
            Object[] copyOf3 = Arrays.copyOf(kArr2, kArr2.length + 1);
            Object[] copyOf4 = Arrays.copyOf(this.values, this.keys.length + 1);
            K[] kArr3 = this.keys;
            copyOf3[kArr3.length] = k2;
            copyOf4[kArr3.length] = v;
            return new CollisionLeaf(copyOf3, copyOf4);
        }

        @Override // io.grpc.PersistentHashArrayMappedTrie.Node
        public int size() {
            return this.values.length;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("CollisionLeaf(");
            for (int i2 = 0; i2 < this.values.length; i2++) {
                sb.append("(key=");
                sb.append(this.keys[i2]);
                sb.append(" value=");
                sb.append(this.values[i2]);
                sb.append(") ");
            }
            sb.append(")");
            return sb.toString();
        }
    }

    /* loaded from: classes3.dex */
    static final class CompressedIndex<K, V> implements Node<K, V> {
        private static final int BITS = 5;
        private static final int BITS_MASK = 31;

        /* renamed from: a  reason: collision with root package name */
        final int f10948a;

        /* renamed from: b  reason: collision with root package name */
        final Node[] f10949b;
        private final int size;

        private CompressedIndex(int i2, Node<K, V>[] nodeArr, int i3) {
            this.f10948a = i2;
            this.f10949b = nodeArr;
            this.size = i3;
        }

        static Node a(Node node, int i2, Node node2, int i3, int i4) {
            int indexBit = indexBit(i2, i4);
            int indexBit2 = indexBit(i3, i4);
            if (indexBit == indexBit2) {
                Node a2 = a(node, i2, node2, i3, i4 + 5);
                return new CompressedIndex(indexBit, new Node[]{a2}, a2.size());
            }
            if (uncompressedIndex(i2, i4) > uncompressedIndex(i3, i4)) {
                node2 = node;
                node = node2;
            }
            return new CompressedIndex(indexBit | indexBit2, new Node[]{node, node2}, node.size() + node2.size());
        }

        private int compressedIndex(int i2) {
            return Integer.bitCount((i2 - 1) & this.f10948a);
        }

        private static int indexBit(int i2, int i3) {
            return 1 << uncompressedIndex(i2, i3);
        }

        private static int uncompressedIndex(int i2, int i3) {
            return (i2 >>> i3) & 31;
        }

        @Override // io.grpc.PersistentHashArrayMappedTrie.Node
        public V get(K k2, int i2, int i3) {
            int indexBit = indexBit(i2, i3);
            if ((this.f10948a & indexBit) == 0) {
                return null;
            }
            return (V) this.f10949b[compressedIndex(indexBit)].get(k2, i2, i3 + 5);
        }

        @Override // io.grpc.PersistentHashArrayMappedTrie.Node
        public Node<K, V> put(K k2, V v, int i2, int i3) {
            int indexBit = indexBit(i2, i3);
            int compressedIndex = compressedIndex(indexBit);
            int i4 = this.f10948a;
            if ((i4 & indexBit) != 0) {
                Node[] nodeArr = this.f10949b;
                Node[] nodeArr2 = (Node[]) Arrays.copyOf(nodeArr, nodeArr.length);
                nodeArr2[compressedIndex] = this.f10949b[compressedIndex].put(k2, v, i2, i3 + 5);
                return new CompressedIndex(this.f10948a, nodeArr2, (size() + nodeArr2[compressedIndex].size()) - this.f10949b[compressedIndex].size());
            }
            int i5 = i4 | indexBit;
            Node[] nodeArr3 = this.f10949b;
            Node[] nodeArr4 = new Node[nodeArr3.length + 1];
            System.arraycopy(nodeArr3, 0, nodeArr4, 0, compressedIndex);
            nodeArr4[compressedIndex] = new Leaf(k2, v);
            Node[] nodeArr5 = this.f10949b;
            System.arraycopy(nodeArr5, compressedIndex, nodeArr4, compressedIndex + 1, nodeArr5.length - compressedIndex);
            return new CompressedIndex(i5, nodeArr4, size() + 1);
        }

        @Override // io.grpc.PersistentHashArrayMappedTrie.Node
        public int size() {
            return this.size;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("CompressedIndex(");
            sb.append(String.format("bitmap=%s ", Integer.toBinaryString(this.f10948a)));
            for (Node node : this.f10949b) {
                sb.append(node);
                sb.append(" ");
            }
            sb.append(")");
            return sb.toString();
        }
    }

    /* loaded from: classes3.dex */
    static final class Leaf<K, V> implements Node<K, V> {
        private final K key;
        private final V value;

        public Leaf(K k2, V v) {
            this.key = k2;
            this.value = v;
        }

        @Override // io.grpc.PersistentHashArrayMappedTrie.Node
        public V get(K k2, int i2, int i3) {
            if (this.key == k2) {
                return this.value;
            }
            return null;
        }

        @Override // io.grpc.PersistentHashArrayMappedTrie.Node
        public Node<K, V> put(K k2, V v, int i2, int i3) {
            int hashCode = this.key.hashCode();
            return hashCode != i2 ? CompressedIndex.a(new Leaf(k2, v), i2, this, hashCode, i3) : this.key == k2 ? new Leaf(k2, v) : new CollisionLeaf(this.key, this.value, k2, v);
        }

        @Override // io.grpc.PersistentHashArrayMappedTrie.Node
        public int size() {
            return 1;
        }

        public String toString() {
            return String.format("Leaf(key=%s value=%s)", this.key, this.value);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public interface Node<K, V> {
        V get(K k2, int i2, int i3);

        Node<K, V> put(K k2, V v, int i2, int i3);

        int size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PersistentHashArrayMappedTrie() {
        this(null);
    }

    private PersistentHashArrayMappedTrie(Node<K, V> node) {
        this.root = node;
    }

    public V get(K k2) {
        Node<K, V> node = this.root;
        if (node == null) {
            return null;
        }
        return node.get(k2, k2.hashCode(), 0);
    }

    public PersistentHashArrayMappedTrie<K, V> put(K k2, V v) {
        Node<K, V> node = this.root;
        return node == null ? new PersistentHashArrayMappedTrie<>(new Leaf(k2, v)) : new PersistentHashArrayMappedTrie<>(node.put(k2, v, k2.hashCode(), 0));
    }

    public int size() {
        Node<K, V> node = this.root;
        if (node == null) {
            return 0;
        }
        return node.size();
    }
}
