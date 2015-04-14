package com.github.dmtk;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class ComPort {

    private static SerialPort serialPort;
    private static String data = " C";
    boolean started = false;

    public ComPort() {

    }

    public void start() {
        System.out.print("Method start");
        //Передаём в конструктор имя порта
        serialPort = new SerialPort("COM3");
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

        public void serialEvent(SerialPortEvent event) {

            if (event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                    //Получаем ответ от устройства, обрабатываем данные и т.д.
                    String portdata = serialPort.readString(event.getEventValue());
                    if(portdata!=null&&portdata.length()!=0){
                        data+=portdata;
                        if("\n".equals(portdata)){
                            System.out.print(data);
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
        System.out.print(this);
        System.out.print(data);
        return data;
    }
}
