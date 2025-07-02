package com.estiam.monitorcontrol.service;

import com.estiam.monitorcontrol.model.Centrale;
import com.estiam.monitorcontrol.repository.CentraleRepository;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentraleService {

    @Autowired
    private CentraleRepository centraleRepository;

    @Autowired
    private MqttClient mqttClient;

    public List<Centrale> getAllCentrales() {
        return centraleRepository.findAll();
    }

    public void safeReloadAll() {
        try {
            reloadAllCentrales();
        } catch (Exception e) {
            System.err.println("Erreur lors du reload MQTT après affectation : " + e.getMessage());
        }
    }

    public void reloadCentraleById(Integer id) throws MqttException {
        Centrale centrale = centraleRepository.findById(id).orElse(null);
        if (centrale != null) {
            reloadCentrale(centrale);
        } else {
            System.err.println("Centrale avec ID " + id + " non trouvée");
        }
    }

    public void reloadAllCentrales() throws MqttException {
        List<Centrale> centrales = getAllCentrales();
        for (Centrale centrale : centrales) {
            reloadCentrale(centrale);
        }
    }

    private void reloadCentrale(Centrale centrale) throws MqttException {
        if (mqttClient != null && mqttClient.isConnected()) {
            String topicReload = centrale.getTopique() + "/reload";
            mqttClient.publish(topicReload, new MqttMessage(new byte[0]));
            System.out.println("Reload envoyé sur topic : " + topicReload);
        } else {
            System.err.println("MQTT client non connecté");
        }
    }
}
