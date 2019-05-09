package com.place4code;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();

    public static void main(String[] args) throws FileNotFoundException, IOException {

        //###############################################################
        // DataOutputStream, BufferedOutputStream, FileOutputStream
        // save binary:

        try(DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))) {
            for (Location location : locations.values()) {
                outputStream.writeInt(location.getLocationID());
                outputStream.writeUTF(location.getDescription());
                outputStream.writeInt(location.getExits().size() - 1);
                for (String direction : location.getExits().keySet()) {
                    outputStream.writeUTF(direction);
                    outputStream.writeInt(location.getExits().get(direction));
                }
            }
        }

    }

    static {



        /*
            read locations from file and save in HashMap (BufferedReader)
        */
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader("locations_big.txt")))) {

            scanner.useDelimiter(",");

            while(scanner.hasNextLine()) {

                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();

                locations.put(loc, new Location(loc, description, new LinkedHashMap<String, Integer>()));
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


        //copy to file

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("locations.txt"));
            BufferedWriter writerDirections = new BufferedWriter(new FileWriter("directions.txt"))) {

            for (Location location : locations.values()) {
                writer.write(location.getLocationID() + "," + location.getDescription() + "\n");

                for (String direction : location.getExits().keySet()) {
                    if (!direction.equalsIgnoreCase("Q")) {
                        writerDirections.write(location.getLocationID() + "," + direction + "," + location.getExits().get(direction) + "\n");
                    }
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