package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        int[] salaries = new int[names.length];

        for (String record : data) {
            String[] parts = record.split(" ");

            LocalDate date = LocalDate.parse(parts[DATE_INDEX], DATE_TIME_FORMATTER);

            if (date.isAfter(to) || date.isBefore(from)) {
                continue;
            }

            String name = parts[NAME_INDEX];

            if (!Arrays.asList(names).contains(name)) {
                continue;
            }

            int hours = Integer.parseInt(parts[HOURS_INDEX]);
            int rate = Integer.parseInt(parts[RATE_INDEX]);
            int salary = hours * rate;

            int index = Arrays.asList(names).indexOf(name);
            salaries[index] += salary;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            int totalSalary = salaries[i];

            builder.append(names[i]).append(" - ")
                    .append(totalSalary)
                    .append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
