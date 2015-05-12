package com.github.dmtk;

import java.util.logging.Level;
import java.util.logging.Logger;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class ComPort {

    private static SerialPort serialPort;
    private static String data = "";
    boolean started = false;
    
    public void emulate(){
        while(true){
            int value = (int) Math.round(Math.random()*100-20);
            int node = (int) Math.random()*8;
            data = "Sensor "+node + "Value " + value; 
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ComPort.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        
    public void start(String comPortName) {

        //Передаём в конструктор имя порта
        serialPort = new SerialPort(comPortName);
        try {
            //Открываем порт
            serialPort.openPort();
            //Выставляем параметры
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            //Включаем аппаратное управление потоком
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN
                    | SerialPort.FLOWCONTROL_RTSCTS_OUT);
            //Устанавливаем ивент лисенер и маску
            serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
            //Отправляем запрос устройству
            //serialPort.writeString("Get data");
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

    private class PortReader implements SerialPortEventListener {

        private String portdata = "";

        public void serialEvent(SerialPortEvent event) {
             /*if (event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                    //Получаем ответ от устройства, обрабатываем данные и т.д.
                    //data = serialPort.readString();
                    //И снова отправляем запрос
                    //serialPort.writeString("Get data");
                    
                    String receiveddata = bytesToHex(serialPort.readBytes(event.getEventValue()));
                    if("AA".equals(receiveddata)&&(!data.endsWith("AA"))){
                        //System.out.println(data);
                        data=data+"<br>"+receiveddata;
                    }else data=data+" "+receiveddata;
                } catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }*/
                        
            if(event.isRXCHAR() && event.getEventValue() > 0){
                try {
                    //Получаем ответ от устройства, обрабатываем данные и т.д.
                    String rxChar = serialPort.readString(event.getEventValue());
                    if (rxChar != null) {
                        portdata += rxChar;
                        if ("\n".equals(rxChar)) {
                            data = portdata;
                            portdata="";
                        }
                    }

                    //И снова отправляем запрос
                    //serialPort.writeString("Get data");
                } catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }

        }

    }
    
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public String getData() {
        return data;
    }

    public void setData(String line) {
        data = line;

    }

}
