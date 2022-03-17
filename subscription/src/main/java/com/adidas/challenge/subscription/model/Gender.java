package com.adidas.challenge.subscription.model;

public enum Gender {
  MALE("male"),
  FEMALE("female"),
  NON_BINARY("other");

  String key;

  Gender(String key) {
    this.key = key;
  }

  public String getKey() {
    return this.key;
  }
}
