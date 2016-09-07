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
package br.inf.ufes.lar.thtbot;

import br.inf.ufes.lar.thtbot.monitors.Observer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is responsible to fetch all the stats generate by the monitor
 * source.
 * <p>
 * The stats are available in a HTML5 webpage. Inittially, the stats data is
 * available in a <b>TEMPERATURE:HUMIDITY:TENSION</b> format. You can always
 * implement your own fetch method by overriding the setStats() method.
 *
 * This class extends Observable class to implement the Observer design pattern.
 *
 * @author Leonardo Lemos
 * @version 1.0
 * @see Observable
 */
public class THTMonitorBot extends Observable {

    private float temperature;
    private float humidity;
    private float tension;
    private URL url;
    private final ArrayList observers;

    /**
     * Creates a new THTMonitor by initializing all the stats to '0', creating a
     * list of observers and assigning a stats webpage URL to the class
     * instance.
     *
     * @param monitorUrl Stats page URL
     * @since 1.0
     */
    public THTMonitorBot(String monitorUrl) {
        this.temperature = 0;
        this.tension = 0;
        this.humidity = 0;

        try {
            this.url = new URL(monitorUrl);
        } catch (MalformedURLException ex) {
            Logger.getLogger(THTMonitorBot.class.getName()).log(Level.SEVERE, 
                    null, ex);
        }

        this.observers = new ArrayList();
    }

    /**
     * Reads the stats from the stats page, updates the <i>temperature</i>,
     * <i>humidity</i> and <i>tension</i> instances and notify all the observers
     * about the changes made to it.
     *
     * @throws IOException
     * @since 1.0
     */
    public void setStats() throws IOException {
        try {
            /* Inicializa os leitores da URL */
            InputStreamReader input = new InputStreamReader(this.url.openStream(),
                    "UTF-8");
            BufferedReader reader = new BufferedReader(input);

            /* Despreza a primeira linha do código HTML da página de leitura */
            reader.readLine();

            /**
             * Pega a segunda linha da página HTML e armazena em uma variável
             * para extrairmos seu conteúdo em medidas
             */
            String line = reader.readLine();

            /* Inicia a busca pelas informações de medição */
            final Pattern p = Pattern.compile("<html>(\\S+)<br />");
            final Matcher m = p.matcher(line);

            if (m.find()) {
                String codeGroup = m.group(1);
                String[] stats = codeGroup.split(":");

                this.temperature = Float.parseFloat(stats[0]);
                this.humidity = Float.parseFloat(stats[1]);
                this.tension = Float.parseFloat(stats[2]);

                setChanged();
                notifyObservers();
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(THTMonitorBot.class.getName()).log(Level.SEVERE, 
                    null, ex);
        }
    }

    /**
     * Adds a new Observer to the Observers List.
     *
     * @param o The Observer to be included in the Observers List.
     * @see Observer
     * @since 1.0
     */
    public void addObserver(Observer o) {
        observers.add(o);
    }

    /**
     * Removes an Observer from the Observers List.
     *
     * @param o The Observer to be removed from the Observers List.
     * @see Observer
     * @since 1.0
     */
    public void deleteObserver(Observer o) {
        observers.remove(o);
    }

    /**
     * Notify all the Observers in the Observers List for <i>temperature</i>,
     * <i>humidity</i> and <i>tension</i>.
     *
     * @see Observer
     * @see Observable
     * @since 1.0
     */
    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer ob = (Observer) observers.get(i);
            ob.update(this.temperature, this.humidity, this.tension);
        }
    }
}
