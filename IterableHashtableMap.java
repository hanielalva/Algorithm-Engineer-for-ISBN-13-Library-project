// --== CS400 Project One File Header ==--
// Name: Haniel Aryan Alva
// CSL Username: haniel
// Email: haalva@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader:

import java.util.ArrayList;
import java.util.Iterator;

/**
 *This class optimizes the functionality of the hash table to allow iteration over all the values stored in it
 *
 * @author Haniel Aryan Alva
 */
public class IterableHashtableMap<KeyType, ValueType> extends HashtableMap<KeyType, ValueType>
        implements IterableMapADT<KeyType, ValueType>{

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<ValueType> iterator() {

        ArrayList<ValueType> hashList = new ArrayList<ValueType>();
        for (int i = 0; i < hashMap.length; ++i) {
            if (hashMap[i] == null){
                continue;
            }
            for (int j = 0; j < hashMap[i].size(); ++j) {
                if (hashMap[i].get(j) == null){
                    continue;
                }
                hashList.add(hashMap[i].get(j).getValue());
            }
        }

        return hashList.iterator();
    }
}
