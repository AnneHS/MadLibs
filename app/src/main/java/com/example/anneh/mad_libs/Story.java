/*
 * Story.java
 * Madlibs
 *
 * Created by Hella Haanstra on April 15, 2016
 *
 * Based on: CS 193A, Marty Stepp
 *
 * This class represents a Mad Libs story that comes from a text file.
 * You can construct it and pass an input stream or Scanner to read the story text.
 * After constructing it, you can ask it for each placeholder by calling
 *  getNextPlaceholder, then filling in that placeholder by calling fillInPlaceholder.
 * To see how many placeholders are left, use the methods
 *  getPlaceholderRemainingCount and isFilledIn.
 * You can get the story's text by calling its toString method.
 * A Story is Serializable, so it can be packed into an Intent as "extra" data.
 */

// !YOU MAY WANT TO CHANGE THE PACKAGE BELOW SO THAT IT MATCHES YOUR PROJECT'S PACKAGE!

package com.example.anneh.mad_libs;

import java.io.*;
import java.util.*;

public class Story implements Serializable {
    private String text;                 // text of the story
    private List<String> placeholders;   // list of placeholders to fill in
    private int filledIn;                // number of placeholders that have been filled in
    private boolean htmlMode;            // set to true to surround placeholders with <b></b> tags

    {
        // Instance initializer; runs before any constructor
        text = "";
        placeholders = new ArrayList<String>();
        filledIn = 0;
        htmlMode = false;
        clear();
    }

    // Constructs a new Story reading its text from the given input stream
    public Story(InputStream stream) {
        read(stream);
    }

    // Resets the story back to an empty initial state */
    public void clear() {
        text = "";
        placeholders.clear();
        filledIn = 0;
    }

    // Replaces the next unfilled placeholder with the given word */
    public void fillInPlaceholder(String word) {
        if (!isFilledIn()) {
            text = text.replace("<" + filledIn + ">", word);
            filledIn++;
        }
    }

    // Returns the next placeholder such as "adjective" (empty string if story complete)
    public String getNextPlaceholder() {
        if (isFilledIn()) {
            return "";
        } else {
            return placeholders.get(filledIn);
        }
    }

    // Returns total number of placeholders in the story
    public int getPlaceholderCount() {
        return placeholders.size();
    }

    // Returns the amount of placeholders left
    public int getPlaceholderRemainingCount() { return placeholders.size() - filledIn;}

    // Returns true if all placeholders have been filled in
    public boolean isFilledIn() {
        return filledIn >= placeholders.size();
    }

    // Reads initial story text from the given input stream
    public void read(InputStream stream) {
        read(new Scanner(stream));
    }

    // Reads initial story text from the given Scanner
    private void read(Scanner input) {
        while (input.hasNext()) {
            String word = input.next();
            if (word.startsWith("<") && word.endsWith(">")) {

                // A placeholder; replace with  "<0>"
                if (htmlMode) {
                    text += " <b><" + placeholders.size() + "></b>";
                } else {
                    text += " <" + placeholders.size() + ">";
                }
                // "<plural-noun>" becomes "plural noun"
                String placeholder = word.substring(1, word.length() - 1).replace("-", " ");
                placeholders.add(placeholder);
            } else {

                // Regular word: concatenate
                if (!text.isEmpty()) {
                    text += " ";
                }
                text += word;
            }
        }
    }

    // Returns story text
    public String toString() {
        return text;
    }
}
