/* 
 * Copyright (C) 2016 Laboratório de Administração de Redes
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.inf.ufes.lar.thtbot.utils;

import java.util.Date;
import org.joda.time.Interval;
import org.joda.time.Period;

/**
 * This class provides a set of static methods to get the time elapsed between
 * two Dates.
 *
 * @author Leonardo Lemos
 * @version 1.0
 */
public class TimeUtil {

    // Suppresses default constructor, ensuring non-instantiability.
    public TimeUtil() {

    }

    /**
     * Get the number of days elapsed between two Dates.
     *
     * @param startDate Start Date.
     * @param endDate End Date.
     * @return Number of days elapsed between two Dates.
     * @see Date
     * @since 1.0
     */
    public static int getDaysElapsed(Date startDate, Date endDate) {
        Interval interval = new Interval(startDate.getTime(),
                endDate.getTime());
        Period period = interval.toPeriod();

        return period.getDays();
    }

    /**
     * Get the number of hours elapsed between two Dates.
     *
     * @param startDate Start Date.
     * @param endDate End Date.
     * @return Number of hours elapsed between two Dates.
     * @see Date
     * @since 1.0
     */
    public static int getHoursElapsed(Date startDate, Date endDate) {
        Interval interval = new Interval(startDate.getTime(),
                endDate.getTime());
        Period period = interval.toPeriod();

        return period.getHours();
    }

    /**
     * Get the number of minutes elapsed between two Dates.
     *
     * @param startDate Start Date.
     * @param endDate End Date.
     * @return Number of minutes elapsed between two Dates.
     * @see Date
     * @since 1.0
     */
    public static int getMinutesElapsed(Date startDate, Date endDate) {
        Interval interval = new Interval(startDate.getTime(),
                endDate.getTime());
        Period period = interval.toPeriod();

        return period.getMinutes();
    }

    /**
     * Get the number of seconds elapsed between two Dates.
     *
     * @param startDate Start Date.
     * @param endDate End Date.
     * @return Number of seconds elapsed between two Dates.
     * @see Date
     * @since 1.0
     */
    public static int getSecondsElapsed(Date startDate, Date endDate) {
        Interval interval = new Interval(startDate.getTime(),
                endDate.getTime());
        Period period = interval.toPeriod();

        return period.getSeconds();
    }

}
