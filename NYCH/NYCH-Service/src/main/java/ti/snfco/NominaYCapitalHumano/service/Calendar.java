package ti.snfco.NominaYCapitalHumano.service;

import java.time.*;
import java.time.format.TextStyle;
import java.util.Locale;

class Calendar {
    public static void main(String ... args) {

        String [] monthNames = new String[12];
        String [] dayNames = new String[7];
        int dowi = 0;
        Locale locale = new Locale("es", "MX");
       
        // primero de enero
        LocalDate day = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        // matriz de 12 meses x 5 semanas cada mes x 7 dias a la semana
        int[][][] a = new int[12][6][7];
        // llenar la matriz
        for (int i = 0 ; i < a.length; i++ ) {
            Month current = day.getMonth();

            monthNames[i] = current.getDisplayName(TextStyle.FULL, locale);
             for (int j = 0 ; day.getMonth() == current ; j++ ) {
                for (int k = 0 ; k < 7 && day.getMonth() == current ; k++ ) {
                    DayOfWeek dayOfWeek = day.getDayOfWeek();
                    int dow = dayOfWeek.getValue();
                    if( dayNames[dow == 7?0:dow] == null ) {
                        dayNames[dow == 7?0:dow] =
                                dayOfWeek.getDisplayName(TextStyle.FULL, locale).substring(0,2);

                    }
                    if ( k == dow ||  (k == 0 && dow == 7) ) {
                        a[i][j][k] = day.getDayOfMonth();
                        day = day.plusDays(1);
                     } else {
                         continue;
                     }
                }
            }
        }

        // fill a matrix with the expected layout
        int [][] layout = new int[24][21];
        int li = 0;
        int lj = 0;

        for( int i = 0 ; i < a.length; i+=3 ) {
            for( int j = 0 ; j < a[i].length; j++ ) {
                for( int k = 0 ; k < a[i][j].length; k++ ) {
                    layout[li][lj]    = a[i][j][k];
                    layout[li][lj+7]  = a[i+1][j][k];
                    layout[li][lj+14] = a[i+2][j][k];
                    lj++;
                }
                lj = 0;
                li++;

            }
        }

        // days of week header
        StringBuilder dowb = new StringBuilder(" ");
        for ( String dowa : dayNames ) {
            dowb.append(dowa);
            dowb.append(" ");
        }
        String daysHeader = dowb.toString() +dowb.toString() + dowb.toString();

        // months header
        StringBuilder monthsHeader = new StringBuilder(" ");
        int k = 0;

        String[] mh = new String[4];
        for( int i = 0 ; i < monthNames.length ; i++){
            String current = monthNames[i];
            for ( int j = 0 ; j < 10 - current.length() / 2; j++ ) {
                monthsHeader.append(' ');
            }
            monthsHeader.append(current);
            for ( int j = 0 ; j < 9 - current.length() / 2; j++ ) {
                monthsHeader.append(' ');
            }
            monthsHeader.append("  ");
            if ( i > 0 && (i+1) % 3 == 0) {
                mh[k++] = monthsHeader.toString();
                monthsHeader = new StringBuilder(" ");
            }
        }

        k = 0;
        System.out.printf("                                "+(day.getYear()-1));
        for ( int i = 0 ; i < layout.length ; i++ ) {
            for ( int j = 0; j < layout[i].length ; j++ ) {
                if ( i % 6 == 0 && j == 0) {
                    System.out.printf("%n%s%n%s%n", mh[k++],daysHeader);
                }
                if ( j > 0 &&  j % 7  == 0 ){
                    System.out.print(" ");
                }
                int d = layout[i][j];

                System.out.printf("%3s", d == 0 ? " ": d );
            }
            System.out.println();
        }
    }
}
