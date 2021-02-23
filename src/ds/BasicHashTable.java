package ds;

public class BasicHashTable<X, Y> {
    private HashEntry[] data;
    private int capacity;
    private int size;

    public BasicHashTable(int tableSize) {
        this.capacity = tableSize;
        this.data = new HashEntry[this.capacity];
        this.size = 0;
    }

    public Y get(X key) {
        int hash = calculateHash(key);

        if (data[hash] == null) {
            return null;
        } else {
            return (Y) data[hash].getValue();
        }

    }

    public Y delete(X key) {
        Y value = get(key);

        if (value != null) {
            int hash = calculateHash(key);
            data[hash] = null;
            size--;
            hash = (hash + 1) % this.capacity;

            while (data[hash] != null) {
                HashEntry he = data[hash];
                data[hash] = null;
                put((X) he.getKey(), (Y) he.getValue());

                size--;
                hash = (hash + 1) % this.capacity;
            }
        }
        return value;
    }

    public boolean hasKey(X key){
        int hash = calculateHash(key);

        if(data[hash]==null){
            return false;
        }else {
            if(data[hash].getKey().equals(key)){
                return true;
            }
        }
        return false;
    }

    public boolean hasValue(Y value){
        for(int i=0;i<this.capacity;i++){
            HashEntry entry =data[i];

            if(entry!=null&&entry.getValue().equals(value)){
                return true;
            }
        }
        return false;
    }


    public void put(X key, Y value) {
        int hash = calculateHash(key);

        data[hash] = new HashEntry<X, Y>(key, value);
        size++;
    }

    private int calculateHash(X key) {
        int hash = (key.hashCode() % this.capacity);
        //this is necessary to deal with collisions
        while (data[hash] != null && !data[hash].getKey().equals(key)) {
            hash = (hash + 1) % this.capacity;
        }
        return hash;
    }

    public int size() {
        return this.size;
    }

    public class HashEntry<X, Y> {
        private X key;
        private Y value;

        public HashEntry(X key, Y value) {
            this.key = key;
            this.value = value;
        }

        public X getKey() {
            return key;
        }

        public void setKey(X key) {
            this.key = key;
        }

        public Y getValue() {
            return value;
        }

        public void setValue(Y value) {
            this.value = value;
        }
    }
}
