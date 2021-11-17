package com.zkxt.mqtt.config;

import com.zkxt.influxdb.controller.InsertController;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class PushCallback implements MqttCallback {
    Logger log= LoggerFactory.getLogger(getClass());




    private MqttPushClient client;
    private MqttConfiguration mqttConfiguration;


    public PushCallback(MqttPushClient client , MqttConfiguration mqttConfiguration) {
        this.client = client;
        this.mqttConfiguration = mqttConfiguration;
    }

    @Override
    public void connectionLost(Throwable cause) {
        /** 连接丢失后，一般在这里面进行重连 **/
        if(client != null) {
            while (true) {
                try {
                    log.info("==============》》》[MQTT] 连接断开，5S之后尝试重连...");
                    Thread.sleep(5000);
                    MqttPushClient mqttPushClient = new MqttPushClient();
                    mqttPushClient.connect(mqttConfiguration);
                    if(MqttPushClient.getClient().isConnected()){
                        log.info("=============>>重连成功");
                    }
                    break;
                } catch (Exception e) {
                    log.error("=============>>>[MQTT] 连接断开，重连失败！<<=============");
                    continue;
                }
            }
        }
        log.info(cause.getMessage());
    }
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        //publish后会执行到这里
        log.info("publish后会执行到这里");
        log.info("pushComplete==============>>>" + token.isComplete());
    }


    /**
     * 监听对应的主题消息
     * @param topic
     * @param message
     * @throws Exception
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        String Payload = new String(message.getPayload());

        log.info("============》》接收消息主题 : " + topic);
        log.info("============》》接收消息Qos : " + message.getQos());
        log.info("============》》接收消息内容 : " + Payload);
        log.info("============》》接收ID : " + message.getId());
        log.info("接收数据结束 下面可以执行数据处理操作");
//        Map<String, Object> fields = JSON.parseObject(Payload, Map.class);
        Map<String, Object> fields = new HashMap<String, Object>();
        fields.put("name","wm");
        fields.put("age",20);
        fields.put("address","雁塔区");
        InsertController insertController = SpringUtil.getBean(InsertController.class);
        insertController.insertDataController(Payload,fields);
    }
}

