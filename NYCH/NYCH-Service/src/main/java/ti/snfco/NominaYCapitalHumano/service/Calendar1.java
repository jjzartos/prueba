package ti.snfco.NominaYCapitalHumano.service;


import java.time.*;
import java.time.format.TextStyle;
import java.util.Locale;

class Calendar1 {

    private LocalDate day;
    private int[][][] a;
    private String [] monthNames = new String[12];
    private String [] dayNames = new String[7];
    private Locale locale = new Locale("es", "MX");
    private String[] mh;
    private String daysHeader;

    /**
     * lanza la app
     */
    public static void main(String ... args ) {
        Calendar c = new Calendar();
        c.main();
    }

    /**
     * main
     */
    void main() {

        // primero de enero
        day = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        a = new int[12][6][7];
        day = fillYear();
        daysHeader = getWeekDaysHeader(dayNames);
        mh = getMonthsHeader(monthNames);
        printCalendar(fillLayout());
    }

    /**
     *
     * Llena 12 meses con 6 semanas cada uno y 7 días por semana.
     * los días inexistentes en ese mes se llenan con 0
     */
    private LocalDate fillYear() {
        for (int i = 0 ; i < a.length; i++ ) {
            Month current = day.getMonth();

            monthNames[i] = current.getDisplayName(TextStyle.FULL, locale);
            for (int j = 0 ; day.getMonth() == current ; j++ ) {
                for (int k = 0 ; k < 7 && day.getMonth() == current ; k++ ) {
                    DayOfWeek dayOfWeek = day.getDayOfWeek();
                    int dow = dayOfWeek.getValue();
                    int zeroIfSunday = dow == 7 ? 0 : dow;
                    if( dayNames[zeroIfSunday] == null ) {
                        dayNames[zeroIfSunday] =
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
        return day;
    }

    /**
     * Fill a matrix with the expected layout
     * Iterates the original months matrix
     * and puts three days at a time
     * in a 24x21 matrix
     */
    public int[][] fillLayout() {
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
        return layout;
    }

    /**
     * Actually prints the calendar by iterating the 24x21 matrix adding headers when needed.
     */
    public void printCalendar(int[][] layout) {
        int l = 0;
        System.out.printf("                                "+(day.getYear()-1));
        for ( int i = 0 ; i < layout.length ; i++ ) {
            for ( int j = 0; j < layout[i].length ; j++ ) {
                if ( i % 6 == 0 && j == 0) {
                    System.out.printf("%n%s%n%s%n", mh[l++],daysHeader);
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

    /**
     * Get the months header for the calendar
     * each line contains 3 months.
     */
    private String[] getMonthsHeader(String[] monthNames) {// months header
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
        return mh;
    }

    /**
     * A single line containing the abbreviated days of week
     * for the calendar
     */
    private String getWeekDaysHeader(String[] dayNames) {
        StringBuilder dowb = new StringBuilder(" ");
        for ( String dowa : dayNames ) {
            dowb.append(dowa);
            dowb.append(" ");
        }
        return dowb.toString() +dowb.toString() + dowb.toString();
    }    
}
