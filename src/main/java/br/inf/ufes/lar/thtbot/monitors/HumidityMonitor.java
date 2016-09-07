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
package br.inf.ufes.lar.thtbot.monitors;

import br.inf.ufes.lar.thtbot.utils.TimeUtil;
import java.util.Date;

/**
 * This class implements a Humidity Monitor by extending the Monitor class.
 *
 * @author Leonardo Lemos
 * @version 1.0
 */
public class HumidityMonitor extends Monitor {

    /**
     * Creates a new instance of a Humidity Monitor.
     *
     * @param minLimit The minimum humidity value to be used for alerts.
     * @param maxLimit The maximum humidity value to be used for alerts.
     * @param botToken The Telegram's bot token to send messages.
     * @param chatID The Telegram's chat ID to send messages.
     * @since 1.0
     */
    public HumidityMonitor(float minLimit, float maxLimit, String botToken,
            String chatID) {
        super(minLimit, maxLimit, botToken, chatID);
    }

    /**
     * Updates the Observer by receiving fresh stats and send alerts to the
     * Telegram's chat setted by the class instance based on the setted limits.
     *
     * <b>If one alert is sent, the method will wait 2 hours to send another
     * alert.</b>
     *
     * @param temp The received temperature by the method.
     * @param humi The received humidity by the method.
     * @param tens The received tension by the method.
     */
    @Override
    public void update(float temp, float humi, float tens) {
        Date updateDate = new Date();

        if (lastAlertDate == null || TimeUtil.getHoursElapsed(lastAlertDate,
                updateDate) >= 2) {
            lastAlertDate = new Date();

            if (humi < minLimit) {
                sendMessage("A humidade do datacenter do CT-6 está abaixo do limite"
                        + " (Umidade atual: " + humi + ").");
            } else if (humi > maxLimit) {
                sendMessage("A humidade do datacenter do CT-6 está acima do limite"
                        + " (Umidade atual: " + humi + ").");
            }
        }
    }
}
