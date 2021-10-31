package com.example.utills;


import com.example.exception.BadRequestException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public class CommonUtils {
    public static void validatePageNumberAndSize(int page, int size) {
        if (page < 0) {
            throw new BadRequestException("The number of pages must not be less than zero.");
        }

        if (size > ApplicationConstance.MAX_PAGE_SIZE) {
            throw new BadRequestException("Number of pages " + ApplicationConstance.MAX_PAGE_SIZE + "  can not be more than.");
        }
    }


    public static Pageable getPageableByCreatedAtDesc(int page, int size) {
        validatePageNumberAndSize(page, size);
        return PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
    }

    public static Pageable getPageableByCreatedAtAsc(int page, int size) {
        validatePageNumberAndSize(page, size);
        return PageRequest.of(page, size, Sort.Direction.ASC, "createdAt");
    }

    public static Pageable simplePageable(int page, int size) {
        validatePageNumberAndSize(page, size);
        return PageRequest.of(page, size);
    }

    public static String thousandSeparator(Integer a) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

        symbols.setGroupingSeparator(' ');
        formatter.setDecimalFormatSymbols(symbols);
        return formatter.format(a.longValue());
    }

    public static int getRandomNumber() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return number;
    }
}
