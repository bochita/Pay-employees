package model;

import java.time.LocalTime;


public class WorkDay {
    private final LocalTime MORNING_LIMIT = LocalTime.of(9, 0);
    private final LocalTime DAY_LIMIT = LocalTime.of(18, 0);
    private final LocalTime NIGHT_LIMIT = LocalTime.of(0, 0);
    private final Integer WEEKEND_BONUS = 5;
    private final Integer MORNING_TARIFF = 25;
    private final Integer DAY_TARIFF = 15;
    private final Integer NIGHT_TARIFF = 20;
    public WeekDay day;
    public LocalTime hourFrom;
    public LocalTime hourTo;
    private Integer weekendBonus;

    public void defineDay(WeekDay day) {
        this.day = day;
        this.weekendBonus = getWeekendBonus();
    }

    private Integer getWeekendBonus() {
        if (isWeekend())
            return WEEKEND_BONUS;
        return 0;
    }

    public Boolean isWeekend() {
        return (this.day == WeekDay.SA || this.day == WeekDay.SU);
    }

    public Boolean isValidHour() {
        return hourFrom.isBefore(hourTo) || LocalTime.MIDNIGHT.equals(hourTo);

    }

    public Boolean isMorning(LocalTime time) {
        return time.isAfter(LocalTime.of(0, 0)) && time.isBefore(LocalTime.of(9, 1));
    }

    public Boolean isDay(LocalTime time) {
        return time.isAfter(LocalTime.of(9, 0)) && time.isBefore(LocalTime.of(18, 1));
    }

    public Boolean isNight(LocalTime time) {
        return time.isAfter(LocalTime.of(18, 0)) || LocalTime.MIDNIGHT.equals(time);
    }

    public LocalTime earliestBetween(LocalTime hour1, LocalTime hour2) {

        if (hour1.isBefore(hour2) || LocalTime.MIDNIGHT.equals(hour2))
            return hour1;
        else
            return hour2;
    }

    public LocalTime latestBetween(LocalTime hour1, LocalTime hour2) {
        if (hour1.isAfter(hour2) || LocalTime.MIDNIGHT.equals(hour1))
            return hour1;
        else
            return hour2;
    }

    public Integer payAmount() {
        return morningPayAmount() + dayPayAmount() + nightPayAmount();
    }

    public Integer morningPayAmount() {
        if (!isMorning(hourFrom))
            return 0;
        return (earliestBetween(hourTo, MORNING_LIMIT).getHour() - hourFrom.getHour()) * (MORNING_TARIFF + weekendBonus);
    }

    public Integer dayPayAmount() {
        if (isMorning(hourTo) || isNight(hourFrom))
            return 0;
        return ((earliestBetween(hourTo, DAY_LIMIT).getHour() - latestBetween(hourFrom, MORNING_LIMIT).getHour())) * (DAY_TARIFF + weekendBonus);
    }

    public Integer nightPayAmount() {
        if (!isNight(hourTo))
            return 0;
        if (LocalTime.MIDNIGHT.equals(hourTo))
            return (24 - latestBetween(hourFrom, DAY_LIMIT).getHour()) * (NIGHT_TARIFF + weekendBonus);
        return (earliestBetween(hourTo, NIGHT_LIMIT).getHour() - latestBetween(hourFrom, DAY_LIMIT).getHour()) * (NIGHT_TARIFF + weekendBonus);
    }
}

