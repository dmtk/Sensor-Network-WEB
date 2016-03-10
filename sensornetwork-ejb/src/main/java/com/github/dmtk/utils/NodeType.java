package com.github.dmtk.utils;

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