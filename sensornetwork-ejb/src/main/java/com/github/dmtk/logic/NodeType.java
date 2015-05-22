package com.github.dmtk.logic;

public enum NodeType {
  SENSOR {
      @Override
      public String toString() {
          return "Sensor";
      }
  },

  CONTROLLER {
      @Override
      public String toString() {
          return "Controller";
      }
  }
}