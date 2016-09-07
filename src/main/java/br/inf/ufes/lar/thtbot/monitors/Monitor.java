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

import de.vivistra.telegrambot.model.message.Message;
import de.vivistra.telegrambot.model.message.TextMessage;
import de.vivistra.telegrambot.sender.Sender;
import de.vivistra.telegrambot.settings.BotSettings;
import java.util.Date;

/**
 * This class serves as a base class for new Monitors (implemented as
 * Observers). It sets the minimum and the maximum value limits for an instance
 * to help it to make alerts via Telegram.
 *
 * @author Leonardo Lemos
 * @version 1.0
 */
public abstract class Monitor implements Observer {

    /**
     * The maximum limit of an instance.
     */
    protected final float maxLimit;

    /**
     * The minimum limit of an instance.
     */
    protected final float minLimit;

    /**
     * The Date in the last alert sent by the subclass.
     *
     * @see Date
     */
    protected Date lastAlertDate;

    private final String botToken;
    private final String chatID;

    /**
     * Sets the main instance variables.
     *
     * @param minLimit The minimum instance limit.
     * @param maxLimit The maximum instance limit.
     * @param botToken The Telegram's bot token to send messages.
     * @param chatID The Telegram's chat ID to send messages.
     * @since 1.0
     */
    public Monitor(float minLimit, float maxLimit, String botToken,
            String chatID) {
        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
        this.botToken = botToken;
        this.chatID = chatID;
    }

    /**
     * Sends Telegram's messages to a chat through a Telegram's bot.
     *
     * @param text Telegram's message content.
     */
    protected void sendMessage(String text) {
        BotSettings.setApiToken(this.botToken);

        long recipient = Integer.parseInt(this.chatID);

        Message message = new TextMessage(recipient, text);

        Sender.send(message);
    }
}
