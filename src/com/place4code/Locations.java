package com.place4code;

import com.sun.corba.se.impl.io.IIOPInputStream;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();

    public static void main(String[] args) throws FileNotFoundException, IOException {

        try(ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))) {
            int count = 0;
            for (Location location : locations.values()) {
                outputStream.writeObject(location);
                count++;
            }
            System.out.println("I wrote " + count + " locations");
        }

    }

    static {

        try(ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {

            boolean end = false;
            while (!end) {

                try {
                    Location location = (Location) inputStream.readObject();
                    locations.put(location.getLocationID(), location);
                } catch (EOFException e) {
                    end = true;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();

    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}