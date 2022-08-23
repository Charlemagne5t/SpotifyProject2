package com.example.spotifyproject.services;

import java.time.LocalDate;

public class ParserHTMLDateToJavaLocalDate {
    public static LocalDate parse(String HTMLDate){
        String[] array = HTMLDate.split("-");
        return LocalDate.of(Integer.parseInt(array[0]),Integer.parseInt(array[1]),Integer.parseInt(array[2]));
    }
}
