package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    private static final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate to = LocalDate.parse(dateTo, dateTimeFormatter);

        int[] salaries = new int[names.length];

        for (String record : data) {
            String[] parts = record.split(" ");

            LocalDate date = LocalDate.parse(parts[0], dateTimeFormatter);

            if (date.isAfter(to) || date.isBefore(from)) {
                continue;
            }

            String name = parts[1];

            if (!Arrays.asList(names).contains(name)) {
                continue;
            }

            int hours = Integer.parseInt(parts[2]);
            int rate = Integer.parseInt(parts[3]);
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
