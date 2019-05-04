package com.place4code;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new HashMap<Integer, Location>();


    static {

        /*
            read locations from file and save in HashMap (BufferedReader)
        */
        try(BufferedReader reader = new BufferedReader(new FileReader("locations_big.txt"))) {

            String input; // to save input
            while((input = reader.readLine()) != null) {

                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String description = data[1];

                locations.put(loc, new Location(loc, description, new HashMap<String, Integer>()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
            read exits from file and save in HashMap (BufferedReader)
        */
        try(BufferedReader reader = new BufferedReader(new FileReader("directions_big.txt"))) {
            String input;
            while ((input = reader.readLine()) != null) {

                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);

                //add exit to location:
                locations.get(loc).addExit(direction, destination);
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