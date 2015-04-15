package com.github.dmtk;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class ComPort {

    private static SerialPort serialPort;
    private static String data = "";
    boolean started = false;

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

            if (event.isRXCHAR() && event.getEventValue() > 0) {
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

    public String getData() {
        return data;
    }

    public void setData(String line) {
        data = line;

    }

}
