package com.example.mycallblocker.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class BlacklistFile extends File {

    public static final String END_NUMBER_DELIMETER = ",";
    public static final String DEFAULT_FILENAME = "CallBlocker_blacklist.txt";

    public BlacklistFile(File parent, String child) {
        super(parent, child);
    }

    public List<Number> load() {
        List<Number> numbers = new LinkedList<>();

        FileInputStream fin;
        BufferedReader reader;
        String line;
        Number n;
        int sep;

        try {
            fin = new FileInputStream(this);
            reader = new BufferedReader(new InputStreamReader(fin));
            while ((line = reader.readLine()) != null &&
                    (sep = line.indexOf(END_NUMBER_DELIMETER)) != -1) {
                int sep2 = line.indexOf(END_NUMBER_DELIMETER, sep + 1);
                n = new Number();
                n.number = line.substring(0, sep);
                n.name = line.substring(sep + END_NUMBER_DELIMETER.length(), sep2);
                String v = line.substring(sep2 + END_NUMBER_DELIMETER.length());
                if(v.equals("1")) {
                    n.allow = 1;
                }
                else {
                    n.allow = 0;
                }
                numbers.add(n);
            }
            fin.close();
            return numbers;
        } catch (IOException exception) {
            exception.printStackTrace();
            return numbers;
        }
    }

    public boolean store(List<Number> numbers) {
        try {
            FileOutputStream fout = new FileOutputStream(this);
            for (Number n : numbers) {
                fout.write(n.number.getBytes());
                fout.write(END_NUMBER_DELIMETER.getBytes());
                fout.write(n.name.getBytes());
                fout.write(END_NUMBER_DELIMETER.getBytes());
                if(n.allow == 1) {
                    fout.write("1".getBytes());
                }
                else {
                    fout.write("0".getBytes());
                }
                fout.write("\n".getBytes());
            }
            fout.close();
            return true;

            // if we don't have permission, bail immediately; failure message is already displayed
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
    }
}